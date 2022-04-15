package com.fixes.DB.Interfaces;

public interface Sensors extends DataBase {
    boolean add(String id,String name,String location);//Добавляется датчик с его местоположением
    boolean set_location(String id,String new_location);// Изменяется положение датчика по его id
    boolean set_name(String id,String new_name);// Изменяется имя датчика по его id
    boolean delete(String id);//Удаляется датчик по его id
}
