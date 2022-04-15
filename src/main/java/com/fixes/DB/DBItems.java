package com.fixes.DB;

import com.fixes.DB.Interfaces.Items;
import com.fixes.Main.Main;

import javax.print.DocFlavor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DBItems implements Items {

    private Handler fileHandler;
    private Logger logger = Logger.getLogger(DBItems.class.getName());
    private SimpleFormatter simpleFormatter = new SimpleFormatter();

    public DBItems(){
        try{
            fileHandler = new FileHandler("src/main/resources/Log/DBItemslog.txt",true);
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        }catch (Exception exception){exception.printStackTrace();}
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;
    private String table = "items";
    @Override
    public boolean connection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/Database/Database");
            logger.info("Connect successful!");
            return true;

        }catch (Exception exception){logger.warning("Error: "+exception);}
        return false;
    }
    @Override
    public boolean add(String id,String name,String location){
        if(!check_if_exist(id)){
            try{
                String query = String.format("INSERT INTO %s (item_id,name,location) VALUES ( %s, '%s', '%s');",table,id,name,location);
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);

                return true;

            }catch (Exception exception){logger.warning("Error: "+exception);}
            return false;
        }else{
            logger.info("Item with item_id - "+id+" is in db");
            return false;
        }

    }
    @Override
    public boolean set_id(String id,String new_id) {
        try {
            String query = String.format("UPDATE %s SET item_id = %s WHERE item_id = %s;",table,new_id,id);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;

        }catch (Exception exception){logger.warning("Error: "+exception);}
        return false;
    }
    @Override
    public boolean set_name(String id,String new_name) {
        try{
            String query = String.format("UPDATE %s SET name = %s WHERE item_id = %s;",table,new_name,id);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;

        }catch (Exception exception){logger.warning("Error: "+exception);}
        return false;
    }
    @Override
    public boolean set_location(String id, String new_location) {
        try{
            String query = String.format("UPDATE %s SET location = %s WHERE item_id = %s;",table,new_location,id);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;

        }catch (Exception exception){logger.warning("Error: "+exception);}
        return false;
    }
    @Override
    public boolean delete(String id) {
        try{
            String query = String.format("DELETE FROM %s WHERE item_id = %s;",table,id);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;

        }catch (Exception exception){logger.warning("Error: "+exception);}
        return false;
    }
    @Override
    public List<String> get(String id) {
        List<String> result= new ArrayList<>();
        try{
            String query = String.format("SELECT * FROM %s WHERE item_id = %s;",table,id);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                String ID = String.valueOf(resultSet.getInt("id"));
                String item_id = String.valueOf(resultSet.getInt("item_id"));
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");

                result.add(ID);
                result.add(item_id);
                result.add(name);
                result.add(location);
            }

        }catch (Exception exception){logger.warning("Error: "+exception);}
        return result;
    }
    @Override
    public List<List<String>> get_all() {
        List<List<String>> result = new ArrayList<>();
        try{
            String query = String.format("SELECT * FROM %s ORDER BY item_id;",table);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                List<String> preresult= new ArrayList<>();
                String ID = String.valueOf(resultSet.getInt("id"));
                String item_id = String.valueOf(resultSet.getInt("item_id"));
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");

                preresult.add(ID);
                preresult.add(item_id);
                preresult.add(name);
                preresult.add(location);
                result.add(preresult);
            }

        }catch (Exception exception){logger.warning("Error: "+exception);}
        return result;
    }
    @Override
    public boolean close(){
        try{
            connection.close();
            return true;

        }catch (Exception exception){logger.warning("Error: "+exception);}
        return false;
    }

    @Override
    public boolean check_if_exist(String id) {
        try{
            String query = String.format("SELECT * FROM %s WHERE item_id = %s",table,id);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()){
                return true;
            }else {
                return false;
            }

        }catch (Exception exception){logger.warning("Error: "+exception);}
        return false;
    }
}
