package org.turing.pangu.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.turing.pangu.controller.phone.MobileResponse;
import org.turing.pangu.controller.phone.Token;
import org.turing.pangu.utils.CipherUtil;
import org.turing.pangu.utils.Const;
import org.turing.pangu.utils.ConstantUtil;

@Service("tokenHandleImpl")
public class TokenHandleImpl implements TokenService {

	@Autowired
	private CacheHandle cacheHandle;

	private String OauthToken;

	@Override
	public String getOauthToken() {
		return OauthToken;
	}

	public void setOauthToken(String oauthToken) {
		OauthToken = oauthToken;
	}

	@Override
	public MobileResponse<String> ValidateSignature(Token token, ArrayList<String> paramList,int type) {
		MobileResponse<String> respone = new MobileResponse<String>();
		if(token==null)
		{
			/** 请求参数错误 */
			respone.setAllData(Const.common_param_error, "common_param_error");
			return respone;
		}
		String timestamp = token.getTimestamp();// 时间戳，1天失效
		String random = token.getRandom();// 随机值
		String cipherToken = ConstantUtil.CipherToken;// cipherToken，加密密钥，约定不再发生更改
		String oauthToken=OauthToken;// OauthToken，服务端授权密钥，不定期更换
		String signature = token.getSignature();// 签名，cipherToken+oauthToken+timestamp+nonce 字符排倒顺序后拼接md5加密
		if (timestamp == null || timestamp.isEmpty() || signature == null || signature.isEmpty() || random == null || random.isEmpty()) {
			/** 请求参数错误 */
			respone.setAllData(Const.common_param_error, "common_param_error");
			return respone;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);

		if (calendar.getTimeInMillis() > Long.parseLong(timestamp)) {
			/** 请求过期 */
			respone.setAllData(Const.oauth_request_overdue, "oauth_request_overdue");
			return respone;
		}

		/** 判断请求是否有效 */
		ArrayList<String> signatureArrayList = new ArrayList<String>();
		signatureArrayList.add(timestamp);
		signatureArrayList.add(random);
		signatureArrayList.add(cipherToken);
		if(type==0)
		{
			signatureArrayList.add(oauthToken);
		}	
		if (paramList != null && paramList.size() > 0) {
			signatureArrayList.addAll(paramList);
		}
		Collections.sort(signatureArrayList);
		StringBuffer strBuffer = new StringBuffer();
		for (int i = signatureArrayList.size() - 1; i >= 0; i--) {
			strBuffer.append(signatureArrayList.get(i));
		}

		String validSignature = CipherUtil.generatePassword(strBuffer.toString());
		if (!validSignature.equals(signature)) {
			/** 请求非法 */
			respone.setAllData(Const.oauth_request_illegal, "oauth_request_illegal");
			return respone;
		}

		/**
		 * 判断签名是否有效
		 */
		if (!validateSignatureRecord(signature)) {
			/** 签名无效 */
			respone.setAllData(Const.oauth_request_invalidSign, "oauth_request_invalidSign");
			return respone;
		}

		respone.setAllData(Const.oauth_request_success, "oauth_request_success");
		return respone;
	}

	/**
	 * 验证签名记录
	 * 
	 * @author turing
	 * @param signature
	 * @return
	 */
	public Boolean validateSignatureRecord(String signature) {
		boolean result = false;
		if (signature == null || signature.isEmpty()) {
			return result;
		}
		StringBuffer keyBuffer = new StringBuffer();
		keyBuffer.append("request.");
		keyBuffer.append(signature);
		String key=keyBuffer.toString();
		if (cacheHandle.hasKey(key)) {
			return result;
		} else {
			Long seconds = (long) (1 * 24 * 3600);
			cacheHandle.setCache(key, System.currentTimeMillis(), seconds);
		}
		result = true;
		return result;
	}

}
