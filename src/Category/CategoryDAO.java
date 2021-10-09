package Category;

import connection.Cnx;
import connection.InterfaceDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class CategoryDAO implements InterfaceDAO<Category> {
    Cnx cnx = Cnx.getInstance() ;
    @Override
    public List<Category> getAll()  {

        List<Category> categories = new ArrayList<>();
      ResultSet resultSet = null;
        Statement statement ;
        try {
            statement = cnx.connect().createStatement();
             Category category;
            resultSet = statement.executeQuery("select  * from category");
            while (resultSet.next()){
                category = new Category();
                category.setId(resultSet.getInt(1));
                category.setType(resultSet.getString(2));
                categories.add(category);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return categories;
    }


    @Override
    public int add(Category category) {

        int a = 0;
        PreparedStatement statement ;
        String sql= "INSERT INTO  category(type) VALUES (?)";
        try {
            statement=cnx.connect().prepareStatement(sql);
            statement.setString(1, category.getType());
            a= statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return a;

    }



    @Override
    public int update(Category category) {
        int a = 0;
        PreparedStatement statement ;
        String sql= "UPDATE category SET type=?  WHERE id=?";
        try {
            statement=cnx.connect().prepareStatement(sql);
            statement.setString(1, category.getType());
            statement.setInt(2, category.getId());
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
        String sql= "DELETE FROM category_reader WHERE id=?";
        try {
            statement=cnx.connect().prepareStatement(sql);
            statement.setInt(1,id);
            a= statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return a>0;
    }



//    public boolean deleteReaderCategory(int idR,int idC) {
//        int a = 0;
//        PreparedStatement statement ;
//        String sql= "DELETE FROM category_reader WHERE reader=? and category =?";
//        try {
//            statement=cnx.connect().prepareStatement(sql);
//            statement.setInt(1,idR);
//            statement.setInt(2,idC);
//            a= statement.executeUpdate();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return a>0;
//    }
    @Override
    public Category getOne(int id) {
        Category category = new Category();
        try {
            PreparedStatement preparedStatement=cnx.connect().prepareStatement("SELECT * FROM category where id =?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
           while(resultSet.next()) {
               category = new Category(resultSet.getInt(1), resultSet.getString(2));
           }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
    public boolean getOneUser(int idC , int idR) {
        int count=0 ;
        try {
            PreparedStatement preparedStatement=cnx.connect().prepareStatement("SELECT COUNT(*)  AS count FROM category_reader WHERE reader=? and category =?");
            preparedStatement.setInt(1,idR);
            preparedStatement.setInt(2,idC);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                count = resultSet.getInt("count");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  count>0;
    }
    public int addCategoryReader(int idUser , int idCat ) {

        int a = 0;
        PreparedStatement statement ;
        String sql= "INSERT INTO  category_reader VALUES (?,?)";
        try {
            statement=cnx.connect().prepareStatement(sql);
            statement.setInt(1,idUser);
            statement.setInt(2,idCat);
            a= statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return a;

    }
}
