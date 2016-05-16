package touchlisten;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/5/15.
 */
public class touchlisten {
    private  Context context;
    public touchlisten(Context context){
        this.context=context;
    }
    //判断手指移动后，id的值是加还是减
    public int addor(float d,int id,int flag,int length){
        if(d<-50){
            if(flag==1){
            if( id<length){

            id++;}
            else {
                Toast.makeText(context,"已经是最后一题了",Toast.LENGTH_SHORT).show();
            }
        }
            else {
            id=1+(int)(Math.random()*length);
            }
        }

        if(d>50) {
            if (flag == 1) {
                if (id > 1) {
                    id--;
                } else {
                    Toast.makeText(context, "已经第一题了", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                id=1+(int)(Math.random()*length);
            }
        }
        return  id;
    }
    public int postion(int length,int postion,float d){
        if(d>50 && postion>0){
            postion--;
        }
        if(d<-50 && postion<length-1){
            postion++;
        }

        return  postion;
    }

}
