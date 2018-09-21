package com.example.pc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityDeomActivity extends AppCompatActivity implements View.OnClickListener{

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

        btn_login=findViewById(R.id.bt_p1);
        btn_info=findViewById(R.id.bt_p2);
        btn_send_value1=findViewById(R.id.bt_p3);
        btn_send_value2=findViewById(R.id.bt_p4);
        btn_send_array=findViewById(R.id.bt_p5);
        btn_send_obj=findViewById(R.id.bt_p6);
        btn_send_objs=findViewById(R.id.bt_p7);
        btn_send_return=findViewById(R.id.bt_p8);
        btn_phone=findViewById(R.id.bt_p9);
        btn_sms=findViewById(R.id.bt_p10);
        btn_email=findViewById(R.id.bt_p11);
        btn_call_page=findViewById(R.id.bt_p12);
        btn_alarm=findViewById(R.id.bt_p13);
        btn_timer=findViewById(R.id.bt_p14);
        btn_show_alarm=findViewById(R.id.bt_p15);
        btn_life_cycle=findViewById(R.id.bt_p16);

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
        switch (v.getId()){
            case R.id.bt_p1:
                Intent intentLogin=new Intent(ActivityDeomActivity.this,MainActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.bt_p2:
                Intent intentInfo=new Intent(ActivityDeomActivity.this,InfoActivity.class);
                startActivity(intentInfo);
                break;
            case R.id.bt_p3:
                //显式Intent
                sendSimpleData1();
                break;
            case R.id.bt_p4:
                sendSimpleData2();
                break;
            case R.id.bt_p5:
                sendSimpleData2();
                break;
            case R.id.bt_p6:
                sendSimpleData2();
                break;
            case R.id.bt_p7:
                sendSimpleData2();
                break;
            case R.id.bt_p8:
                sendSimpleData2();
                break;
            case R.id.bt_p9:
                sendSimpleData2();
                break;
            case R.id.bt_p10:
                sendSimpleData2();
                break;
        }

    }

    //传递基本数据类型的数据，方法1
    private void sendSimpleData1() {
        Intent intent=new Intent(ActivityDeomActivity.this,IntentActivity.class);
        intent.putExtra("flag",1);
        intent.putExtra("version",1.0f);
        startActivity(intent);
    }
    //传递基本数据类型的数据，方法2
    private void sendSimpleData2() {
        Intent intent=new Intent(ActivityDeomActivity.this,IntentActivity.class);
        Bundle bundle=new Bundle();
        bundle.putFloat("version",2.0f);
        bundle.putString("activity","ActivityDemoAvtivity");
        intent.putExtras(bundle);
        intent.putExtra("flag",2);
        startActivity(intent);
    }
}
