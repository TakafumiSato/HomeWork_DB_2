/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Log;

/**
 *
 * @author 佐藤孝史
 * 
 * データベースへのアクセスを管理
 */
public class DBController {
    
    Log log = null;
    private Connection connection = null;
    
    
    public DBController() {
        this.log = new Log(DBController.class.getName(), DBController.class.getName()+".log");
    }
    
    /*
    データベースをオープン
    */
    public Connection openDB() throws SQLException {
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynumberdb?useUnicode=true&characterEncoding=utf8","TakafumiSato","1234567");
        } catch(SQLException e) {
            e.printStackTrace();
            log.log(Level.SEVERE, "データベースオープン失敗", e);
            throw new SQLException();
        }
        
        return connection;
    }
    
    /*
    データベースをクローズ
    */
    public void closeDB() {
        
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection = null;
        }
    }
}
