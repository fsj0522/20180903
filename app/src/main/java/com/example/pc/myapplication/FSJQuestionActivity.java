package com.example.pc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FSJQuestionActivity extends AppCompatActivity  implements View.OnClickListener {

    private TextView question;
    private Button yes;
    private Button no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsjquestion);

        question=findViewById(R.id.tv_question);
        yes=findViewById(R.id.btn_yes);
        no=findViewById(R.id.btn_no);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        String name=getIntent().getStringExtra("name");
        String que=name+"同学：学习Android有没有信心？";
        question.setText(que);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_yes:
                Intent intent=new Intent();
                String result="有";
                intent.putExtra("result",result);
                setResult(1001,intent);
                finish();
                break;
            case R.id.btn_no:
                result="没有";
                intent=new Intent();
                intent.putExtra("result",result);
                setResult(1001,intent);
                finish();
                break;
        }

    }
}
