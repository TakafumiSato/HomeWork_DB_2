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

/**
 *
 * @author 佐藤孝史
 * 
 * データベースへのアクセスを管理
 */
public class DBController {
    
    private Connection connection = null;
    
    /*
    データベースをオープン
    */
    public Connection openDB() throws SQLException {
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynumberdb?useUnicode=true&characterEncoding=utf8","TakafumiSato","1234567");
            
            System.out.println("データベース接続成功");
        } catch (SQLException se) {

            try {
                // 接続に失敗したらクローズ
                connection.close();
                connection = null;
            } catch (SQLException ex) {
                Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, "データベースクローズ失敗", ex);
                throw ex;
            }
            
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, "データベース接続失敗", se);
            throw se;
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
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection = null;
        }
    }
}
