/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginprac1;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static loginprac1.SearchStudentController.pass;

/**
 * FXML Controller class
 *
 * @author Atharva
 */
public class UpdateStudController implements Initializable {

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
    private Label lblUpdt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        int nm = SearchStudentController.Give();

        System.out.println("ID : " + nm);
        String a = "", b = "", y = "", d = "", f = "";
        int id = 0;
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM Student where ID=" + nm);

            while (rs.next()) {
                a = rs.getString("NAME");
                b = rs.getString("Phone_NO");
                y = rs.getString("E_mail");
                d = rs.getString("College");
                f = rs.getString("Address");
            }

        } catch (SQLException e) {
        }
        StudNm.setText(a);
        StudPhn.setText(b);
        StudEmail.setText(y);
        StudAddr.setText(f);
        StudClg.setText(d);
        System.out.print(a + " " + b + " " + y + " " + d + " " + f);
        pass = nm;
    }

    @FXML
    private void updateStudInfo(ActionEvent event) {
        int nm = SearchStudentController.Give();
        lblUpdt.setText("" + nm);

        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");

            String sql = "update student set NAME=?,PHONE_NO=?,E_MAIL=?,COLLEGE=?,ADDRESS=? where ID=" + nm;

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, StudNm.getText());
            ps.setString(2, StudPhn.getText());
            ps.setString(3, StudEmail.getText());
            ps.setString(4, StudAddr.getText());
            ps.setString(5, StudClg.getText());
            int a = ps.executeUpdate();
            if (a > 0) {
                System.out.println("Update Successful");
            }

        } catch (SQLException e) {
        }
                         
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
    }

}
