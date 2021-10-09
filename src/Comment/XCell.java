package Comment;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;


public class XCell extends ListCell<Comment> {
    HBox hbox = new HBox();
    Text comment = new Text("");
    Text user = new Text("");
    Pane pane = new Pane();

    Button update = new Button();
    Button delete = new Button();
    Comment lastItem;
    public XCell() {
        super();



        ImageView imageRemove=new ImageView();
        imageRemove.setImage(new Image(getClass().getResourceAsStream("../remove.png")));
        ImageView imageEdit=new ImageView();
        imageEdit.setImage(new Image(getClass().getResourceAsStream("../edit.png")));
        delete.setGraphic(imageRemove);
        update.setGraphic(imageEdit);
        imageRemove.setFitWidth(20);
        imageRemove.setFitHeight(20);
         imageEdit.setFitWidth(20);
        imageEdit.setFitHeight(20);
        update.setStyle(" -fx-background-color: transparent");
        delete.setStyle(" -fx-background-color: transparent");

        hbox.getChildren().addAll(comment,pane,user,update,delete );

       HBox.setHgrow(pane, Priority.ALWAYS);

    }

    @Override
    protected void updateItem(Comment item, boolean empty) {
        super.updateItem(item, empty);

        if (!empty) {
            lastItem = item;
            comment.setText(lastItem.getComment()!=null ? lastItem.getComment() : "<null>");
            user.setText(lastItem.getUser()!=null ? lastItem.getUser() : "<null>");
            setGraphic(hbox);

        } else {
        setGraphic(null);
        }
    }}
