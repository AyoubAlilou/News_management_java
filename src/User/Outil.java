package User;

import Editor.Editor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Outil {
    public Outil() {
    }
   User user = UserHolder.getInstance().getUser();
    public void goHome() throws IOException {
        FXMLLoader loader;
        if(user instanceof Editor){
            loader = new FXMLLoader(getClass().getResource("../Editor/HomeEditor.fxml"));


        }
        else{

            loader = new FXMLLoader(getClass().getResource("../Reader/HomeReader.fxml"));

        }
        // Step 4

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Gestion News");
        primaryStage.setScene(scene);
        primaryStage.show();





    }

    public void  logout() throws IOException {
        user = null ;
        Parent root= FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));
        Scene scene = new Scene(root);

        UserHolder holder = UserHolder.getInstance();
        holder.setUser(user);

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Gestion News");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
