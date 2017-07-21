package com.haosencredit.Haosenfinance.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.base.BaseActivity;
import com.haosencredit.Haosenfinance.view.ScoreTrend;
import com.haosencredit.Haosenfinance.view.TimeView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Nick.Ming on 2017/6/14.
 */
@Route(path = "/Hsjr/WeakDetailActivity")
public class WeakDetailActivity extends BaseActivity {

    @BindView(R.id.timeview)
    TimeView timeView;
    @BindView(R.id.scoreTrend)
    ScoreTrend scoreTrend;
    private int[]    score     = new int[]{1,2,3,5,6,7,7,8,9,9,10,10};

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        timeView.setTime(864000);
        scoreTrend.setScore(score);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_weekdetail;
    }

    @Override
    public Object newP() {
        return null;
    }

    @OnClick({R.id.back_image})
    public void back(View view) {
        finish();
    }
}
