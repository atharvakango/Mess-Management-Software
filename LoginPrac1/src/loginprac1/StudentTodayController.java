/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginprac1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Atharva
 */
public class StudentTodayController implements Initializable {

    @FXML
    private CheckBox bf1;
    @FXML
    private CheckBox bf2;
    @FXML
    private CheckBox bf3;
    @FXML
    private CheckBox lunch_v;
    @FXML
    private CheckBox lunch_nv;
    @FXML
    private CheckBox dinner_v;
    @FXML
    private CheckBox dinner_nv;
    @FXML
    private TextField srchtxt;
    @FXML
    private Button srch;
    @FXML
    private Button doneSave;
    @FXML
    private Label nmOfStud;

    static int studID;
    static String b1 = null;
    static String b2 = null;
    static String b3 = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nmOfStud.setText("");
        srchtxt.setText("");
        lunch_v.setSelected(false);
        lunch_nv.setSelected(false);
        dinner_v.setSelected(false);
        dinner_nv.setSelected(false);
        bf1.setSelected(false);
        bf2.setSelected(false);
        bf3.setSelected(false);
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM TODAYSMENU WHERE DATE=CURRENT_DATE");

            while (rs.next()) {
                bf1.setText(rs.getString("BREAKFAST1"));
                bf2.setText(rs.getString("BREAKFAST2"));
                bf3.setText(rs.getString("BREAKFAST3"));
                /* b1=rs.getString("BREAKFAST1");
                b2=rs.getString("BREAKFAST2");
                b3=rs.getString("BREAKFAST3");*/
            }
        } catch (SQLException e) {
        }

        setAllTrue(true);

    }

    void checkIt() {
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM TODAYSMENU WHERE DATE=CURRENT_DATE");
            while (rs.next()) {
                if (rs.getBoolean("veg_in_lunch") == false) {
                    lunch_v.setDisable(true);
                }
                if (rs.getBoolean("nonveg_in_lunch") == false) {
                    lunch_nv.setDisable(true);
                }
                if (rs.getBoolean("veg_in_dinner") == false) {
                    dinner_v.setDisable(true);
                }
                if (rs.getBoolean("nonveg_in_dinner") == false) {
                    dinner_nv.setDisable(true);
                }
            }

            System.out.println("CHECK IT : " + rs.getBoolean("veg_in_lunch"));

        } catch (SQLException e) {
        }
    }

    @FXML
    private void doThis(ActionEvent event) {
        if (lunch_v.isSelected()) {
            lunch_nv.setSelected(false);
        }
    }

    @FXML
    private void doThis1(ActionEvent event) {
        if (lunch_nv.isSelected()) {
            lunch_v.setSelected(false);
        }
    }

    @FXML
    private void doThis2(ActionEvent event) {
        if (dinner_v.isSelected()) {
            dinner_nv.setSelected(false);
        }
    }

    @FXML
    private void doThis3(ActionEvent event) {
        if (dinner_nv.isSelected()) {
            dinner_v.setSelected(false);
        }
    }

    @FXML
    private void getAndSearch(ActionEvent event) {
        String s = srchtxt.getText();

        URL r = null;
        ResourceBundle g = null;
        if (s.equals("")) {
            initialize(r, g);
            return;
        }
        int stuID = 0;
        String stuNm = null;
        int n = Integer.parseInt(s);

        int flg = 0;
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM student WHERE ID=" + n);
            // System.out.println(rs.next());
            while (rs.next()) {
                stuID = rs.getInt("ID");
                stuNm = rs.getString("NAME");
                System.out.println("" + n);
                System.out.println(rs.getString("NAME"));
                flg = 1;
                studID = rs.getInt("ID");
            }
        } catch (SQLException e) {
        }

        if (flg == 1) {
            setAllTrue(false);
            nmOfStud.setText("ID : " + stuID + " \nName : " + stuNm);
            checkIt();
            flg = 0;
        } else {
            setAllTrue(true);
            nmOfStud.setText("No such ID Exists !!");
            //checkIt();

        }

    }

    @FXML
    private void getAndSearch1(KeyEvent event) {
        String s = srchtxt.getText();

        URL r = null;
        ResourceBundle g = null;
        if (s.equals("")) {
            initialize(r, g);
            return;
        }
        int stuID = 0;
        String stuNm = null;
        int n = Integer.parseInt(s);

        int flg = 0;
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM student WHERE ID=" + n);
            // System.out.println(rs.next());
            while (rs.next()) {
                stuID = rs.getInt("ID");
                stuNm = rs.getString("NAME");
                System.out.println("" + n);
                System.out.println(rs.getString("NAME"));
                flg = 1;
                studID = rs.getInt("ID");
            }
        } catch (SQLException e) {
        }

        if (flg == 1) {
            setAllTrue(false);
            nmOfStud.setText("ID : " + stuID + " \nName : " + stuNm);
            flg = 0;
            checkIt();
        } else {
            setAllTrue(true);
            nmOfStud.setText("No such ID Exists !!");
        }
    }

    private void setAllTrue(boolean t) {
        bf1.setDisable(t);
        bf2.setDisable(t);
        bf3.setDisable(t);
        lunch_v.setDisable(t);
        lunch_nv.setDisable(t);
        dinner_v.setDisable(t);
        dinner_nv.setDisable(t);
    }

    @FXML
    private void saveAndExit(ActionEvent event) throws SQLException {

        Connection c = null;
        java.sql.Statement stmt = null;
        int todaysbill = 0;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            PreparedStatement ps = c.prepareStatement("insert into daily_track(ID,DATE,BF1,BF2,BF3,LUNCH_V,LUNCH_NV,DINNER_V,DINNER_NV) values(?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, studID);
            java.util.Date date = new java.util.Date();
            java.sql.Date dt = new java.sql.Date(date.getTime());
            ps.setDate(2, dt);
            ps.setBoolean(3, bf1.isSelected());
            ps.setBoolean(4, bf2.isSelected());
            ps.setBoolean(5, bf3.isSelected());
            ps.setBoolean(6, lunch_v.isSelected());
            ps.setBoolean(7, lunch_nv.isSelected());
            ps.setBoolean(8, dinner_v.isSelected());
            ps.setBoolean(9, dinner_nv.isSelected());

            int a = ps.executeUpdate();
            if (a > 0) {
                System.out.println("Data Inserted");
            }

            
            
            stmt = c.createStatement();
            ResultSet rs2;
            rs2 = stmt.executeQuery("SELECT * FROM breakfastoptions");                       
            while (rs2.next()) {
                System.out.println("cnt loop : " + todaysbill);
                if (bf1.getText().equals(rs2.getString("item"))) {
                    if (bf1.isSelected()) {
                        todaysbill += rs2.getInt("cost");
                    }
                    // System.out.println(rs2.getString("item"));
                }
                if (bf2.getText().equals(rs2.getString("item"))) {
                    if (bf2.isSelected()) {
                        todaysbill += rs2.getInt("cost");
                    }
                    
                }
                if (bf3.getText().equals(rs2.getString("item"))) {
                    if (bf3.isSelected()) {
                        todaysbill += rs2.getInt("cost");
                    }                    
                }
            }
            
            
            
             int prevbill=0;
            String s = srchtxt.getText();
            System.out.println("s : "+s);
            int n1 = Integer.parseInt(s);
            System.out.println("n1 : "+n1);
            ResultSet rs3;
            rs3 = stmt.executeQuery("SELECT * FROM student WHERE ID=" + n1);
              // System.out.println(rs.next());
            while (rs3.next()) 
            {
                 prevbill= rs3.getInt("bill");            
            }
            todaysbill+=prevbill;
                        
            ResultSet rs4;
            rs4 = stmt.executeQuery("SELECT * FROM lunch_dinner");
            
            
            
           
            rs4.next();
           if(lunch_v.isSelected())
           {

              todaysbill += rs4.getInt("cost");
           }
            rs4.next();
           if(lunch_nv.isSelected())
           {

              todaysbill += rs4.getInt("cost");
           }
            rs4.next();
           if(dinner_v.isSelected())
           {

              todaysbill += rs4.getInt("cost");
           }
            rs4.next();
           if(dinner_nv.isSelected())
           {
              todaysbill += rs4.getInt("cost");
           }
            
            
            System.out.println("final todaysbill : "+todaysbill);
            String sql = "update student set bill=? where ID=" + n1;

            PreparedStatement ps1 = c.prepareStatement(sql);
            ps1.setInt(1, todaysbill);
            int a1 = ps1.executeUpdate();
            if( a1 > 0)
                System.out.println("Data Inserted for bill");
            

        
        
        
        
        } catch (SQLException e) {
        }
        srchtxt.requestFocus();
        URL r = null;
        ResourceBundle g = null;
        initialize(r, g);

    }

    @FXML
    private void onBack(ActionEvent event) throws IOException {
        Parent hm_pg_prnt = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        Scene hm_pg_scene = new Scene(hm_pg_prnt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(hm_pg_scene);
        app_stage.show();
    }

}