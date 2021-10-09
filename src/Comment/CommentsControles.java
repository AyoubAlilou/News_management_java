package Comment;


import News.*;
import User.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class CommentsControles  implements Initializable {


    @FXML
    ListView<Comment> lv =new ListView<Comment>();
    private Comment comment=null;
    private User user =null ;
  CommentDAO commentDAO= new CommentDAO();
    static int id;
    @FXML
    private Button add=new Button("Ajouter Commentaire");
    private News news =null ;
   public void addComment(ActionEvent event,int id) {
       TextArea text2 = new TextArea();
       Dialog<Comment> dialog = new Dialog<>();
//       System.out.println("id" +id);
       if(id!=-1){

           comment  = commentDAO.getOne(id);
        dialog.setTitle("Modifier commentaire");
        dialog.setHeaderText("Modifier commentaire :");
       text2.setText(comment.getComment());}
       else {

       dialog.setTitle("Ajouter commentaire");
       dialog.setHeaderText("Ajouter commentaire :");}
        dialog.setResizable(true);

        Label label1 = new Label("Commentaire");



        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(text2, 2, 2);
        dialog.getDialogPane().setContent(grid);

   ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
       dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.setResultConverter(new Callback<ButtonType,Comment>() {
            @Override
            public Comment call(ButtonType b) {

                if (b == buttonTypeOk) {
                    if(comment==null){
                    comment =new Comment(text2.getText(),user.toString(),news);
                    if(commentDAO.add(comment)>0){
                        init();
                        dialog.close();
                    }
                }
                    else {
                        user = UserHolder.getInstance().getUser();
                        comment.setComment(text2.getText());
                        comment.setUser(user.toString());
                        if (commentDAO.update(comment) > 0) {
                            init();
                            dialog.close();
                        }
                    }
                    return comment;
                }

                return null;
            }
        });

       Optional<Comment> result = dialog.showAndWait();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        news=new NewsDAO().getOne(id);
        add.setOnAction(event -> {
            addComment(event,-1);
        });
        init();

    }


    public void init() {
        comment = null;
        user = UserHolder.getInstance().getUser();
        List<Comment> data = commentDAO.getAll();
//        System.out.println(id);
        data = data.stream().filter(comment -> comment.getNews().getId() == id).collect(Collectors.toList());
        ObservableList<Comment> list = FXCollections.observableArrayList(data);
//        System.out.println(lv.getItems().toString());
        lv.setItems(list);
//        System.out.println(lv.getItems().toString());
        lv.setCellFactory(param ->
        { XCell xCell=new XCell();
            xCell.delete.setOnAction(event -> {
                if(commentDAO.delete(xCell.lastItem.getId())){
                init();
                }
            });
            xCell.update.setOnAction(event -> {
              addComment(event,xCell.lastItem.getId());
            });
        return  xCell;
        });



    }


    public void setNews(int id) {
        this.id= id;
    }
    @FXML
    void logout(ActionEvent event) throws IOException {
        Stage stage= (Stage)add.getScene().getWindow();
        stage.close();
        Outil  outil = new Outil();
        outil.logout();

    }

    @FXML
    void openHome(ActionEvent event) throws IOException {
        Stage stage= (Stage)add.getScene().getWindow();
        stage.close();
        Outil  outil = new Outil();
        outil.goHome();

    }

}
