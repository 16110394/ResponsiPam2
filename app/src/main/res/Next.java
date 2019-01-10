package com.amikom.thesaint;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Next extends AppCompatActivity {

    private ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next);

        listView = (ListView) findViewById(R.id.list);
        new CheckConnectionStatus().execute("https://www.thesportsdb.com/api/v1/json/1/eventsnext.php?id=134778");
    }

    class CheckConnectionStatus extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            try{
                url = new URL(params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try{
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String s = bufferedReader.readLine();
                bufferedReader.close();

                return s;
            }catch (IOException e) {
                Log.e("Error: ", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONObject jsonObject = null;

            try {
                jsonObject = new JSONObject(s);

                ArrayList<com.amikom.thesaint.NextDetails> nextList = new ArrayList<>();

                JSONArray jsonArray = jsonObject.getJSONArray("events");

                for (int i=0; i<jsonArray.length();i++)
                {
                    JSONObject object = jsonArray.getJSONObject(i);
                    com.amikom.thesaint.NextDetails nextDetails = new com.amikom.thesaint.NextDetails();
                    nextDetails.setOriginal_title(object.getString("strLeague"));
                    nextDetails.setOverview(object.getString("dateEvent"));
                    nextDetails.setRelease_date(object.getString("strEvent"));
                    nextDetails.setPoster_path(object.getString("idEvent"));
                    nextList.add(nextDetails);
                }
                NextArrayAdapter nextArrayAdapter = new NextArrayAdapter(com.amikom.thesaint.Next.this, R.layout.next_daftar,nextList);
                listView.setAdapter(nextArrayAdapter);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }

    }
}
