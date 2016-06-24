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

/**
 * Created by HP on 4/8/2016.
 */
public class Fav_deatail extends Activity {
    TextView  txtTitle, txtDes;
    ImageView imageView,loveimg;
    String title, descr,img,id;
    DBModel dbModel;
    DBAdapter dbAdapter;
    Button back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_detail);
        back=(Button)findViewById(R.id.fback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        txtTitle = (TextView) findViewById(R.id.txttitle);
        txtDes = (TextView) findViewById(R.id.txtdes);
        imageView=(ImageView)findViewById(R.id.img);
        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        img = intent.getStringExtra("image");
        Picasso.with(this).load(img).into(imageView);
        title = intent.getStringExtra("title");
        txtTitle.setText(title);
        descr = intent.getStringExtra("description");
        txtDes.setText(descr);
        dbAdapter = new DBAdapter(this);
        dbModel = new DBModel();
        dbModel.setID(id);
        loveimg = (ImageView) findViewById(R.id.love);
        loveimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loveimg.setImageResource(R.drawable.unfav);
                Log.d("mylog", "change image" + R.drawable.unfav);
                dbAdapter.deletedb(dbModel);
            }
        });
    }
}

