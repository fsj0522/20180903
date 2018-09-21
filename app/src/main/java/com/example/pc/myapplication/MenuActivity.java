package com.example.pc.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_info:
                Toast.makeText(MenuActivity.this,"个人信息维护",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_setting:
                Toast.makeText(MenuActivity.this,"设置",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_question:
                Toast.makeText(MenuActivity.this,"问卷调查",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_about:
                Toast.makeText(MenuActivity.this,"关于",
                        Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
