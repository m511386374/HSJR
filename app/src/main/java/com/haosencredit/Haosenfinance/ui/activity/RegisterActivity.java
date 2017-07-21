package com.haosencredit.Haosenfinance.ui.activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.base.BaseActivity;
import com.haosencredit.Haosenfinance.ui.fragment.CompanyRegisterFragment;
import com.haosencredit.Haosenfinance.ui.fragment.PersonRegisterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;

@Route(path = "/Hsjr/RegisterActivity")
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.back_image)
    ImageView back_image;
    @BindView(R.id.viewpager_rg)
    ViewPager viewpager_rg;
    List<Fragment> fragmentList = new ArrayList<>();
    XFragmentAdapter adapter;
    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
//        RxView.clicks(back_image)
//                .throttleFirst(1,TimeUnit.SECONDS)
//                .subscribe(new Action1<Void>() {
//                    @Override
//                    public void call(Void aVoid) {
//                       finish();
//                    }
//                });

        fragmentList.add(PersonRegisterFragment.newInstance());
        fragmentList.add(CompanyRegisterFragment.newInstance());
        if (adapter == null) {
            adapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList);
        }
        viewpager_rg.setAdapter(adapter);
        viewpager_rg.setOffscreenPageLimit(1);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public Object newP() {
        return null;
    }

    public void personregister(View view){
        viewpager_rg.setCurrentItem(0);
    }
    public void companyregister(View view){
        viewpager_rg.setCurrentItem(1);
    }
    @OnClick({R.id.back_image})
    public void back(View view) {
        finish();
    }
}