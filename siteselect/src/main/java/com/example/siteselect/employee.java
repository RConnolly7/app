package com.example.siteselect;



    // Creating an entity Employee
    public class employee {

        public employee() {}

        // Parameterized Constructor
        // to assign the values
        // to the properties of
        // the entity
        public employee(Integer id, String fname,String lname, String username){
            super();
            this.id = id;
            this.fname = fname;
            this.lname = lname;
            this.username = username;
        }
        private Integer id;
        private String fname;
        private String lname;
        private String username;

        // Overriding the toString method
        // to find all the values
        @Override
        public String toString(){
            return "Employee [id="
                    + id + ", fname="
                    + fname + ", lname="
                    + lname + ", username="
                    + username + "]";
        }

        // Getters and setters of
        // the properties
        public Integer getId(){
        return id;
        }

        public void setId(Integer id){
            this.id = id;
        }

        public String getfname(){
            return fname;
        }

        public void setfname(String fname){this.fname = fname;
        }

        public String getlname(){
            return lname;
        }
        public void setlname(String lname){
            this.lname = lname;
        }
        public String getusername(){
            return username;
        }

        public void setusername(String username){
            this.username = username;
        }
    }

