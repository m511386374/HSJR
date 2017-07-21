package com.haosencredit.Haosenfinance.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.model.TotalIncomeRvModel;

import java.util.List;

/**
 * Created by Nick.Ming on 2017/6/27.
 */

public class TotalIncomeAdapter extends BaseQuickAdapter<TotalIncomeRvModel, BaseViewHolder> {


    public TotalIncomeAdapter(List<TotalIncomeRvModel> rpm) {
        super(R.layout.totalasset_income_item, rpm);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, TotalIncomeRvModel item) {
        viewHolder.setText(R.id.totalasset_time, item.getA())
                .setText(R.id.totalasset_money, item.getB());


    }

}