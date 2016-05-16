package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.vb.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/15.
 */
public class adapter extends BaseAdapter {
    private Context context;
    private List<data> list;
    public adapter(Context context,List<data> list){
        this.context=context;
        this.list=list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
        viewholder vh;
        if(convertView==null){
            view= LayoutInflater.from(context).inflate(R.layout.listtitle,null);
            vh=new viewholder();
            vh.t3=(TextView)view.findViewById(R.id.t3);
            view.setTag(vh);

        }
        else{
                view=convertView;
            vh=(viewholder)view.getTag();

        }
        if(list.get(position).getTitle().length()<15) {
            vh.t3.setText(list.get(position).getTitle());
        }
        else{
            vh.t3.setText(list.get(position).getTitle().substring(0,14)+"........");
        }
        return view;
    }
    class viewholder{
            private TextView t3;
    }
}
