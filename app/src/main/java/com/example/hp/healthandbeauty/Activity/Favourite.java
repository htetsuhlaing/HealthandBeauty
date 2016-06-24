package com.example.hp.healthandbeauty.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.hp.healthandbeauty.Adapter.DBAdapter;
import com.example.hp.healthandbeauty.Adapter.MylistAdapter;
import com.example.hp.healthandbeauty.R;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by HP on 4/4/2016.
 */
public class Favourite extends AppCompatActivity{
    ListView listView;
    Button back;
    ArrayList<MylistAdapter> dbmodellist;

MylistAdapter adapter;
    DBAdapter dbAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourite);
        back=(Button)findViewById(R.id.fback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
       listView=(ListView)findViewById(R.id.favlist);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),Fav_deatail.class);

                try {
                    intent.putExtra("id",dbAdapter.getData().get(position).getID());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    intent.putExtra("title",dbAdapter.getData().get(position).getTitle());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    intent.putExtra("image",dbAdapter.getData().get(position).getImage());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    intent.putExtra("description",dbAdapter.getData().get(position).getDescription());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                startActivity(intent);

            }
        });
        dbAdapter = new DBAdapter(this);
        dbmodellist=new ArrayList<>();


//        MylistAdapter adapter= null;
//        try {
//            dbmodellist.add((DBModel) dbAdapter.getData());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        try {
       MylistAdapter adapter = new MylistAdapter(getApplicationContext(),dbAdapter.getData());
            listView.setAdapter(adapter);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.d("mylog","get data "+listView);
       // listView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            MylistAdapter adapter=new MylistAdapter(getApplicationContext(),dbAdapter.getData());
            adapter.notifyDataSetChanged();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
