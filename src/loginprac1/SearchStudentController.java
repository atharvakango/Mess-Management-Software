/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginprac1;

//import java.awt.Image;
//import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Atharva
 */
public class SearchStudentController implements Initializable {

    ObservableList<Student> list = FXCollections.observableArrayList();
    static int pass = 0;
    @FXML
    private TextField SearchBar;
    @FXML
    private Button SearchStud;
    @FXML
    private Label thisNm;
    @FXML
    private Label thisphn;
    @FXML
    private Label thisemail;
    @FXML
    private Label thisaddr;
    @FXML
    private Label thisclg;
    @FXML
    private Button Back;
    @FXML
    private Button updatebtn;
    @FXML
    private Button refresh;
    @FXML
    private Label thisId;
    @FXML
    private Label thisDt;
    private ImageView imgvw;

    private FileChooser fileChooser;

    private File filePath;
    @FXML
    private TableColumn<Student, Integer> ID;
    @FXML
    private TableColumn<Student, String> nm;
    //@FXML
    //private TableColumn<?, ?> date;
//    @FXML
    @FXML
    private TableView<Student> myTable;
    @FXML
    private TableColumn<Student, Date> dt;

    static int tempVar;
    @FXML
    private Label mny;

    /**private TableView<Student> myTable;
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {    
        //System.out.println("shdvhdssdjfhvdsjhgfdshgfhdsgfdshgfjvdsvf");
         refresh.setDisable(false);
            Date date=java.util.Calendar.getInstance().getTime();  
	    // System.out.println(date); 
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date); 
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH); 
            System.out.println(dayOfMonth);
	// System.out.println(dayOfWeek);
         /* if(dayOfMonth>=1 && dayOfMonth<=5)
            {
                //boolean xy=true;
                System.out.println(dayOfMonth);
                refresh.setDisable(false);
            }*/
        

        
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM Student");

            while (rs.next()) {
                list.add(new Student(rs.getInt("ID"), rs.getString("NAME"), rs.getDate("Date_of_join")));
            }
        } catch (SQLException e) {
        }

        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nm.setCellValueFactory(new PropertyValueFactory<>("name"));
        dt.setCellValueFactory(new PropertyValueFactory<>("Join_Date"));
        myTable.setItems(list);
    }

    @FXML
    private void PerformSearch(ActionEvent event) {
        myTable.getItems().clear();
        String srch = SearchBar.getText();
        URL url = null;
        ResourceBundle rb = null;
        if (srch.equals("")) {
            initialize(url, rb);
            return;
        }
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM Student where NAME like" + "'" + srch + "%'");
            while (rs.next()) {
                String a = rs.getString("NAME");
                int id = rs.getInt("ID");
//                ListView.getItems().add(a);
                myTable.getItems().add(new Student(id, a));
            }
        } catch (Exception e) {
        }
    }

    
    //used to actually display data from selectionmodel
    @FXML
    private void displaySelected(MouseEvent event) throws IOException {
        Student l;
        l = myTable.getSelectionModel().getSelectedItem();
        int h;
        h = l.getID();
        tempVar = h;
        System.out.println("Value : " + h);
        int bill=0;
        String a = "", b = "", y = "", d = "", f = "";
        java.sql.Date joinDate = null;
        int id = 0;
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM Student where ID=" + h);

            while (rs.next()) {
                id = rs.getInt("ID");
                a = rs.getString("NAME");
                b = rs.getString("Phone_NO");
                y = rs.getString("E_mail");
                d = rs.getString("College");
                f = rs.getString("Address");
                joinDate = rs.getDate("DATE_OF_JOIN");
                bill = rs.getInt("bill");
            }
        } catch (SQLException e) {
            System.out.println("" + e);
        }
        thisId.setText("" + id);
        thisNm.setText(a);
        thisphn.setText(b);
        thisemail.setText(y);
        thisaddr.setText(f);
        thisclg.setText(d);
        thisDt.setText("" + joinDate);
        mny.setText("" + bill);
        System.out.print(a + " " + b + " " + y + " " + d + " " + f);
        pass = h;

        /*filePath = new File("file:/C:/Users/Atharva/Downloads/123.JPG");

        try {
            BufferedImage bfimg = ImageIO.read(filePath);
            //imgvw = new ImageView();
            Image img = SwingFXUtils.toFXImage(bfimg, null);
            imgvw.setImage(img);
//            javafx.scene.image.Image img = imgvw.setImage();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            //e.printStackTrace();
        }*/
    }

    @FXML
    private void GoBack(ActionEvent event) throws IOException {
        FXML2Controller a = new FXML2Controller();
        a.loadFXML("FXML2.fxml", event);
    }

    @FXML
    private void onUpdate(ActionEvent event) throws IOException {

        Parent hm_pg_prnt = FXMLLoader.load(getClass().getResource("UpdateStud.fxml"));
        Scene hm_pg_scene = new Scene(hm_pg_prnt);
        Stage app_stage = new Stage();//(Stage)( (Node) event.getSource() ).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(hm_pg_scene);
        app_stage.initModality(Modality.APPLICATION_MODAL);
        app_stage.showAndWait();

        onRefresh(event);
        displaySelectedAgain();

    }

    static int Give() {
        return pass;
    }

    
    
    //for run time updates - called by update method
    void displaySelectedAgain() {
        int h;
        h = tempVar;
        int bill=0;
        System.out.println("Value : " + h);
        String a = "", b = "", y = "", d = "", f = "";
        java.sql.Date joinDate = null;
        int id = 0;
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM Student where ID=" + h);

            while (rs.next()) {
                id = rs.getInt("ID");
                a = rs.getString("NAME");
                b = rs.getString("Phone_NO");
                y = rs.getString("E_mail");
                d = rs.getString("College");
                f = rs.getString("Address");
                joinDate = rs.getDate("DATE_OF_JOIN");
                bill = rs.getInt("bill");
            }
        } catch (SQLException e) {
            System.out.println("" + e);
        }
        thisId.setText("" + id);
        thisNm.setText(a);
        thisphn.setText(b);
        thisemail.setText(y);
        thisaddr.setText(f);
        thisclg.setText(d);
        thisDt.setText("" + joinDate);
        mny.setText("" + bill);
        System.out.print(a + " " + b + " " + y + " " + d + " " + f);

    }

    void onRefresh(ActionEvent event) {

        myTable.getItems().clear();
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM Student");

            while (rs.next()) {
                list.add(new Student(rs.getInt("ID"), rs.getString("NAME"), rs.getDate("Date_of_join")));
            }
        } catch (SQLException e) {
        }

        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));//done
        nm.setCellValueFactory(new PropertyValueFactory<>("name"));
        dt.setCellValueFactory(new PropertyValueFactory<>("Join_Date"));
        myTable.setItems(list);
    }

    @FXML
    private void PerformSearch1(KeyEvent event) {
//         ListView.getItems().clear();
        myTable.getItems().clear();
        String srch = SearchBar.getText();
        URL url = null;
        ResourceBundle rb = null;
        if (srch.equals("")) {
            initialize(url, rb);
            return;
        }
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM Student where NAME like" + "'" + srch + "%'");
            while (rs.next()) {
                String a = rs.getString("NAME");
                int id = rs.getInt("ID");
                myTable.getItems().add(new Student(id, a));
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void onLeaving(ActionEvent event) throws IOException {
        Student l = myTable.getSelectionModel().getSelectedItem();
        int g = l.getID();
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH,1);
        
        java.util.Date d = cal.getTime();        
        java.sql.Date sqldt = new java.sql.Date(d.getTime());
        System.out.println(d.toString());
                        
        Connection c = null;

        try {
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "AtharvaK", "123");
            PreparedStatement ps = c.prepareStatement("insert into leavingstudents(ID,Date,MONEYLEFT) values(?,?,?)");
            ps.setInt(1,g);
            ps.setDate(2,sqldt);
            ps.setInt(3, 5000);
            ps.executeUpdate();                                   
            System.out.println("Student countdown starts!!!");
        } catch (SQLException e) {
        }
        
        
        ModalFXMLController a = new ModalFXMLController();
        a.callIt("CountDown Started");
    }
}
