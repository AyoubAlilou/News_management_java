package Comment;

import Category.Category;
import News.*;
import connection.Cnx;
import connection.InterfaceDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CommentDAO implements InterfaceDAO<Comment> {
    Cnx cnx = Cnx.getInstance();
    NewsDAO newsDAO = new NewsDAO();
    @Override
    public List<Comment> getAll() {
        List<Comment> comments = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement;
        try {
            statement = cnx.connect().createStatement();
            Comment comment;
            resultSet = statement.executeQuery("select  * from comment");
            while (resultSet.next()) {
                comment = new Comment();
                comment.setId(resultSet.getInt(1));
                comment.setComment(resultSet.getString(2));
                 comment.setUser(resultSet.getString(3));
                comment.setNews(newsDAO.getOne(resultSet.getInt(4)));
                comments.add(comment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public int add(Comment comment) {

        int a = 0;
        PreparedStatement statement;
        String sql = "INSERT INTO  comment(comment,user,news) VALUES (?,?,?)";
        try {
            statement = cnx.connect().prepareStatement(sql);
            statement.setString(1, comment.getComment());
            statement.setString(2, comment.getUser());
            statement.setInt(3, comment.getNews().getId());
            a = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;

    }


    @Override
    public int update(Comment comment) {
        int a = 0;
        PreparedStatement statement;
        String sql = "UPDATE comment SET comment=? WHERE id=?";
        try {
            statement = cnx.connect().prepareStatement(sql);
            statement.setString(1, comment.getComment());
            statement.setInt(2, comment.getId());
            a = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;

    }

    @Override
    public boolean delete(int id) {
        int a = 0;
        PreparedStatement statement;
        String sql = "DELETE FROM comment WHERE id=?";
        try {
            statement = cnx.connect().prepareStatement(sql);
            statement.setInt(1, id);
            a = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a > 0;
    }

    @Override
    public Comment getOne(int id) {
        Comment comment = new Comment();
        try {
            PreparedStatement preparedStatement = cnx.connect().prepareStatement("SELECT * FROM comment where id =?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comment = new Comment(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),newsDAO.getOne(resultSet.getInt(4)));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }
}