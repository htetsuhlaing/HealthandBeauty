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

import com.example.hp.healthandbeauty.Adapter.HairAdapter;
import com.example.hp.healthandbeauty.Model.Hair;
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
 * Created by HP on 3/29/2016.
 */
public class HairActivity extends Activity {
    ListView listView;
    ArrayList<Hair>hairlist;
    Button back;
//    int[]imgid=new int[]{R.drawable.hair1,R.drawable.hair2,R.drawable.hair3,R.drawable.hair4};
    String url;
    String filter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hair_list);
        back=(Button)findViewById(R.id.fback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        filter=getIntent().getExtras().getString("filter");
        url = getIntent().getExtras().getString("url");
        listView=(ListView)findViewById(R.id.hairlist);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Soft.class);
                intent.putExtra("id",hairlist.get(position).getId());
                intent.putExtra("image",hairlist.get(position).getImage());
                intent.putExtra("title", hairlist.get(position).getTitle());
                intent.putExtra("description", hairlist.get(position).getDescription());
                intent.putExtra("filter",filter);
                startActivity(intent);

            }
        });
        hairlist=new ArrayList<Hair>();

        new HairAsyncTask().execute();

    }
    public class HairAsyncTask extends AsyncTask<String,Void,Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {
            //return null;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = null;
            try{
                response = client.newCall(request).execute();
                String json = response.body().string();
                Log.d("mylog","hair "+json);
                JSONObject jsonObj = new JSONObject(json);
                Log.d("myLog","obj "+jsonObj);
                JSONArray jArray=jsonObj.getJSONArray("hair");
                Log.d("mylog","array "+jArray);

                for(int i=0;i<jArray.length();i++){
                    JSONObject jRealObject=jArray.getJSONObject(i);
                    Log.d("mylog","ljieuj"+jRealObject);

                    hairlist.add(new Hair(jRealObject.getString("id"),jRealObject.getString("image"),jRealObject.getString("title"),jRealObject.getString("description")));
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
                final HairAdapter adapter=new HairAdapter(getApplicationContext(),R.layout.hair_row,hairlist);
                listView.setAdapter(adapter);

            }
        }
    }
}
