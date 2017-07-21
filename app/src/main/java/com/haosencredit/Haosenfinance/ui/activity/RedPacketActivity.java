package com.haosencredit.Haosenfinance.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.base.BaseActivity;
import com.haosencredit.Haosenfinance.ui.fragment.ExperienceGoldFragment;
import com.haosencredit.Haosenfinance.ui.fragment.MyRedPacketFregment;
import com.haosencredit.Haosenfinance.utils.DpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;

/**
 * Created by Nick.Ming on 2017/6/14.
 */
public class RedPacketActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;
    private XFragmentAdapter pagerAdapter;
    List<Fragment> fragmentList = new ArrayList<>();
    private String tabTitles[] = new String[]{"我的红包","我的体验金"};
    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        fragmentList.clear();
        fragmentList.add(MyRedPacketFregment.newInstance());
        fragmentList.add(ExperienceGoldFragment.newInstance());
        pagerAdapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList,tabTitles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.table_v_divider)); //设置分割线的样式
        linearLayout.setDividerPadding(DpUtil.dip2px(10,getResources())); //设置分割线间隔
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_redpacket;
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
