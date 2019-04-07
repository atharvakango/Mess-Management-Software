/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginprac1;

//import java.sql.Date;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Atharva
 */
public class Student {

    int ID;
    String name;
    String email;
    String addr;
    String phoneno;
    String college;
    Date Join_Date;
    int money;

    public Student(int ID, String name, String email, String addr, String phoneno, String college, Date Join_Date, int money) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.addr = addr;
        this.phoneno = phoneno;
        this.college = college;
        this.Join_Date = Join_Date;
        this.money = money;
    }
    
    
    
    public Student(int ID, String name,String phoneno, String email, int money) {
        this.ID = ID;
        this.name = name;
        this.email = email;       
        this.phoneno = phoneno;
        this.money = money;
    }
    
    
    
     public Student(int ID,Date Join_Date, int money) {
        this.ID = ID;
        this.Join_Date = Join_Date;
        this.money = money;
    }
    
    
      public Student(int ID, String name,String phoneno, String email,Date Join_Date, int money) {
        this.ID = ID;
        this.name = name;
        this.email = email;       
        this.phoneno = phoneno;
        this.money = money;
        this.Join_Date = Join_Date;
    }
    
    
    
    
    
    

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Date getJoin_Date() {
        return Join_Date;
    }

    public void setJoin_Date(Date Join_Date) {
        this.Join_Date = Join_Date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Student(int ID, String name, String email, String addr, String phoneno, String college) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.addr = addr;
        this.phoneno = phoneno;
        this.college = college;
    }

    public Student(int ID, String name) {
        this.ID = ID;
        this.name = name;

    }

    public Student(int ID, String name, Date Join_Date) {
        this.ID = ID;
        this.name = name;
        this.Join_Date = Join_Date;

    }

}
