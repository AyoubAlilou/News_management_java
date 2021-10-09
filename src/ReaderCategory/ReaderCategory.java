package ReaderCategory;

import javafx.scene.control.CheckBox;

public class ReaderCategory {


        private String type ;
        private CheckBox checkBox ;

    public ReaderCategory( String type, CheckBox checkBox) {

        this.type = type;
        this.checkBox = checkBox;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
