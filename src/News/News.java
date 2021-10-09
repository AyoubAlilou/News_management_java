package News;

import Category.Category;
import Editor.Editor;

import java.time.LocalDate;

public class News {
    private int id ;
    private String title ;
    private String text ;
    private LocalDate dateCreation ;
    private Editor editor ;
    private Category category;

    public News(int id, String title, String text, LocalDate dateCreation, Editor editor, Category category) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.dateCreation = dateCreation;
        this.editor = editor;
        this.category = category;
    }

    public News(String title, String text, LocalDate dateCreation, Editor editor, Category category) {
        this.title = title;
        this.text = text;
        this.dateCreation = dateCreation;
        this.editor = editor;
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public News() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
