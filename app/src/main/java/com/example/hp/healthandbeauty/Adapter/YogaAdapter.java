package com.example.hp.healthandbeauty.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.healthandbeauty.Model.Yoga;
import com.example.hp.healthandbeauty.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP on 3/31/2016.
 */
public class YogaAdapter extends ArrayAdapter<Yoga> {
    Context context;
    LayoutInflater lf;
    ArrayList<Yoga>Arraylistyoga;
    int Resource;
    public YogaAdapter(Context context, int resource,  ArrayList<Yoga> objects) {
        super(context, resource, objects);
        this.context=context;
        this.Resource=resource;
        Arraylistyoga=objects;

        lf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
        if(convertView==null){
            convertView=lf.inflate(Resource,null);
            holder=new ViewHolder();
            holder.txttitle=(TextView)convertView.findViewById(R.id.txttitle);
            holder.txtdes=(TextView)convertView.findViewById(R.id.txtdes);
            holder.imge=(ImageView)convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        Yoga yoga=Arraylistyoga.get(position);
        Picasso.with(context).load(yoga.getImage()).into(holder.imge);

        holder.txttitle.setText(yoga.getTitle());
        String des = yoga.getDescription();
        des = des.substring(0,30);
        des = des + "continue reading";
        holder.txtdes.setText(des);
        return convertView;
    }

    public class ViewHolder{
        public TextView txttitle;
        public TextView txtdes;
        public ImageView imge;
    }
}
