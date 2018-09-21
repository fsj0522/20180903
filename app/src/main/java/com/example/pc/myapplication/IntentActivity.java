package com.example.pc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IntentActivity extends AppCompatActivity {

    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        tvData=findViewById(R.id.tv_value);

        // 获取ActivityDemoActivity的Intent传递的数据
        Intent intent=getIntent();
        int flag=intent.getIntExtra("flag",1);
        String value="";
        switch (flag){
            case 1:
                float version=intent.getFloatExtra("version",0.0f);
                value="version :"+String.valueOf(version);
                break;
            case 2:
                Bundle bundle=intent.getExtras();
                if(bundle != null){
                    version=bundle.getFloat("version");
                    String activityName=bundle.getString("activity");
                    value="version: "+version+" activityName: "+activityName;
                }
                break;
        }
        tvData.setText(value);

    }
}
