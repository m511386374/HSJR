package com.haosencredit.Haosenfinance.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.adapter.UnusedGoldAdapter;
import com.haosencredit.Haosenfinance.base.BaseFragment;
import com.haosencredit.Haosenfinance.model.GoldModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Nick.Ming on 2017/5/11.
 */
public class UnusedGoldFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView unused_rv;

    public static UnusedGoldFragment newInstance() {
        UnusedGoldFragment fragment = new UnusedGoldFragment();
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        List<GoldModel> redlist = new ArrayList<GoldModel>();
        for(int i=1;i<=10;i++){
            GoldModel rpm = new GoldModel();
            rpm.setA("2016-06-01");
            rpm.setB("500,000.00");
            rpm.setC("已经使用");
            redlist.add(rpm);
        }
        unused_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        UnusedGoldAdapter quickadapter = new UnusedGoldAdapter(redlist);
        unused_rv.setAdapter(quickadapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_unused;
    }

    @Override
    public Object newP() {
        return null;
    }
}
