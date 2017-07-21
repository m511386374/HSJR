package com.haosencredit.Haosenfinance.ui.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.adapter.FragmentAdapter;
import com.haosencredit.Haosenfinance.base.BaseRxFragment;
import com.haosencredit.Haosenfinance.model.LoginModel;
import com.haosencredit.Haosenfinance.model.LzyResponse;
import com.haosencredit.Haosenfinance.present.PLoadData;
import com.haosencredit.Haosenfinance.ui.activity.GlideImageLoader;
import com.haosencredit.Haosenfinance.view.ChildViewPager;
import com.haosencredit.Haosenfinance.view.loop.CircleIndicator;
import com.lzy.okgo.model.Response;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import butterknife.BindView;
import cn.droidlover.xdroidmvp.event.BusProvider;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;
import rx.Observable;
import rx.Subscriber;


/**
 * Created by bruce on 2016/11/1.
 * BaseFragment
 */

public class HomeFragment extends BaseRxFragment<PLoadData> {
    Observable observable;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.home_viewpager)
    public ChildViewPager home_viewpager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    FragmentAdapter adapter;
    List<Fragment> fragmentList = new ArrayList<>();
    String[] array ={"http://wap.haosenjr.com/upload/lists/20170330/201703300138074409.jpg","http://wap.haosenjr.com/upload/lists/20170330/201703300138409587.jpg","http://wap.haosenjr.com/upload/lists/20170330/201703300138599798.jpg","http://wap.haosenjr.com/upload/lists/20170427/201704271007522268.jpg","http://wap.haosenjr.com/upload/lists/20170330/201703300139319356.jpg"};
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    @Override
    public void initData(Bundle savedInstanceState) {
        setBanner();
        initViewpager(home_viewpager);
        getP().loadHomeData("福利", 10, 18);
    }



    private void initViewpager(ChildViewPager viewPager) {
        fragmentList.clear();
        fragmentList.add(BannerFragment.newInstance());
        fragmentList.add(BannerFragment.newInstance());
        fragmentList.add(BannerFragment.newInstance());
        fragmentList.add(BannerFragment.newInstance());
        if (adapter == null) {
            adapter = new FragmentAdapter(getChildFragmentManager(), fragmentList);
        }
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);


        getP().loadData("福利", 10, 18)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                          LogUtils.d("请求中");
                    }
                })
                .map(new Function<Response<LzyResponse<List<LoginModel>>>, List<LoginModel>>() {
                    @Override
                    public List<LoginModel> apply(@NonNull Response<LzyResponse<List<LoginModel>>> lzyResponseResponse) throws Exception {
                        LogUtils.d(lzyResponseResponse.isFromCache());
                        return lzyResponseResponse.body().results;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LoginModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }
                    @Override
                    public void onNext(@NonNull List<LoginModel> loginModels) {
                        LogUtils.d(loginModels);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                        LogUtils.d("请求结束");
                    }
                });


    }

    public void showDate(Response<LzyResponse<List<LoginModel>>> response){
        LogUtils.d(response.body());

    }

    private void setBanner() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//        banner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,getScreenH(getActivity())/3));
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(Arrays.asList(array));
//        banner.setBannerTitles(Arrays.asList(ar));
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override

    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public PLoadData newP() {
        return new PLoadData();
    }



    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
//        banner.startAutoPlay();
    }
    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
//        banner.stopAutoPlay();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        dispose();
    }
}
