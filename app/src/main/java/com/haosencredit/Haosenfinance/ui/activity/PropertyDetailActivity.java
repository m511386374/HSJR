package com.haosencredit.Haosenfinance.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.base.BaseActivity;
import com.haosencredit.Haosenfinance.ui.fragment.TotalAssetFragment;
import com.haosencredit.Haosenfinance.ui.fragment.TotalIncomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;

/**
 * Created by Nick.Ming on 2017/6/14.
 */
public class PropertyDetailActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;
    private XFragmentAdapter pagerAdapter;
    List<Fragment> fragmentList = new ArrayList<>();
    private String tabTitles[] = new String[]{"总资产","总收益"};
    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        fragmentList.clear();
        fragmentList.add(TotalAssetFragment.newInstance());
        fragmentList.add(TotalIncomeFragment.newInstance());
        pagerAdapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList,tabTitles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_property_detail;
    }

    @Override
    public Object newP() {
        return null;
    }

    @OnClick({R.id.back_image})
    public void back(View view) {
        finish();
    }
}
