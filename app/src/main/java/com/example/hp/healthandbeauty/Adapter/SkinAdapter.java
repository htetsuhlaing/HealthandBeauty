package com.example.hp.healthandbeauty.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.healthandbeauty.Model.Skin;
import com.example.hp.healthandbeauty.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP on 3/28/2016.
 */
public class SkinAdapter extends ArrayAdapter<Skin> {
    ArrayList<Skin>ArraylistSkin;
    Context context;
    LayoutInflater lf;
    int Resource;

    public SkinAdapter(Context context, int resource, ArrayList<Skin> objects) {
        super(context, resource, objects);
        this.context=context;
        this.Resource=resource;
        ArraylistSkin=objects;


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
            holder.img=(ImageView)convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        Skin skin=ArraylistSkin.get(position);
        Picasso.with(context).load(skin.getImage()).into(holder.img);
        holder.txttitle.setText(skin.getTitle());

        String des = skin.getDescription();
        des = des.substring(0,30);
        des = des + "continue reading";
        holder.txtdes.setText(des);
        return convertView;

    }

    public class ViewHolder{
        public TextView txttitle;
        public TextView txtdes;
        public ImageView img;
    }

}
