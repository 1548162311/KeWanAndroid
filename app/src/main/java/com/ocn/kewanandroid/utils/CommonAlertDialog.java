package com.ocn.kewanandroid.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.ocn.kewanandroid.R;

/**
 * Created by kevin on 2018/4/25.
 */

public class CommonAlertDialog {
    private AlertDialog mAlertDialog;
    public static CommonAlertDialog newInstance(){
        return CommonAlertDialogHolder.COMMON_ALERT_DIALOG;
    }
    private static class CommonAlertDialogHolder{
        private static final CommonAlertDialog COMMON_ALERT_DIALOG = new CommonAlertDialog();
    }

    /**
     * Cancel alertDialog
     * @param isAdd
     */
    public void cancelDialog(boolean isAdd){
        if (isAdd && mAlertDialog != null && mAlertDialog.isShowing()){
            mAlertDialog.dismiss();
            mAlertDialog = null;
        }
    }

    /**
     * Show alertDialog
     * @param mActivity
     * @param content
     * @param btnContent
     */
    public void showDialog(final Activity mActivity, String content, String btnContent){
        if (mActivity == null){
            return;
        }
        if (mAlertDialog == null){
            mAlertDialog = new AlertDialog.Builder(mActivity, R.style.myCorDialog).create();
        }
        mAlertDialog.setCanceledOnTouchOutside(false);
        Window window = mAlertDialog.getWindow();
        if(window!= null){
            window.setContentView(R.layout.common_alert_dialog);
            TextView contentTv = window.findViewById(R.id.dialog_content);
            contentTv.setText(content);
            Button mOkBtn = window.findViewById(R.id.dialog_btn);
            mOkBtn.setText(btnContent);
            mOkBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mAlertDialog!=null){
                        mAlertDialog.cancel();
                        mAlertDialog =null;
                    }
                }
            });
            View btnDivider = window.findViewById(R.id.dialog_btn_divider);
            btnDivider.setVisibility(View.GONE);
            Button mNeBtn = window.findViewById(R.id.dialog_negative_btn);
            mNeBtn.setVisibility(View.GONE);
        }
    }

    public void showDialog(Activity mActivity, String content, String btnContent, final View.OnClickListener onClickListener) {
        if (mActivity == null){
            return;
        }
        if (mAlertDialog == null){
            mAlertDialog = new AlertDialog.Builder(mActivity,R.style.myCorDialog).create();
        }
        if (!mAlertDialog.isShowing()){
            mAlertDialog.show();
        }
        mAlertDialog.setCanceledOnTouchOutside(false);
        Window window = mAlertDialog.getWindow();
        if (window!= null){
            window.setContentView(R.layout.common_alert_dialog);
            TextView contentTv =  window.findViewById(R.id.dialog_content);
            contentTv.setText(content);
            Button mOkBtn = window.findViewById(R.id.dialog_btn);
            mOkBtn.setText(btnContent);
            mOkBtn.setOnClickListener(onClickListener);
            View btnDivider = window.findViewById(R.id.dialog_btn_divider);
            btnDivider.setVisibility(View.GONE);
            Button mNeBtn = window.findViewById(R.id.dialog_negative_btn);
            mNeBtn.setVisibility(View.GONE);
        }
    }

    public void showDialog(Activity mActivity, String content, String btnContent, String neContent,
                           final View.OnClickListener onPoClickListener,
                           final View.OnClickListener onNeClickListener) {
        if(mActivity==null){
            return;
        }
        if (mAlertDialog == null){
            mAlertDialog = new AlertDialog.Builder(mActivity,R.style.myCorDialog).create();
        }
        if (!mAlertDialog.isShowing()){
            mAlertDialog.show();
        }
        mAlertDialog.setCanceledOnTouchOutside(false);
        Window window = mAlertDialog.getWindow();
        if (window!=null){
            window.setContentView(R.layout.common_alert_dialog);
            TextView contentTv = window.findViewById(R.id.dialog_content);
            contentTv.setText(content);
            Button mOkBtn = window.findViewById(R.id.dialog_btn);
            mOkBtn.setText(btnContent);
            mOkBtn.setOnClickListener(onPoClickListener);
            View btnDivider = window.findViewById(R.id.dialog_btn_divider);
            btnDivider.setVisibility(View.VISIBLE);
            Button mNeBtn = window.findViewById(R.id.dialog_negative_btn);
            mNeBtn.setText(neContent);
            mNeBtn.setVisibility(View.VISIBLE);
            mNeBtn.setOnClickListener(onNeClickListener);
        }

    }

}
