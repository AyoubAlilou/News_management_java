package User;

import javafx.scene.control.Alert;

public class AlertException extends  Exception {
    public AlertException(String title, String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(msg);
        alert.showAndWait();
    }
}