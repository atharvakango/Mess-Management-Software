/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginprac1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Atharva
 */
public class AddStudentController implements Initializable {

    @FXML
    private TextField StudNm;
    @FXML
    private TextField StudPhn;
    @FXML
    private TextField StudEmail;                                                                                                                                                                 
    @FXML
    private TextField StudClg;
    @FXML
    private TextField StudAddr;
    @FXML
    private Button SubmitStud;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SubmitStudInfo(ActionEvent event) throws SQLException, IOException
    {
        String nm = StudNm.getText();
        String pno = StudPhn.getText();
        String email = StudEmail.getText();
        String addr = StudAddr.getText();
        String clg = StudClg.getText();            
        Connection con =null;
        try
        {
            con= DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase","AtharvaK","123");
            PreparedStatement ps = con.prepareStatement("insert into student(NAME,PHONE_NO,E_MAIL,COLLEGE,ADDRESS,date_of_join,bill) values(?,?,?,?,?,?,?)");
            ps.setString(1, nm);
            ps.setString(2, pno);
            ps.setString(3, email);
            ps.setString(4, addr);
            ps.setString(5, clg);
            java.util.Date date=new java.util.Date();   
            java.sql.Date dt = new java.sql.Date(date.getTime());
            ps.setDate(6,dt );
            ps.setInt(7,0);
            int a = ps.executeUpdate();
            if( a > 0)
                System.out.println("Data Inserted");            
        }
        catch(SQLException e){
        System.out.println(""+e);}
        

        ModalFXMLController a = new ModalFXMLController();
        a.callIt("Student Added Successfully");



        
        Parent hm_pg_prnt = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        Scene hm_pg_scene = new Scene(hm_pg_prnt);
        Stage app_stage = (Stage)( (Node) event.getSource() ).getScene().getWindow();   
        app_stage.hide();
        app_stage.setScene(hm_pg_scene);
        app_stage.show();                                                
    }

    @FXML
    private void onback1(ActionEvent event) throws IOException 
    {
         FXML2Controller a = new FXML2Controller();
        a.loadFXML("FXML2.fxml", event);
    }

    private void checkIt(ActionEvent event) throws IOException 
    {
        String k=StudPhn.getText();
        try
        {
            int j=Integer.parseInt(k);            
        }catch(NumberFormatException E)
        {
            ModalFXMLController a = new ModalFXMLController();
            a.callIt("Please Enter valid phone number");
        }
    }

   // @FXML
    /*private void checkIt(InputMethodEvent event) {
    }*/

}
