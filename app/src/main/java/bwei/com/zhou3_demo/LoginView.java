package bwei.com.zhou3_demo;

import bwei.com.zhou3_demo.base.IView;

public interface LoginView extends IView {
    void getsuccceed(String json);
    void geteeror(Exception error);
}
