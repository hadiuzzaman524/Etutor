package com.nested.etutor.models;

public class UploadStudentPostInformation {

    private String name,address, mobile, salary, institute, _class, subject,imagelink,date,profileinfo;

    public UploadStudentPostInformation() {
    }

    public UploadStudentPostInformation(String name, String address, String mobile, String salary, String institute, String _class, String subject, String imagelink, String date, String profileinfo) {
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.salary = salary;
        this.institute = institute;
        this._class = _class;
        this.subject = subject;
        this.imagelink = imagelink;
        this.date=date;
        this.profileinfo=profileinfo;
    }

    public String getProfileinfo() {
        return profileinfo;
    }

    public void setProfileinfo(String profileinfo) {
        this.profileinfo = profileinfo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getSalary() {
        return salary;
    }

    public String getInstitute() {
        return institute;
    }

    public String get_class() {
        return _class;
    }

    public String getSubject() {
        return subject;
    }

    public String getImagelink() {
        return imagelink;
    }
}
