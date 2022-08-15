package com.example.siteselect;

public class SiteDAO {

    private static SitesContainer list= new SitesContainer();
    static {

        list.getSiteList().add(new site(1,1,"location", "1-02-2020","1-02-2020","cabinhamshire"));
        list.getSiteList().add(new site(2,2,"site", "1-03-2020","1-03-2020","cottagehamshire"));
        list.getSiteList().add(new site(3,3,"Goodnes", "1-02-2020","1-02-2025","prokhamshire"));
    }

    public SitesContainer getAllSites()
    {

        return list;
    }

}
