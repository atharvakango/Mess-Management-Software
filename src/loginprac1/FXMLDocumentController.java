/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




//LOGIN VALIDATION PAGE i.e. First Page






package loginprac1;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Atharva
 */
public class FXMLDocumentController implements Initializable 
{    
    @FXML
    private javafx.scene.control.TextField UserName;

    @FXML
    private javafx.scene.control.PasswordField PassWord;

    @FXML
    private javafx.scene.control.Button Login;

    @FXML
    private javafx.scene.control.Button Exit;
   // private javafx.scene.layout.AnchorPane rootpane;
    @FXML
    private Label No;
    @FXML
    private AnchorPane pane11;
    
    /**
     *
     * @param url
     * @param rb
     */
   /* public void OnClose(ActionEvent event) {
        
    }*/

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnClose(javafx.event.ActionEvent event) {
        System.exit(0);
       // System.out.println("Helo");
    }

    @FXML
    private void GetData(javafx.event.ActionEvent event) throws IOException
    {
        Parent hm_pg_prnt = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        Scene hm_pg_scene = new Scene(hm_pg_prnt);
        Stage app_stage = (Stage)( (Node) event.getSource() ).getScene().getWindow();
        
      
        if(isValidCredentials())
        {
            //app_stage.setEditable(false);
        app_stage.hide();
        app_stage.setScene(hm_pg_scene);
        app_stage.show();
        }
        else
        {
            UserName.clear();
            PassWord.clear();
            No.setText("Wrong !!!");
        }
        //app_stage.setMaximized(true);
        //app_stage.setFullScreen(true);
        Login.prefWidthProperty().bind(pane11.widthProperty());
    }

    private boolean isValidCredentials() //throws SQLException 
    {
        boolean let_in=false;
     
         String usnm = UserName.getText();
        String psvd = PassWord.getText();
         System.out.print("SELECT * FROM ATHARVAK.VALIDATION WHERE USERNAME = " + "'" + usnm + "'" 
            + " AND PASSWORD= " + "'" + psvd + "'" );
           
        
        Connection c = null;
        java.sql.Statement stmt = null;
        
        try
        {
            /*try {              
                Class.forName("org.apache.derby.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
            //ex.printStackTrace();
            }*/
            
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase","AtharvaK","123");
           //   No.setText("askdbhjsak");
            c.setAutoCommit(false);
            
              No.setText("Opened DataBase SuccessFully");
            stmt = c.createStatement();
            
            ResultSet rs;
            rs = stmt.executeQuery( "SELECT * FROM ATHARVAK.VALIDATION WHERE USERNAME= " + "'" + usnm + "'"  + " AND PASSWORD= " + "'" + psvd + "'");
        
            
            
            while ( rs.next() ) {
                 if (rs.getString("USERNAME") != null && rs.getString("PASSWORD") != null) { 
                     String  username = rs.getString("USERNAME");
                     System.out.println( "USERNAME = " + username );
                     String password = rs.getString("PASSWORD");
                     System.out.println("PASSWORD = " + password);
                     let_in = true;
                 }  
            }
        
        rs.close();
        stmt.close();
        c.close();        
        
        }catch(SQLException e){
        System.out.println("No database");
        //e.printStackTrace();
         // No.setText(""+e);
        }
        return let_in;
    }   
}
 