package activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.vb.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.data;
import sqlite.operate;
import touchlisten.touchlisten;

/**
 * Created by Administrator on 2016/5/15.
 */
public class errorcollectliatcontent extends Activity implements View.OnTouchListener, View.OnClickListener {
    private Button btncollect;
    private float distance;
    private TextView title,tanswer,bgetanswer;
    private RadioButton ra,rb,rc,rd;
    private List<data> list=new ArrayList<>();
    private Map<String,Object> map=new HashMap<>();
    private int postion;
    private operate op;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.errorcollectcontent);
        Bundle b=getIntent().getExtras();
       list= b.getParcelableArrayList("list");
        postion=b.getInt("postion");
        operate op=new operate(this);
        init();
        show();
    }
    private void show(){

        op=new operate(this);
        map=op.select(list.get(postion).getId(),"[2014]");
        title.setText(""+map.get("id")+"."+map.get("title").toString().trim());
        Log.d("haha",""+map.get("id")+"."+map.get("title").toString().trim());
        ra.setText(map.get("a").toString());
        rb.setText(map.get("b").toString());
        rc.setText(map.get("c").toString());
        rd.setText(map.get("d").toString());


    }

    private void init() {
        btncollect=(Button)findViewById(R.id.butcollect);
        bgetanswer=(TextView)findViewById(R.id.getanswer);
        title=(TextView)findViewById(R.id.gettitle);
        tanswer=(TextView)findViewById(R.id.answer);
        ra=(RadioButton)findViewById(R.id.a);
        rb=(RadioButton)findViewById(R.id.b);
        rc=(RadioButton)findViewById(R.id.c);
        rd=(RadioButton)findViewById(R.id.d);

        bgetanswer.setOnClickListener(this);
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
                touchlisten t=new touchlisten(this);
               postion= t.postion(list.size(),postion,d);
                tanswer.setText("");
                show();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onClick(View v) {
        tanswer.setText(map.get("answer").toString());
    }
}
