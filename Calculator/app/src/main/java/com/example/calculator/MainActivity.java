package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import java.math.BigDecimal;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener{
    Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_poi;
    Button btn_mul,btn_div,btn_add,btn_sub;
    Button btn_del,btn_equ;
    TextView text_input;
    boolean poi_flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_0= (Button) findViewById(R.id.button_0);
        btn_1= (Button) findViewById(R.id.button_1);
        btn_2= (Button) findViewById(R.id.button_2);
        btn_3= (Button) findViewById(R.id.button_3);
        btn_4= (Button) findViewById(R.id.button_4);
        btn_5= (Button) findViewById(R.id.button_5);
        btn_6= (Button) findViewById(R.id.button_6);
        btn_7= (Button) findViewById(R.id.button_7);
        btn_8= (Button) findViewById(R.id.button_8);
        btn_9= (Button) findViewById(R.id.button_9);
        btn_poi=(Button) findViewById(R.id.button_point);
        btn_add= (Button) findViewById(R.id.button_add);
        btn_sub= (Button) findViewById(R.id.button_minus);
        btn_mul= (Button) findViewById(R.id.button_multiply);
        btn_div= (Button) findViewById(R.id.button_divided);
        btn_del=(Button) findViewById(R.id.button_delete);
        btn_equ=(Button) findViewById(R.id.button_equals);
        text_input= (TextView) findViewById(R.id.result);
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_poi.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_equ.setOnClickListener(this);
        btn_del.setOnLongClickListener(this);
    }
    @Override
    public boolean onLongClick(View v)
    {
        poi_flag=true;
        text_input.setText("");
        return true;
    }
    @Override
    public void onClick(View v) {
        String str=text_input.getText().toString(),tstr;
        switch (v.getId()) {
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
                text_input.setText(str+((Button)v).getText());
                break;
            case R.id.button_point:
                if(poi_flag)
                {
                    text_input.setText(str+".");
                    poi_flag=false;
                }
                break;
            case R.id.button_add:
            case R.id.button_divided:
            case R.id.button_multiply:
                if(str!=null&&!str.equals("")) {
                    if (str.contains("+") || str.contains("×") || str.contains("÷")) {
                        break;
                    } else if (str.substring(str.length() - 1, str.length() - 0).contains("-")) {
                        break;
                    }
                }
                poi_flag=true;
                text_input.setText(str+((Button)v).getText());
                break;
            case R.id.button_minus:
                if(str!=null&&!str.equals("")) {
                    poi_flag=true;
                    tstr=str.substring(str.length() - 1, str.length()-0);
                    if (tstr.equals("+")) {
                        str=str.substring(0, str.length() - 1);
                        text_input.setText(str+"-");
                    }
                    else if(tstr.equals("-"))
                    {
                        str=str.substring(0, str.length() - 1);
                        text_input.setText(str+"+");
                    }
                    else if(tstr.equals("÷")||tstr.equals("×"))
                    {
                        text_input.setText(str+((Button)v).getText());
                    }
                    else if(str.contains("+")||str.contains("×")||str.contains("÷"))
                    {
                        break;
                    }
                    else if(!str.substring(1,str.length()).contains("-"))
                    {
                        text_input.setText(str+((Button)v).getText());
                    }
                }
                else
                {
                    text_input.setText(str+((Button)v).getText());
                }
                break;
            case R.id.button_delete:
                if(str!=null&&!str.equals("")) {
                     tstr=str.substring(str.length() - 1, str.length());
                    if(tstr.equals("."))
                    {
                        poi_flag=true;
                    }
                    else if(tstr.equals("+")||tstr.equals("-")||tstr.equals("×")||tstr.equals("÷"))
                    {
                        if(str.contains(".")) poi_flag=false;
                        else poi_flag=true;
                    }
                    text_input.setText(str.substring(0, str.length() - 1));
                }
                break;
            case R.id.button_equals:
                getResult();
                if(str.contains(".")) poi_flag=false;
                else poi_flag=true;
                break;

        }
    }
    private void getResult() {
        String exp=text_input.getText().toString();
        if(exp==null||exp.equals("")) return ;
        //因为没有运算符所以不用运算
        String s1,op,s2;
        if(exp.contains("+"))
        {
            //截取运算符前面的字符串
            s1=exp.substring(0,exp.indexOf("+"));
            //截取的运算符
            op=exp.substring(exp.indexOf("+"),exp.indexOf("+")+1);
            //截取运算符后面的字符串
            s2=exp.substring(exp.indexOf("+")+1);
        }
        else if(exp.contains("×"))
        {
            //截取运算符前面的字符串
            s1=exp.substring(0,exp.indexOf("×"));
            //截取的运算符
            op=exp.substring(exp.indexOf("×"),exp.indexOf("×")+1);
            //截取运算符后面的字符串
            s2=exp.substring(exp.indexOf("×")+1);
        }
        else if(exp.contains("÷"))
        {
            //截取运算符前面的字符串
            s1=exp.substring(0,exp.indexOf("÷"));
            //截取的运算符
            op=exp.substring(exp.indexOf("÷"),exp.indexOf("÷")+1);
            //截取运算符后面的字符串
            s2=exp.substring(exp.indexOf("÷")+1);
        }
        else if(exp.contains("-"))
        {
            //截取运算符前面的字符串
            s1=exp.substring(0,exp.indexOf("-",1));
            //截取的运算符
            op=exp.substring(exp.indexOf("-",1),exp.indexOf("-",1)+1);
            //截取运算符后面的字符串
            s2=exp.substring(exp.indexOf("-",1)+1);
        }
        else
        {
            return;
        }
        double cnt=0;
        if(!s1.equals("")&&!s2.equals("")){
            double d1=Double.parseDouble(s1);
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=add(d1,d2);
            }
            if(op.equals("-")){
                cnt=sub(d1,d2);
            }
            if(op.equals("×")){
                cnt=mul(d1,d2);
            }
            if(op.equals("÷")){
                if(d2==0) cnt=0;
                else cnt=div(d1,d2,6);
            }
            if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷")) {
                int res = (int) cnt;
                text_input.setText(res+"");
            }else {
                text_input.setText(cnt+"");}
        }
        //如果s1是空    s2不是空  就执行下一步
        else if(!s1.equals("")&&s2.equals("")){
            double d1=Double.parseDouble(s1);
            if(op.equals("+")){
                cnt=d1;
            }
            if(op.equals("-")){
                cnt=d1;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!s1.contains(".")) {
                int res = (int) cnt;
                text_input.setText(res+"");
            }else {
                text_input.setText(cnt+"");}
        }
        //如果s1是空    s2不是空  就执行下一步
        else if(s1.equals("")&&!s2.equals("")){
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d2;
            }
            if(op.equals("-")){
                cnt=0-d2;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!s2.contains(".")) {
                int res = (int) cnt;
                text_input.setText(res+"");
            }else {
                text_input.setText(cnt+"");}
        }
        else {
            text_input.setText("");
        }
    }
    public static double add(double a1, double b1) {
        BigDecimal a2 = new BigDecimal(Double.toString(a1));
        BigDecimal b2 = new BigDecimal(Double.toString(b1));
        return a2.add(b2).doubleValue();
    }
    public static double sub(double a1, double b1) {
        BigDecimal a2 = new BigDecimal(Double.toString(a1));
        BigDecimal b2 = new BigDecimal(Double.toString(b1));
        return a2.subtract(b2).doubleValue();
    }
    public static double mul(double a1, double b1) {
        BigDecimal a2 = new BigDecimal(Double.toString(a1));
        BigDecimal b2 = new BigDecimal(Double.toString(b1));
        return a2.multiply(b2).doubleValue();
    }
    public static double div(double a1, double b1, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("error");
        }
        BigDecimal a2 = new BigDecimal(Double.toString(a1));
        BigDecimal b2 = new BigDecimal(Double.toString(b1));
        return a2.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
