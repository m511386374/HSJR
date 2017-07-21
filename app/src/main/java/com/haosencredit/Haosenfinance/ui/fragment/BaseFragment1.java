package com.haosencredit.Haosenfinance.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haosencredit.Haosenfinance.R;

/**
 * Created by bruce on 2016/11/1.
 * BaseFragment
 */

public class BaseFragment1 extends Fragment {

    FragmentCallBack callBack;
//    public static BaseFragment newInstance() {
//        BaseFragment fragment = new BaseFragment();
//        return fragment;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base1, null);
        if(callBack!=null){
            callBack.showMsg("66666666666666666666666");
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCallBack){
            callBack= (FragmentCallBack) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callBack=null;
    }

    public static interface FragmentCallBack{
        void showMsg(String msg);
    }
}
