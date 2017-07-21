package com.haosencredit.Haosenfinance.ui.fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.haosencredit.Haosenfinance.R;
import com.haosencredit.Haosenfinance.base.BaseFragment;
import com.haosencredit.Haosenfinance.ui.activity.WeakDetailActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Nick.Ming on 2017/5/11.
 */

public class FinanceFragment extends BaseFragment {

    @BindView(R.id.new_weekandweek)
    LinearLayout new_weekandweek;
    public static FinanceFragment newInstance() {
        FinanceFragment fragment = new FinanceFragment();
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_finance;
    }

    @Override
    public Object newP() {
        return null;
    }

    @OnClick({R.id.new_weekandweek,R.id.weekandweek,R.id.monthandmonth,R.id.three_monthandmonth,R.id.six_monthandmonth,
              R.id.nine_monthandmonth,R.id.twelve_monthandmonth,R.id.eighteen_monthandmonth,R.id.scattered})
    public void jumpDetail(View view){
        switch(view.getId())
        {
            case R.id.new_weekandweek:
                startActivity(WeakDetailActivity.class);
                break;
//            case R.id.weekandweek:
//                startActivity(WeakDetailActivity.class);
//                break;
//            case R.id.monthandmonth:
//                startActivity(WeakDetailActivity.class);
//                break;
//            case R.id.three_monthandmonth:
//                startActivity(WeakDetailActivity.class);
//                break;
//            case R.id.six_monthandmonth:
//                startActivity(WeakDetailActivity.class);
//                break;
//            case R.id.nine_monthandmonth:
//                startActivity(WeakDetailActivity.class);
//                break;
//            case R.id.twelve_monthandmonth:
//                startActivity(WeakDetailActivity.class);
//                break;
//            case R.id.eighteen_monthandmonth:
//                startActivity(WeakDetailActivity.class);
//                break;
//            case R.id.scattered:
//                startActivity(WeakDetailActivity.class);
//                break;
        }

    }

}
