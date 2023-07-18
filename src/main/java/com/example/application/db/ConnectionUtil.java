package com.example.application.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
    public Connection conn(String dbname, String user, String pass){
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=public" + dbname, user, pass);
            if(conn != null){
                System.out.println("Conexiune reusita");
            }else {
                System.out.println("Conexiune esuata");
            }
        }  catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }
}
