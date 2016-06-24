package com.example.hp.healthandbeauty.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.healthandbeauty.Model.Face;
import com.example.hp.healthandbeauty.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP on 3/25/2016.
 */
public class FaceAdapter extends ArrayAdapter<Face> {
    ArrayList<Face>Arraylistface;
    Context context;
    LayoutInflater lf;
    int Resource;
     //int[] imgid;
    public FaceAdapter(Context context, int resource, ArrayList<Face> objects) {
        super(context, resource, objects);
        this.context=context;
        this.Resource=resource;
        Arraylistface=objects;
      //  this.imgid=imgid;

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
        Face face=Arraylistface.get(position);
        Picasso.with(context).load(face.getImage()).into(holder.imge);
     //   new DownloadImageTask(holder.imge).execute(face.getImage());
        holder.txttitle.setText(face.getTitle());

        String des = face.getDescription();
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
