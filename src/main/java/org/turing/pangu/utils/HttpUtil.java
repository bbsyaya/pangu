package org.turing.pangu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

@SuppressWarnings("deprecation")
public class HttpUtil {

	private static final Logger log = Logger.getLogger(HttpUtil.class);
	
	/**
	 * post方式提交表单（模拟用户登录请求）
	 */
	public static void postForm(String url, Map<String, Object> params,String urlEnCode) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列
		List<NameValuePair> formparams = getHttpParams(params);
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, urlEnCode);
			httppost.setEntity(uefEntity);
			log.info("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					log.info("--------------------------------------");
					log.info("Response content: " + EntityUtils.toString(entity, "UTF-8"));
					log.info("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			log.error(e.getMessage(),e);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
		}
	}

	/**
	 * 把map参数转成http参数
	 * @author turing
	 * @param map
	 * @return
	 */
	private static List<NameValuePair> getHttpParams(Map<String, Object> map) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (String key : map.keySet()) {
			formparams.add(new BasicNameValuePair(key, map.get(key).toString()));
		}
		return formparams;
	}

	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
	 */
	public static Boolean post(String url, Map<String, Object> params,String urlEnCode) {
		Boolean result=false;
		
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列
		List<NameValuePair> formparams = getHttpParams(params);
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, urlEnCode);
			httppost.setEntity(uefEntity);
			log.info("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK)
				{
					result=true;
				}
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					log.info("--------------------------------------");
					log.info("Response content: " + EntityUtils.toString(entity,urlEnCode));
					log.info("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			log.error(e.getMessage(),e);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
		}
		return result;
	}

	/**
	 * 发送 get请求
	 */
	public static void get(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			log.info("executing request " + httpget.getURI());
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				log.info("--------------------------------------");
				// 打印响应状态
				log.info(response.getStatusLine());
				if (entity != null) {
					// 打印响应内容长度
					log.info("Response content length: " + entity.getContentLength());
					// 打印响应内容
					log.info("Response content: " + EntityUtils.toString(entity));
				}
				log.info("------------------------------------");
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			log.error(e.getMessage(),e);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
		}
	}

	/**
	 * 上传文件
	 */
	public void upload(String url, String filePath) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(url);

			FileBody bin = new FileBody(new File(filePath));
			StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);

			HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();

			httppost.setEntity(reqEntity);

			log.info("executing request " + httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				log.info("----------------------------------------");
				log.info(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					log.info("Response content length: " + resEntity.getContentLength());
				}
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			log.error(e.getMessage(),e);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
		}
	}

	/**
	 * HttpClient连接SSL
	 */
	public static void ssl(String url, String keypath) {
		CloseableHttpClient httpclient = null;
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			FileInputStream instream = new FileInputStream(new File(keypath));
			try {
				// 加载keyStore d:\\tomcat.keystore
				trustStore.load(instream, "123456".toCharArray());
			} catch (CertificateException e) {
				log.error(e.getMessage(),e);
			} finally {
				try {
					instream.close();
				} catch (Exception ignore) {
				}
			}
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			// 创建http请求(get方式)
			HttpGet httpget = new HttpGet(url);
			log.info("executing request" + httpget.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				log.info("----------------------------------------");
				log.info(response.getStatusLine());
				if (entity != null) {
					log.info("Response content length: " + entity.getContentLength());
					log.info(EntityUtils.toString(entity));
					EntityUtils.consume(entity);
				}
			} finally {
				response.close();
			}
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		} catch (KeyManagementException e) {
			log.error(e.getMessage(),e);
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(),e);
		} catch (KeyStoreException e) {
			log.error(e.getMessage(),e);
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					log.error(e.getMessage(),e);
				}
			}
		}
	}
}