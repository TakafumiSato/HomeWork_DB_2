package data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 佐藤孝史
 * 
 * MyNumberデータクラス
 */
public class MyNumber {
    
    private String id = null;
    private String myNumber = null;
    
    public MyNumber(String id, String myNumber) {
        this.id = id;
        this.myNumber = myNumber;
    }
    
    public String getID() {
        return id;
    }
    
    public String getMyNumber() {
        return myNumber;
    }
}
