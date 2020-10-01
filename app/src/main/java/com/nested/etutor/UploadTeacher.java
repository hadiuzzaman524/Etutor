package com.nested.etutor;

public class UploadTeacher {
    private String tname, taddress, teducation, t_class, tsubject, tmobile, tsalary, timagelink, date,userprofile;

    public UploadTeacher() {
    }

    public UploadTeacher(String tname, String taddress, String teducation, String t_class, String tsubject, String tmobile, String tsalary, String timagelink, String date,String userprofile) {
        this.tname = tname;
        this.taddress = taddress;
        this.teducation = teducation;
        this.t_class = t_class;
        this.tsubject = tsubject;
        this.tmobile = tmobile;
        this.tsalary = tsalary;
        this.timagelink = timagelink;
        this.date = date;
        this.userprofile=userprofile;
    }

    public String getUserprofile() {
        return userprofile;
    }

    public void setUserprofile(String userprofile) {
        this.userprofile = userprofile;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTaddress(String taddress) {
        this.taddress = taddress;
    }

    public void setTeducation(String teducation) {
        this.teducation = teducation;
    }

    public void setT_class(String t_class) {
        this.t_class = t_class;
    }

    public void setTsubject(String tsubject) {
        this.tsubject = tsubject;
    }

    public void setTmobile(String tmobile) {
        this.tmobile = tmobile;
    }

    public void setTsalary(String tsalary) {
        this.tsalary = tsalary;
    }

    public void setTimagelink(String timagelink) {
        this.timagelink = timagelink;
    }

    public String getTname() {
        return tname;
    }

    public String getTaddress() {
        return taddress;
    }

    public String getTeducation() {
        return teducation;
    }

    public String getT_class() {
        return t_class;
    }

    public String getTsubject() {
        return tsubject;
    }

    public String getTmobile() {
        return tmobile;
    }

    public String getTsalary() {
        return tsalary;
    }

    public String getDate() {
        return date;
    }

    public String getTimagelink() {
        return timagelink;
    }

}
