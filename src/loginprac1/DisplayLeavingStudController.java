/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginprac1;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//import javax.mail.*;

/**
 * FXML Controller class
 *
 * @author Atharva
 */
public class DisplayLeavingStudController implements Initializable {

    ObservableList<DailyTrack> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<DailyTrack, Integer> DLID;
    @FXML
    private TableColumn<DailyTrack, Date> DLDate;
    @FXML
    private TableColumn<DailyTrack, Boolean> DLBF1;
    @FXML
    private TableColumn<DailyTrack, Boolean> DLBF2;
    @FXML
    private TableColumn<DailyTrack, Boolean> DLBF3;
    @FXML
    private TableColumn<DailyTrack, Boolean> DLLunch_v;
    @FXML
    private TableColumn<DailyTrack, Boolean> DLLunch_NV;
    @FXML
    private TableColumn<DailyTrack, Boolean> DLDinner_v;
    @FXML
    private TableColumn<DailyTrack, Boolean> DLDinner_NV;
    @FXML
    private TableView<DailyTrack> myTable;
    @FXML
    private Label labelamount;
    @FXML
    private Button depo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Student ab = MessEndFXMLController.giveObj();
        int id = ab.getID();
        int bill = 0;
        System.out.println("Value : " + id);        
        java.sql.Date joinDate = null;
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM Daily_track where id=" + id);

            while (rs.next()) {
                list.add(new DailyTrack(rs.getInt("ID"), rs.getDate("date"), rs.getBoolean("bf1"), rs.getBoolean("bf2"), rs.getBoolean("bf3"), rs.getBoolean("Lunch_v"), rs.getBoolean("lunch_NV"), rs.getBoolean("dinner_v"), rs.getBoolean("dinner_nv")));
            }

        } catch (SQLException e) {
        }
        DLID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        DLDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        DLBF1.setCellValueFactory(new PropertyValueFactory<>("bf1"));
        DLBF2.setCellValueFactory(new PropertyValueFactory<>("bf2"));
        DLBF3.setCellValueFactory(new PropertyValueFactory<>("bf3"));   
        DLLunch_v.setCellValueFactory(new PropertyValueFactory<>("lunch_v"));
        DLLunch_NV.setCellValueFactory(new PropertyValueFactory<>("lunch_nv"));
        DLDinner_v.setCellValueFactory(new PropertyValueFactory<>("dinner_v"));
        DLDinner_NV.setCellValueFactory(new PropertyValueFactory<>("dinner_nv"));
        myTable.setItems(list);
        
        
         System.out.println("akjdb");
              
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM student where id=" + id);

            int amt=0;
            while (rs.next()) {
                amt=rs.getInt("bill");
            }
            
            labelamount.setText(""+(5000-amt));
                    System.out.println("Amount : " + amt);
        } catch (SQLException e) {
        }        
    }

    @FXML
    private void depositreturnedanddelete(ActionEvent event) 
    {
        
        Student ab = MessEndFXMLController.giveObj();
        int idp = ab.getID();
        System.out.println("Value : " + idp);        
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();              
            //String sql ="" ;
             //System.out.println("DELETE FROM STUDENT WHERE id="+idp);
            stmt.executeUpdate("DELETE FROM daily_track WHERE id="+idp);          
            stmt.executeUpdate("DELETE FROM leavingstudents WHERE id="+idp);       
            stmt.executeUpdate("DELETE FROM STUDENT WHERE id="+idp);       
            
        } catch (SQLException e) {
        }
    }

}
