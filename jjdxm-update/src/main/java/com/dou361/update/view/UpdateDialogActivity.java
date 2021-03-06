package com.dou361.update.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dou361.download.DownloadManager;
import com.dou361.download.DownloadModel;
import com.dou361.download.ParamsManager;
import com.dou361.update.R;
import com.dou361.update.UpdateHelper;
import com.dou361.update.bean.Update;
import com.dou361.update.server.DownloadingService;
import com.dou361.update.type.UpdateType;
import com.dou361.update.util.InstallUtil;
import com.dou361.update.util.NetworkUtil;
import com.dou361.update.util.UpdateConstants;
import com.dou361.update.util.UpdateSP;

import java.io.File;

import qiu.niorgai.StatusBarCompat;

/**
 * ========================================
 * <p/>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2016/6/17
 * <p/>
 * 描 述：
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class UpdateDialogActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private View jjdxm_update_wifi_indicator;
    private TextView jjdxm_update_content;
    private CheckBox jjdxm_update_id_check;
    private Button jjdxm_update_id_ok;
    private Button jjdxm_update_id_cancel;
    private Update mUpdate;
    private int mAction;
    private String mPath;
    private Context mContext;
    private String text;
    //是否已经下载完成
    private boolean finshDown;
    //调起方式
    private boolean isActivityEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.setStatusBarColor(UpdateDialogActivity.this, getResources().getColor(R.color.alertdialogbackground));
        mContext = this;
        @LayoutRes int layoutId = UpdateSP.getDialogLayout();
        if (layoutId > 0) {
            setContentView(layoutId);
        } else {
            setContentView(R.layout.jjdxm_update_dialog);
        }
        Intent intent = getIntent();
        mUpdate = (Update) intent.getSerializableExtra(UpdateConstants.DATA_UPDATE);
        mAction = intent.getIntExtra(UpdateConstants.DATA_ACTION, 0);
        mPath = intent.getStringExtra(UpdateConstants.SAVE_PATH);
        isActivityEnter = intent.getBooleanExtra(UpdateConstants.START_TYPE, false);
        String updateContent = null;
        jjdxm_update_wifi_indicator = findViewById(R.id.jjdxm_update_wifi_indicator);
        jjdxm_update_content = (TextView) findViewById(R.id.jjdxm_update_content);
        jjdxm_update_id_check = (CheckBox) findViewById(R.id.jjdxm_update_id_check);
        jjdxm_update_id_ok = (Button) findViewById(R.id.jjdxm_update_id_ok);
        jjdxm_update_id_cancel = (Button) findViewById(R.id.jjdxm_update_id_cancel);
        if (jjdxm_update_wifi_indicator != null) {
            if (NetworkUtil.isConnectedByWifi()) {
                //WiFi环境
                jjdxm_update_wifi_indicator.setVisibility(View.INVISIBLE);
            } else {
                jjdxm_update_wifi_indicator.setVisibility(View.VISIBLE);
            }
        }
        if (TextUtils.isEmpty(mPath)) {
            String url = mUpdate.getUpdateUrl();
            mPath = DownloadManager.getInstance(mContext).getDownPath() + File.separator + url.substring(url.lastIndexOf("/") + 1, url.length());
        }
        if (mAction == 0) {
            DownloadModel dd = DownloadManager.getInstance(mContext).getDownloadByUrl(mUpdate.getUpdateUrl());
            if (dd != null) {
                finshDown = (dd.getDOWNLOAD_STATE() == ParamsManager.State_FINISH);
                File fil = new File(mPath);
                if (finshDown && fil.exists() && (fil.length() > 0) && (fil.length() + "").equals(dd.getDOWNLOAD_TOTALSIZE())) {
                    finshDown = true;
                } else {
                    DownloadManager.getInstance(mContext).deleteAllDownload();
                    finshDown = false;
                }
            } else {
                finshDown = false;
            }
        } else {
            finshDown = true;
        }
        if (finshDown) {
            //完成下载
            if (isActivityEnter) {
                /**Activity方式调起的*/
                if (mUpdate.getApkSize() > 0) {
                   text = "\n"+"\n"+getText(R.string.jjdxm_update_dialog_installapk) ;
                     updateContent =  mUpdate.getUpdateContent()+text ;
                    jjdxm_update_id_ok.setText(R.string.jjdxm_update_installnow);
                    jjdxm_update_content.setText(updateContent);
                } else {
                    updateContent = mUpdate.getUpdateContent();
                    jjdxm_update_id_ok.setText(R.string.jjdxm_update_installnow);
                    jjdxm_update_content.setText(updateContent);
                }

            } else {
                /**服务方式方式调起的*/
                InstallUtil.installApk(mContext, mPath);
                finish();
                if (UpdateHelper.getInstance().getForceListener() != null) {
                    UpdateHelper.getInstance().getForceListener().onUserCancel(UpdateSP.isForced());
                }
            }

        } else {
            updateContent =mUpdate.getUpdateContent() +"";
            jjdxm_update_id_ok.setText(R.string.jjdxm_update_updatenow);
            jjdxm_update_content.setText(updateContent);
        }
        if (jjdxm_update_id_check != null) {
            if (UpdateHelper.getInstance().getUpdateType() == UpdateType.checkupdate) {
                //手动更新
                jjdxm_update_id_check.setVisibility(View.GONE);
            } else {
                jjdxm_update_id_check.setVisibility(View.GONE);
//                jjdxm_update_id_check.setVisibility(UpdateSP.isForced() ? View.GONE : View.VISIBLE);
            }
        }

        if (jjdxm_update_id_check != null) {
            jjdxm_update_id_check.setOnCheckedChangeListener(this);
        }
        jjdxm_update_id_ok.setOnClickListener(this);
        jjdxm_update_id_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.jjdxm_update_id_ok) {
            if (finshDown) {
                DownloadModel dd = DownloadManager.getInstance(mContext).getDownloadByUrl(mUpdate.getUpdateUrl());
                if (dd != null) {
                    finshDown = (dd.getDOWNLOAD_STATE() == ParamsManager.State_FINISH);
                    File fil = new File(mPath);
                    if (finshDown && fil.exists() && (fil.length() > 0) && (fil.length() + "").equals(dd.getDOWNLOAD_TOTALSIZE())) {
                        finshDown = true;
                    } else {
                        DownloadManager.getInstance(mContext).deleteAllDownload();
                        finshDown = false;
                    }
                } else {
                    finshDown = false;
                }
                if (finshDown) {
                    InstallUtil.installApk(mContext, mPath);
                    finish();
                    if (UpdateHelper.getInstance().getForceListener() != null) {
                        UpdateHelper.getInstance().getForceListener().onUserCancel(UpdateSP.isForced());
                    }
                } else {
                    Intent intent = new Intent(mContext, DownloadingService.class);
                    intent.putExtra(UpdateConstants.DATA_ACTION, UpdateConstants.START_DOWN);
                    intent.putExtra(UpdateConstants.DATA_UPDATE, mUpdate);
                    startService(intent);
                    if (UpdateSP.isForced()) {
                        Intent intenta = new Intent(mContext, DownloadDialogActivity.class);
                        startActivity(intenta);
                    } else {
                        if (UpdateHelper.getInstance().getForceListener() != null) {
                            UpdateHelper.getInstance().getForceListener().onUserCancel(UpdateSP.isForced());
                        }
                    }
                    finish();
                }
            } else {
                Intent intent = new Intent(mContext, DownloadingService.class);
                intent.putExtra(UpdateConstants.DATA_ACTION, UpdateConstants.START_DOWN);
                intent.putExtra(UpdateConstants.DATA_UPDATE, mUpdate);
                startService(intent);
                if (UpdateSP.isForced()) {
                    Intent intenta = new Intent(mContext, DownloadDialogActivity.class);
                    startActivity(intenta);
                } else {
                    if (UpdateHelper.getInstance().getForceListener() != null) {
                        UpdateHelper.getInstance().getForceListener().onUserCancel(UpdateSP.isForced());
                    }
                }
                finish();
            }

        } else if (id == R.id.jjdxm_update_id_cancel) {
            if (UpdateHelper.getInstance().getForceListener() != null) {
                UpdateHelper.getInstance().getForceListener().onUserCancel(UpdateSP.isForced());
            }
            if (UpdateHelper.getInstance().getUpdateListener() != null) {
                if (!finshDown) {
                    UpdateHelper.getInstance().getUpdateListener().onUserCancelDowning();
                } else {
                    UpdateHelper.getInstance().getUpdateListener().onUserCancelInstall();
                }
            }
            finish();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        UpdateSP.setIgnore(isChecked ? mUpdate.getVersionCode() + "" : "");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && UpdateSP.isForced()) {
            finish();
            if (UpdateHelper.getInstance().getForceListener() != null) {
                UpdateHelper.getInstance().getForceListener().onUserCancel(UpdateSP.isForced());
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
