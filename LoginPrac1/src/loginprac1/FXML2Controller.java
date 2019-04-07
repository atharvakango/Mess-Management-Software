/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//MAIN MENU PAGE i.e. Page 2
package loginprac1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Atharva
 */
public class FXML2Controller implements Initializable {

    @FXML
    private Button LogOut;
    @FXML
    private Button add_new;
    @FXML
    private Button search;
    @FXML
    private Button today_menu;
    @FXML
    private Button startToday;
    @FXML
    private Button messEnd;
    @FXML
    private Label notifyLbl;
    @FXML
    private Circle circl;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int cnt = 0;
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM leavingstudents where date=CURRENT_DATE");
            while (rs.next()) {
                cnt++;
            }
        } catch (SQLException e) {
        }

        if (cnt == 0) {
            circl.setVisible(false);
            notifyLbl.setVisible(false);
        } else {
            notifyLbl.setText(cnt + "");
        }

    }

    @FXML
    private void messEndFunc(ActionEvent event) throws IOException {
         loadFXML("MessEndFXML.fxml", event);
    }

    @FXML
    private void Log_out(ActionEvent event) throws IOException {
        loadFXML("FXMLDocument.fxml", event);
    }

    @FXML
    private void addNewStud(ActionEvent event) throws IOException {
        loadFXML("AddStudent.fxml", event);
    }

    @FXML
    private void searchStud(ActionEvent event) throws IOException {
        loadFXML("SearchStudent.fxml", event);
    }

    @FXML
    private void todayMenu(ActionEvent event) throws IOException {
        loadFXML("TodaysMenu.fxml", event);
    }

    public void loadFXML(String nm, ActionEvent event) throws IOException {
        Parent hm_pg_prnt = FXMLLoader.load(getClass().getResource(nm));
        Scene hm_pg_scene = new Scene(hm_pg_prnt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(hm_pg_scene);
        app_stage.show();
    }

    @FXML
    private void startToday(ActionEvent event) throws IOException {
        loadFXML("StudentToday.fxml", event);
    }

}
