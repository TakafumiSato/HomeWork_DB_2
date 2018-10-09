package data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 佐藤孝史
 */
public class StaffMyNumber {
    
    private String id;
    private String name;
    private String gender;
    private Date birth;
    private int age;
    private String myNumber;
    
    
    public StaffMyNumber() {
        
    }
    
    public StaffMyNumber(String id, String name, String gender, Date birth, String myNumber) {
        
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.myNumber = myNumber;
        
        setAge();
    }
    
    public void setData(StaffMyNumber data) {
        
        this.id = data.getID();
        this.name = data.getName();
        this.gender = data.getGender();
        this.birth = data.getBirth();
        this.myNumber = data.getMyNumber();
    }
    
    public String getID() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMyNumber(String myNumber) {
        this.myNumber = myNumber;
    }
    
    public String getMyNumber() {
        return myNumber;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    private void setAge() {
        Date now = new Date();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        this.age = (Integer.parseInt(sdf.format(now)) - Integer.parseInt(sdf.format(birth))) / 10000;
    }
    
    public int getAge() {
        return age;
    }
    
    public void output() {
        System.out.println(id + "," + name + "," + gender + "," + birth + "," + age);
    }
}
