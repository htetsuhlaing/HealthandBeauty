package com.example.hp.healthandbeauty.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.healthandbeauty.R;

import java.util.Random;

/**
 * Created by HP on 3/24/2016.
 */
public class SplashSceen extends Activity {
    private TextView txt;
    private ImageView imgWallpaper;

    int[]wallpaper={R.drawable.ss1,R.drawable.ss2,R.drawable.ss3,R.drawable.ss4,R.drawable.ss5,R.drawable.ss6,R.drawable.ss7,R.drawable.ss8};
    Typeface typeface;
    Animation animation;
    Thread splashTread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        typeface = Typeface.createFromAsset(getAssets(), "fonts/zawgyi.ttf");
        String[] text = getResources().getStringArray(R.array.word);
        txt = (TextView) this.findViewById(R.id.txt);
        txt.setTypeface(typeface);
        imgWallpaper = (ImageView) this.findViewById(R.id.bg);
        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Random r = new Random();
        imgWallpaper.setImageResource(wallpaper[r.nextInt(8)]);
        txt.setText(text[r.nextInt(8)]);

        txt.setAnimation(animation);
splashTread=new Thread(){
    @Override
    public void run() {
       // super.run();
        try {
            int waited=0;
            while (waited<3500){
                sleep(100);
                waited += 100;
            }
            Intent intent = new Intent(SplashSceen.this,
                    MainActivity.class);
            startActivity(intent);
            SplashSceen.this.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            SplashSceen.this.finish();
        }
    }
};
        splashTread.start();

    }
}
