package com.example.pc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private ImageView img_head;
//    private Button btn_previous;
//    private Button btn_next;
    //1.定义要使用的控件
    private EditText uname;
    private EditText psd;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = findViewById(R.id.name);
        psd=findViewById(R.id.password);
//        img_head=findViewById(R.id.img_head);
//        btn_previous=findViewById(R.id.btn_previous);
//        btn_next=findViewById(R.id.btn_next);
//        btn_previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(v.getId()==R.id.btn_previous){
//
//                }
//            }
//        });
        // 2. 获取R.layout.activity_main已经定义的控件对象
        loginBtn=findViewById(R.id.loginBtn);

        //3. 设置按钮的监听事件
        loginBtn.setOnClickListener(this);


    }

    //3.1 内部匿名类
    public void showToast(View view){
        Toast.makeText(this,"跳转中",Toast.LENGTH_SHORT).show();
    }

    //3.2 activity实现接口 重写onClick
    //4. 处理监听事件
    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this,"登陆中",Toast.LENGTH_SHORT).show();
        switch (v.getId()){
            case R.id.loginBtn:
                String name=uname.getText().toString();
                String pasd=psd.getText().toString();
                login(name,pasd);
                break;
            case R.id.regBtn:
                register();
                break;
        }

    }

    //处理登陆
    private void login(String name,String password) {

        if("niit".equals(name) && "111".equals(password)){
            Intent intentLogin=new Intent(MainActivity.this,InfoActivity.class);
            intentLogin.putExtra("username",name);
            startActivity(intentLogin);
        }else{
            Toast.makeText(this,"用户名或密码不正确",Toast.LENGTH_LONG).show();
        }

    }

    //处理注册
    private void register() {
    }
}
