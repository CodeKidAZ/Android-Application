package com.example.sumit.snair9_lab7_ecpart1;

/**
 * Created by sumit on 12/4/2015.
 */
public class Forecast {

    private String city;
    private String countrycode;

    private double temp;
    private double tempmin;
    private double tempmax;
    private String pressure;
    private String sealevel;
    private String groundlevel;
    private String humidity;

    private String weatherMain;
    private String weatherDescription;


    //_________________________________________________SET
    public void setCity(String city) {
        this.city = city;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public void setTempmin(Double tempmin) {
        this.tempmin = tempmin;
    }

    public void setTempmax(Double tempmax) {
        this.tempmax = tempmax;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setSealevel(String sealevel) {
        this.sealevel = sealevel;
    }

    public void setGroundlevel(String groundlevel) {
        this.groundlevel = groundlevel;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }


    //______________________________________________GET
    public String getCity() {
        return city;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public Double getTemp() {
        return temp;
    }

    public Double getTempmin() {
        return tempmin;
    }

    public Double getTempmax() {
        return tempmax;
    }

    public String getPressure() {
        return pressure;
    }

    public String getSealevel() {
        return sealevel;
    }

    public String getGroundlevel() {
        return groundlevel;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }
}
