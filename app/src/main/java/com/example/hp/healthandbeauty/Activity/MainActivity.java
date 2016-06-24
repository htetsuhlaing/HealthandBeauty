package com.example.hp.healthandbeauty.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hp.healthandbeauty.R;
import com.example.hp.healthandbeauty.app.AppConstant;


public class MainActivity extends AppCompatActivity {
    Button Face,Skin,Hair,yoga,health,favourite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Face=(Button)findViewById(R.id.face);
        Face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), FaceActivity.class);
                intent.putExtra("url", AppConstant.Face_Url);
                intent.putExtra("filter",AppConstant.Face_Table);
                startActivity(intent);
            }
        });
        Skin=(Button)findViewById(R.id.skin);
        Skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SkinActivity.class);
                intent.putExtra("url", AppConstant.Skin_Url);
                intent.putExtra("filter",AppConstant.Skin_Table);

                startActivity(intent);

                    }
                });
        Hair=(Button)findViewById(R.id.hair);
        Hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HairActivity.class);
                intent.putExtra("url",AppConstant.Hair_Url);
                intent.putExtra("filter",AppConstant.Hair_Table);

                startActivity(intent);
            }
        });
        yoga=(Button)findViewById(R.id.yoga);
        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), YogaActivity.class);
                intent.putExtra("url", AppConstant.Yoga_Url);
                intent.putExtra("filter",AppConstant.Yoga_Table);

                startActivity(intent);
            }
        });

health=(Button)findViewById(R.id.health);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HealthtipActivity.class);
                intent.putExtra("url", AppConstant.Health_Url);
                intent.putExtra("filter",AppConstant.Health_Table);

                startActivity(intent);
            }
        });
        favourite=(Button)findViewById(R.id.fav);
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Favourite.class);
                startActivity(intent);
            }
        });

            }


        }

