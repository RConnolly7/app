package com.example.siteselect;

import java.util.ArrayList;
import java.util.List;

// Class to store the list of all the sites in an Array List
public class SitesContainer {
    private List<site> sitelist;
    // Method to return the list of employees
    public List<site> getSiteList() {
        if (sitelist == null) {
            sitelist = new ArrayList<>();
        }
        return sitelist;
    }

    site[] ImportSites(){
        site SitesContainer[] = new site[0];

        return SitesContainer;
    }

    public int getsize() {
        return sitelist.size();
    }

    public void setEmployeeList(List<site> sitelist){
        this.sitelist = sitelist;
    }
    public static SitesContainer dummylist(SitesContainer dummy){
        dummy.getSiteList().add(new site(1,1,"location", "1-02-2020","1-02-2020","cabinhamshire"));
        dummy.getSiteList().add(new site(2,2,"site", "1-03-2020","1-03-2020","cottagehamshire"));
        dummy.getSiteList().add(new site(1,3,"Goodnes", "1-02-2020","1-02-2025","porkhamshire"));
        return dummy;
    }
}