package com.example.siteselect;

public class site {

    public site() {}

    // Parameterized Constructor
    // to assign the values
    // to the properties of
    // the entity
    public site(Integer id,Integer Sitenum, String Sitename,String startdate, String EndDate,String location){
        super();
        this.id = id;
        this.Sitenum = Sitenum;
        this.Sitename = Sitename;
        this.startdate = startdate;
        this.EndDate = EndDate;
        this.location = location;
    }
    private Integer id;
    private Integer Sitenum;
    private String Sitename;
    private String startdate;
    private String location;
    private String EndDate;

    // Overriding the toString method
    // to find all the values
    @Override
    public String toString(){
        return "Site [id="+ id
                + ", Sitename="+ Sitename
                + ", startdate="+ startdate
                + ", Endate="+ EndDate
                + ", location="+ location+ "]";
    }

    public String OutName() {
        return Sitenum + " " + location;
    }

    // Getters and setters of
    // the properties
    public Integer getId(){
        return id;
    }
    public String getSitename(){
        return Sitename;
    }
    public String getlocation(){
        return location;
    }
    public String getstartdate(){
        return startdate;
    }
    public String getEndate(){
        return EndDate;
    }

    public void setId(Integer id){this.id = id;}
    public void setSitename(String Sitename){this.Sitename = Sitename;}
    public void setstartdate(String startdate){
        this.startdate = startdate;
    }
    public void setEndDate(String EndDate){
        this.EndDate = EndDate;
    }
    public void setlocation(String location){
        this.location = location;
    }
    }
