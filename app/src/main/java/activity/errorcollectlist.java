package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.vb.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import  sqlite.*;
import adapter.*;

/**
 * Created by Administrator on 2016/5/15.
 */
public class errorcollectlist extends Activity implements AdapterView.OnItemClickListener{
    private ListView l;
    private List<data> list;
    private adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.errorcollect);
        l=(ListView)findViewById(R.id.listView);
        Intent intent=getIntent();
        if(intent.getStringExtra("key").equals("error")){
            operate op=new operate(this);
            list=op.data("ErrorKey");
        }
        else{
            operate op=new operate(this);
            list=op.data("Collect");
        }
        adapter=new adapter(this,list);
        l.setAdapter(adapter);
        l.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this,errorcollectliatcontent.class);
        Bundle b=new Bundle();
       // b.putSerializable("list", (Serializable) list);
       b.putParcelableArrayList("list", (ArrayList<data>) list);
        b.putInt("postion",position);
        intent.putExtras(b);
        startActivity(intent);

    }
}
