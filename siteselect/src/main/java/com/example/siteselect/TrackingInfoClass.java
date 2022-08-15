package com.example.siteselect;

public class TrackingInfoClass {
    private int empid;
    private String emplname;
    private String currentsitenm;
    private int siteid;

    public TrackingInfoClass(int empid, String emplname, String currentsitenm, int siteid) {
        super();
        this.empid = empid;
        this.emplname = emplname;
        this.currentsitenm = currentsitenm;
        this.siteid = siteid;
    }

    public TrackingInfoClass(String emplname, String currentsitenm, int siteid) {
        super();
        this.emplname = emplname;
        this.currentsitenm = currentsitenm;
        this.siteid = siteid;
    }

    public TrackingInfoClass(String emplname){
        super();
        this.emplname = emplname;
    }



    public String getempnm(){
        return this.emplname;
    }
    public int getEmpid(){return this.empid;}
    public String getcurrentsitenm(){
        return this.currentsitenm;
    }
    public int getsiteid(){
        return this.siteid;
    }

    public void setempnm(String emplname){
        this.emplname = emplname;
    }
    public void setcurrentsitenm(String currentsitenm){
        this.currentsitenm = currentsitenm;
    }
    public void setsiteid(int siteid){
        this.siteid = siteid;
    }
    public void setEmpid(int empid){this.empid = empid;}

    public String asstring(){
        String output = "Employee " + this.emplname + " Current Site " + this.currentsitenm + " Current site ID " + this.siteid;
        return output;
    }
}
