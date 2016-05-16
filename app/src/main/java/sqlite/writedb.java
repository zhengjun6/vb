package sqlite;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.vb.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/5/14.
 */
public class writedb {
    private Context context;
    public writedb(Context context){
        this.context=context;
    }
    public void wr(String db){

            File file=new File("/data/data/com.example.administrator.vb/databases/hehe.db");
        if(file.exists()==false){
            try {
                InputStream is = context.getResources().openRawResource(R.raw.hehe);
                OutputStream out=new FileOutputStream(file);
                int len;
                byte b[]=new byte[1024];
                while((len=is.read(b))!=-1){
                    out.write(b, 0, len);
                    out.flush();
                }

                Toast.makeText(context,"gg",Toast.LENGTH_SHORT).show();
                Log.d("path",file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context,"haha",Toast.LENGTH_SHORT).show();
            }}
//            FileOutputStream fos = null;
//            try {
//                fos = new FileOutputStream(db);
//                byte[] buffer = new byte[1024];
//
//                int count = 0;
//
//                while ((count = is.read(buffer)) > 0) {
//
//                    fos.write(buffer, 0, count);
//
//                }
//                fos.flush();
//                fos.close();
//
//                is.close();
//                Toast.makeText(context,"succ",Toast.LENGTH_SHORT).show();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }




    }

}
