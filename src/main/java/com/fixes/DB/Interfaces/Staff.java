package com.fixes.DB.Interfaces;

public interface Staff extends DataBase{
    boolean add(String id, String name, String surname,String position);//Добавляется сотрудник с его id, именем, фамилией, должностью.
    boolean set_id(String id,String name,String surname, String new_id);//Изменяется id сотрудника по его id, имени, фамилии
    boolean set_name(String id,String surname,String new_name);//Изменяется имя сотрудника по его id, фамилии
    boolean set_surname(String id,String name,String new_surname);// Изменяется фамилия сотрудника по его id, имени
    boolean set_position(String id,String name,String surname,String new_position);// Изменяется должность сотрудника по его id, имени, фамилии
    boolean delete(String id,String name,String surname);//Удаляется сотрудник по его id, имени, фамилии

}
