package com.example.hp.healthandbeauty.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.hp.healthandbeauty.Adapter.HealthAdapter;
import com.example.hp.healthandbeauty.Model.Healthtip;
import com.example.hp.healthandbeauty.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HP on 3/31/2016.
 */
public class HealthtipActivity extends Activity{
    ListView listView;
    ArrayList<Healthtip>healthlist;
    String url;
    String filter;
    Button back;
   // int[]imgid=new int[]{R.drawable.run,R.drawable.food,R.drawable.hhfood,R.drawable.clock,R.drawable.water,R.drawable.takhwar,R.drawable.low};
    HealthAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.healthtip_list);
        back=(Button)findViewById(R.id.fback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        filter=getIntent().getExtras().getString("filter");
        url = getIntent().getExtras().getString("url");
        listView=(ListView)findViewById(R.id.healthlist);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Health1.class);

                intent.putExtra("id",healthlist.get(position).getId());
                intent.putExtra("image",healthlist.get(position).getImage());
                intent.putExtra("title", healthlist.get(position).getTitle());
                intent.putExtra("description", healthlist.get(position).getDescription());
                intent.putExtra("filter",filter);
                startActivity(intent);
            }
        });
        healthlist=new ArrayList<Healthtip>();
        new HealthAsyncTask().execute();


    }
    public class HealthAsyncTask extends AsyncTask<String,Void,Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = null;

            try {
                response = client.newCall(request).execute();
                String json = response.body().string();
                Log.d("mylog","healthtip "+json);
                JSONObject jsonObj = new JSONObject(json);
                Log.d("myLog","obj "+jsonObj);
                JSONArray jArray=jsonObj.getJSONArray("healthtip");
                Log.d("mylog","array "+jArray);
                for(int i=0;i<jArray.length();i++){
                    JSONObject jRealObject=jArray.getJSONObject(i);
                    Log.d("mylog","ljieuj"+jRealObject);
                    healthlist.add(new Healthtip(jRealObject.getString("id"),jRealObject.getString("image"),jRealObject.getString("title"),jRealObject.getString("description")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if(result==false){
                final HealthAdapter adapter=new HealthAdapter(getApplicationContext(),R.layout.healthtip_row,healthlist);
                listView.setAdapter(adapter);

            }
        }
    }
}
