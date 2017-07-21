package com.haosencredit.Haosenfinance.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.model.GoldModel;
import com.haosencredit.Haosenfinance.model.RedPacketModel;

import java.util.List;

/**
 * Created by Nick.Ming on 2017/6/27.
 */

public class UnusedGoldAdapter extends BaseQuickAdapter<GoldModel, BaseViewHolder> {


    public UnusedGoldAdapter(List<GoldModel> rpm) {
        super(R.layout.unusedgold_item, rpm);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, GoldModel item) {
        viewHolder.setText(R.id.gold_time, item.getA())
                .setText(R.id.gold_money, item.getB())
                .setText(R.id.gold_state, item.getC());


    }

}