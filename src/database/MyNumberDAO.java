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
        ArrayList<MyNumber> dataList = (ArrayList<MyNumber>)list;
        
        try {
            // SQL構文を登録 REPLACE
            ps = connection.prepareStatement(replaceSql);
            
            for (int i = 0; i < dataList.size(); i++) {
                ps.setInt(1,dataList.get(i).getID());
                ps.setLong(2,dataList.get(i).getMyNumber());
                ps.executeUpdate();
            }
            
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            
            // クローズ
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                throw new SQLException();
            }
            
            // クローズ
            ps = null;
            dataList = null;
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
