package tech.icoding.commons.platform.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 类描述：调用华为接口工具类
 */
public class HttpUtils {

    /**
     * 日志
     */
    private final static Logger logger = (Logger) LoggerFactory.getLogger(HttpUtils.class);

    // 连接初始化需要类
    private static CloseableHttpClient httpClient;

    /**
     * 方法描述：创建新的连接
     *
     * @return CloseableHttpClient 连接对象
     * @throws Exception
     */
    public static CloseableHttpClient getHttpClient() throws Exception {
        if (httpClient == null) {
            httpClient = createSSLClient();
        }
        return httpClient;
    }

    /**
     * 方法描述：返回值之后，关闭http连接
     *
     * @throws Exception
     * @Date:2021年3月3日下午3:48:15
     * @author jiangdong
     */
    public static void closeHttpClient() throws Exception {
        if (httpClient != null) {
            httpClient.close();
        }
    }

    /**
     * 方法描述：创建连接，设置客户端信任所有证书
     *
     * @return CloseableHttpClient client对象
     * @throws KeyStoreException        e
     * @throws NoSuchAlgorithmException e
     * @throws KeyManagementException   e
     */
    private static CloseableHttpClient createSSLClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1.2"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    /**
     * 方法描述：设置请求时的header
     *
     * @param httpUriRequest 请求对象
     * @param appId          应用ID
     * @param appKey         应用密钥
     * @param headParam      head参数
     */
    private static void setSSLHeader(HttpUriRequest httpUriRequest, String appId, String appKey, Map<String, Object> headParam) {
        long time = System.currentTimeMillis();
        String authorization = DigestUtils.sha256Hex(appId + appKey + time);
        if (headParam != null && !headParam.isEmpty()) {
            for (String key : headParam.keySet()) {
                Object value = headParam.get(key);
                httpUriRequest.addHeader(key, value.toString());
            }
        }
        httpUriRequest.addHeader("Content-Type", "application/json");
        httpUriRequest.addHeader("Authorization", authorization);
        httpUriRequest.addHeader("timestamp", String.valueOf(time));
    }

    /**
     * 方法描述：华为保存类接口访问,post请求需要自己填充：appId（实际情况），appKey（实际情况），uri（华为文档），param（map）
     *
     * @param url       请求地址
     * @param appId     应用ID
     * @param appKey    应用密钥
     * @param headParam 头部
     * @param param     主体内容
     * @return
     * @throws Exception
     */
    public static Map<String, Object> hwApiPost(String url, String appId, String appKey, Map<String, Object> headParam, Map<String, Object> param) throws Exception {
        // post请求
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(com.alibaba.fastjson.JSONObject.toJSON(param).toString(), "utf-8");
        httpPost.setEntity(stringEntity);
        //设置请求的header
        setSSLHeader(httpPost, appId, appKey, headParam);
        Map<String, Object> postResponseMap = getSSLResponse(httpPost);
        return postResponseMap;
    }


    /**
     * 方法描述：解析响应结果
     *
     * @param httpUriRequest Request对象
     * @return Map<String, Object> 返回值
     * @throws Exception e
     */
    private static Map<String, Object> getSSLResponse(HttpUriRequest httpUriRequest) throws Exception {
        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse response = httpClient.execute(httpUriRequest);
        HttpEntity responseEntity = response.getEntity();
        Map<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("code", response.getStatusLine().getStatusCode());
        if (responseEntity != null) {
            String result = EntityUtils.toString(responseEntity);
            //logger.info("响应结果：{}", result);
            try {
                if (result.startsWith("[")) {
                    responseMap.put("data", com.alibaba.fastjson.JSONObject.parseArray(result));
                    logger.info("================使用了parseArray============");
                } else {
                    responseMap.put("data", com.alibaba.fastjson.JSONObject.parseObject(result));
                }
            } catch (Exception e) {
                logger.error("=====结果转换异常====", e.getMessage());
                responseMap.put("msg", "结果转换异常");
            }
        }
        return responseMap;
    }
}
