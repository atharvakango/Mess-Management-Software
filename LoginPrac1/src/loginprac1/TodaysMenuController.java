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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Atharva
 */
public class TodaysMenuController implements Initializable {

  @FXML
    private CheckBox lunchveg;
    @FXML
    private CheckBox lunchnonveg;
    @FXML
    private CheckBox dinnerveg;
    @FXML
    private CheckBox dinnernonveg;
    @FXML
    private ChoiceBox<String> bf1;
    @FXML
    private ChoiceBox<String> bf2;
    @FXML
    private ChoiceBox<String> bf3;

    
     ObservableList list1 = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        /*bf1.setValue("BreakFast1");
        bf2.setValue("BreakFast2");
        bf3.setValue("BreakFast3");*/
        
         Connection c =null;
        java.sql.Statement stmt = null;
        try
        {
        c= DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase","AtharvaK","123");
        
        stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("select * from breakfastoptions");
            String s;
            while (rs.next()) {
                s=rs.getString("item");
                list1.add(s);
            }
            bf1.getItems().setAll(list1);

            
        }catch(SQLException e){
        System.out.println(""+e);}             
    }    
    
    
    
    
    @FXML
    private void bf1clicked(ActionEvent event) {
        String brkfst1 = (String) bf1.getSelectionModel().getSelectedItem();
            System.out.println("breakfast1 : "+brkfst1);
            boolean h = list1.remove(brkfst1);
            bf2.getItems().setAll(list1);
            bf3.getItems().setAll(list1);
            System.out.println("Remove Successful : "+h);
    }
    
    
    @FXML
    private void bf2clicked(ActionEvent event) {
        String brkfst2 = (String) bf2.getSelectionModel().getSelectedItem();
            System.out.println("breakfast2 : "+brkfst2);
            boolean h = list1.remove(brkfst2);
            bf3.getItems().setAll(list1);
            System.out.println("Remove Successful : "+h);
    }
    
    

    @FXML
    private void onback(ActionEvent event) throws IOException 
    {
         Parent hm_pg_prnt = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        Scene hm_pg_scene = new Scene(hm_pg_prnt);
        Stage app_stage = (Stage)( (Node) event.getSource() ).getScene().getWindow();   
        app_stage.hide();
        app_stage.setScene(hm_pg_scene);
        app_stage.show();
     
    }

    @FXML
    private boolean onactionlunchveg(ActionEvent event) 
    {
       return lunchveg.isSelected();
    }

    @FXML
    private boolean onaction_lunch_nonveg(ActionEvent event)
    {
        return lunchnonveg.isSelected();
    }

    @FXML
    private boolean onactiondinner_veg(ActionEvent event) 
    {
          return dinnerveg.isSelected();
    }

    @FXML
    private boolean onactiondinner_nonveg(ActionEvent event) 
    {
         return dinnernonveg.isSelected();
    }

    @FXML
    private void onsave(ActionEvent event) throws IOException
    {
        String brkfst1 = (String)bf1.getValue();
        String brkfst2 = (String)bf2.getValue();
        String brkfst3 = (String)bf3.getValue();
       // String addr = StudAddr.getText();
       // String clg = StudClg.getText();
       boolean b1=onactionlunchveg(event);
        //b1=onactionlunchveg
       boolean b2= onaction_lunch_nonveg(event);
        
        boolean b3= onactiondinner_veg(event);
        
        boolean b4=onactiondinner_nonveg(event) ;
        //RequiredFieldValidator a = new RequiredFieldValidator();
        java.util.Date date=new java.util.Date();   
        java.sql.Date dt = new java.sql.Date(date.getTime());
        Connection con =null;
        try
        {
        con= DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase","AtharvaK","123");
        
        PreparedStatement ps = con.prepareStatement("insert into todaysmenu(BREAKFAST1,BREAKFAST2,BREAKFAST3,VEG_IN_LUNCH,NONVEG_IN_LUNCH,VEG_IN_DINNER,NONVEG_IN_DINNER,DATE) values(?,?,?,?,?,?,?,?)");
        ps.setString(1, brkfst1);
        ps.setString(2, brkfst2);
        ps.setString(3, brkfst3);
        ps.setBoolean(4, b1);
        ps.setBoolean(5, b2);
        ps.setBoolean(6, b3);
        ps.setBoolean(7, b4);
        ps.setDate(8, dt);
        //ps.setString(5, clg);
        
        int a = ps.executeUpdate();
        if( a > 0)
            System.out.println("Data Inserted for todays menu");
            
        }catch(SQLException e){
        System.out.println(""+e);}
        
        
         Parent hm_pg_prnt = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        Scene hm_pg_scene = new Scene(hm_pg_prnt);
        Stage app_stage = (Stage)( (Node) event.getSource() ).getScene().getWindow();   
        app_stage.hide();
        app_stage.setScene(hm_pg_scene);
        app_stage.show();
    }



    
}
