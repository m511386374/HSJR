package com.haosencredit.Haosenfinance.adapter;

import android.os.AsyncTask;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.model.RedPacketModel;

import java.util.List;

/**
 * Created by Nick.Ming on 2017/6/27.
 */

public class RedPacketAdapter extends BaseQuickAdapter<RedPacketModel, BaseViewHolder> {


    public RedPacketAdapter(List<RedPacketModel> rpm) {
        super(R.layout.redpacket_item, rpm);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, RedPacketModel item) {
        viewHolder
                .setText(R.id.title_one, item.getA())
                .setText(R.id.title_two, item.getB())
                .setText(R.id.title_three, item.getC())
                .setText(R.id.title_four, item.getD())
                .setText(R.id.title_five, item.getE());


    }

}