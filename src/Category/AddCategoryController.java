package Category;

import User.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCategoryController {

    @FXML
    private Button btnAddcategory;

    @FXML
    private TextField txtType;

    @FXML
    void addCategory(ActionEvent event) throws IOException, AlertException {
        CategoryDAO categoryDAO= new CategoryDAO();
        if(txtType.getText()!= null){
            if(categoryDAO.add(new Category(txtType.getText()))>0){
                Stage stage= (Stage)btnAddcategory.getScene().getWindow();
                stage.close();
                Outil outil = new Outil();
                outil.goHome();
            }else {
                throw new AlertException("Error !!!!","Votre type deja existe  ");
            }

        }

    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Stage stage= (Stage)btnAddcategory.getScene().getWindow();
        stage.close();
        Outil outil = new Outil();
        outil.goHome();
    }

}
