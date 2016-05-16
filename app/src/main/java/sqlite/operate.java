package sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import adapter.data;

/**
 * Created by Administrator on 2016/5/14.
 */
public class operate  {
    private sqlite sql;
    private SQLiteDatabase read,write;
    private Map<String,Object> hashMap;
    private List<data> list;
    private data data;
    private Context context;

    public operate(Context context){
        this.context=context;

    }
    //查找数据库的长度
    public  int len(){

        sql=new sqlite(context);
        read=sql.getReadableDatabase();
        Cursor c=read.rawQuery("select * from  [2014]",null);

            return  c.getCount();

    }

    public Map<String, Object> select(int id, String table){
        sql=new sqlite(context);
        read=sql.getReadableDatabase();
        Cursor c=read.rawQuery("select * from [2014] where id= ?",new String[]{""+id});
        hashMap=new HashMap<>();
        while(c.moveToNext()){
            hashMap.put("id",c.getInt(0));
            hashMap.put("title",c.getString(2).toString().trim());
            hashMap.put("a",c.getString(8));
            hashMap.put("b",c.getString(9));
            hashMap.put("c",c.getString(10));
            hashMap.put("d",c.getString(11));
            hashMap.put("answer",c.getString(6));


        }
        return hashMap;
    }
    //查找错题集
    public List<data> data(String name){
        sql=new sqlite(context);
        read=sql.getReadableDatabase();
        Cursor c=read.rawQuery("select * from [2014] where "+ name+"= ?",new String[]{""+1});
        list=new ArrayList<>();
        while(c.moveToNext()){
           data=new data(c.getInt(0),c.getString(2));

            list.add(data);
        }
        return list;
    }
    public void update(int id,String answer){
        select(id,"[2014");
        sql=new sqlite(context);
        write=sql.getWritableDatabase();
        if(hashMap.get("answer").equals(answer)){
            ContentValues c=new ContentValues();
            c.put("ErrorKey",0);
            write.update("[2014]",c,"id=?",new String[]{""+id});
        }
        else{
            ContentValues c=new ContentValues();
            c.put("ErrorKey",1);
            write.update("[2014]",c,"id=?",new String[]{""+id});
        }
        write.close();

    }
    public  void clean(){
        sql=new sqlite(context);
        write=sql.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("ErrorKey",0);
        c.put("Collect",0);
        write.update("[2014]",c,null,null);
        Toast.makeText(context,"clean success",Toast.LENGTH_SHORT).show();


    }


}
