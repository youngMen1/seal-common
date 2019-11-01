package com.seal.util.httputil;

import okhttp3.*;
import okhttp3.Request.Builder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/1 20:53
 * @description
 **/
public class OkHttpUtil {
    private static final Logger log = LoggerFactory.getLogger(OkHttpUtil.class);

    public OkHttpUtil() {
    }

    public static String get(String url, Map<String, String> queries) {
        String responseBody = "";
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }

        Request request = (new Builder()).url(sb.toString()).build();
        Response response = null;

        String var8;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (!response.isSuccessful()) {
                return responseBody;
            }

            var8 = response.body().string();
        } catch (Exception var12) {
            log.error("okhttp3 put error >> ex = {}", ExceptionUtils.getStackTrace(var12));
            return responseBody;
        } finally {
            if (response != null) {
                response.close();
            }

        }

        return var8;
    }

    public static String get(String url, Map<String, String> queries, String header) {
        String responseBody = "";
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }

        Request request = (new Builder()).url(sb.toString()).addHeader("token", header).build();
        Response response = null;

        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                String var9 = response.body().string();
                return var9;
            }
        } catch (Exception var13) {
            log.error("okhttp3 put error >> ex = {}", ExceptionUtils.getStackTrace(var13));
        } finally {
            if (response != null) {
                response.close();
            }

        }

        return responseBody;
    }

    public static String post(String url, Map<String, String> params) {
        String responseBody = "";
        okhttp3.FormBody.Builder builder = new okhttp3.FormBody.Builder();
        if (params != null && params.keySet().size() > 0) {
            Iterator var4 = params.keySet().iterator();

            while (var4.hasNext()) {
                String key = (String) var4.next();
                builder.add(key, (String) params.get(key));
            }
        }

        Request request = (new Builder()).url(url).post(builder.build()).build();
        Response response = null;

        String var8;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (!response.isSuccessful()) {
                return responseBody;
            }

            var8 = response.body().string();
        } catch (Exception var12) {
            log.error("okhttp3 post error >> ex = {}", ExceptionUtils.getStackTrace(var12));
            return responseBody;
        } finally {
            if (response != null) {
                response.close();
            }

        }

        return var8;
    }

    public static String postJsonParams(String url, String jsonParams) {
        String responseBody = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = (new Builder()).url(url).post(requestBody).build();
        Response response = null;

        String var8;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (!response.isSuccessful()) {
                return responseBody;
            }

            var8 = response.body().string();
        } catch (Exception var12) {
            log.error("okhttp3 post error >> ex = {}", ExceptionUtils.getStackTrace(var12));
            return responseBody;
        } finally {
            if (response != null) {
                response.close();
            }

        }

        return var8;
    }

    public static String postJsonParamsAndHeader(String url, String jsonParams, String header) {
        String responseBody = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = (new Builder()).url(url).addHeader("token", header).post(requestBody).build();
        Response response = null;

        String var9;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (!response.isSuccessful()) {
                return responseBody;
            }

            var9 = response.body().string();
        } catch (Exception var13) {
            log.error("okhttp3 post error >> ex = {}", ExceptionUtils.getStackTrace(var13));
            return responseBody;
        } finally {
            if (response != null) {
                response.close();
            }

        }

        return var9;
    }
}
