package com.haosencredit.Haosenfinance.ui.activity;
import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.widget.ImageView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.constants.Constans;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xdroidmvp.util.SPUtils;
import qiu.niorgai.StatusBarCompat;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

@Route(path = "/Hsjr/WelcomeActivity")
public class WelcomeActivity extends XActivity {
    @BindView(R.id.iv_entry)
    ImageView mIVEntry;

    private static final int ANIM_TIME = 2000;

    private static final float SCALE_END = 1F;

    private static final int[] Imgs = {R.mipmap.flash};

    @Override
    public void initData(Bundle savedInstanceState) {
        boolean isFirstOpen = (boolean) SPUtils.get(WelcomeActivity.this, Constans.FIRST_OPEN, true);
        // 如果是第一次启动，则先进入功能引导页
        if (isFirstOpen) {
            ARouter.getInstance()
                    .build("/Hsjr/WelcomeGuideActivity")
                    .navigation();
            finish();
            return;
        }
        // 如果不是第一次启动app，则正常显示启动屏
        StatusBarCompat.translucentStatusBar(WelcomeActivity.this);
        ButterKnife.bind(this);
        startMainActivity();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public Object newP() {
        return null;
    }

    private void startMainActivity() {
        Random random = new Random(SystemClock.elapsedRealtime());//SystemClock.elapsedRealtime() 从开机到现在的毫秒数（手机睡眠(sleep)的时间也包括在内）
        mIVEntry.setImageResource(Imgs[0]);

        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {

                    @Override
                    public void call(Long aLong) {
                        startAnim();
                    }
                });
    }

    private void startAnim() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIVEntry, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIVEntry, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
       set.setDuration(ANIM_TIME).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {

                ARouter.getInstance()
                        .build("/Hsjr/MainActivity")
                        .navigation();
                finish();


            }
        });
    }

    /**
     * 屏蔽物理返回按钮
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
