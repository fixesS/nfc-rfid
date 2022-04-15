package com.fixes.DB.Interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Items extends DataBase {
    boolean add(String id,String name,String location) throws SQLException;//Добавляется новая вещь с ее номером и именем
    boolean set_id(String id,String new_id);//Изменяется номер вещи, указывая старый
    boolean set_name(String id,String new_name);//Изменяется имя вещи указывая номер
    boolean set_location(String id,String new_location);//Изменяется локацию вещи по ее номеру
    boolean delete(String id);//Удаляется вещь по номеру
}

