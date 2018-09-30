package com.example.pc.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityDeomActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login;
    private Button btn_info;
    private Button btn_send_value1;
    private Button btn_send_value2;
    private Button btn_send_array;
    private Button btn_send_obj;
    private Button btn_send_objs;
    private Button btn_send_return;
    private Button btn_phone;
    private Button btn_sms;
    private Button btn_email;
    private Button btn_call_page;
    private Button btn_life_cycle;
    private Button btn_timer;
    private Button btn_show_alarm;
    private Button btn_alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitydeom);

        btn_login = findViewById(R.id.bt_p1);
        btn_info = findViewById(R.id.bt_p2);
        btn_send_value1 = findViewById(R.id.bt_p3);
        btn_send_value2 = findViewById(R.id.bt_p4);
        btn_send_array = findViewById(R.id.bt_p5);
        btn_send_obj = findViewById(R.id.bt_p6);
        btn_send_objs = findViewById(R.id.bt_p7);
        btn_send_return = findViewById(R.id.bt_p8);
        btn_phone = findViewById(R.id.bt_p9);
        btn_sms = findViewById(R.id.bt_p10);
        btn_email = findViewById(R.id.bt_p11);
        btn_call_page = findViewById(R.id.bt_p12);
        btn_alarm = findViewById(R.id.bt_p13);
        btn_timer = findViewById(R.id.bt_p14);
        btn_show_alarm = findViewById(R.id.bt_p15);
        btn_life_cycle = findViewById(R.id.bt_p16);

        btn_login.setOnClickListener(this);
        btn_info.setOnClickListener(this);
        btn_send_value1.setOnClickListener(this);
        btn_send_value2.setOnClickListener(this);
        btn_send_array.setOnClickListener(this);
        btn_send_obj.setOnClickListener(this);
        btn_send_objs.setOnClickListener(this);
        btn_send_return.setOnClickListener(this);
        btn_phone.setOnClickListener(this);
        btn_sms.setOnClickListener(this);
        btn_email.setOnClickListener(this);
        btn_call_page.setOnClickListener(this);
        btn_alarm.setOnClickListener(this);
        btn_timer.setOnClickListener(this);
        btn_show_alarm.setOnClickListener(this);
        btn_life_cycle.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_p1:
                Intent intentLogin = new Intent(ActivityDeomActivity.this, MainActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.bt_p2:
                //隐式Intent
                getInfoActivity();
                break;
            case R.id.bt_p3:
                //显式Intent
                sendSimpleData1();
                break;
            case R.id.bt_p4:
                sendSimpleData2();
                break;
            case R.id.bt_p5:
                sendSimpleArray();
                break;
            case R.id.bt_p6:
                sendObject();
                break;
            case R.id.bt_p7:
                sendObjects();
                break;
            case R.id.bt_p8:
                sendAndReturn();
                break;
            case R.id.bt_p9:
                call();
                break;
            case R.id.bt_p10:
                sendSms();
                break;
            case R.id.bt_p11:
                sendSms();
                break;
            case R.id.bt_p12:
                callWebPage();
                break;
        }

    }


    //传递基本数据类型的数据，方法1
    private void sendSimpleData1() {
        Intent intent = new Intent(ActivityDeomActivity.this, IntentActivity.class);
        intent.putExtra("flag", 1);
        intent.putExtra("version", 1.0f);
        startActivity(intent);
    }

    //传递基本数据类型的数据，方法2
    private void sendSimpleData2() {
        Intent intent = new Intent(ActivityDeomActivity.this, IntentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putFloat("version", 2.0f);
        bundle.putString("activity", "ActivityDemoActivity");
        intent.putExtras(bundle);
        intent.putExtra("flag", 2);
        startActivity(intent);
    }

    private void sendSimpleArray() {
        Intent intent = new Intent(ActivityDeomActivity.this, IntentActivity.class);
        intent.putExtra("flag", 3);
        //方法一
        int[] value = {1, 2, 3};
        intent.putExtra("int_array", value);
        //方法二
        Bundle bundle = new Bundle();
        bundle.putIntArray("another_array", value);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void sendObject() {
        Intent intent = new Intent(ActivityDeomActivity.this, IntentActivity.class);
        intent.putExtra("flag", 4);
        User user1 = new User("niit", "123456", 100);
        intent.putExtra("user", user1);

        Bundle bundle = new Bundle();
        User user2 = new User("fsj", "111111", 3);
        bundle.putSerializable("another_user", user2);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private void sendObjects() {
        Intent intent = new Intent(ActivityDeomActivity.this, IntentActivity.class);
        intent.putExtra("flag", 5);

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User("niit" + i, "123456", 100);
            users.add(user);
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("another_user", (Serializable) users);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 1000 && requestCode == Activity.RESULT_OK) {
            String result = data.getStringExtra("return data");
            //将结果显示到相应的TextView
        }
    }

    private void sendAndReturn() {
        Intent intent = new Intent(ActivityDeomActivity.this, IntentActivity.class);
        intent.putExtra("flag", 6);
        intent.putExtra("name", "niit"); //此处的值需要通过EditText获取
        startActivityForResult(intent, 1000); //此处的1000是requestCode
    }

    private void call() {
        Uri uri = Uri.parse("tel:10086");
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

    private void sendSms(){
        Uri uri = Uri.parse("smsto:10086");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body","hello");
        startActivity(intent);
    }

    private void callWebPage() {
        Uri uri = Uri.parse("http://www.baidu.com");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        //startActivity(intent);
        startActivity(Intent.createChooser(intent,"请选择浏览器"));
    }

    private void getInfoActivity() {
        Intent intent=new Intent();
        intent.setAction("edu.niit.android.info");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("niit://edu.niit.android.info:8888/target"));
        startActivity(intent);
    }
}
