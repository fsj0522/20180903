package com.example.pc.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

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
                    value="version: "+version+" \nactivityName: "+activityName;
                }
                break;
            case 3:
                int[] value1=intent.getIntArrayExtra("int_array");
                value="int_array: ";
                for(int i:value1){
                    value+= i+",";
                }
                value=value.substring(0,value.length()-1);

                bundle=intent.getExtras();
                if (bundle!=null){
                    int[] value2=bundle.getIntArray("another_array");
//                    for(int i=0;i<=value2.length;i++){
//
//                    }
                    value="int_array: ";
                    for(int i:value2){
                        value+= i+",";
                    }
                    value+="\nanother_array: "+value.substring(0,value.length()-1);
                }
                break;
            case 4:
                User user= (User) intent.getSerializableExtra("user");
                value="user: "+ user;

                bundle=intent.getExtras();
                if(bundle!=null){
                    User anotherUser= (User) bundle.getSerializable("another_user");
                    value +="\nanotherUser："+anotherUser;
                }
                break;
            case 5:
                bundle=intent.getExtras();
                if(bundle!=null) {
                    List<User> users = (List<User>) intent.getSerializableExtra("another_user");
                    value+="anotherUser："+users;
                }
                break;
            case 6:
                String name=intent.getStringExtra("name");
                String result=name+",有"; //“有”这个值是通过Button获取的
                intent.putExtra("result data",result);
                setResult(Activity.RESULT_OK,intent);
                finish();
                break;
        }
        tvData.setText(value);
    }
}
