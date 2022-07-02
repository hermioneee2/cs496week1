package com.example.cs496week1.ui.main;

public class CardItem {

    private int drawableId;
//    private String name;
//    private String location;

//    public CardItem(int drawableId, String name, String location) {
    public CardItem(int drawableId) {
        this.drawableId = drawableId;
//        this.name = name;
//        this.location = location;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLocation() {
//        return location;
//    }
}