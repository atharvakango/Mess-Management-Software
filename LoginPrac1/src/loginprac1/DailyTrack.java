/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginprac1;

import java.sql.Date;

/**
 *
 * @author Atharva
 */
public class DailyTrack {

    int ID;
    Date date;
    Boolean bf1;
    Boolean bf2;
    Boolean bf3;
    Boolean lunch_v;
    Boolean lunch_nv;
    Boolean dinner_v;
    Boolean dinner_nv;

    public DailyTrack(int ID, Date date, Boolean bf1, Boolean bf2, Boolean bf3, Boolean lunch_v, Boolean lunch_nv, Boolean dinner_v, Boolean dinner_nv) {
        this.ID = ID;
        this.date = date;
        this.bf1 = bf1;
        this.bf2 = bf2;
        this.bf3 = bf3;
        this.lunch_v = lunch_v;
        this.lunch_nv = lunch_nv;
        this.dinner_v = dinner_v;
        this.dinner_nv = dinner_nv;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getBf1() {
        return bf1;
    }

    public void setBf1(Boolean bf1) {
        this.bf1 = bf1;
    }

    public Boolean getBf2() {
        return bf2;
    }

    public void setBf2(Boolean bf2) {
        this.bf2 = bf2;
    }

    public Boolean getBf3() {
        return bf3;
    }

    public void setBf3(Boolean bf3) {
        this.bf3 = bf3;
    }

    public Boolean getLunch_v() {
        return lunch_v;
    }

    public void setLunch_v(Boolean lunch_v) {
        this.lunch_v = lunch_v;
    }

    public Boolean getLunch_nv() {
        return lunch_nv;
    }

    public void setLunch_nv(Boolean lunch_nv) {
        this.lunch_nv = lunch_nv;
    }

    public Boolean getDinner_v() {
        return dinner_v;
    }

    public void setDinner_v(Boolean dinner_v) {
        this.dinner_v = dinner_v;
    }

    public Boolean getDinner_nv() {
        return dinner_nv;
    }

    public void setDinner_nv(Boolean dinner_nv) {
        this.dinner_nv = dinner_nv;
    }

}
