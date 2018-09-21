package com.example.pc.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pc.myapplication.widge.CustomDialog;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    private Button CommonDialog;
    private Button Multiialog;
    private Button ItemDialog;
    private Button SingleDialog;
    private Button ProgressBarDialog;
    //private ProgressBar progressBar;
    private Button ReadingDialog;
    private Button DefintionSelfDialog;
    private Button InputDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        CommonDialog = findViewById(R.id.commonDialog);
        CommonDialog.setOnClickListener(this);

        Multiialog = findViewById(R.id.multiialog);
        Multiialog.setOnClickListener(this);

        ItemDialog = findViewById(R.id.itemDialog);
        ItemDialog.setOnClickListener(this);

        SingleDialog = findViewById(R.id.SingleDialog);
        SingleDialog.setOnClickListener(this);

        ProgressBarDialog = findViewById(R.id.ProgressBarDialog);
        ProgressBarDialog.setOnClickListener(this);


        ReadingDialog = findViewById(R.id.ReadingDialog);
        ReadingDialog.setOnClickListener(this);

        DefintionSelfDialog = findViewById(R.id.DefintionSelfDialog);
        DefintionSelfDialog.setOnClickListener(this);

        InputDialog=findViewById(R.id.InputDialog);
        InputDialog.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commonDialog://普通对话框
                AlertDialog.Builder quitDia = new AlertDialog.Builder(DialogActivity.this);
                quitDia.setIcon(R.mipmap.ic_launcher);
                quitDia.setTitle("提示");
                quitDia.setMessage("退出？");
                quitDia.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                        dialog.dismiss();
                    }
                });
                quitDia.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                quitDia.create().show();
                break;
            case R.id.multiialog://多选对话框
                final String[] array=new String[]{"Java","Android","高数"};
                boolean[] isSelected=new boolean[]{true,false,false};
                AlertDialog.Builder builder=new AlertDialog.Builder(DialogActivity.this);
                builder.setTitle("选择课程").setMultiChoiceItems(array, isSelected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Toast.makeText(DialogActivity.this,array[which]+":"+isChecked,Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.itemDialog://列表对话框
                AlertDialog.Builder Itembuilder=new AlertDialog.Builder(DialogActivity.this);
                Itembuilder.setTitle("请选择：").setMessage("你觉得课程如何？")
                        .setPositiveButton("很好", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogActivity.this,"你很诚实",Toast.LENGTH_SHORT).show();
                            }
                        }).setNeutralButton("一般", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this,"你再瞅瞅~",Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("不好", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this,"睁眼说瞎话",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.SingleDialog://单选列表对话框
                final String[] arraySingle=new String[]{"男","女"};
                AlertDialog.Builder builderSingle=new AlertDialog.Builder(DialogActivity.this);
                builderSingle.setTitle("选择性别")
                        .setSingleChoiceItems(arraySingle, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this,arraySingle[which],Toast.LENGTH_SHORT).show();
                    }
                }).setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.ProgressBarDialog://进度条对话框
                ProgressDialog progressDialog=new ProgressDialog(DialogActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setTitle("提示");
                progressDialog.setMessage("正在加载...");
                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(DialogActivity.this,"cancel...",Toast.LENGTH_SHORT).show();
                    }
                });
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            case R.id.ReadingDialog:
                ProgressDialog ReadingDialog=new ProgressDialog(DialogActivity.this);
                //ReadingDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //ReadingDialog.setProgress(30);
                ReadingDialog.setTitle("提示");
                ReadingDialog.setMessage("读取中...");
                ReadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(DialogActivity.this,"cancel...",Toast.LENGTH_SHORT).show();
                    }
                });
                ReadingDialog.setCancelable(true);
                ReadingDialog.show();
                break;
            case R.id.DefintionSelfDialog:
                CustomDialog customDialog=new CustomDialog(DialogActivity.this);
                customDialog.setTitle("提示").setMessage("确认删除此项？")
                        .setCancel("取消", new CustomDialog.IOnCancelListener() {
                            @Override
                            public void onCancel(CustomDialog dialog) {

                            }
                        }).setConfirm("确认", new CustomDialog.IOnConfirmListener() {
                    @Override
                    public void onConfirm(CustomDialog dialog) {

                    }
                }).show();
                break;
            case R.id.InputDialog:

                break;

        }
    }
}
