package com.fixes.DB;

import com.fixes.DB.Interfaces.Staff;
import com.fixes.Main.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DBStaff implements Staff {

    private Handler fileHandler;
    private Logger logger = Logger.getLogger(DBStaff.class.getName());
    private SimpleFormatter simpleFormatter = new SimpleFormatter();

    public DBStaff(){
        try{
            fileHandler = new FileHandler("src/main/resources/Log/DBStafflog.txt",true);
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
    private String table = "staff";

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
    public boolean add(String id, String name, String surname, String position) {
        if(!check_if_exist(id)){
            try{
                String query = String.format("INSERT INTO %s (worker_id,name,surname,position) VALUES (%s, '%s', '%s', '%s');",table,id,name,surname,position);
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                return true;

            }catch (Exception exception){logger.warning("Error: "+exception);}
            return false;
        }else{
            logger.info("Worker with worker_id - "+id+" is in db");
            return false;
        }

    }
    @Override
    public boolean set_id(String id, String name, String surname, String new_id) {
        try{
            String query = String.format("UPDATE %s SET worker_id = %s WHERE worker_id = %s AND name = %s AND surname = %s;",table,new_id,id,name,surname);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;

        }catch (Exception exception){logger.warning("Error: "+exception);}
        return false;
    }
    @Override
    public boolean set_name(String id, String surname, String new_name) {
        try{
            String query = String.format("UPDATE %s SET name = %s WHERE worker_id = %s AND surname = %s;",table,new_name,id,surname);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;

        }catch (Exception exception){logger.warning("Error: "+exception);}
        return false;
    }
    @Override
    public boolean set_surname(String id, String name, String new_surname) {
        try{
            String query = String.format("UPDATE %s SET surname = %s WHERE worker_id = %s AND name = %s;",table,new_surname,id,name);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;

        }catch (Exception exception){logger.warning("Error: "+exception);}
        return false;
    }
    @Override
    public boolean set_position(String id, String name, String surname, String new_position) {
        try{
            String query = String.format("UPDATE %s SET position = %s WHERE worker_id = %s AND name = %s AND surname = %s;",table,new_position,id,name,surname);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;

        }catch (Exception exception){logger.warning("Error: "+exception);}
        return false;
    }
    @Override
    public boolean delete(String id, String name, String surname) {
        try{
            String query = String.format("DELETE FROM %s WHERE worker_id = %s AND name = %s AND surname = %s",table,id,name,surname);
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
            String query = String.format("SELECT * FROM %s WHERE worker_id = %s;",table,id);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                String ID = String.valueOf(resultSet.getInt("id"));
                String worker_id = String.valueOf(resultSet.getInt("worker_id"));
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String position = resultSet.getString("position");

                result.add(ID);
                result.add(worker_id);
                result.add(name);
                result.add(surname);
                result.add(position);
            }

        }catch (Exception exception){logger.warning("Error: "+exception);}
        return result;
    }
    @Override
    public List<List<String>> get_all() {
        List<List<String>> result= new ArrayList<>();
        try{
            String query = String.format("SELECT * FROM %s ORDER BY worker_id;",table);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                List<String> preresult= new ArrayList<>();
                String ID = String.valueOf(resultSet.getInt("id"));
                String worker_id = String.valueOf(resultSet.getInt("worker_id"));
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String position = resultSet.getString("position");

                preresult.add(ID);
                preresult.add(worker_id);
                preresult.add(name);
                preresult.add(surname);
                preresult.add(position);
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
            String query = String.format("SELECT * FROM %s WHERE worker_id = %s",table,id);
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
