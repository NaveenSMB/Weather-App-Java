package com.example.weatherapp;

import android.os.Bundle;
import android.os.*;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView cityName;
    Button search;
    TextView show;
    String url;

    class getWeather extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... urls) {
            StringBuilder res = new StringBuilder();
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
                urlConnection.connect();

                InputStream inpst=urlConnection.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(inpst));


                String line="";
                while((line=reader.readLine())!=null){
                    res.append(line).append("\n");
                }
                return res.toString();
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPostExecute(String res){
            super.onPostExecute(res);
            try{
                JSONObject jsonObject=new JSONObject(res);
                String weatherrep=jsonObject.getString("main");
                weatherrep=weatherrep.replace("temp","Temperature");
                weatherrep=weatherrep.replace("feels_like","Feels Like");
                weatherrep=weatherrep.replace("temp_max","Temperature Max");
                weatherrep=weatherrep.replace("temp_min","Temperature Min");
                weatherrep=weatherrep.replace("pressure","Pressure");
                weatherrep=weatherrep.replace("humidity","Humidity");
                weatherrep=weatherrep.replace("{","");
                weatherrep=weatherrep.replace("{","");
                weatherrep=weatherrep.replace(",","\n");
                weatherrep=weatherrep.replace(":","-");

                show.setText(weatherrep);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityName=findViewById(R.id.cityName);
        search=findViewById(R.id.search);
        show=findViewById(R.id.weather);
        final String[] temp={""};

        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                String city = cityName.getText().toString();

                try {
                    if(city!=null) {
                        url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=2717d67b397c9c18e4db76b5f344a897";
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Enter City", Toast.LENGTH_SHORT).show();
                    }
                    getWeather task=new getWeather();
                    temp[0]=task.execute(url).get();
                }catch(ExecutionException e){
                    e.printStackTrace();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                if(temp[0]==null){
                    show.setText("Cannot able to find Weather");
                }
            }
        } );
    }
}