package com.example.pc.myapplication;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvResult;
    private TextView tvFomula;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        tvResult = findViewById(R.id.et_result);
        tvFomula = findViewById(R.id.et_input);
        text = "";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zero:
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
            case R.id.dot:
            case R.id.subtract:
            case R.id.add:
            case R.id.mutiplication:
            case R.id.division:
                //将Button的text加到fomula的最后
                Button btn = (Button) view;
                text += btn.getText().toString();
                tvFomula.setText(text);
                break;
            case R.id.reZero:
                text = "";
                tvFomula.setText("");
                tvResult.setText("");
                break;
            case R.id.delete:
                text = tvFomula.getText().toString();
                if (text.length() >= 1) {
                    tvFomula.setText(text.substring(0, text.length() - 1));
                }
                break;
            case R.id.equal:
                //检验fomula是否是一个合理的表达式
                String regex = "^(-)?\\d+(.\\d+)?[+\\-*/]\\d+(.\\d+)?";//正则表达式
                text = tvFomula.getText().toString();
                if (!TextUtils.isEmpty(text) && text.matches(regex)) {
                    try {
                        Symbols symbols = new Symbols();
                        double res = symbols.eval(text);
                        tvResult.setText(String.valueOf(res));
                    } catch (SyntaxException e) {
                        e.printStackTrace();
                        Toast.makeText(this, text + "语法错误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, text + "不是合法的", Toast.LENGTH_SHORT).show();
                }
                text = "";
                break;
        }
    }

    //横竖屏切换：先保存数据 然后恢复数据
    //保存数据的方法
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String fomula = tvFomula.getText().toString();
        String data = tvResult.getText().toString();

        outState.putString("fomula", fomula);
        outState.putString("result", data);
    }
    //恢复数据的方法

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String fomula=savedInstanceState.getString("fomula");
        String data=savedInstanceState.getString("result");

        tvFomula.setText(fomula);
        tvResult.setText(data);
    }
}