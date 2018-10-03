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

/**
 *
 * @author 佐藤孝史
 */
public class MyNumberDAO extends BaseDAO {
    
    public MyNumberDAO(Connection connection) {
        
        this.connection = connection;
        this.tableName = "mynumber_table";
        this.selectSql = "SELECT * FROM mynumber_table";
        this.replaceSql = "REPLACE INTO mynumber_table (id,myNumber) VALUES(?,?)";
    }
    
    @Override
    public void setTable(ArrayList<?> list) throws SQLException {
        
        PreparedStatement ps = null;
        Connection connection = null;
        
        // データベースオープン
        DBController dbController = new DBController();
        
        connection = dbController.openDB();
        try {
            // オートコミットをオフ
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, "オートコミット失敗", ex);
            throw ex;
        }
        
        try {
            // SQL構文を登録 REPLACE
            ps = connection.prepareStatement(
                    "REPLACE INTO mynumber_table (id,myNumber) VALUES(?,?)");
            
            ArrayList<MyNumber> dataList = (ArrayList<MyNumber>)list;
            
            for (int i = 0; i < dataList.size(); i++) {
                ps.setInt(1,dataList.get(i).getID());
                ps.setLong(2,dataList.get(i).getMyNumber());
                ps.executeUpdate();
            }
            
            dataList = null;
            
            // コミット
            connection.commit();
            
        } catch (SQLException ex) {
            try {
                // エラーの場合ロールバックし、登録を無効
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, "ロールバック失敗", ex1);
                throw ex1;
            }
            
            Logger.getLogger(DBController.class.getName()).log(Level.INFO, "例外のスローを捕捉", ex);
            throw ex;
            
        } finally {
            
            try {
                // PreparedStatement クローズ
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, "クローズ失敗", ex);
                throw ex;
            }
            
            // データベースクローズ
            dbController.closeDB();
            dbController = null;
            connection = null;
            ps = null;
        }
    }
    
    @Override
    protected ArrayList<?> readResultSet(ResultSet rs) throws SQLException {
        
        ArrayList<MyNumber> list = new ArrayList<>();
        
        while (rs.next()) {
            list.add(new MyNumber(rs.getInt("id"), rs.getLong("myNumber")));
        }
        
        return list;
    }
}
