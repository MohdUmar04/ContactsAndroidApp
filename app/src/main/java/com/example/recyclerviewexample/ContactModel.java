package com.example.recyclerviewexample;

public class ContactModel {
    int img;
    String name,number;

    public ContactModel(int image, String nm, String num){
        img = image;
        name=nm;
        number=num;
    }
    public ContactModel(String nm, String num){
        img = R.drawable.user;
        name=nm;
        number=num;
    }

}
