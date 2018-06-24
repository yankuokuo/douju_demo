package bwei.com.zhou3_demo;

import okhttp3.OkHttpClient;

public class Model {

    private HttpUtils httputils;
    private String myurl="http://365jia.cn/news/api3/365jia/news/headline?page=1";
    public void login(final getModelHttpIntence getModelHttpIntence){

        httputils = HttpUtils.getIntence();
        httputils.doGet(myurl, new HttpUtils.OkHttpIntence() {
            @Override
            public void getsuccceed(String json) {

                getModelHttpIntence.getsuccceed(json);
            }

            @Override
            public void geteeror(Exception error) {

            }
        });
    }
    public interface getModelHttpIntence{
        void getsuccceed(String json);
        void geteeror(Exception error);
    }
}
