package com.example.cs496week1;

import java.util.ArrayList;

public class Commons {
    public static ArrayList<People> peopleArrayList;
    public static ArrayList<People> selectedArrayList;

    public static class People{
        private Integer id;
        private String name;
        private String univ;
        private String sid;
        private String pSrc;
        private String numb;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUniv() {
            return univ;
        }

        public void setUniv(String university) {
            this.univ = university;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getNumb() {
            return numb;
        }

        public void setNumb(String numb) {
            this.numb = numb;
        }
    }
}

