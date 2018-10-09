package data;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 佐藤孝史
 */
public class StaffMaster {
    
    private String id;
    private String name;
    private String gender;
    private Date birth;
    
    public StaffMaster(String id, String name, String gender, Date birth) {
        
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
    }
    
    public String getID() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirth() {
        return birth;
    }
}
