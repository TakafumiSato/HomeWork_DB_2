/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import data.MyNumber;
import data.StaffMaster;
import java.io.IOException;
import java.util.logging.FileHandler;
import util.Log;

/**
 *
 * @author 佐藤孝史
 */
public class MyNumberDAO implements TableController {
    
    private Log log = null;
    private Connection connection = null;
    private String tableName = null;
    private String selectSql = null;
    private String replaceSql = null;
    
    public MyNumberDAO(Connection connection) {
        
        this.log = new Log(MyNumberDAO.class.getName(), MyNumberDAO.class.getName()+".log");
        this.connection = connection;
        this.tableName = "mynumber_table";
        this.selectSql = "SELECT * FROM mynumber_table";
        this.replaceSql = "REPLACE INTO mynumber_table (id,myNumber) VALUES(?,?)";
    }
    
    @Override
    public ArrayList getTable() throws SQLException {
        
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<MyNumber> list = new ArrayList<>();
        
        try {
            // データの取得
            stmt = connection.createStatement();
            rs = stmt.executeQuery(selectSql);

            while (rs.next()) {
                list.add(new MyNumber(rs.getString("id"), rs.getString("myNumber")));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            log.log(Level.SEVERE, "SQL例外です", e);
            throw new SQLException();
        } finally {
            // クローズ
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                log.log(Level.SEVERE, "Statement クローズ失敗", e);
            }
            try {
                if  (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                log.log(Level.SEVERE, "ResultSet クローズ失敗", e);
            }
            
            stmt = null;
            rs = null;
        }
        
        return list;
    }
    
    @Override
    public void setTable(ArrayList list) throws SQLException {
        
        PreparedStatement ps = null;
        ArrayList<MyNumber> dataList = (ArrayList<MyNumber>)list;
        
        try {
            // SQL構文を登録 REPLACE
            ps = connection.prepareStatement(replaceSql);
            
            for (int i = 0; i < dataList.size(); i++) {
                ps.setString(1,dataList.get(i).getID());
                ps.setString(2,dataList.get(i).getMyNumber());
                ps.executeUpdate();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            log.log(Level.SEVERE, "SQL例外です", e);
            throw new SQLException();
        } finally {
            
            // クローズ
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
                log.log(Level.SEVERE, "SQL例外です", e);
                throw new SQLException();
            }
            
            // クローズ
            ps = null;
            dataList = null;
        }
    }
}
