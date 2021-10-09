package News;

import Category.CategoryDAO;
import Editor.Editor;
import User.UserDAO;
import connection.Cnx;
import connection.InterfaceDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO implements InterfaceDAO<News> {
    Cnx cnx = Cnx.getInstance() ;
    @Override
    public List<News> getAll()  {
        List<News> news = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement ;
        try {
            statement = cnx.connect().createStatement();
            News news1 ;
            resultSet = statement.executeQuery("select  * from news");
            while (resultSet.next()){
                news1 = new News();
                news1.setId(resultSet.getInt(1));
                news1.setTitle(resultSet.getString(2));
                news1.setText(resultSet.getString(3));
                news1.setDateCreation(resultSet.getDate(4).toLocalDate());
                news1.setEditor(new Editor(new UserDAO().getOne(resultSet.getInt(5))));
                news1.setCategory(new CategoryDAO().getOne(resultSet.getInt(6)));
                news.add(news1);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return news;
    }
    public List<News> getAllByReader(int id)  {
        List<News> news = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            PreparedStatement statement = cnx.connect().prepareStatement("select n.* from news n , category_reader cat , category c ,reader r where r.id=? AND cat.reader = r.id AND c.id = cat.category and n.category=c.id");
            News news1 ;
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                news1 = new News();
                news1.setId(resultSet.getInt(1));
                news1.setTitle(resultSet.getString(2));
                news1.setText(resultSet.getString(3));
                news1.setDateCreation(resultSet.getDate(4).toLocalDate());
                news1.setEditor(new Editor(new UserDAO().getOne(resultSet.getInt(5))));
                news1.setCategory(new CategoryDAO().getOne(resultSet.getInt(6)));
                news.add(news1);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return news;
    }


    @Override
    public int add(News news) {

        int a = 0;
        PreparedStatement statement ;
        String sql= "INSERT INTO news(title, text, dateCreation, editor, category) VALUES (?,?,?,?,?)";
        try {
            statement=cnx.connect().prepareStatement(sql);
            statement.setString(1,news.getTitle());
            statement.setString(2,news.getText());
            statement.setDate(3, Date.valueOf(news.getDateCreation()));
            statement.setInt(4,news.getEditor().getId());
            statement.setInt(5,news.getCategory().getId());
            a= statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return a;

    }

    @Override
    public int update(News news) {
        int a = 0;
        PreparedStatement statement ;
        String sql= "UPDATE news SET title=?,text=?,dateCreation=?,editor=?,category=? WHERE id=?";
        try {
            statement=cnx.connect().prepareStatement(sql);
            statement.setString(1,news.getTitle());
            statement.setString(2,news.getText());
            statement.setDate(3,Date.valueOf(news.getDateCreation()));
            statement.setInt(4,news.getEditor().getId());
            statement.setInt(5,news.getCategory().getId());
            statement.setInt(6,news.getId());
            a= statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return a;

    }

    @Override
    public boolean delete(int id) {
        int a = 0;
        PreparedStatement statement ;
        String sql= "DELETE FROM news WHERE id=?";
        try {
            statement=cnx.connect().prepareStatement(sql);
            statement.setInt(1,id);
            a= statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return a>0;
    }

    @Override
    public News getOne(int id) {
        News news = null;
        try {
            PreparedStatement preparedStatement=cnx.connect().prepareStatement("SELECT * FROM news where id =?");
            preparedStatement.setInt(1,id);


            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                news = new News();
                news.setId(resultSet.getInt(1));
                news.setTitle(resultSet.getString(2));
                news.setText(resultSet.getString(3));
                news.setDateCreation(resultSet.getDate(4).toLocalDate());
                news.setEditor(new Editor(new UserDAO().getOne(resultSet.getInt(5))));
                news.setCategory(new CategoryDAO().getOne(resultSet.getInt(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  news;
    }
}
