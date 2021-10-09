package Reader;

import Category.*;
import ReaderCategory.ReaderCategory;
import User.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeReaderController  implements Initializable {
    @FXML
    private TableView<ReaderCategory> tableCategory;



    @FXML
    private TableColumn<ReaderCategory, String> colType;

    @FXML
    private TableColumn<ReaderCategory, CheckBox> colSelection;
    private ObservableList<ReaderCategory> data;
    List<Category> listcat= new CategoryDAO().getAll();
    CategoryDAO categoryDAO = new CategoryDAO();
    private User user ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = UserHolder.getInstance().getUser();
        data = FXCollections.observableArrayList();
        listcat.forEach(category -> {
            CheckBox checkBox= new CheckBox();
            checkBox.setId(category.getId()+"");
            checkBox.setSelected(categoryDAO.getOneUser(category.getId(),user.getId()));
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {

              if(newValue){
//                System.out.println(category.getId());
//                  System.out.println(;
                  categoryDAO.addCategoryReader(user.getId(),category.getId());

              }
//              else{
//                  System.out.println(categoryDAO.deleteReaderCategory(user.getId(),category.getId()));
//
//              }

            });
            data.add(new ReaderCategory(category.getType(),checkBox));
        });


        tableCategory.setItems(data);

        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colSelection.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

    }


    public void openNews(ActionEvent actionEvent) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../News/NewsReader.fxml"));
        final Parent layout = fxmlLoader.load();
        Stage stage= (Stage)tableCategory.getScene().getWindow();
        stage.setScene(new Scene(layout));
    }
    @FXML
    void logout(ActionEvent event) throws IOException {
        Stage stage= (Stage)tableCategory.getScene().getWindow();
        stage.close();
        Outil  outil = new Outil();
        outil.logout();

    }


}

