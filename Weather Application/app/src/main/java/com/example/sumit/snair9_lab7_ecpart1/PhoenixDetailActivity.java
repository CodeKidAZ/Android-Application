package com.example.sumit.snair9_lab7_ecpart1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;

public class PhoenixDetailActivity extends AppCompatActivity {

    TextView PDtemperature;
    TextView PDtemperatureMin;
    TextView PDtemperatureMax;
    TextView PDpressure;
    TextView PDsealevel;
    TextView PDgroundlevel;
    TextView PDhumidity;
    TextView PDweathermain;
    TextView PDweatherdescription;

    TextView LDtemperature;
    TextView LDtemperatureMin;
    TextView LDtemperatureMax;
    TextView LDpressure;
    TextView LDsealevel;
    TextView LDgroundlevel;
    TextView LDhumidity;
    TextView LDweathermain;
    TextView LDweatherdescription;

    TextView TDtemperature;
    TextView TDtemperatureMin;
    TextView TDtemperatureMax;
    TextView TDpressure;
    TextView TDsealevel;
    TextView TDgroundlevel;
    TextView TDhumidity;
    TextView TDweathermain;
    TextView TDweatherdescription;

    Forecast Pforecast = new Forecast();
    Forecast Lforecast = new Forecast();
    Forecast Tforecast = new Forecast();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoenix_detail);

        getPhoenixDetailTextViews();
        getLondonDetailTextViews();
        getTokyoDetailTextViews();

        populateCityDetails();

    }

    private void getPhoenixDetailTextViews() {
        PDtemperature = (TextView)findViewById(R.id.PDtemperature);
        PDtemperatureMin = (TextView)findViewById(R.id.PDtemperatureMin);
        PDtemperatureMax = (TextView)findViewById(R.id.PDtemperatureMax);
        PDpressure = (TextView)findViewById(R.id.PDpressure);
        PDsealevel = (TextView)findViewById(R.id.PDsealevel); //start here now
        PDgroundlevel = (TextView)findViewById(R.id.PDgroundlevel); //start here now
        PDhumidity = (TextView)findViewById(R.id.PDhumidity);
        PDweathermain = (TextView)findViewById(R.id.PDweathermain);
        PDweatherdescription = (TextView)findViewById(R.id.PDweatherdescription);

    }
    private void getLondonDetailTextViews(){
        LDtemperature = (TextView)findViewById(R.id.LDtemperature);
        LDtemperatureMin = (TextView)findViewById(R.id.LDtemperatureMin);
        LDtemperatureMax = (TextView)findViewById(R.id.LDtemperatureMax);
        LDpressure = (TextView)findViewById(R.id.LDpressure);
        LDsealevel = (TextView)findViewById(R.id.LDsealevel); //start here now
        LDgroundlevel = (TextView)findViewById(R.id.LDgroundlevel); //start here now
        LDhumidity = (TextView)findViewById(R.id.LDhumidity);
        LDweathermain = (TextView)findViewById(R.id.LDweathermain);
        LDweatherdescription = (TextView)findViewById(R.id.LDweatherdescription);

    }

    private void getTokyoDetailTextViews() {
        TDtemperature = (TextView)findViewById(R.id.TDtemperature);
        TDtemperatureMin = (TextView)findViewById(R.id.TDtemperatureMin);
        TDtemperatureMax = (TextView)findViewById(R.id.TDtemperatureMax);
        TDpressure = (TextView)findViewById(R.id.TDpressure);
        TDsealevel = (TextView)findViewById(R.id.TDsealevel); //start here now
        TDgroundlevel = (TextView)findViewById(R.id.TDgroundlevel); //start here now
        TDhumidity = (TextView)findViewById(R.id.TDhumidity);
        TDweathermain = (TextView)findViewById(R.id.TDweathermain);
        TDweatherdescription = (TextView)findViewById(R.id.TDweatherdescription);
    }

    private void populateCityDetails() {
        showphoenixCityDetails();
        showlondonCityDetails();
        showtokyoCityDetails();

    }

    private void showtokyoCityDetails() {
        new HttpTokyoAsyncTask().execute();

    }

    private void showlondonCityDetails() {
        new HttpLondonAsyncTask().execute();

    }

    private void showphoenixCityDetails() {
        new HttpPhoenixAsyncTask().execute();
    }

    public void goBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private class HttpPhoenixAsyncTask extends AsyncTask<String,Void,Void> {
        String loc = "Phoenix";
        String website = "http://api.openweathermap.org/data/2.5/forecast?q=";
        String location = loc;
        String forecastDays = "&mode=json&cnt=" +1;
        String appID = "&APPID=";
        String myKey = "b080b39a058d0f3c955bdde5de696597";
        String url = website + location + forecastDays + appID + myKey;
        String content="";
        @Override
        protected Void doInBackground(String... a) {

            System.out.println("HttpPhoenixAsyncTask NOW");

            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            ResponseHandler<String> handler = new BasicResponseHandler();
            try {
                content = client.execute(httpGet, handler);
                System.out.println("YOUR CONTENT: "+ content);
                try
                {
                   //___________________________________________phoenix2 object
                    JSONObject myJsonObject = new JSONObject(content);

                    Pforecast.setCity(myJsonObject.getJSONObject("city").getString("name"));
                    Pforecast.setCountrycode(myJsonObject.getJSONObject("city").getString("country"));
                    Pforecast.setTemp(kelvinToCelcius(Double.parseDouble(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("temp"))));
                    Pforecast.setTempmin(kelvinToCelcius(Double.parseDouble(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("temp_min"))));
                    Pforecast.setTempmax(kelvinToCelcius(Double.parseDouble(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("temp_max"))));
                    Pforecast.setPressure(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("pressure"));
                    Pforecast.setSealevel(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("sea_level"));
                    Pforecast.setGroundlevel(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("grnd_level"));
                    Pforecast.setHumidity(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("humidity"));
                    Pforecast.setWeatherMain(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main"));
                    Pforecast.setWeatherDescription(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description"));
                }
                catch (JSONException e)
                {
                    System.out.println(e);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {

            PDtemperature.setText(Pforecast.getTemp().toString() +" C");
            PDtemperatureMin.setText(Pforecast.getTempmin().toString()+" C");
            PDtemperatureMax.setText(Pforecast.getTempmax().toString()+ " C");
            PDpressure.setText(Pforecast.getPressure()+" hPa");
            PDsealevel.setText(Pforecast.getSealevel()+" hPa");
            PDgroundlevel.setText(Pforecast.getGroundlevel()+"hPa");
            PDhumidity.setText(Pforecast.getHumidity()+" %");
            PDweathermain.setText(Pforecast.getWeatherMain());
            PDweatherdescription.setText(Pforecast.getWeatherDescription());
        }

    }
    private class HttpLondonAsyncTask extends AsyncTask<String,Void,Void> {
        String loc = "London";
        String website = "http://api.openweathermap.org/data/2.5/forecast?q=";
        String location = loc;
        String forecastDays = "&mode=json&cnt=" +1;
        String appID = "&APPID=";
        String myKey = "b080b39a058d0f3c955bdde5de696597";
        String url = website + location + forecastDays + appID + myKey;
        String content="";
        @Override
        protected Void doInBackground(String... a) {

            System.out.println("HttpPhoenixAsyncTask NOW");

            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            ResponseHandler<String> handler = new BasicResponseHandler();
            try {
                content = client.execute(httpGet, handler);
                System.out.println("YOUR CONTENT: "+ content);
                try
                {
                    //___________________________________________phoenix2 object
                    JSONObject myJsonObject = new JSONObject(content);

                    Lforecast.setCity(myJsonObject.getJSONObject("city").getString("name"));
                    Lforecast.setCountrycode(myJsonObject.getJSONObject("city").getString("country"));
                    Lforecast.setTemp(kelvinToCelcius(Double.parseDouble(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("temp"))));
                    Lforecast.setTempmin(kelvinToCelcius(Double.parseDouble(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("temp_min"))));
                    Lforecast.setTempmax(kelvinToCelcius(Double.parseDouble(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("temp_max"))));
                    Lforecast.setPressure(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("pressure"));
                    Lforecast.setSealevel(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("sea_level"));
                    Lforecast.setGroundlevel(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("grnd_level"));
                    Lforecast.setHumidity(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("humidity"));
                    Lforecast.setWeatherMain(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main"));
                    Lforecast.setWeatherDescription(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description"));
                }
                catch (JSONException e)
                {
                    System.out.println(e);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {

            LDtemperature.setText(Lforecast.getTemp().toString() +" C");
            LDtemperatureMin.setText(Lforecast.getTempmin().toString()+" C");
            LDtemperatureMax.setText(Lforecast.getTempmax().toString() + " C");
            LDpressure.setText(Lforecast.getPressure()+" hPa");
            LDsealevel.setText(Lforecast.getSealevel() + " hPa");
            LDgroundlevel.setText(Lforecast.getGroundlevel()+"hPa");
            LDhumidity.setText(Lforecast.getHumidity() + " %");
            LDweathermain.setText(Lforecast.getWeatherMain());
            LDweatherdescription.setText(Lforecast.getWeatherDescription());
        }

    }
    private class HttpTokyoAsyncTask extends AsyncTask<String,Void,Void> {
        String loc = "Tokyo";
        String website = "http://api.openweathermap.org/data/2.5/forecast?q=";
        String location = loc;
        String forecastDays = "&mode=json&cnt=" +1;
        String appID = "&APPID=";
        String myKey = "b080b39a058d0f3c955bdde5de696597";
        String url = website + location + forecastDays + appID + myKey;
        String content="";
        @Override
        protected Void doInBackground(String... a) {

            System.out.println("HttpPhoenixAsyncTask NOW");

            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            ResponseHandler<String> handler = new BasicResponseHandler();
            try {
                content = client.execute(httpGet, handler);
                System.out.println("YOUR CONTENT: "+ content);
                try
                {
                    //___________________________________________phoenix2 object
                    JSONObject myJsonObject = new JSONObject(content);

                    Tforecast.setCity(myJsonObject.getJSONObject("city").getString("name"));
                    Tforecast.setCountrycode(myJsonObject.getJSONObject("city").getString("country"));
                    Tforecast.setTemp(kelvinToCelcius(Double.parseDouble(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("temp"))));
                    Tforecast.setTempmin(kelvinToCelcius(Double.parseDouble(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("temp_min"))));
                    Tforecast.setTempmax(kelvinToCelcius(Double.parseDouble(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("temp_max"))));
                    Tforecast.setPressure(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("pressure"));
                    Tforecast.setSealevel(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("sea_level"));
                    Tforecast.setGroundlevel(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("grnd_level"));
                    Tforecast.setHumidity(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("humidity"));
                    Tforecast.setWeatherMain(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main"));
                    Tforecast.setWeatherDescription(myJsonObject.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description"));
                }
                catch (JSONException e)
                {
                    System.out.println(e);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {

            TDtemperature.setText(Tforecast.getTemp().toString() +" C");
            TDtemperatureMin.setText(Tforecast.getTempmin().toString()+" C");
            TDtemperatureMax.setText(Tforecast.getTempmax().toString()+ " C");
            TDpressure.setText(Tforecast.getPressure()+" hPa");
            TDsealevel.setText(Tforecast.getSealevel()+" hPa");
            TDgroundlevel.setText(Tforecast.getGroundlevel()+"hPa");
            TDhumidity.setText(Tforecast.getHumidity()+" %");
            TDweathermain.setText(Tforecast.getWeatherMain());
            TDweatherdescription.setText(Tforecast.getWeatherDescription());
        }

    }
    public double kelvinToCelcius(double k){
        double c = k- 273.15;
        DecimalFormat twoDForm = new DecimalFormat();
        twoDForm.setMinimumFractionDigits(3);
        return Double.valueOf(twoDForm.format(c));

    }
}
