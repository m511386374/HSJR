package com.haosencredit.Haosenfinance.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.adapter.TotalIncomeAdapter;
import com.haosencredit.Haosenfinance.base.BaseFragment;
import com.haosencredit.Haosenfinance.model.TotalIncomeRvModel;
import com.haosencredit.Haosenfinance.view.RingStatisticsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Nick.Ming on 2017/5/11.
 */
public class TotalIncomeFragment extends BaseFragment {
    @BindView(R.id.id_rsv)
    RingStatisticsView ringStatisticsView;
    @BindView(R.id.totalasset_rv)
    RecyclerView totalasset_rv;
    float f = 800;
    float a = 225.5f;
    float b = 40f;
    float c = 274.5f;
    float d = 85.5f;
    float e = 174.5f;
    public static TotalIncomeFragment newInstance() {
        TotalIncomeFragment fragment = new TotalIncomeFragment();
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        ringStatisticsView.setPercentAndColors(new float[]{a/f,b/f,c/f,d/f,e/f},new int[]{ Color.parseColor("#F9AA28"), Color.parseColor("#009752"),Color.parseColor("#2EC1FB"), Color.parseColor("#FA6723"),Color.parseColor("#7CB5EC")});
        ringStatisticsView.refresh();
        List<TotalIncomeRvModel> redlist = new ArrayList<TotalIncomeRvModel>();
        for(int i=1;i<=7;i++){
            TotalIncomeRvModel rpm = new TotalIncomeRvModel();
            rpm.setA("2016-03-0"+i);
            rpm.setB(i+"00");
            redlist.add(rpm);
        }
        totalasset_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        TotalIncomeAdapter quickadapter = new TotalIncomeAdapter(redlist);
        totalasset_rv.setAdapter(quickadapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_total_income;
    }

    @Override
    public Object newP() {
        return null;
    }
}
