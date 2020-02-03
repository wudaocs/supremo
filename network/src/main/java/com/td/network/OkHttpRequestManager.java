package com.td.network;


import androidx.annotation.NonNull;

import com.td.network.entity.GetEntity;
import com.td.network.entity.PostEntity;
import com.td.network.entity.RequestEntity;
import com.td.network.util.SRequestGsonUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Description :
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
public class OkHttpRequestManager implements ISRequestManager {

    private OkHttpClient.Builder mOkHttpClient;

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    OkHttpRequestManager() {
    }

    private OkHttpClient getHttpClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder();
        }
        return mOkHttpClient.connectTimeout(SRequestConfigs.CONNECT_TIME_OUT, TimeUnit.SECONDS).readTimeout(SRequestConfigs.READ_TIME_OUT, TimeUnit.SECONDS).writeTimeout(SRequestConfigs.WRITE_TIME_OUT, TimeUnit.SECONDS).build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void post(PostEntity postEntity) {
        request(getBuilder(postEntity.getHeads()).url(postEntity.getUrl()).post(RequestBody.create(JSON, postEntity.getContent())).build(), postEntity.getRequestCallback());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void get(GetEntity getEntity) {
        request(getBuilder(getEntity.getHeads()).url(appendParams(getEntity.getUrl(), getEntity.getParams())).build(), getEntity.getRequestCallback());
    }


    @SuppressWarnings("unchecked")
    private void request(final Request request, final IRequestCallback requestCallback) {
        getHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                String data = "server not return message !";
                call.request();
                if (call.request().body() != null) {
                    data = e.getMessage();
                }
                if (requestCallback != null) {
                    RequestEntity requestEntity = new RequestEntity();
                    requestEntity.setCode(SRequestConfigs.ERROR_CODE);
                    requestEntity.setMessage(data);
                    requestCallback.onFail(requestEntity);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.code() == 200) {
                    String data = "";
                    ResponseBody body = response.body();
                    if (body != null) {
                        data = body.string();
                    }
                    if (requestCallback != null) {
                        Type[] genericType = ((ParameterizedType) requestCallback).getActualTypeArguments();
                        Type type = genericType[0];
                        requestCallback.onSuccess(SRequestGsonUtil.fromJson(data, type));
                    }
                } else {
                    if (requestCallback != null) {
                        RequestEntity requestEntity = new RequestEntity();
                        requestEntity.setCode(response.code());
                        requestEntity.setMessage("error");
                        requestCallback.onFail(requestEntity);
                    }
                }
            }
        });
    }

    private Request.Builder getBuilder(Map<String, String> params) {
        Request.Builder builder = new Request.Builder();
        Headers headers = null;
        if (params != null && !params.isEmpty()) {
            headers = Headers.of(params);
        }
        if (headers != null) {
            builder.headers(headers);
        }
        return builder;
    }

    private String appendParams(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(url).append("?");
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=");
                try {
                    sb.append(URLEncoder.encode(params.get(key), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                sb.append("&");
            }
        }
        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
