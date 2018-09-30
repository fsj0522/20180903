package com.example.pc.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private EditText username;
    private RadioGroup rgSex;
    private CheckBox cnb_java;
    private CheckBox cnb_english;
    private CheckBox cnb_math;
    private CheckBox cnb_chinese;
    private Button btn_confirm;
    private TextView tv_showIp;
    private EditText et_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        imageView = findViewById(R.id.img_head);
        username = findViewById(R.id.et_name);
        rgSex = findViewById(R.id.rg);
        cnb_java = findViewById(R.id.cnb_java);
        cnb_english = findViewById(R.id.cnb_english);
        cnb_math = findViewById(R.id.cnb_math);
        cnb_chinese = findViewById(R.id.cnb_chinese);
        tv_showIp = findViewById(R.id.tv_showIp);
        et_ip = findViewById(R.id.et_ip);
        btn_confirm = findViewById(R.id.btn_confirm);

        username.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    //关闭软键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null && imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });

        imageView.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
        //处理键盘事件逻辑
        et_ip.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (event.getAction()) {
                    case KeyEvent.ACTION_UP://键盘释放
                        String ip = et_ip.getText().toString();
                        String newIp = "";
                        for (int i = 0; i < ip.length() / 3; i++) {
                            if (i * 3 + 3 < ip.length()) {
                                newIp = newIp + ip.substring(i * 3, Math.min(i * 3 + 3, ip.length())) + ".";
                            } else {
                                newIp = newIp + ip.substring(i * 3, Math.min(i * 3 + 3, ip.length()));
                            }
                        }
                        tv_showIp.setText(newIp);
                        break;
                    case KeyEvent.ACTION_DOWN:
                        break;
                }
                return false;
            }
        });
        //通知
        sendNormalNotification();
        //sendFoldNotification();
        //sendHangNotification();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_head:
                imageView.setImageResource(R.drawable.head);
                break;
            case R.id.btn_confirm:
                String name = username.getText().toString();
                String sex = "";
                int id = rgSex.getCheckedRadioButtonId();
                if (id == R.id.rb_teenager) {
                    sex = "男";
                } else if (id == R.id.rb_lolita) {
                    sex = "女";
                }
                String course = "";
                if (cnb_java.isChecked()) {
                    course += " " + cnb_java.getText().toString();
                }
                if (cnb_english.isChecked()) {
                    course += " " + cnb_english.getText().toString();
                }
                if (cnb_math.isChecked()) {
                    course += " " + cnb_math.getText().toString();
                }
                if (cnb_chinese.isChecked()) {
                    course += " " + cnb_chinese.getText().toString();
                }
                //course=course.substring(0,course.length()-1);
                String info = "个人信息：姓名=" + name + "，性别=" + sex + "，喜欢：" + course;
                Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
                //Snackbar.make(v,info,Snackbar.Length_LONG).show(); 在app的build.gradle下载
                break;
        }
    }

    //处理触屏事件逻辑
    public boolean onTouchEvent(MotionEvent event) {
        //区分触屏事件的方式
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            String pos = "";
            float x = event.getX();
            float y = event.getY();
            pos = "X的坐标：" + x + ",Y的坐标：" + y;
            Toast.makeText(this, pos, Toast.LENGTH_SHORT).show();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            Toast.makeText(this, "手指抬起", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    //自定义通知 针对Android 8.0
    public void sendNormalNotification() {
        //1.创建通知
        Notification.Builder builder = null;
        //2.通知操作，定义 PendingIntent
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String id = "normal";
            NotificationChannel channel = new NotificationChannel("normal", "正常通知",
                    NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(channel);
            builder = new Notification.Builder(InfoActivity.this, id);
            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setContentTitle("普通通知")
                    .setContentText("通知内容")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
        } else {
            builder = new Notification.Builder(InfoActivity.this);
            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setContentTitle("普通通知")
                    .setContentText("通知内容")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
        }
        // 3.发送
        manager.notify(1, builder.build());

    }

    //折叠通知
    public void sendFoldNotification() {
        //1.创建通知
        Notification.Builder builder = null;
        //2.通知操作，定义 PendingIntent
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String id = "normal";
            NotificationChannel channel = new NotificationChannel("normal", "正常通知",
                    NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(channel);
            builder = new Notification.Builder(InfoActivity.this, id);
            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setContentTitle("普通通知")
                    .setContentText("通知内容")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
        } else {
            builder = new Notification.Builder(InfoActivity.this);
            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setContentTitle("折叠通知")
                    .setContentText("通知内容")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
        }
        // 3.发送
        // 用RemoteViews显示自定义通知的内容
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_fold);
        Notification notification = builder.build();
        notification.bigContentView = remoteViews;
        manager.notify(1, notification);
    }

    //悬挂通知
    public void sendHangNotification() {
        //1.创建通知
        Notification.Builder builder = null;
        //2.通知操作，定义 PendingIntent
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String id = "normal";
            NotificationChannel channel = new NotificationChannel("normal", "正常通知",
                    NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(channel);
            builder = new Notification.Builder(InfoActivity.this, id);
            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setContentTitle("普通通知")
                    .setContentText("通知内容")
                    //.setContentIntent(pendingIntent)
                    .setFullScreenIntent(pendingIntent,true)
                    .setAutoCancel(true);
        } else {
            builder = new Notification.Builder(InfoActivity.this);
            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setContentTitle("普通通知")
                    .setContentText("通知内容")
                    //.setContentIntent(pendingIntent)
                    .setFullScreenIntent(pendingIntent,true)
                    .setAutoCancel(true);
        }
        //设置悬挂的Intent
        Intent hIntent=new Intent();
        hIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //设置跳转hIntent.setClass(this,MainActivity.class);
        PendingIntent hangIntent=PendingIntent.getActivity(InfoActivity.this,0,hIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(hangIntent,true);
        // 3.发送
        manager.notify(1, builder.build());
    }


}