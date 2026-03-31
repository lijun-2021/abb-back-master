package com.abb_soft_01.mpdemo.entity;

import java.time.LocalDateTime; // 导入 LocalDateTime 类

public class abb_timedemo_0125 {
   private String ID;
   private long SN;
   private int Stationid;
   private LocalDateTime CreatDateTime;
   private LocalDateTime LastDateTime;
   private int mintutes;
   private float hours;

    public LocalDateTime getCreatDateTime() {
        return CreatDateTime;
    }

    public void setCreatDateTime(LocalDateTime creatDateTime) {
        CreatDateTime = creatDateTime;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public long getSN() {
        return SN;
    }

    public void setSN(long SN) {
        this.SN = SN;
    }

    public int getStationid() {
        return Stationid;
    }

    public void setStationid(int stationid) {
        Stationid = stationid;
    }

    public LocalDateTime getLastDateTime() {
        return LastDateTime;
    }

    public void setLastDateTime(LocalDateTime lastDateTime) {
        LastDateTime = lastDateTime;
    }

    public int getMintutes() {
        return mintutes;
    }

    public void setMintutes(int mintutes) {
        this.mintutes = mintutes;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "abb_timedemo_0125{" +
                "ID='" + ID + '\'' +
                ", SN=" + SN +
                ", Stationid=" + Stationid +
                ", CreatDateTime=" + CreatDateTime +
                ", LastDateTime=" + LastDateTime +
                ", mintutes=" + mintutes +
                ", hours=" + hours +
                '}';
    }

}

