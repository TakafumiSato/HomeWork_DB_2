/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import util.Sort;
import util.Merge;
import database.StaffMasterDAO;
import database.MyNumberDAO;
import database.StaffMyNumberDAO;
import database.TableController;
import data.MyNumber;
import data.StaffMaster;
import data.StaffMyNumber;
import database.DBController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 佐藤孝史
 */
public class HomeWork_DB_2 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        Connection connection = null;
        DBController dbController = new DBController();
        
        TableController dao = null;
        
        ArrayList<StaffMaster> staffList = new ArrayList<StaffMaster>();
        ArrayList<MyNumber> myNumberList = new ArrayList<MyNumber>();
        ArrayList<StaffMyNumber> staffMyNumberList = new ArrayList<StaffMyNumber>();
        
        try {
            // データベースへ接続
            connection = dbController.openDB();
            connection.setAutoCommit(false);
            
            dao = new StaffMasterDAO(connection);
            staffList = (ArrayList<StaffMaster>)dao.getTable();
            dao = new MyNumberDAO(connection);
            myNumberList = (ArrayList<MyNumber>)dao.getTable();
            
            // マージ
            staffMyNumberList = Merge.mergeStaffMasterAndMyNumber(staffList, myNumberList);

            // ソート
            Sort.sortAge(staffMyNumberList, Sort.SortMode.BUCKET);

            // テーブルにセット
            dao = new StaffMyNumberDAO(connection);
            dao.setTable(staffMyNumberList);
            
            connection.commit();
            
        } catch (Exception ex) {
            if (connection != null)
                connection.rollback();
            ex.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }
    }
    
}
