package com.course.myapplication1204;

public class DiaryInfo {
    String name;
    String stdin;
    String lati;
    String longi;
    byte[] image;

    public DiaryInfo(String name, String stdin, String lati,String longi, byte[] image) {
        this.name = name;
        this.stdin = stdin;
        this.lati = lati;
        this.longi = longi;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStdin() {
        return stdin;
    }

    public void setStdin(String stdin) {
        this.stdin = stdin;
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "DiaryInfo{" +
                "name='" + name + '\'' +
                ", stdin='" + stdin + '\'' +
                ", lati='" + lati + '\'' +
                ", longi='" + longi + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
