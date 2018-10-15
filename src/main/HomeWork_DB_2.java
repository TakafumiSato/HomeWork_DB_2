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
        
        ArrayList<StaffMaster> staffList = new ArrayList<StaffMaster>();
        ArrayList<MyNumber> myNumberList = new ArrayList<MyNumber>();
        ArrayList<StaffMyNumber> staffMyNumberList = new ArrayList<StaffMyNumber>();
        
        try {
            // データベースへ接続
            connection = dbController.openDB();
            // コミットを手動に
            connection.setAutoCommit(false);         

            // 従業員マスターのテーブルデータを取得
            staffList = getTable(new StaffMasterDAO(connection));
            // マイナンバーのテーブルデータを取得
            myNumberList = getTable(new MyNumberDAO(connection));

            // マージ
            staffMyNumberList = Merge.mergeStaffMasterAndMyNumber(staffList, myNumberList);

            // ソート
            Sort.sortAge(staffMyNumberList, Integer.parseInt(args[0]));

            // テーブルにセット
            setTable(new StaffMyNumberDAO(connection), staffMyNumberList);
            
            // コミット
            connection.commit();
            
        } catch (Exception e) {
            
            // エラーが出た場合ロールバック
            if (connection != null)
                connection.rollback();
            
            e.printStackTrace();
            throw new Exception();
            
        } finally {
            
            // クローズ
            if (connection != null)
                connection.close();
            
        }
    }
    
    
    
    private static ArrayList getTable(TableController tc) throws SQLException {
        return tc.getTable();
    }
    
    private static void setTable(TableController tc, ArrayList list) throws SQLException {
        tc.setTable(list);
    }
    
}
