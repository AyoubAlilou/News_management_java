package Editor;

import Category.*;

import Comment.CommentsControles;
import News.News;
import News.NewsDAO;
import User.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class HomeEditor implements Initializable {
    CategoryDAO categoryDAO=new CategoryDAO();
    ObservableList<Category> categoryObservableList;

    @FXML
    private ChoiceBox<Category> Catégories;

    NewsDAO newsDAO = new NewsDAO();
    private ObservableList<News> data;
public void init(){
    data = FXCollections.observableArrayList(newsDAO.getAll());
    tableNews.setItems(data);
    colID.setCellValueFactory(
            new PropertyValueFactory<>("id"));
    colText.setCellValueFactory(new PropertyValueFactory<>("text"));
    colTitre.setCellValueFactory(new PropertyValueFactory<>("title"));

    colDate.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
    colEditor.setCellValueFactory(new PropertyValueFactory<>("editor"));
    colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
    categoryObservableList = FXCollections.observableArrayList(categoryDAO.getAll());

    Catégories.setItems(categoryObservableList);
}
    public void initialize(URL location, ResourceBundle resources) {
   this.init();
    user = UserHolder.getInstance().getUser();
//    System.out.println(user);


    }

    @FXML
    private TableView<News> tableNews;

    @FXML
    private TableColumn<News, Integer> colID;
    @FXML
    private TableColumn<News, String> colTitre;

    @FXML
    private TableColumn<News, String> colText;

    @FXML
    private TableColumn<News, LocalDate> colDate;

    @FXML
    private TableColumn<News, Editor> colEditor;

    @FXML
    private TableColumn<News, Category> colCategory;


    @FXML
    private Label txtNom;
    @FXML
    private Button btnAddcategory;
    @FXML
    private Button btnAddNews;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextArea txtText;

    public void addCategory(ActionEvent actionEvent) throws IOException {

        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Category/AddCategory.fxml"));
        final Parent layout = fxmlLoader.load();
        Stage stage= (Stage)btnAddcategory.getScene().getWindow();
        stage.setScene(new Scene(layout));

    }
        private News news;
    @FXML
    void addNews(ActionEvent event) {
        if(btnAddNews.getText().equals("Modifier nouvelle")){
            news.setText(txtText.getText());
            news.setTitle(txtTitle.getText());
            news.setCategory(Catégories.getValue());
          if (newsDAO.update(news)>0){
              btnAddNews.setText("Ajouter nouvelle");
              txtText.setText(null);
              txtTitle.setText(null);
              init();}
        }

        else {
         //   System.out.println(user.toString());
            if( newsDAO.add(new News(txtTitle.getText(),txtText.getText(), LocalDate.now(),((Editor)(user)),Catégories.getValue()))>0){
            System.out.println("Ajouter avec succes ");
            txtText.setText(null);
            txtTitle.setText(null);
           this.init();
        }}

    }
    @FXML
    void deleteNews(ActionEvent event) {
        newsDAO.delete(tableNews.getSelectionModel().getSelectedItem().getId());
        init();

    }

    @FXML
    void updateNews(ActionEvent event) {
        btnAddNews.setText("Modifier nouvelle");
        news= new News(tableNews.getSelectionModel().getSelectedItem().getId(),tableNews.getSelectionModel().getSelectedItem().getTitle(),tableNews.getSelectionModel().getSelectedItem().getText(),LocalDate.now(),((Editor)(user)),tableNews.getSelectionModel().getSelectedItem().getCategory());
        txtText.setText(tableNews.getSelectionModel().getSelectedItem().getText());
        txtTitle.setText(tableNews.getSelectionModel().getSelectedItem().getTitle());

        int a=-1 ;

        for (int i = 0; i < categoryObservableList.size(); i++) {
            if (categoryObservableList.get(i).getId()==tableNews.getSelectionModel().getSelectedItem().getCategory().getId()){a=i;}
        }
        Catégories.getSelectionModel().select(a);
    }
private User user =null ;

@FXML
void goComment(ActionEvent event) {
    Stage stage = (Stage) btnAddcategory.getScene().getWindow();
    stage.close();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Comment/Comments.fxml"));
    CommentsControles scene2Controller = new CommentsControles();
    scene2Controller.setNews(tableNews.getSelectionModel().getSelectedItem().getId());
    Parent root = null;
    try {
        root = loader.load();
    } catch (IOException e) {
        e.printStackTrace();
    }

    stage = new Stage();
    stage.setScene(new Scene(root));
    stage.setTitle("Gestion des commentaires ");
    stage.show();
}
    @FXML
    void logout(ActionEvent event) throws IOException {
        Stage stage= (Stage)btnAddcategory.getScene().getWindow();
        stage.close();
        Outil  outil = new Outil();
        outil.logout();

    }

}
