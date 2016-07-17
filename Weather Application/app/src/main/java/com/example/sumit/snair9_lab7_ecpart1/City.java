package com.example.sumit.snair9_lab7_ecpart1;

/**
 * Created by sumit on 11/30/2015.
 */
public class City {

    private String name;
    private String countrycode;
    private String timestamp; //hard set initial data
    private double temperature;
    private String timestampCurrent;  // new value , also save diff here
    private int humidity;
    private double windspeed;
    private int cloudiness;

    public String getName() {
        return name;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getTimestampCurrent() {
        return timestampCurrent;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public int getCloudiness() {
        return cloudiness;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setTimestampCurrent(String timestampCurrent) {
        this.timestampCurrent = timestampCurrent;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public void setCloudiness(int cloudiness) {
        this.cloudiness = cloudiness;
    }

}
