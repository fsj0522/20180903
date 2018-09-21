package com.example.pc.myapplication.widge;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.example.pc.myapplication.R;

public class CustomDialog extends Dialog implements View.OnClickListener {
    private TextView mTvTitle,mTvMessage,mTvCancel,mTvConfirm;
    private  String title,message,cancel,confirm;
    private IOnCancelListener cancelListener;
    private IOnConfirmListener confirmListener;


    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public CustomDialog setTitle(String title) {//用CustomDialog返回当前的dialog
        this.title = title;
        return this;
    }

    public CustomDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public CustomDialog setCancel(String cancel,IOnCancelListener listener) {
        this.cancel = cancel;
        this.cancelListener=listener;
        return this;
    }

    public CustomDialog setConfirm(String confirm,IOnConfirmListener listener) {
        this.confirm = confirm;
        this.confirmListener=listener;
        return this;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        //设置屏幕宽度
        WindowManager windowManager=getWindow().getWindowManager();
        Display display=windowManager.getDefaultDisplay();
        WindowManager.LayoutParams params=getWindow().getAttributes();
        Point size=new Point();
        display.getSize(size);
        params.width=(int) (size.x*0.8);//设置dialog的宽度为当前手机屏幕的宽度*0.8
        getWindow().setAttributes(params);

        mTvTitle=findViewById(R.id.tv_title);
        mTvMessage=findViewById(R.id.tv_message);
        mTvCancel=findViewById(R.id.tv_cancel);
        mTvConfirm=findViewById(R.id.tv_confirm);

        if (!TextUtils.isEmpty(title)){
            mTvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(message)){
            mTvMessage.setText(message);
        }
        if (!TextUtils.isEmpty(cancel)){
            mTvCancel.setText(cancel);
        }
        if (!TextUtils.isEmpty(confirm)){
            mTvConfirm.setText(confirm);
        }
        mTvCancel.setOnClickListener(this);
        mTvConfirm.setOnClickListener(this);

    }

    public interface IOnCancelListener{
        void  onCancel(CustomDialog dialog);
    }

    public interface IOnConfirmListener{
        void  onConfirm(CustomDialog dialog);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_cancel:
                if (cancelListener!=null){
                    cancelListener.onCancel(this);
                }
                dismiss();//按钮默认取消
                break;
            case R.id.tv_confirm:
                if (confirmListener!=null){
                    confirmListener.onConfirm(this);
                    dismiss();
                }
                break;
        }
    }
}
