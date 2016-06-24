package com.example.hp.healthandbeauty.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.healthandbeauty.Model.DBModel;
import com.example.hp.healthandbeauty.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HP on 4/5/2016.
 */
public class MylistAdapter extends ArrayAdapter<DBModel> {
    List<DBModel> Arraylistdbmodel;
    Context context;
    LayoutInflater lf;

    public MylistAdapter(Context context, List<DBModel> objects) {
        super(context, 0, objects);
        this.context=context;
        Arraylistdbmodel=objects;
        lf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = lf.inflate(R.layout.fav_row, parent, false);
            holder=new ViewHolder();
         //   holder.txtdate=(TextView)convertView.findViewById(R.id.txtdate);
            holder.txttitle=(TextView)convertView.findViewById(R.id.txttitle);
            holder.txtdes=(TextView)convertView.findViewById(R.id.txtdes);
            holder.imge=(ImageView)convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        DBModel dbModel=Arraylistdbmodel.get(position);
        Picasso.with(context).load(dbModel.getImage()).into(holder.imge);
      // holder.txtdate.setText(AppConstant.formatter.format(dbModel.getDate()));

        holder.txttitle.setText(dbModel.getTitle());
      //  holder.txtdes.setText(dbModel.getDescription());

        String des = dbModel.getDescription();
        des = des.substring(0,30);
        des = des + "continue reading";
        holder.txtdes.setText(des);
       return convertView;
    }
    public class ViewHolder{
      //  private TextView txtdate;
        public TextView txttitle;
        public TextView txtdes;
        public ImageView imge;
    }

}
