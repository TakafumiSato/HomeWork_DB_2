/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import data.MyNumber;
import data.StaffMaster;
import data.StaffMyNumber;
import java.util.ArrayList;

/**
 *
 * @author 佐藤孝史
 */
public class Merge {
    
    public static ArrayList<StaffMyNumber> mergeStaffMasterAndMyNumber(ArrayList<StaffMaster> staffList, ArrayList<MyNumber> myNumberList) {
        
        // StaffMasterのIDと照らし合わせて整列させた個人番号を配列で確保
        long[] numbers = new long[staffList.size()];
        for (int i = 0; i < myNumberList.size(); i++) {
            
            numbers[searchID(staffList, myNumberList.get(i).getID())] = myNumberList.get(i).getMyNumber();
        }
        
        // StaffMasterの従業員番号から年齢までと整列させたnumbers(MyNumber)を
        // StaffMyNumberへと落し込み
        ArrayList<StaffMyNumber> resultList = new ArrayList<StaffMyNumber>();
        for (int i = 0; i < staffList.size(); i++) {
            
            resultList.add(new StaffMyNumber(staffList.get(i).getID(),
                                                    staffList.get(i).getName(),
                                                    staffList.get(i).getGender(),
                                                    staffList.get(i).getBirth(),
                                                    numbers[i]));
        }
        
        numbers = null;
        
        return resultList;
    }
    
    /*
    StaffMasterをID検索
    引数:ArrayList<StaffMaster>,int
    */
    private static int searchID(ArrayList<StaffMaster> list, int id) {
        
        int index = 99999;

        // 該当するIDを抽出
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID() == id) {
                index = i;
                break;
            }
        }

        return index;
    }
}
