package bwei.com.zhou3_demo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import bwei.com.zhou3_demo.MainActivity;

public abstract class BaseActivity<P extends BasePresenter> extends Activity{
   protected P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutId());
        initView();
        initListener();
        presenter=providePresenter();
        initData();
    }

    protected abstract void initData();

    protected abstract P providePresenter();

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract int LayoutId();

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
