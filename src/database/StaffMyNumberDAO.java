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
import data.StaffMyNumber;

/**
 *
 * @author 佐藤孝史
 */
public class StaffMyNumberDAO extends BaseDAO {
    
    public StaffMyNumberDAO(Connection connection) {
        
        this.connection = connection;
        this.tableName = "staffmynumber_table";
        this.selectSql = "SELECT * FROM staffmynumber_table";
        this.replaceSql = "REPLACE INTO staffmynumber_table (id,name,gender,birth,myNumber) VALUES(?,?,?,?,?)";
    }
    
    @Override
    public void setTable(ArrayList<?> list) throws SQLException {
        
        PreparedStatement ps = null;
        ArrayList<StaffMyNumber> dataList = (ArrayList<StaffMyNumber>)list;
        
        try {
            // SQL構文を登録 REPLACE
            ps = connection.prepareStatement(replaceSql);

            for (int i = 0; i < dataList.size(); i++) {
                ps.setInt(1,dataList.get(i).getID());
                ps.setString(2,dataList.get(i).getName());
                ps.setString(3,dataList.get(i).getGender());
                ps.setInt(4,dataList.get(i).getBirth());
                ps.setLong(5,dataList.get(i).getMyNumber());
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
            ps = null;
            dataList = null;
        }
    }
    
    @Override
    protected ArrayList<?> readResultSet(ResultSet rs) throws SQLException {
        
        ArrayList<StaffMyNumber> list = new ArrayList<>();
        
        try {
            while (rs.next()) {
                list.add(new StaffMyNumber(rs.getInt("id"),rs.getString("name"),rs.getString("gender"),rs.getInt("birth"),rs.getLong("myNumber")));
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
        
        return list;
    }
}
