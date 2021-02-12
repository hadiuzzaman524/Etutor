package com.nested.etutor.models;

public class PersonalUserInformations {
    private String uname;
    private String uemail;


    private String databasename;
    private String path;
    private String imagepath;
    private String joindate;
    private String qualification;
    private String about;
    private String contact;
    private String birthday;


    public void setDatabasename(String databasename) {
        this.databasename = databasename;
    }

    public String getDatabasename() {
        return databasename;
    }


    public PersonalUserInformations(String uname, String uemail, String databasename, String path, String joindate, String qualification, String about, String contact, String birthday, String imagepath) {
        this.uname = uname;
        this.uemail = uemail;
        this.databasename = databasename;
        this.path = path;
        this.joindate = joindate;
        this.qualification = qualification;
        this.about = about;
        this.contact = contact;
        this.birthday = birthday;
        this.imagepath = imagepath;

    }


    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public String getJoindate() {
        return joindate;
    }

    public String getQualification() {
        return qualification;
    }

    public String getAbout() {
        return about;
    }

    public String getContact() {
        return contact;
    }

    public String getBirthday() {
        return birthday;
    }

    public PersonalUserInformations() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUname() {
        return uname;
    }

    public String getUemail() {
        return uemail;
    }
}
