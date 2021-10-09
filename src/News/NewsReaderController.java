package News;

import Comment.CommentsControles;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NewsReaderController implements Initializable {
  static class XCell extends ListCell<News> {
        GridPane gridPane = new GridPane();
        HBox hBox = new HBox();
        Text titre = new Text("");
        Text category = new Text("");
        Text text= new Text("");
        Text  editor= new Text("");
        Pane pane = new Pane();


//    ImageView image=new ImageView("../imageNews.png");


        Button button = new Button();
        News lastItem;

        public XCell() {
            super();
            ImageView imageView=new ImageView();

            imageView.setImage(new Image(getClass().getResourceAsStream("../imageNews.png")));
            imageView.setFitWidth(70);
            imageView.setFitHeight(70);
//        titre.setMaxWidth(Double.MAX_VALUE);
            ImageView imageView2=new ImageView();
            imageView2.setImage(new Image(getClass().getResourceAsStream("../Next.png")));
//            imageView2.setFitHeight(50);
//            imageView2.setFitHeight(50);
            button.setGraphic(imageView2);
              button.setStyle("-fx-background-color: transparent");
            gridPane.addRow(0, new Label("  Titre:  "),titre);
            gridPane.addRow(1, new Label("  Text :   "),text);
            gridPane.addRow(2, new Label("  Categorie :   "),category);
            gridPane.addRow(3, new Label("  Editor :   "),editor);

            //vBox.getChildren().addAll(titre,category,text,editor );
//
//

            hBox.getChildren().addAll(imageView,gridPane,pane,button);
            HBox.setHgrow(pane, Priority.ALWAYS);
            button.setOnAction(event -> {
                Stage stage= (Stage)titre.getScene().getWindow();
                stage.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Comment/Comments.fxml"));
                CommentsControles scene2Controller =new CommentsControles();
                scene2Controller.setNews(lastItem.getId());
               // loader.setController(scene2Controller);


            //Get controller of scene2

            //Pass whatever data you want. You can have multiple method calls here
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("wafa" +lastItem.getId());

            //Show scene 2 in new window
             stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestion des commentaires ");
            stage.show();

            });
        }

        @Override
        protected void updateItem(News item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);  // No text in label of super class
            if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
                lastItem = item;
                titre.setText(item.getTitle()!=null ? item.getTitle() : "<null>");
                category.setText(item.getCategory().getType()!=null ? item.getCategory().getType() : "<null>");
                editor.setText(item.getEditor().getName()!=null ? item.getEditor().getName() : "<null>");
                text.setText(item.getText()!=null ? item.getText() : "<null>");


                setGraphic(hBox);
            }
        }}
    NewsDAO newsDAO = new NewsDAO();

    @FXML
    private Pane pane;

    private User user =null ;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = UserHolder.getInstance().getUser();
        List<News> data = newsDAO.getAllByReader(user.getId());
        ObservableList<News> list = FXCollections.observableArrayList(data);
       ListView<News> lv = new ListView<>(list);

        lv.setCellFactory(param -> new XCell());
        lv.setPrefHeight(500);
        lv.setPrefWidth(800);
        pane.getChildren().add(lv);

    }
    @FXML
    void logout(ActionEvent event) throws IOException {
        Stage stage= (Stage)pane.getScene().getWindow();
        stage.close();
        Outil  outil = new Outil();
        outil.logout();

    }

    @FXML
    void openHome(ActionEvent event) throws IOException {
        Stage stage= (Stage)pane.getScene().getWindow();
        stage.close();
        Outil  outil = new Outil();
        outil.goHome();

    }
}