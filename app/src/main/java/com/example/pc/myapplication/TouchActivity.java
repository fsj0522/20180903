package com.example.pc.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TouchActivity extends AppCompatActivity implements View.OnTouchListener {

    private TextView textView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        imageView=findViewById(R.id.image);
        textView=findViewById(R.id.text_view);
        imageView.setOnTouchListener(this);
        //不要在onCreat()获取控件坐标
    }

    private float deltaX;
    private float deltaY;
    @SuppressLint("StringFormatMatches")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        /* 当触屏按下的时候计算所在点到位置X Y
         * 当初点移动的时候 计算与按下时的位置的偏移，将图片的参数LayoutParams更改为移动后的偏移
         * 重绘图片
         * 更好的体验，让图片在可视区域显示
         * 先获得可视区域的大小 然后判断
         */
        Rect rect=new Rect();
        getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(rect);

        RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) v.getLayoutParams();
        float x=event.getRawX();
        float y=event.getRawY();
        //更新坐标的值显示在TextView
        //textView.setText("坐标位置："+x+","+y);
        textView.setText(getResources().getString(R.string.location,x,y));
        //textView.setText(String.format(getResources().getString(R.string.location),x,y));
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                deltaX=x-params.leftMargin;
                deltaY=y-params.topMargin;
                break;
            case MotionEvent.ACTION_MOVE:
                float distanceX=x-deltaX;
                float distanceY=y-deltaY;
                //判断是否超出边界
                if(distanceX>=rect.left && distanceX<=rect.right && distanceY>=rect.top
                        && distanceY<=rect.bottom){
                    params.leftMargin= (int) distanceX;
                    params.topMargin= (int) distanceY;
                    v.setLayoutParams(params);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        v.invalidate();
        return true;
    }

    private long startTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis()-startTime>2000){
                Toast.makeText(TouchActivity.this,"再按一次退出",Toast.LENGTH_SHORT).show();
                startTime=System.currentTimeMillis();
            }else{
                finish();
                System.exit(0);
            }
        }
        return true;
    }
}
