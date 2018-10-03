/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import data.StaffMaster;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author 佐藤孝史
 */
public abstract class BaseDAO implements TableController {
    
    protected Connection connection = null;
    protected String tableName = null;
    protected String selectSql = null;
    protected String replaceSql = null;
    
    @Override
    public ArrayList<?> getTable() throws SQLException {
        
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<?> list = new ArrayList<>();
        
        // データの取得
        stmt = connection.createStatement();
        rs = stmt.executeQuery(selectSql);

        list = readResultSet(rs);
        
        // クローズ
        stmt.close();
        rs.close();
        stmt = null;
        rs = null;
        
        return list;
    }
    
//    public void setTable(ArrayList<?> list) throws SQLException {
//        
//        PreparedStatement ps = null;
//        
//        // SQL構文を登録 REPLACE
//        ps = connection.prepareStatement(replaceSql);
//
//        ArrayList<StaffMaster> dataList = (ArrayList<StaffMaster>)list;
//
//        for (int i = 0; i < dataList.size(); i++) {
//            ps.setInt(1,dataList.get(i).getID());
//            ps.setString(2,dataList.get(i).getName());
//            ps.setString(3,dataList.get(i).getGender());
//            ps.setInt(4,dataList.get(i).getBirth());
//            ps.executeUpdate();
//        }
//
//        // クローズ
//        ps.close();
//        ps = null;
//        dataList = null;
//    }
    
    protected abstract ArrayList<?> readResultSet(ResultSet rs) throws SQLException;
}
