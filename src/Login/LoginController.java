package Login;

import Editor.Editor;
import User.*;
import User.UserDAO;
import connection.TableCreation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController extends Application {
    public static void main(String[] args) {

//        String name = "admin",password = "admin";
//        Predicate<List<User>> pr = list -> list.stream().filter(user -> user.getName().equals(name)&& user.getPassword().equals(password)).count()>0;
//
//        System.out.println(pr.test(lst));
        launch(args);
        TableCreation tableCreation = new TableCreation();
        tableCreation.creationEditor();
        tableCreation.creationReader();
        tableCreation.creationCategory();
        tableCreation.creationCategory_Reader();
        tableCreation.creationNews();
        tableCreation.creationComment();
   //     UserDAO userDAO = new UserDAO();
//    User user = new Editor("wafa","ben yahia","wafa","wafa@gmail.com", LocalDate.now(ZoneId.systemDefault()));
//    userDAO.add(user);

    }
//    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        //  primaryStage = new MainView();
        Parent root= FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Gestion News");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPass;
     public static User user1=null ;
    public void login(ActionEvent actionEvent) throws IOException, AlertException {

     UserDAO userDAO = new UserDAO();
        User  user=userDAO.login(txtEmail.getText(),txtPass.getText());
        if (user != null){
            user1= userDAO.login(txtEmail.getText(),txtPass.getText());
            UserHolder holder = UserHolder.getInstance();
            holder.setUser(user);
            Stage stage= (Stage)txtEmail.getScene().getWindow();
            stage.close();
            Outil outil=new Outil();
            outil.goHome();
        }else{
            throw  new AlertException("Error !!!!","Merci de verifier vos informations ");

        }
    }

    public void openRegistrer(ActionEvent actionEvent)  {
        try {
            Parent layout= FXMLLoader.load(getClass().getResource("../User/Register.fxml"));

            Stage stage= (Stage)txtEmail.getScene().getWindow();
            stage.setScene(new Scene(layout));
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
}
