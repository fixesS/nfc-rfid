package com.fixes.Main;

import com.fixes.DB.DBItems;
import com.fixes.DB.DBSensors;
import com.fixes.DB.DBStaff;
import com.fixes.GUI.MainFrame;
import com.fixes.Properties.Place;
import com.fixes.Reader.Readers;

import javax.xml.namespace.QName;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {


    private MainFrame mainFrame;



    private Handler fileHandler;
    private Logger logger = Logger.getLogger(Main.class.getName());
    private SimpleFormatter simpleFormatter = new SimpleFormatter();
    private final DBItems dbItems = new DBItems();
    private final DBStaff dbStaff = new DBStaff();
    private final DBSensors dbSensors = new DBSensors();

    private Place place = new Place();// обьект для работы с конфигом
    private Readers readers = new Readers();//обьект для работы с считывателями

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void main() {
        try{
            fileHandler = new FileHandler("src/main/resources/Log/Mainlog.txt",true);
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        }catch (Exception exception){exception.printStackTrace();}

        readers.setMain(this);
        ConnectionToDB();

    }
    public void stage_0(String reader_id,String tag_id){
        switch (reader_id){
            case("0"):
                setItemLocation(tag_id,place.getProperty("place.lobby"));
            case ("1"):
                setItemLocation(tag_id,place.getProperty("place.floor1"));
            case ("2"):
                setItemLocation(tag_id,place.getProperty("place.floor2"));
        }
    }

    public void setItemLocation(String tag_id,String new_location){
        dbItems.set_location(tag_id,new_location);
        logger.info("Laptop with tag_id(item_id) - "+tag_id+" has been moved to "+new_location);
    }

    public void registerReader(String id,String name, String location,String hostname, int port, int timeout){
        dbSensors.add(id,name,location);
        readers.addReader(hostname,port,timeout,id);
        logger.info("Reader with name - "+name+", id - "+id+", location - "+location+" has been registered("+hostname+":"+port+" timeout:"+timeout+")" );
    }

    public void registerItem(String id,String name,String location){
        dbItems.add("0","Acer",location);
        logger.info("Laptop with name - "+name+", id - "+id+", location - "+location+" has been registered" );
    }

    public void ConnectionToDB(){
        dbItems.connection();
        dbStaff.connection();
        dbSensors.connection();
    }

    public void CloseConnectionToDB(){
        dbItems.close();
        dbStaff.close();
        dbSensors.close();
    }
}
