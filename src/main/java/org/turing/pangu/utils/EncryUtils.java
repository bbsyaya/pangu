package org.turing.pangu.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

public class EncryUtils {

	/**
	 * 生成RSAUtil签名
	 */
	public static String handleRSA(TreeMap<String, Object> map,
			String privateKey) {
		StringBuffer sbuffer = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sbuffer.append(entry.getValue());
		}
		String signTemp = sbuffer.toString();

		String sign = "";
		if (StringUtils.isNotEmpty(privateKey)) {
			sign = RSAUtils.sign(signTemp, privateKey);
		}
		return sign;
	}

	/**
	 * 对易宝支付返回的结果进行验签
	 * 
	 * @param data
	 *            易宝支付返回的业务数据密文
	 * @param encrypt_key
	 *            易宝支付返回的对ybAesKey加密后的密文
	 * @param yibaoPublickKey
	 *            易宝支付提供的公钥
	 * @param merchantPrivateKey
	 *            商户自己的私钥
	 * @return 验签是否通过
	 * @throws Exception
	 */
	public static boolean checkDecryptAndSign(String data, String encrypt_key,
			String yibaoPublickKey, String merchantPrivateKey) throws Exception {

		/** 1.使用YBprivatekey解开aesEncrypt。 */
		String AESUtilsKey = "";
		try {
			AESUtilsKey = RSAUtils.decrypt(encrypt_key, merchantPrivateKey);
		} catch (Exception e) {
			/** AESUtils密钥解密失败 */
			e.printStackTrace();
			return false;
		}

		/** 2.用aeskey解开data。取得data明文 */
		String realData = AESUtils.decryptFromBase64(data, AESUtilsKey);
		
		TreeMap<String, String> map = JsonUtils.toObject(realData,new TreeMap<String, String>().getClass());

		/** 3.取得data明文sign。 */
		String sign = StringUtils.trimToEmpty(map.get("sign"));

		/** 4.对map中的值进行验证 */
		StringBuffer signData = new StringBuffer();
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();

			/** 把sign参数隔过去 */
			if (StringUtils.equals((String) entry.getKey(), "sign")) {
				continue;
			}
			signData.append(entry.getValue() == null ? "" : entry.getValue());
		}
		
		/** 5. result为true时表明验签通过 */
		//String str = '"'+"CUSTOMER"+'"'+"1004002057817701200070NEWTZTBINDPAY160422_141536221204573TO_VALIDATE";
		System.out.println("signData.toString()==="+signData.toString());
		System.out.println("sign==="+sign);
		System.out.println("yibaoPublickKey==="+yibaoPublickKey);
		boolean result = RSAUtils.checkSign(signData.toString(), sign,
				yibaoPublickKey);
		
		return result;
	}

	/**
	 * 生成hmac
	 */
	public static String handleHmac(TreeMap<String, String> map, String hmacKey) {
		StringBuffer sbuffer = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sbuffer.append(entry.getValue());
		}
		String hmacTemp = sbuffer.toString();

		String hmac = "";
		if (StringUtils.isNotEmpty(hmacKey)) {
			hmac = DigestUtils.hmacSHASign(hmacTemp, hmacKey, DigestUtils.ENCODE);
		}
		return hmac;
	}

}
