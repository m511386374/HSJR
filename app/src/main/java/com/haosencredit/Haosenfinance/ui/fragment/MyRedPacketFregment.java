package com.haosencredit.Haosenfinance.ui.fragment;

/**
 * Created by Nick.Ming on 2017/6/26.
 */
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.adapter.RedPacketAdapter;
import com.haosencredit.Haosenfinance.base.BaseRxFragment;
import com.haosencredit.Haosenfinance.model.RedPacketModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Administrator on 2015/7/30.
 */
public class MyRedPacketFregment extends BaseRxFragment {
    @BindView(R.id.redpacket_rv)
    RecyclerView redpacket_rv;
    public static MyRedPacketFregment newInstance() {
        MyRedPacketFregment fragment = new MyRedPacketFregment();
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        List<RedPacketModel>  redlist = new ArrayList<RedPacketModel>();
        for(int i=1;i<=10;i++){
            RedPacketModel rpm = new RedPacketModel();
            rpm.setPrice("100");
            rpm.setA("邀请奖励红包");
            rpm.setB("投资满2000元以上可用");
            rpm.setC("限借贷标使用");
            rpm.setD("一次性使用");
            rpm.setE("2016-03-08至2016-04-08");
            redlist.add(rpm);
        }
        redpacket_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        RedPacketAdapter quickadapter = new RedPacketAdapter(redlist);
        View headerView = getHeaderView();
        quickadapter.addHeaderView(headerView);
        redpacket_rv.setAdapter(quickadapter);

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_redpacket_rv;
    }

    @Override
    public Object newP() {
        return null;
    }

    private View getHeaderView() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.redpacket_head, (ViewGroup) redpacket_rv.getParent(), false);
        return view;
    }
}