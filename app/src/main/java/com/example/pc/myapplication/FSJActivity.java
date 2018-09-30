package com.example.pc.myapplication;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FSJActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private TextView tv_result;
    private Button btn_in;
    private Button btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsj);

        name=findViewById(R.id.et_name);
        tv_result=findViewById(R.id.tv_result);
        btn_in=findViewById(R.id.btn_in);
        btn_exit=findViewById(R.id.btn_exit);

        btn_in.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_in:
                in();
                break;
            case R.id.btn_exit:
                exit();
                break;
        }
    }

    private void in() {
        Intent intent=new Intent(FSJActivity.this,FSJQuestionActivity.class);

        if(TextUtils.isEmpty(name.getText().toString())){
            Toast.makeText(FSJActivity.this,"输入姓名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        intent.putExtra("name",name.getText().toString().trim());
        startActivityForResult(intent,1000);

    }

    private void exit() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == 1001 && requestCode==1000){
            String result_value=data.getStringExtra("result");
            //将结果显示到相应的TextView
            tv_result.setText("评论内容返回为："+result_value);
        }
    }

}
