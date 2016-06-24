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

import com.example.hp.healthandbeauty.Adapter.FaceAdapter;
import com.example.hp.healthandbeauty.Model.Face;
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
 * Created by HP on 3/25/2016.
 */
public class FaceActivity extends Activity {
    ListView listView;
    ArrayList<Face>facelist;
    FaceAdapter adapter;
Button back;
  // int[]imgid=new int[]{R.drawable.ace,R.drawable.natural2,R.drawable.milasama,R.drawable.scar};
    String filter;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face_list);
        back=(Button)findViewById(R.id.fback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        filter=getIntent().getExtras().getString("filter");
        url = getIntent().getExtras().getString("url");
        listView=(ListView)findViewById(R.id.facelist);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Ace.class);
                intent.putExtra("id",facelist.get(position).getId());
                intent.putExtra("image",facelist.get(position).getImage());

                intent.putExtra("title", facelist.get(position).getTitle());
                intent.putExtra("description", facelist.get(position).getDescription());
                intent.putExtra("filter",filter);
                startActivity(intent);
            }
        });
        facelist=new ArrayList<Face>();
        new FaceAsyncTask().execute();
    }
    public class FaceAsyncTask extends AsyncTask<String,Void,Boolean>{
        @Override
        protected Boolean doInBackground(String... params) {
            Log.d("mylog","jkj");
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                String json = response.body().string();
                Log.d("mylog","face "+json);
                JSONObject jsonObj = new JSONObject(json);
                Log.d("myLog","obj "+jsonObj);
                JSONArray jArray=jsonObj.getJSONArray("face");
                Log.d("mylog","array "+jArray);
                for(int i=0;i<jArray.length();i++){
                    JSONObject jRealObject=jArray.getJSONObject(i);
                    Log.d("mylog","ljieuj"+jRealObject);
                    facelist.add(new Face(jRealObject.getString("id"),jRealObject.getString("image"),jRealObject.getString("title"),jRealObject.getString("description")));
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
            Log.d("mylog", "ok");
            super.onPostExecute(result);
            if (result==false){
                final FaceAdapter adapter=new FaceAdapter(getApplicationContext(),R.layout.face_row,facelist);
                listView.setAdapter(adapter);

            }
        }
    }
}
