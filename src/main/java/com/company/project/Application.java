package com.company.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        String s = "2020-04-14 16:55:47.0";
        if (s.length() > 19) {
            System.out.println(s.substring(0, 19));
        }
        Date currentTime = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");


        Date date = new Date();
        //格式里的时如果用hh表示用12小时制，HH表示用24小时制。MM必须是大写!
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
        String strDate = sdf.format(date);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = sdf.parse(strDate, pos);
        System.out.println(strtodate);


        SpringApplication.run(Application.class, args);
    }
}

