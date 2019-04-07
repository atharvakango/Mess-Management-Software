/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginprac1;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class MyFXMLController implements Initializable 
{

    
    

    @FXML
    private javafx.scene.control.TextField UserName;

    @FXML
    private javafx.scene.control.TextField PassWord;

    @FXML
    private javafx.scene.control.Button Login;

    @FXML
    private javafx.scene.control.Button Exit;
    private Scene scene;
    @FXML
    private Label No;
    
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
    }

    @FXML
    private void GetData(javafx.event.ActionEvent event) throws IOException
    {
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML2.fxml"));
        scene.setRoot((Parent) loader.load());*/
        Parent hm_pg_prnt = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        Scene hm_pg_scene = new Scene(hm_pg_prnt);
        Stage app_stage = (Stage)( (Node) event.getSource() ).getScene().getWindow();
        
        
        if(isValidCredentials())
        {
            
        app_stage.hide();
        app_stage.setScene(hm_pg_scene);
        app_stage.show();
        }
        else
        {
            UserName.clear();
            PassWord.clear();
        }
        
    }

    private boolean isValidCredentials() 
    {
        boolean let_in=false;
        String usnm = UserName.getText();
        String psvd = PassWord.getText();
        if(usnm.equals("123") && psvd.equals("123"))
        {
            System.out.println("No Allowed");
            let_in = true;
        } //To change body of generated methods, choose Tools | Templates.
        
        
        return let_in;
    }
    
}
 