package com.haosencredit.Haosenfinance.ui.activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.dou361.update.UpdateHelper;
import com.dou361.update.listener.UpdateListener;
import com.haosencredit.Haosenfinance.MyApplication;
import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.base.BaseActivity;
import com.haosencredit.Haosenfinance.hepler.BottomNavigationViewHelper;
import com.haosencredit.Haosenfinance.present.PMainActivityData;
import com.haosencredit.Haosenfinance.ui.fragment.FinanceFragment;
import com.haosencredit.Haosenfinance.ui.fragment.HomeFragment;
import com.haosencredit.Haosenfinance.ui.fragment.MyFragment;
import com.haosencredit.Haosenfinance.ui.fragment.PropertyFragment;
import com.haosencredit.Haosenfinance.view.MyViewPager;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import rx.functions.Action1;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

@Route(path = "/Hsjr/MainActivity")
public  class MainActivity extends BaseActivity<PMainActivityData> {
    private MenuItem menuItem;
    @BindView(R.id.viewpager)
    MyViewPager viewPager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    XFragmentAdapter adapter;
    private boolean isAutoUpdate;//更新标识
    List<Fragment> fragmentList = new ArrayList<>();
    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);




        getPersimmions();
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_news:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.item_lib:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.item_find:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.item_more:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        setupViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        fragmentList.clear();
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(FinanceFragment.newInstance());
        fragmentList.add(PropertyFragment.newInstance());
        fragmentList.add(MyFragment.newInstance());
        if (adapter == null) {
            adapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList);
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public PMainActivityData newP() {
        return new PMainActivityData();
    }


    private void getPersimmions() {

        getRxPermissions().request(
                WRITE_EXTERNAL_STORAGE,
                READ_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) { // 在android 6.0之前会默认返回true
                    // 已经获取权限
                    checkUpdate();
                } else {
                    // 未获取权限
                    Toast.makeText(getApplicationContext(),"获取权限失败！",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void checkUpdate() {

        getP().update(new PMainActivityData.UpDateCallback() {
            @Override
            public void onUpDateReady(Response<String> listLzyResponse) {

                isAutoUpdate = false;
                UpdateHelper.getInstance()
                        .setRequestResultData(listLzyResponse.body())
                        .setUpdateListener(new UpdateListener() {
                            @Override
                            public void noUpdate() {
                                if (!isAutoUpdate) {
                                    Toast.makeText(MyApplication.getContext(), "已经是最新版本了", Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onCheckError(int code, String errorMsg) {
                                if (!isAutoUpdate) {
                                    Toast.makeText(MyApplication.getContext(), "检测更新失败：" + errorMsg, Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onUserCancel() {
                                if (!isAutoUpdate) {
                                    Toast.makeText(MyApplication.getContext(), "用户取消", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .checkNoUrl(MainActivity.this);

            }
        });
    }

    private static boolean mBackKeyPressed = false;//记录是否有首次按键

    @Override
    public void onBackPressed() {
        if(!mBackKeyPressed){
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦错第一次按键记录
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        }
        else{//退出程序
            this.finish();
            System.exit(0);
        }
    }

}
