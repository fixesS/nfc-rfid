package com.fixes.DB.Interfaces;

import java.util.List;

public interface DataBase {
    boolean connection();
    List<String> get(String id);
    List<List<String>> get_all();
    boolean close();
    boolean check_if_exist(String id);
}
