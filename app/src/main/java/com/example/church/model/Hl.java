package com.example.church.model;

public class Hl {
    public Hl(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public String getNumber(){
        String number;
        number = this.title.substring(0,3);
        number = "H"+number;
        return number;
    }

    String title;


}
