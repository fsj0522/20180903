package com.example.pc.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;


public class ToolbarActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        toolbar=findViewById(R.id.my_toolbar);

        //给toolbar加载菜单
        setSupportActionBar(toolbar);//需要重写onCreatOptionMenu()和onOptionItemSelected()
        toolbar.inflateMenu(R.menu.demo);//需要设置菜单选择的事件监听器
        toolbar.setTitle("详情页");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setLogo(R.mipmap.ic_notifications);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_search:
                        Toast.makeText(ToolbarActivity.this,"toolbar search",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}
