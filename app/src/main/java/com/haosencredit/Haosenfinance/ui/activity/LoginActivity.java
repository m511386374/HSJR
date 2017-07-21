package com.haosencredit.Haosenfinance.ui.activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.base.BaseActivity;
import com.haosencredit.Haosenfinance.ui.fragment.CompanyLoginFragment;
import com.haosencredit.Haosenfinance.ui.fragment.PersonLoginFragment;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import rx.functions.Action1;

@Route(path = "/Hsjr/LoginActivity")
public class LoginActivity extends BaseActivity {
    @BindView(R.id.back_image)
    ImageView back_image;
    @BindView(R.id.viewpager_lg)
    ViewPager viewpager_lg;
    List<Fragment> fragmentList = new ArrayList<>();
    XFragmentAdapter adapter;
    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        RxView.clicks(back_image)
                .throttleFirst(1,TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                       finish();
                    }
                });
        fragmentList.add(PersonLoginFragment.newInstance());
        fragmentList.add(CompanyLoginFragment.newInstance());
        if (adapter == null) {
            adapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList);
        }
        viewpager_lg.setAdapter(adapter);
        viewpager_lg.setOffscreenPageLimit(1);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public Object newP() {
        return null;
    }

    public void personregister(View view){
        viewpager_lg.setCurrentItem(0);
    }
    public void companyregister(View view){
        viewpager_lg.setCurrentItem(1);
    }

}