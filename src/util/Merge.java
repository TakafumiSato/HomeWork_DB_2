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
        
        ArrayList<StaffMyNumber> resultList = new ArrayList<StaffMyNumber>();
        
        for (int i = 0; i < staffList.size(); i++) {
            // スタッフマスターの登録
            resultList.add(new StaffMyNumber());
            resultList.get(i).setId(staffList.get(i).getID());
            resultList.get(i).setName(staffList.get(i).getName());
            resultList.get(i).setGender(staffList.get(i).getGender());
            resultList.get(i).setBirth(staffList.get(i).getBirth());
            resultList.get(i).setMyNumber("000000000000");
            for (int j = 0; j < myNumberList.size(); j++) {
                // IDの比較
                if (staffList.get(i).getID().equals(myNumberList.get(j).getID())) {
                    // 一致しているマイナンバーを登録
                    resultList.get(i).setMyNumber(myNumberList.get(j).getMyNumber());
                    break;
                }
            }
        }
        
        return resultList;
    }
}
