package com.example.mkai.pry;


import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonDescriptor {
   // private ImageView photo;
    private String name;
    private String city;
    private String birthday;

   /* public PersonDescriptor() {
        name.setText("Вася Пупкин");
        birthday.setText("13 марта");
        city.setText("Москва");
    }*/
    public PersonDescriptor() {
        name = new String("Вася Пупкин");
        birthday = new String("13 марта");
        city = new String("Москва");
    }

    public String getName() {
        return name;
    }
    public String getBirthday() {
        return birthday;
    }
    public String getCity() {
        return city;
    }

}
