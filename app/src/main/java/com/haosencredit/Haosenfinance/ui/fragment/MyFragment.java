package com.haosencredit.Haosenfinance.ui.fragment;

import android.os.Bundle;

import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.base.BaseFragment;

/**
 * Created by Nick.Ming on 2017/5/11.
 */
public class MyFragment extends BaseFragment {

//    @BindView(R.id.login_tv)
//    TextView login_tv;

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
//        RxView.clicks(login_tv)
//                .throttleFirst(1, TimeUnit.SECONDS)
//                .subscribe(new Action1<Void>() {
//                    @Override
//                    public void call(Void aVoid) {
//
//                        ARouter.getInstance()
//                                .build("/Hsjr/LoginActivity")
//                                .navigation();
//                    }
//                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public Object newP() {
        return null;
    }
}
