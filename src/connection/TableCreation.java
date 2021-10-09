package connection;

import java.sql.SQLException;
import java.sql.Statement;

public class TableCreation {
    Cnx cnx= Cnx.getInstance();

    public  void  creationReader (){
        try {
            String sql="CREATE TABLE IF NOT EXISTS Reader ( id int PRIMARY KEY AUTO_INCREMENT, name varchar(20), lastName varchar(20),password varchar (50), email varchar(50), dateOfBirth date )";
            Statement statement = (Statement) cnx.connect().createStatement();
            int i= statement.executeUpdate(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void  creationEditor() {
        Statement statement;
        try {
            String sql="CREATE TABLE IF NOT EXISTS Editor ( id int PRIMARY KEY AUTO_INCREMENT, name varchar(20), lastName varchar(20),password varchar (50),email varchar(50), dateOfBirth date )";
            statement = (Statement) cnx.connect().createStatement();
            int i= statement.executeUpdate(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }
    public  void  creationCategory (){
        try {
            String sql="CREATE TABLE IF NOT EXISTS Category ( id int PRIMARY KEY AUTO_INCREMENT, type varchar(50) NOT NULL UNIQUE)";
            Statement statement = (Statement) cnx.connect().createStatement();
            int i= statement.executeUpdate(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    public  void  creationCategory_Reader(){
        try {
            String sql="CREATE TABLE IF NOT EXISTS Category_Reader ( reader int  references  Reader (id), category int  references  Category (id),primary key (reader,category))";
            Statement statement = (Statement) cnx.connect().createStatement();
            int i= statement.executeUpdate(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public  void  creationNews(){
        try {
            String sql="CREATE TABLE IF NOT EXISTS News ( id int PRIMARY KEY AUTO_INCREMENT, title varchar(50),text varchar(200), dateCreation date,editor int references Editor,category int references  Category)";
            Statement statement = (Statement) cnx.connect().createStatement();
            int i= statement.executeUpdate(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void  creationComment (){
        try {
            String sql="CREATE TABLE IF NOT EXISTS Comment ( id int PRIMARY KEY AUTO_INCREMENT, comment varchar(100),user varchar(100), news int references  News)";
            Statement statement = (Statement) cnx.connect().createStatement();
            int i= statement.executeUpdate(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }


}
