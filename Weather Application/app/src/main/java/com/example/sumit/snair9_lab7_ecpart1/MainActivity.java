package com.example.sumit.snair9_lab7_ecpart1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    City phoenixCity = new City();
    City phoenix2 = new City();

    City londonCity = new City();
    City london2 = new City();

    City tokyoCity = new City();
    City tokyo2 = new City();

    String url;
    int refreshBit = 0;

    private String prefName = "MyPref";
    private SharedPreferences prefs;

    //_________________________________________ CITY TEXT VIEWS
    TextView Ptimestamp;
    TextView Ptemperature;
    TextView Phumidity;
    TextView Pwindspeed;
    TextView Pcloudiness;

    TextView Ltimestamp;
    TextView Ltemperature;
    TextView Lhumidity;
    TextView Lwindspeed;
    TextView Lcloudiness;

    TextView Ttimestamp;
    TextView Ttemperature;
    TextView Thumidity;
    TextView Twindspeed;
    TextView Tcloudiness;

    //____________________________________________CITY DIFF TEXT VIEWS
    TextView PdiffTimestamp;
    TextView PdiffTemperature;
    TextView PdiffHumidity;
    TextView PdiffWindspeed;
    TextView PdiffCloudiness;

    TextView LdiffTimestamp;
    TextView LdiffTemperature;
    TextView LdiffHumidity;
    TextView LdiffWindspeed;
    TextView LdiffCloudiness;

    TextView TdiffTimestamp;
    TextView TdiffTemperature;
    TextView TdiffHumidity;
    TextView TdiffWindspeed;
    TextView TdiffCloudiness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //____________________________________________________________________GET TEXT VIEW
        //Getting all TextView used to display phoenixCity data
        Ptimestamp = (TextView)findViewById(R.id.Ptimestamp);
        Ptemperature = (TextView)findViewById(R.id.Ptemperature);
        Phumidity = (TextView)findViewById(R.id.Phumidity);
        Pwindspeed = (TextView)findViewById(R.id.Pwindspeed);
        Pcloudiness = (TextView)findViewById(R.id.Pcloudiness);

        //Getting all TextView used to display londonCity data
        Ltimestamp = (TextView)findViewById(R.id.Ltimestamp);
        Ltemperature = (TextView)findViewById(R.id.Ltemperature);
        Lhumidity = (TextView)findViewById(R.id.Lhumidity);
        Lwindspeed = (TextView)findViewById(R.id.Lwindspeed);
        Lcloudiness = (TextView)findViewById(R.id.Lcloudiness);

        //Getting all TextView used to display tokyoCity data
        Ttimestamp = (TextView)findViewById(R.id.Ttimestamp);
        Ttemperature = (TextView)findViewById(R.id.Ttemperature);
        Thumidity = (TextView)findViewById(R.id.Thumidity);
        Twindspeed = (TextView)findViewById(R.id.Twindspeed);
        Tcloudiness = (TextView)findViewById(R.id.Tcloudiness);

        //_____________________________________________________________________GET DIFF TEXTVIEWS
        ////Getting all TextView used to display diff data
        PdiffTimestamp =  (TextView)findViewById(R.id.PdiffTimestamp);
        PdiffTemperature = (TextView)findViewById(R.id.PdiffTemperature);
        PdiffHumidity = (TextView)findViewById(R.id.PdiffHumidity);
        PdiffWindspeed = (TextView)findViewById(R.id.PdiffWindspeed);
        PdiffCloudiness = (TextView)findViewById(R.id.PdiffCloudiness);

        LdiffTimestamp = (TextView)findViewById(R.id.LdiffTimestamp);
        LdiffTemperature = (TextView)findViewById(R.id.LdiffTemperature);
        LdiffHumidity = (TextView)findViewById(R.id.LdiffHumidity);
        LdiffWindspeed = (TextView)findViewById(R.id.LdiffWindspeed);
        LdiffCloudiness = (TextView)findViewById(R.id.LdiffCloudiness);

        TdiffTimestamp = (TextView)findViewById(R.id.TdiffTimestamp);
        TdiffTemperature = (TextView)findViewById(R.id.TdiffTemperature);
        TdiffHumidity = (TextView)findViewById(R.id.TdiffHumidity);
        TdiffWindspeed = (TextView)findViewById(R.id.TdiffWindspeed);
        TdiffCloudiness = (TextView)findViewById(R.id.TdiffCloudiness);


        populateInitialCityData();


        //new MyTask().execute();

    }
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume CLICKED now will get from SharPref");
        //grab data and set variables accordingly
        prefs = getSharedPreferences(prefName, MODE_PRIVATE);

        ////Getting all TextView used to display diff data

        //RESTORE ALL CITY INITIAL DATA
        if(prefs.getString("Ptimestamp", "unknown") != "unknown")
        {
            Ptimestamp.setText(prefs.getString("Ptimestamp", "unknown"));
            Ptemperature.setText( prefs.getString("Ptemperature", "unknown"));
            Phumidity.setText(prefs.getString("Phumidity", "unknown"));
            Pwindspeed.setText(prefs.getString("Pwindspeed", "unknown"));
            Pcloudiness.setText(prefs.getString("Pcloudiness", "unknown"));

            Ltimestamp.setText(prefs.getString("Ltimestamp", "unknown"));
            Ltemperature.setText( prefs.getString("Ltemperature", "unknown"));
            Lhumidity.setText(prefs.getString("Lhumidity", "unknown"));
            Lwindspeed.setText(prefs.getString("Lwindspeed", "unknown"));
            Lcloudiness.setText(prefs.getString("Lcloudiness", "unknown"));

            Ttimestamp.setText(prefs.getString("Ttimestamp", "unknown"));
            Ttemperature.setText( prefs.getString("Ttemperature", "unknown"));
            Thumidity.setText(prefs.getString("Thumidity", "unknown"));
            Twindspeed.setText(prefs.getString("Twindspeed", "unknown"));
            Tcloudiness.setText(prefs.getString("Tcloudiness", "unknown"));
        }

        if(prefs.getString("PdiffTimestamp", "unknown") != "unknown")
        {
            System.out.println("Shared Pref is NOT EMPTY");
            //RESTORE ALL DIFFERENCE DATA
            PdiffTimestamp.setText(prefs.getString("PdiffTimestamp", "unknown"));
            PdiffTemperature.setText( prefs.getString("PdiffTemperature", "unknown"));
            PdiffHumidity.setText(prefs.getString("PdiffHumidity", "unknown"));
            PdiffWindspeed.setText(prefs.getString("PdiffWindspeed", "unknown"));
            PdiffCloudiness.setText(prefs.getString("PdiffCloudiness", "unknown"));

            LdiffTimestamp.setText(prefs.getString("LdiffTimestamp", "unknown"));
            LdiffTemperature.setText( prefs.getString("LdiffTemperature", "unknown"));
            LdiffHumidity.setText(prefs.getString("LdiffHumidity", "unknown"));
            LdiffWindspeed.setText(prefs.getString("LdiffWindspeed", "unknown"));
            LdiffCloudiness.setText(prefs.getString("LdiffCloudiness", "unknown"));

            TdiffTimestamp.setText(prefs.getString("TdiffTimestamp", "unknown"));
            TdiffTemperature.setText( prefs.getString("TdiffTemperature", "unknown"));
            TdiffHumidity.setText(prefs.getString("TdiffHumidity", "unknown"));
            TdiffWindspeed.setText(prefs.getString("TdiffWindspeed", "unknown"));
            TdiffCloudiness.setText(prefs.getString("TdiffCloudiness", "unknown"));

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause CLICKED and now will SAVE");
        prefs = getSharedPreferences(prefName, MODE_PRIVATE); //instantiate shared pref
        SharedPreferences.Editor editor = prefs.edit(); //need editor to save data

        //RefreshBit = 1;

        //SAVE P TextView Values
        editor.putString("Ptimestamp", Ptimestamp.getText().toString());
        editor.putString("Ptemperature", Ptemperature.getText().toString());
        editor.putString("Phumidity", Phumidity.getText().toString());
        editor.putString("Pwindspeed", Pwindspeed.getText().toString());
        editor.putString("Pcloudiness", Pcloudiness.getText().toString());

        //SAVE L TextView Values
        editor.putString("Ltimestamp", Ltimestamp.getText().toString());
        editor.putString("Ltemperature", Ltemperature.getText().toString());
        editor.putString("Lhumidity", Lhumidity.getText().toString());
        editor.putString("Lwindspeed", Lwindspeed.getText().toString());
        editor.putString("Lcloudiness", Lcloudiness.getText().toString());

        //SAVE T TextView Values
        editor.putString("Ttimestamp", Ttimestamp.getText().toString());
        editor.putString("Ttemperature", Ttemperature.getText().toString());
        editor.putString("Thumidity", Thumidity.getText().toString());
        editor.putString("Twindspeed", Twindspeed.getText().toString());
        editor.putString("Tcloudiness", Tcloudiness.getText().toString());


        //SAVE PDIFF
        editor.putString("PdiffTimestamp", PdiffTimestamp.getText().toString());
        editor.putString("PdiffTemperature", PdiffTemperature.getText().toString());
        editor.putString("PdiffHumidity", PdiffHumidity.getText().toString());
        editor.putString("PdiffWindspeed", PdiffWindspeed.getText().toString());
        editor.putString("PdiffCloudiness", PdiffCloudiness.getText().toString());

        //SAVING LDIFF
        editor.putString("LdiffTimestamp", LdiffTimestamp.getText().toString());
        editor.putString("LdiffTemperature", LdiffTemperature.getText().toString());
        editor.putString("LdiffHumidity", LdiffHumidity.getText().toString());
        editor.putString("LdiffWindspeed", LdiffWindspeed.getText().toString());
        editor.putString("LdiffCloudiness", LdiffCloudiness.getText().toString());

        //SAVING TDIFF
        editor.putString("TdiffTimestamp", TdiffTimestamp.getText().toString());
        editor.putString("TdiffTemperature", TdiffTemperature.getText().toString());
        editor.putString("TdiffHumidity", TdiffHumidity.getText().toString());
        editor.putString("TdiffWindspeed", TdiffWindspeed.getText().toString());
        editor.putString("TdiffCloudiness", TdiffCloudiness.getText().toString());



        editor.commit();//save the data to shared prefs

    }
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart CLICKED");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart CLICKED");

    }
    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop CLICKED");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestory CLICKED");

    }



    public void populateInitialCityData()
    {
        showphoenixCityDataOnScreen();
        showlondonCityDataOnScreen();
        showtokyoCityDataOnScreen();

    }

    private void showtokyoCityDataOnScreen() {

        new HttpTokyoAsyncTask().execute();
    }

    public void showphoenixCityDataOnScreen(){

        new HttpPhoenixAsyncTask().execute();
        //new HttpPhoenixAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "Param 1","Param 2", "Param 3");
    }

    private void showlondonCityDataOnScreen() {

        new HttpLondonAsyncTask().execute();
    }
    public void refreshWithRandomData(View view){
        refreshBit = 1;
        new HttpPhoenixAsyncTask().execute();
        new HttpLondonAsyncTask().execute();
        new HttpTokyoAsyncTask().execute();

    }

    //String - what we are passing in
    //Void - progress
    //Void - whare we are returning
    //______________________________________________________________________________PHOENIX ASYNC TASK
    private class HttpPhoenixAsyncTask extends AsyncTask<String,Void,Void> {
        String loc = "Phoenix";
        String website = "http://api.openweathermap.org/data/2.5/weather?q=";
        String location = loc;
        String appID = "&APPID=";
        String myKey = "b080b39a058d0f3c955bdde5de696597";
        String url = website + location + appID + myKey;
        String content="";
        @Override
        protected Void doInBackground(String... a) {

            System.out.println("HttpPhoenixAsyncTask NOW");

            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            ResponseHandler<String>handler = new BasicResponseHandler();
            try {
                content = client.execute(httpGet, handler);
                //System.out.println("YOUR CONTENT: "+ content);
                try
                {
                    if(refreshBit==1){
                        //_____________________________________________________________phoenix2 object
                        JSONObject myJsonObjectDiff = new JSONObject(content);

                        phoenix2.setName(myJsonObjectDiff.getString("name"));
                        phoenix2.setCountrycode(myJsonObjectDiff.getJSONObject("sys").getString("country"));
                        phoenix2.setTimestamp(myJsonObjectDiff.getString("dt"));
                        //phoenixCity.setTimestampCurrent();
                        phoenix2.setTemperature(kelvinToCelcius(myJsonObjectDiff.getJSONObject("main").getInt("temp")));
                        phoenix2.setHumidity(myJsonObjectDiff.getJSONObject("main").getInt("humidity"));
                        phoenix2.setWindspeed(meterToMiles(myJsonObjectDiff.getJSONObject("wind").getInt("speed")));
                        phoenix2.setCloudiness(myJsonObjectDiff.getJSONObject("clouds").getInt("all"));

                    }
                    else
                    {
                        JSONObject myJsonObject = new JSONObject(content);

                        phoenixCity.setName(myJsonObject.getString("name"));
                        phoenixCity.setCountrycode(myJsonObject.getJSONObject("sys").getString("country"));
                        phoenixCity.setTimestamp(myJsonObject.getString("dt"));
                        //phoenixCity.setTimestampCurrent();
                        phoenixCity.setTemperature(kelvinToCelcius(myJsonObject.getJSONObject("main").getInt("temp")));
                        phoenixCity.setHumidity(myJsonObject.getJSONObject("main").getInt("humidity"));
                        phoenixCity.setWindspeed(meterToMiles(myJsonObject.getJSONObject("wind").getInt("speed")));
                        phoenixCity.setCloudiness(myJsonObject.getJSONObject("clouds").getInt("all"));
                        //printphoenixCityObject();
                    }
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
            if(refreshBit==1){//if refresh is clicked
                populatePdiffOnScreen();
            }
            else{
                populatePhoenixRow1();
            }
            
        }
        private void populatePhoenixRow1() {
            Ptimestamp.setText(phoenixCity.getTimestamp());
            Ptemperature.setText(Double.toString(phoenixCity.getTemperature())+" C");
            Phumidity.setText(Integer.toString(phoenixCity.getHumidity()) + " %");
            Pwindspeed.setText(Double.toString(phoenixCity.getWindspeed())+" mi/hr");
            Pcloudiness.setText(Integer.toString(phoenixCity.getCloudiness())+" %");
        }

        private void populatePdiffOnScreen() {
            /*
            int time1 =Integer.parseInt(phoenixCity.getTimestamp());
            int time2=Integer.parseInt(phoenix2.getTimestamp());
            int difftime = time2-time1;
            System.out.println("DIFF TIME " +difftime);
            System.out.println(Integer.parseInt(phoenixCity.getTimestamp()));
            */

            //PdiffTimestamp.setText(difftime+"Hello");
            PdiffTimestamp.setText(Integer.toString(Integer.parseInt(phoenixCity.getTimestamp()) - Integer.parseInt(phoenix2.getTimestamp())));
            PdiffTemperature.setText(Double.toString(phoenix2.getTemperature() - phoenixCity.getTemperature()) + " C");
            PdiffHumidity.setText(Integer.toString(phoenix2.getHumidity() - phoenixCity.getHumidity()) + " %");
            PdiffWindspeed.setText(Double.toString(phoenix2.getWindspeed() - phoenixCity.getWindspeed()) + " mi/hr");
            PdiffCloudiness.setText(Integer.toString(phoenix2.getCloudiness() - phoenixCity.getCloudiness()) + " %");

        }
    }


    //____________________________________________________________________________LONDON ASYNC TASK
    private class HttpLondonAsyncTask extends AsyncTask<String,Void,Void> {
        String loc = "London";
        String website = "http://api.openweathermap.org/data/2.5/weather?q=";
        String location = loc;
        String appID = "&APPID=";
        String myKey = "b080b39a058d0f3c955bdde5de696597";
        String url2 = website + location + appID + myKey;
        String content="";
        @Override
        protected Void doInBackground(String... a) {

            System.out.println("HttpLondonAsyncTask NOW");

            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url2);
            ResponseHandler<String>handler = new BasicResponseHandler();
            try {
                content = client.execute(httpGet, handler);
                try {
                    if(refreshBit==1){
                        //_____________________________________________________________phoenix2 object
                        JSONObject myJsonObjectDiff = new JSONObject(content);

                        london2.setName(myJsonObjectDiff.getString("name"));
                        london2.setCountrycode(myJsonObjectDiff.getJSONObject("sys").getString("country"));
                        london2.setTimestamp(myJsonObjectDiff.getString("dt"));
                        //phoenixCity.setTimestampCurrent();
                        london2.setTemperature(kelvinToCelcius(myJsonObjectDiff.getJSONObject("main").getInt("temp")));
                        london2.setHumidity(myJsonObjectDiff.getJSONObject("main").getInt("humidity"));
                        london2.setWindspeed(meterToMiles(myJsonObjectDiff.getJSONObject("wind").getInt("speed")));
                        london2.setCloudiness(myJsonObjectDiff.getJSONObject("clouds").getInt("all"));

                    }
                    else {

                        JSONObject myJsonObject = new JSONObject(content);

                        londonCity.setName(myJsonObject.getString("name"));
                        londonCity.setCountrycode(myJsonObject.getJSONObject("sys").getString("country"));
                        londonCity.setTimestamp(myJsonObject.getString("dt"));
                        //phoenixCity.setTimestampCurrent();
                        londonCity.setTemperature(kelvinToCelcius(myJsonObject.getJSONObject("main").getInt("temp")));
                        londonCity.setHumidity(myJsonObject.getJSONObject("main").getInt("humidity"));
                        londonCity.setWindspeed(meterToMiles(myJsonObject.getJSONObject("wind").getInt("speed")));
                        londonCity.setCloudiness(myJsonObject.getJSONObject("clouds").getInt("all"));
                        //printlondonCityObject();
                    }
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
        protected void onPostExecute(Void aVoid)
        {
            if(refreshBit==1){
                populateLdiffOnScreen();
            }
            else{
                populateLondonRow3();
            }
        }

        private void populateLdiffOnScreen() {
            LdiffTimestamp.setText(Integer.toString(Integer.parseInt(london2.getTimestamp()) - Integer.parseInt(londonCity.getTimestamp())));
            LdiffTemperature.setText(Double.toString(london2.getTemperature() - londonCity.getTemperature()) + " C");
            LdiffHumidity.setText(Integer.toString(london2.getHumidity() - londonCity.getHumidity()) + " %");
            LdiffWindspeed.setText(Double.toString(london2.getWindspeed() - londonCity.getWindspeed()) + " mi/hr");
            LdiffCloudiness.setText(Integer.toString(london2.getCloudiness() - londonCity.getCloudiness()) + " %");
        }

        private void populateLondonRow3() {
            Ltimestamp.setText(phoenixCity.getTimestamp());
            Ltemperature.setText(Double.toString(londonCity.getTemperature())+" C");
            Lhumidity.setText(Integer.toString(londonCity.getHumidity())+" %");
            Lwindspeed.setText(Double.toString(londonCity.getWindspeed())+" mi/hr");
            Lcloudiness.setText(Integer.toString(londonCity.getCloudiness())+" %");
        }

    }

    //____________________________________________________________________________LONDON ASYNC TASK
    private class HttpTokyoAsyncTask extends AsyncTask<String,Void,Void> {

        String loc = "Tokyo";
        String website = "http://api.openweathermap.org/data/2.5/weather?q=";
        String location = loc;
        String appID = "&APPID=";
        String myKey = "b080b39a058d0f3c955bdde5de696597";
        String url3 = website + location + appID + myKey;
        String content="";
        @Override
        protected Void doInBackground(String... a) {
            System.out.println("HttpTokyoAsyncTask NOW");

            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url3);
            ResponseHandler<String>handler = new BasicResponseHandler();
            try {
                content = client.execute(httpGet, handler);
                //System.out.println("YOUR CONTENT: "+ content);
                try {
                    if(refreshBit==1)
                    {
                        //_____________________________________________________________phoenix2 object
                        JSONObject myJsonObjectDiff = new JSONObject(content);

                        tokyo2.setName(myJsonObjectDiff.getString("name"));
                        tokyo2.setCountrycode(myJsonObjectDiff.getJSONObject("sys").getString("country"));
                        tokyo2.setTimestamp(myJsonObjectDiff.getString("dt"));
                        //phoenixCity.setTimestampCurrent();
                        tokyo2.setTemperature(kelvinToCelcius(myJsonObjectDiff.getJSONObject("main").getInt("temp")));
                        tokyo2.setHumidity(myJsonObjectDiff.getJSONObject("main").getInt("humidity"));
                        tokyo2.setWindspeed(meterToMiles(myJsonObjectDiff.getJSONObject("wind").getInt("speed")));
                        tokyo2.setCloudiness(myJsonObjectDiff.getJSONObject("clouds").getInt("all"));

                    }
                    else
                    {
                        JSONObject myJsonObject = new JSONObject(content);

                        tokyoCity.setName(myJsonObject.getString("name"));
                        tokyoCity.setCountrycode(myJsonObject.getJSONObject("sys").getString("country"));
                        tokyoCity.setTimestamp(myJsonObject.getString("dt"));
                        //phoenixCity.setTimestampCurrent();
                        tokyoCity.setTemperature(kelvinToCelcius(myJsonObject.getJSONObject("main").getInt("temp")));
                        tokyoCity.setHumidity(myJsonObject.getJSONObject("main").getInt("humidity"));
                        tokyoCity.setWindspeed(meterToMiles(myJsonObject.getJSONObject("wind").getInt("speed")));
                        tokyoCity.setCloudiness(myJsonObject.getJSONObject("clouds").getInt("all"));
                        //printtokyoCityObject();
                    }
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
            if(refreshBit==1){//if refresh is clicked
                populateTdiffOnScreen();
            }
            else{
                populatePhoenixRow1();
            }
        }

        private void populateTdiffOnScreen() {
            TdiffTimestamp.setText(Integer.toString(Integer.parseInt(tokyo2.getTimestamp()) - Integer.parseInt(tokyoCity.getTimestamp())));
            TdiffTemperature.setText(Double.toString(tokyo2.getTemperature() - tokyoCity.getTemperature()) + " C");
            TdiffHumidity.setText(Integer.toString(tokyo2.getHumidity() - tokyoCity.getHumidity()) + " %");
            TdiffWindspeed.setText(Double.toString(tokyo2.getWindspeed() - tokyoCity.getWindspeed()) + " mi/hr");
            TdiffCloudiness.setText(Integer.toString(tokyo2.getCloudiness() - tokyoCity.getCloudiness()) + " %");
        }

        private void populatePhoenixRow1() {
            Ttimestamp.setText(londonCity.getTimestamp());
            Ttemperature.setText(Double.toString(tokyoCity.getTemperature()) + " C");
            Thumidity.setText(Integer.toString(tokyoCity.getHumidity()) + " %");
            Twindspeed.setText(Double.toString(tokyoCity.getWindspeed()) + " mi/hr");
            Tcloudiness.setText(Integer.toString(tokyoCity.getCloudiness()) + " %");
        }
    }


    //_________________________________________________________________UTILITY
    public double kelvinToCelcius(double k){
        double c = k- 273.15;
        //float b = Math.round(c);
        DecimalFormat twoDForm = new DecimalFormat();
        twoDForm.setMinimumFractionDigits(3);
        return Double.valueOf(twoDForm.format(c));

    }

    public int meterToMiles(int ws){

        double speedMilesPerHour = ws*2.4;
        return ((int) speedMilesPerHour);
    }

    /*public String epochToCurrent(int t){
           int unixEpoch =t;
           Date fullCurrentTime = new Date( t * 1000);
           String shortCurrentTime = fullCurrentTime.getHours()+":"+fullCurrentTime.getMinutes()+":"+fullCurrentTime.getMilliseconds();
           //console.log(fullCurrentTime);
           return shortCurrentTime;
       }*/

    private void printphoenixCityObject() {
        System.out.println(" ");
        System.out.println("PHOENIX NAME " + phoenixCity.getName());
        System.out.println("PHOENIX CC " + phoenixCity.getCountrycode());
        System.out.println("PHOENIX TIMESTAMP "+ phoenixCity.getTimestamp());
        System.out.println("PHOENIX TEMPERATURE "+ phoenixCity.getTemperature());
        System.out.println("PHOENIX HUMIDITY "+ phoenixCity.getHumidity());
        System.out.println("PHOENIX WINDSPEED "+ phoenixCity.getWindspeed());
        System.out.println("PHOENIX CLOUDINESS "+ phoenixCity.getCloudiness());
    }

    private void printlondonCityObject() {
        System.out.println(" ");
        System.out.println("LONDON OBJECT " + londonCity.getName());
        System.out.println("LONDON OBJECT " + londonCity.getCountrycode());
        System.out.println("LONDON OBJECT "+ londonCity.getTimestamp());
        System.out.println("LONDON OBJECT "+ londonCity.getTemperature());
        System.out.println("LONDON OBJECT "+ londonCity.getHumidity());
        System.out.println("LONDON OBJECT "+ londonCity.getWindspeed());
        System.out.println("LONDON OBJECT " + londonCity.getCloudiness());
    }

    private void printtokyoCityObject() {
        System.out.println(" ");
        System.out.println("TOKYO OBJECT " + tokyoCity.getName());
        System.out.println("TOKYO OBJECT " + tokyoCity.getCountrycode());
        System.out.println("TOKYO OBJECT "+ tokyoCity.getTimestamp());
        System.out.println("TOKYO OBJECT "+ tokyoCity.getTemperature());
        System.out.println("TOKYO OBJECT "+ tokyoCity.getHumidity());
        System.out.println("TOKYO OBJECT "+ tokyoCity.getWindspeed());
        System.out.println("TOKYO OBJECT "+ tokyoCity.getCloudiness());
    }
    public void mapPhoenix(View view){

        Uri gmmIntentUri = Uri.parse("geo:33.4483800,-112.0740400");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(mapIntent);
        }
        else {
            System.out.println("NO APP TO HANDLE MAPS");
        }
    }

    public void mapLondon(View view){

        Uri gmmIntentUri = Uri.parse("geo:51.5085300, -0.1257400");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(mapIntent);
        }
        else {
            System.out.println("NO APP TO HANDLE MAPS");
        }
    }

    public void mapTokyo(View view){

        Uri gmmIntentUri = Uri.parse("geo:35.6895000,139.6917100");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(mapIntent);
        }
        else {
            System.out.println("NO APP TO HANDLE MAPS");
        }
    }

    public void showDetails(View view){
        Intent i = new Intent(this, PhoenixDetailActivity.class);
        //String name= mUserNameText.getText().toString();
        // i.putExtra(USER_KEY,name);

        startActivity(i);

    }

    public void delShPref(View view){
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
        System.out.println("SharedPref now DELETED");
    }

    public void averageTemperature() {

        double[] tempArray = new double[4];
        double[] humidityArray = new double[4];
        double[] windspeedArray = new double[4];
        double[] cloudinessArray = new double[4];


        tempArray[0] = phoenixCity.getTemperature();
        tempArray[1] = londonCity.getTemperature();
        tempArray[2] = tokyoCity.getTemperature();

        humidityArray[0] = phoenixCity.getHumidity();
        humidityArray[1] = londonCity.getHumidity();
        humidityArray[2] = tokyoCity.getHumidity();

        windspeedArray[0] = phoenixCity.getWindspeed();
        windspeedArray[1] = londonCity.getWindspeed();
        windspeedArray[2] = tokyoCity.getWindspeed();

        cloudinessArray[0] = phoenixCity.getCloudiness();
        cloudinessArray[1] = londonCity.getCloudiness();
        cloudinessArray[2] = tokyoCity.getCloudiness();


        /*
        tempArray.;
        humidityArray.sort(function(a, b) {
            return a - b
        });
        windspeedArray.sort(function(a, b) {
            return a - b
        });
        cloudinessArray.sort(function(a, b) {
            return a - b
        });
        */

        /*
        console.log(tempArray);
        console.log(humidityArray);
        console.log(windspeedArray);
        console.log(cloudinessArray);
        */

/*
        var avgTemp = ((parseInt(tempArray[0])+parseInt(tempArray[1])+parseInt(tempArray[2]))/3).toFixed(0);
        var avgHumidity = ((parseInt(humidityArray[0])+parseInt(humidityArray[1])+parseInt(humidityArray[2]))/3).toFixed(0);
        var avgWSpeed= ((parseInt(windspeedArray[0])+parseInt(windspeedArray[1])+parseInt(windspeedArray[2]))/3).toFixed(0);
        var avgCloud= ((parseInt(cloudinessArray[0])+parseInt(cloudinessArray[1])+parseInt(cloudinessArray[2]))/3).toFixed(0);

        console.log("AVG are : "+"TEMP: "+avgTemp + " "+"HUMID: "+avgHumidity+" "+"WS: "+avgWSpeed+ " "+"CLOUD: "+ avgCloud);

        var hottest ="";
        var humidest ="";
        var nicest ="";
        var worst ="";

        //City with higher than avg temp and also least cloudy should be very HOT
        if(phoenixCity.getTemperature() >parseInt(avgTemp) && phoenixCity.cloudiness < parseInt(avgCloud))
        {
            hottest = phoenixCity.name;
        }
        else if(londonCity.temperature >parseInt(avgTemp) && londonCity.cloudiness < parseInt(avgCloud)){
            hottest = londonCity.name;
        }
        else if(tokyoCity.temperature >parseInt(avgTemp) && tokyoCity.cloudiness < parseInt(avgCloud)){
            hottest = tokyoCity.name;
        }


        //City with higher than avg humidty and also higher than avg windspeed will be most Humid
        if(phoenixCity.humidity >parseInt(avgHumidity) && phoenixCity.windspeed < parseInt(avgWSpeed))
        {
            humidest = phoenixCity.name;
        }
        else if(londonCity.humidity >parseInt(avgHumidity) && londonCity.windspeed < parseInt(avgWSpeed))
        {
            humidest = londonCity.name;
        }
        else if(tokyoCity.humidity >parseInt(avgHumidity) && tokyoCity.windspeed < parseInt(avgWSpeed))
        {
            humidest = tokyoCity.name;
        }

        //City whose weather is less than  avg weather should be PERFECT CONDITIONS
        if(phoenixCity.getTemperature() < parseInt(avgTemp))
        {
            nicest = londonCity.name;
        }
        else if(londonCity.temperature < parseInt(avgTemp))
        {
            nicest = londonCity.name;
        }
        else if(tokyoCity.temperature < parseInt(avgTemp))
        {
            nicest = tokyoCity.name;
        }

        //city that has lowest wind and lowest cloud is the worst city
        if(phoenixCity.windspeed<parseInt(avgWSpeed) && phoenixCity.cloudiness<parseInt(avgCloud))
        {
            worst = phoenixCity.name;
        }
        if(londonCity.windspeed<parseInt(avgWSpeed) && londonCity.cloudiness<parseInt(avgCloud))
        {
            worst = londonCity.name;
        }
        if(tokyoCity.windspeed<parseInt(avgWSpeed) && tokyoCity.cloudiness<parseInt(avgCloud))
        {
            worst = tokyoCity.name;
        }

        console.log(humidest + " "+ avgHumidity);

        document.getElementById("temperature").innerHTML ="The average temperature is "+avgTemp+" and the hottest city is "+hottest;
        document.getElementById("humidity").innerHTML = "The average humidity is "+avgHumidity+" and the most humid city is "+humidest;
        document.getElementById("niceweather").innerHTML = "The city with the nicest weather is "+ nicest;
        document.getElementById("worstweather").innerHTML = "The city with the worst weather is "+ worst;
        */
    }


}
