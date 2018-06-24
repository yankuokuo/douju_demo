package bwei.com.zhou3_demo;

import android.icu.util.TimeUnit;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils {
    private static HttpUtils httpUtils=new HttpUtils();
    private final OkHttpClient okHttpClient;
    private final Handler handler;

    private HttpUtils(){
        handler = new Handler(Looper.getMainLooper());
        okHttpClient = new OkHttpClient.Builder()
               .connectTimeout(5000, java.util.concurrent.TimeUnit.MILLISECONDS)
               .readTimeout(5000, java.util.concurrent.TimeUnit.MILLISECONDS)
               .writeTimeout(5000, java.util.concurrent.TimeUnit.MILLISECONDS)
               .build();
    }
    public static HttpUtils getIntence(){
        if (httpUtils!=null){
            synchronized (HttpUtils.class){
                if(httpUtils!=null){
                    return httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }
    public void doGet(String url,final OkHttpIntence okHttpIntence){
         Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
        @Override
        public void onFailure(Call call, final IOException e) {
            if (okHttpIntence!=null){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okHttpIntence.geteeror(e);
                    }
                });
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response!=null&&response.isSuccessful()){
                final String json = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (okHttpIntence!=null){
                            okHttpIntence.getsuccceed(json);
                        }
                    }
                });
            }
        }
    });
    }
    public interface OkHttpIntence{
        void getsuccceed(String json);
        void geteeror(Exception error);
    }
}
