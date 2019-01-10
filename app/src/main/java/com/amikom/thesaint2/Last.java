package com.amikom.thesaint2;

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

public class Last extends AppCompatActivity {

    private ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last);

        listView = (ListView) findViewById(R.id.list);
        new CheckConnectionStatus().execute("https://www.thesportsdb.com/api/v1/json/1/eventslast.php?id=134778");
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

                ArrayList<LastDetails> lastList = new ArrayList<>();

                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i=0; i<jsonArray.length();i++)
                {
                    JSONObject object = jsonArray.getJSONObject(i);
                    LastDetails lastDetails = new LastDetails();
                    lastDetails.setOriginal_title(object.getString("strLeague"));
                    lastDetails.setVote_average(object.getString("dateEvent"));
                    lastDetails.setOverview(object.getString("strEvent"));
                    lastDetails.setRelease_date(object.getString("intHomeScore"));
                    lastDetails.setGol_away(object.getString("intAwayScore"));
                    lastDetails.setPoster_path(object.getString("intAwayScore"));
                    lastList.add(lastDetails);
                }
                LastArrayAdapter lastArrayAdapter = new LastArrayAdapter(Last.this, R.layout.last_daftar,lastList);
                listView.setAdapter(lastArrayAdapter);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }

    }
}
