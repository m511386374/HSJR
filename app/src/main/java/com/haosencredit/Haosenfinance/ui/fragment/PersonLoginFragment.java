package com.haosencredit.Haosenfinance.ui.fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.haosencredit.Haosenfinance.R;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XFragment;


/**
 * Created by bruce on 2016/11/1.
 * BaseFragment
 */

public class PersonLoginFragment extends XFragment {

    @BindView(R.id.quick_register)
    TextView quick_register;
    public static PersonLoginFragment newInstance() {
        PersonLoginFragment fragment = new PersonLoginFragment();
        return fragment;
    }
    @Override
    public void initData(Bundle savedInstanceState) {
            quick_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build("/Hsjr/RegisterActivity")
                        .navigation();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personlogin;
    }

    @Override
    public Object newP() {
        return null;
    }

}
