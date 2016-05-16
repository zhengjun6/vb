package activity;

import android.app.Activity;
import touchlisten.*;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.vb.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import sqlite.operate;
import sqlite.sqlite;

/**
 * Created by Administrator on 2016/5/14.
 */
public class sortrandom  extends Activity implements View.OnClickListener,View.OnTouchListener {

    private Button btncollect;
    private TextView title,tanswer,bgetanswer;
    private int sqlitelength;//数据库表的长度
    private RadioButton ra,rb,rc,rd;
    private int id=1;
    private int flag=1;//代表顺序或者随机练习
    private float distance;
    private static String table="[2014]";
    private Map<String,Object> map=new HashMap<>();
    private sqlite s;
    private SQLiteDatabase read;
    private operate op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sortrandom);
        init();
        Intent intent=getIntent();
        operate op=new operate(this);
        sqlitelength=op.len();
        //0代表随机练习，1代表顺序练习
       if(intent.getStringExtra("sort").equals("0")){
            flag=0;
           id=1+(int)(Math.random()*sqlitelength);
           Toast.makeText(this,""+id,Toast.LENGTH_SHORT).show();
       }





        show();
    }


    //显示sort内容
    private void show(){
        op=new operate(this);
        map=op.select(id,table);
        title.setText(""+map.get("id")+"."+map.get("title").toString().trim());
        Log.d("haha",""+map.get("id")+"."+map.get("title").toString().trim());
        ra.setText(map.get("a").toString());
        rb.setText(map.get("b").toString());
        rc.setText(map.get("c").toString());
        rd.setText(map.get("d").toString());


    }

//初始化
    private void init() {
        btncollect=(Button)findViewById(R.id.butcollect);
        bgetanswer=(TextView)findViewById(R.id.getanswer);
        title=(TextView)findViewById(R.id.gettitle);
        tanswer=(TextView)findViewById(R.id.answer);
        ra=(RadioButton)findViewById(R.id.a);
        rb=(RadioButton)findViewById(R.id.b);
        rc=(RadioButton)findViewById(R.id.c);
        rd=(RadioButton)findViewById(R.id.d);
       btncollect.setOnClickListener(this);
        bgetanswer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //显示答案
        switch (v.getId()){
            case R.id.getanswer:
                tanswer.setText(map.get("answer").toString());
                break;

            case R.id.butcollect:
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
               touchlisten  t=new touchlisten(this);
               id= t.addor(d,id,flag,sqlitelength);
             tanswer.setText("");
                show();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return true;
    }
}
