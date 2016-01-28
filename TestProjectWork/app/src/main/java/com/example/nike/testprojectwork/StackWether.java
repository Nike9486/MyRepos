package com.example.nike.testprojectwork;

import java.util.Date;

/**
 * Created by Nike on 27.01.2016.
 */
public class StackWether {
    private Date date=new Date();
    private int maxPressure,minPressure,maxTemp,minTemp,minWind,maxWind,maxRelwet,minRelwet,minHeat,maxHeat;
    public StackWether(){}

    public Date getDate(){return date;}
    public void setDate(int d,int m,int y,int h){
        this.date.setDate(d);
        this.date.setMonth(m);
        this.date.setYear(y);
        this.date.setHours(h);
    }
    public int getMaxPressure(){return maxPressure;}
    public int getMinPressure(){return minPressure;}
    public void setMaxPressure(int maxPressure){this.maxPressure=maxPressure;}
    public void setMinPressure(int minPressure){this.minPressure=minPressure;}

    public int getMaxTemp(){return maxTemp;}
    public int getMinTemp(){return minTemp;}
    public void setMaxTemp(int maxTemp){this.maxTemp=maxTemp;}
    public void setMinTemp(int minTemp){this.minTemp=minTemp;}

    public int getMinWind(){return minWind;}
    public int getMaxWind(){return maxWind;}
    public void setMinWind(int minWind){this.minWind=minWind;}
    public void setMaxWind(int maxWind){this.maxWind=maxWind;}

    public int getMaxRelwet(){return maxRelwet;}
    public int getMinRelwet(){return minRelwet;}
    public void setMaxRelwet(int maxRelwet){this.maxRelwet=maxRelwet;}
    public void setMinRelwet(int minRelwet){this.minRelwet=minRelwet;}

    public int getMinHeat(){return minHeat;}
    public int getMaxHeat(){return maxHeat;}
    public void setMinHeat(int minHeat){this.minHeat=minHeat;}
    public void setMaxHeat(int maxHeat){this.maxHeat=maxHeat;}



}
