package com.example.natan.backgroundtasks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.natan.backgroundtasks.Network.NetworkUtils;
import com.example.natan.backgroundtasks.Pojo.Contacts;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        URL url = NetworkUtils.buildURl();
        Log.i("url", String.valueOf(url));
        new MyAsyncTask().execute(url);
    }


    class MyAsyncTask extends AsyncTask<URL, Void, List<Contacts>> {


        @Override
        protected List<Contacts> doInBackground(URL... urls) {


            try {
                List<Contacts> json = NetworkUtils.fetchContactData(urls[0]);
                return json;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(List<Contacts> contacts) {
            super.onPostExecute(contacts);

            Toast.makeText(MainActivity.this, String.valueOf(contacts), Toast.LENGTH_SHORT).show();

        }
    }
}
