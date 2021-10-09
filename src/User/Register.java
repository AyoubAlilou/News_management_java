package User;

import Editor.Editor;
import Reader.Reader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Register implements Initializable {
    ObservableList roles= FXCollections.observableArrayList("Rédacteur","Lecteur");
    @FXML
    private ChoiceBox role ;
    @FXML
    private TextField txtName ;
    @FXML
    private TextField txtLastname ;
    @FXML
    private TextField txtEmail ;
    @FXML
    private DatePicker txtDate;
    @FXML
    private PasswordField txtPwd;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        role.setItems(roles);
    }

    public void addUser(ActionEvent actionEvent) throws AlertException {
        UserDAO userDAO= new UserDAO();
        User user  = null;
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if(userDAO.login(txtEmail.getText(),txtPwd.getText())!=null){

            throw  new AlertException("Error !!!!","Email deja existe ");
        }else {
        if( txtEmail.getText().length()>2 && txtLastname.getText().length()>2 && txtPwd.getText().length()>2 && txtEmail.getText().length()>2 && txtName.getText().length()>2 && txtDate.getValue()!=null && role.getValue()!=null ){
        if(role.getValue().equals("Rédacteur")){
            user= new Editor(txtName.getText(),txtLastname.getText(),txtPwd.getText(),txtEmail.getText(), LocalDate.of(txtDate.getValue().getYear(),txtDate.getValue().getMonth(),txtDate.getValue().getDayOfMonth()));
        }

        if(role.getValue().equals("Lecteur")){
                user= new Reader(txtName.getText(),txtLastname.getText(),txtPwd.getText(),txtEmail.getText(), LocalDate.of(txtDate.getValue().getYear(),txtDate.getValue().getMonth(),txtDate.getValue().getDayOfMonth()));
            }


            if(userDAO.add(user)>0) {
            try {
                Parent layout= FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));

                Stage stage= (Stage)txtEmail.getScene().getWindow();
                stage.setScene( new Scene(layout, 600, 374));


            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        }
        else {

                throw  new AlertException("Error ","Verif votre champs ");

        }}


    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        Parent layout= FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));

        Stage stage= (Stage)txtEmail.getScene().getWindow();
        stage.setScene( new Scene(layout, 600, 374));
    }
}
