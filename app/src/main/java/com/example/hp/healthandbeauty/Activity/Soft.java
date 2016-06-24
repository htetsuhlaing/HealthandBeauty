package com.example.hp.healthandbeauty.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.healthandbeauty.Adapter.DBAdapter;
import com.example.hp.healthandbeauty.Model.DBModel;
import com.example.hp.healthandbeauty.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;

/**
 * Created by HP on 3/30/2016.
 */
public class Soft extends Activity {
    TextView txtTitle, txtDes;
    ImageView imageView,loveimg;
    String title,descr,img,jsonid,tablefilter;
    DBAdapter dbAdapter;
    DBModel dbModel;
    Button back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft);
        txtTitle=(TextView)findViewById(R.id.txttitle);
        txtDes=(TextView)findViewById(R.id.txtdes);
        imageView=(ImageView)findViewById(R.id.img);
        back=(Button)findViewById(R.id.fback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        jsonid = intent.getStringExtra("id");
        Log.d("mylog", "jid " + jsonid);
        img=intent.getStringExtra("image");
        Picasso.with(this).load(img).into(imageView);

        title=intent.getStringExtra("title");
        txtTitle.setText(title);
        descr=intent.getStringExtra("description");
        txtDes.setText(descr);
        tablefilter = intent.getStringExtra("filter");
        Log.d("mylog", "table " + tablefilter);

        loveimg = (ImageView) findViewById(R.id.love);

        dbAdapter = new DBAdapter(this);
        dbModel = new DBModel(this);

        try {
            dbAdapter.getData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        loveimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loveimg.setImageResource(R.drawable.heart);
                Log.d("mylog", "change image");

                dbModel.setJID(jsonid);
                dbModel.setTitle(title);
                dbModel.setDescription(descr);
                dbModel.setImage(img);
                //  dbModel.setDate(calendar.getTime());
                Log.d("mylog", "image :" + img);
                dbModel.setFilter(tablefilter);

                try {
                    dbAdapter.insert(dbModel);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
