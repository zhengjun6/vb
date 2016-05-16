package activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.vb.R;

import java.util.HashMap;
import java.util.Map;

import sqlite.operate;
import touchlisten.touchlisten;

/**
 * Created by Administrator on 2016/5/15.
 */
public class imament extends Activity implements RadioGroup.OnCheckedChangeListener,View.OnTouchListener {
    private TextView title,time;
    private RadioButton ra,rb,rc,rd;
    private RadioGroup rg;
    private int length;
    private operate op;
    private String answer=null;//表示选择的答案
    private Map<String,Object> map=new HashMap<>();
   private  int id=0;
    private float distance;
    private int []question=new int[20];
    private int hour=0,min=0,second=0;
    private Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int a=msg.arg1;
            int b=msg.arg2;
//			int g=msg.what;
//			if(g>10){
//				pr.dismiss();
//			}
            if(a>9&&b>9){
                time.setText("时长： 0"+hour+":"+b+":"+a);

            }
            else if(a>9&&b<10){
                time.setText("时长： 0"+hour+":0"+b+":"+a);
            }
            else if(a<10&&b>9){
                time.setText("时长： 0"+hour+":"+b+":0"+a);
            }
            else{
                time.setText("时长： 0"+hour+":0"+b+":0"+a);
            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imanent);
        init();
        threa();
        length();
        show();

    }
    private void threa(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    second++;
                    if(second>60){
                        min++;second=0;
                    }
                    if(min>60){
                        hour++;
                        min=0;
                    }
                    try {
                        Thread.sleep(1000);
                        Message message=Message.obtain();
                        message.arg1=second;
                        message.arg2=min;
                        //message.what=kt;
                        h.sendMessage(message);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void show(){
        rg.clearCheck();;
        op=new operate(this);
        map=op.select(question[id],"[2014]");
        title.setText(id+1+"."+map.get("title").toString().trim());

        ra.setText(map.get("a").toString());
        rb.setText(map.get("b").toString());
        rc.setText(map.get("c").toString());
        rd.setText(map.get("d").toString());


    }

//获取数据库长度并随机分配20到题目
    private void length() {
        op=new operate(this);
        length=op.len();
        for(int i=0;i<20;i++){
            question[i]= (int) (1+Math.random()*length);
        }
    }

    private void init() {

        title=(TextView)findViewById(R.id.gettitle);
        time=(TextView)findViewById(R.id.time);
        ra=(RadioButton)findViewById(R.id.a);
        rb=(RadioButton)findViewById(R.id.b);
        rc=(RadioButton)findViewById(R.id.c);
        rd=(RadioButton)findViewById(R.id.d);
        rg=(RadioGroup)findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);


    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.a:
                answer="A";
                break;
            case R.id.b:
                answer="B";
                break;
            case R.id.c:
                answer="C";
                break;
            case R.id.d:
                answer="D";
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                distance=ev.getX();
                break;
            case MotionEvent.ACTION_UP:
                float d;
                d=ev.getX()-distance;
                if(Math.abs(d)>50){
                     op=new operate(this);
                    if(answer!=null){
                        op.update(question[id],answer);}
                    else{
                        op.update(question[id],"e");
                    }
                }
                touchlisten t=new touchlisten(this);
                id= t.postion(20,id,d);

                show();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}

