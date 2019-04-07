/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginprac1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Atharva
 */
public class ModalFXMLController implements Initializable {

    @FXML
    private Label lbl;
    @FXML
    private Button ok;

    static String ll = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        lbl.setText(ll);
        
        
        //callIt();
    }

    public void callIt(String ak) throws IOException {
        ll=ak;
        Parent hm_pg_prnt = FXMLLoader.load(getClass().getResource("ModalFXML.fxml"));
        Scene hm_pg_scene = new Scene(hm_pg_prnt);
        Stage app_stage = new Stage();//(Stage)( (Node) event.getSource() ).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(hm_pg_scene);
        app_stage.initModality(Modality.APPLICATION_MODAL);
        app_stage.showAndWait();
        
    }

    @FXML
    private void okClicked(ActionEvent event) {
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
    }

}
