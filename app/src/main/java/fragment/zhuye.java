package fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import activity.errorcollectlist;
import activity.imament;
import sqlite.*;

import com.example.administrator.vb.R;

import activity.sortrandom;

/**
 * Created by Administrator on 2016/5/14.
 */
public class zhuye extends Fragment implements View.OnClickListener {
    private View view;
    private TextView sort,random,error,collect,imanent,more;
    private sqlite s;
    private SQLiteDatabase read;
    private    Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.zhuye,container,false);
        init();
        return view;
    }

    private void init(){
        sort=(TextView)view.findViewById(R.id.sort);
        random=(TextView)view.findViewById(R.id.random);
        error=(TextView)view.findViewById(R.id.error);
        collect=(TextView)view.findViewById(R.id.collect);
        imanent=(TextView)view.findViewById(R.id.imament);
        more=(TextView)view.findViewById(R.id.more);
        sort.setOnClickListener(this);
        random.setOnClickListener(this);
        error.setOnClickListener(this);
        collect.setOnClickListener(this);
        imanent.setOnClickListener(this);
        more.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sort:

               intent=new Intent(getActivity(), sortrandom.class);
                intent.putExtra("sort","1");
                startActivity(intent);
                break;
            case R.id.random:
                 intent=new Intent(getActivity(), sortrandom.class);
                intent.putExtra("sort","0");
                startActivity(intent);
                break;
            case R.id.error:
                intent=new Intent(getActivity(), errorcollectlist.class);

                intent.putExtra("key","error");
                startActivity(intent);
                break;
            case R.id.collect:
                intent=new Intent(getActivity(), errorcollectlist.class);

                intent.putExtra("key","collect");
                startActivity(intent);
                break;
            case R.id.imament:
                intent=new Intent(getActivity(), imament.class);


                startActivity(intent);
                break;
            case R.id.more:
                break;


        }

    }

}
