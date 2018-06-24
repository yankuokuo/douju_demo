package bwei.com.zhou3_demo;

import bwei.com.zhou3_demo.base.BasePresenter;

public class presenter extends BasePresenter<LoginView> {

    private Model model;

    public presenter(LoginView loginView) {
        super(loginView);

    }
    @Override
    protected void initModel() {
        model = new Model();
    }
    public void login1(){
        model.login(new Model.getModelHttpIntence() {
            @Override
            public void getsuccceed(String json) {
                view.getsuccceed(json);
            }

            @Override
            public void geteeror(Exception error) {

            }
        });
    }


}
