package com.fixes.Properties;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Place extends Properties {

    private File file = new File("src/main/resources/Config/config.properties");

    public Place (){
        setFile();
    }

    public void setFile(){
        try{
            load(new FileReader(file)); //Загрузка конфига(config.properties), чтобы можно было прочитать из него информацию

        }catch (Exception exception){exception.printStackTrace();}
    }
}