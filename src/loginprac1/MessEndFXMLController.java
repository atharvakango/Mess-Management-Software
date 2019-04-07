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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Atharva
 */
public class MessEndFXMLController implements Initializable {

    ObservableList<Student> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Student, Integer> endID;
    @FXML
    private TableColumn<Student, String> endName;
    @FXML
    private TableView<Student> myTable;
    @FXML
    private TableColumn<Student, Integer> endMny;

    static Student temp = null;
    @FXML
    private TableColumn<Student, Date> endDate;
    @FXML
    private TableColumn<Student, String> endPhn;
    @FXML
    private TableColumn<Student, String> endMail;
    @FXML
    private Button back23;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection c = null;
        ResultSet rs;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();

            rs = stmt.executeQuery("select LEAVINGSTUDENTS.ID, STUDENT.NAME,STUDENT.PHONE_NO, STUDENT.E_MAIL, "
                    + " STUDENT.DATE_OF_JOIN ,STUDENT.BILL "
                    + " from LEAVINGSTUDENTS  INNER JOIN STUDENT "
                    + " ON LEAVINGSTUDENTS.ID = STUDENT.ID where date=current_date");

            while (rs.next()) {
                list.add(new Student(rs.getInt("ID"), rs.getString("name"), rs.getString("e_mail"), rs.getString("phone_no"), rs.getDate("date_of_join"), rs.getInt("bill")));
            }
        } catch (SQLException e) {
        }

        endID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        endName.setCellValueFactory(new PropertyValueFactory<>("name"));
        endMny.setCellValueFactory(new PropertyValueFactory<>("money"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("Join_Date"));
        endPhn.setCellValueFactory(new PropertyValueFactory<>("phoneno"));
        endMail.setCellValueFactory(new PropertyValueFactory<>("email"));
        myTable.setItems(list);

    }

    @FXML
    private void displaySelected(MouseEvent event) throws IOException {
        Student l = myTable.getSelectionModel().getSelectedItem();
        temp = l;
        Parent hm_pg_prnt = FXMLLoader.load(getClass().getResource("DisplayLeavingStud.fxml"));
        Scene hm_pg_scene = new Scene(hm_pg_prnt);
        Stage app_stage = new Stage();//(Stage)( (Node) event.getSource() ).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(hm_pg_scene);
        app_stage.initModality(Modality.APPLICATION_MODAL);
        app_stage.showAndWait();

    }

    static Student giveObj() 
    {
        return temp;
    }

    @FXML
    private void onback23(ActionEvent event) throws IOException 
    {
        FXML2Controller a = new FXML2Controller();
        a.loadFXML("FXML2.fxml", event);
    }

}
