package com.haosencredit.Haosenfinance.ui.fragment;

/**
 * Created by Nick.Ming on 2017/6/30.
 */

import android.os.Bundle;

import com.haosencredit.Haosenfinance.R;

import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by bruce on 2016/11/1.
 * BaseFragment
 */

public class BannerFragment extends XFragment {


        public static BannerFragment newInstance() {
            BannerFragment fragment = new BannerFragment();
        return fragment;
    }
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_base;
    }

    @Override
    public Object newP() {
        return null;
    }

    public void startActivity(Class activity){
        Router.newIntent(getActivity())
                .to(activity)
                .launch();
    }
}

