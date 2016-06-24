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

import com.example.hp.healthandbeauty.Adapter.YogaAdapter;
import com.example.hp.healthandbeauty.Model.Yoga;
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
 * Created by HP on 3/30/2016.
 */
public class YogaActivity extends Activity {
    ListView listView;
    ArrayList<Yoga>yogalist;
    String url;
    String filter;
    Button back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yoga_list);
        back=(Button)findViewById(R.id.fback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        filter=getIntent().getExtras().getString("filter");
        url = getIntent().getExtras().getString("url");
       listView=(ListView)findViewById(R.id.yogalist);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Yogadetail.class);

                intent.putExtra("id",yogalist.get(position).getId());
                intent.putExtra("image",yogalist.get(position).getImage());

                intent.putExtra("title", yogalist.get(position).getTitle());
                intent.putExtra("description", yogalist.get(position).getDescription());
                intent.putExtra("filter",filter);
                startActivity(intent);
            }
        });
        yogalist=new ArrayList<Yoga>();
        new YogaAsyncTask().execute();
    }
    public class YogaAsyncTask extends AsyncTask<String,Void,Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                String json = response.body().string();
                Log.d("mylog","yoga "+json);
                JSONObject jsonObj = new JSONObject(json);
                Log.d("myLog","obj "+jsonObj);
                JSONArray jArray=jsonObj.getJSONArray("yoga");
                Log.d("mylog","array "+jArray);

                for(int i=0;i<jArray.length();i++){
                    JSONObject jRealObject=jArray.getJSONObject(i);
                    Log.d("mylog","ljieuj"+jRealObject);
                    yogalist.add(new Yoga(jRealObject.getString("id"),jRealObject.getString("image"),jRealObject.getString("title"),jRealObject.getString("description")));
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
                YogaAdapter adapter=new YogaAdapter(getApplicationContext(),R.layout.yoga_row,yogalist);
                listView.setAdapter(adapter);
            }
        }
    }
}
