package User;

import Editor.Editor;
import Reader.Reader;
import connection.Cnx;
import connection.InterfaceDAO;


import java.sql.*;


public  class UserDAO  {
    Cnx cnx= Cnx.getInstance();

//    public List<Editor> getAllEditor(){
//        List<Editor> users = new ArrayList<>();
//        ResultSet resultSet = null;
//        Statement statement ;
//        try {
//            statement = cnx.connect().createStatement();
//            Editor user ;
//            resultSet = statement.executeQuery("select  * from Editor");
//            while (resultSet.next()){
//                user = new Editor();
//                user.setId(resultSet.getInt(1));
//                user.setName(resultSet.getString(2));
//                user.setLast_name(resultSet.getString(3));
//                user.setPassword(resultSet.getString(4));
//                user.setEmail(resultSet.getString(5));
//                user.setDateOfBirth(resultSet.getDate(6).toLocalDate());
//                users.add(user);
//            }
//
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//        return users;
//
//    }
//    public List<Reader> getAllReader(){
//        List<Reader> users = new ArrayList<>();
//        ResultSet resultSet = null;
//        Statement statement ;
//        try {
//            statement = cnx.connect().createStatement();
//            Reader user ;
//            resultSet = statement.executeQuery("select  * from Reader");
//            while (resultSet.next()){
//                user = new Reader();
//                user.setId(resultSet.getInt(1));
//                user.setName(resultSet.getString(2));
//                user.setLast_name(resultSet.getString(3));
//                user.setPassword(resultSet.getString(4));
//                user.setEmail(resultSet.getString(5));
//                user.setDateOfBirth(resultSet.getDate(6,Calendar.getInstance(TimeZone.getTimeZone(ZoneId.systemDefault()))).toLocalDate());
//                users.add(user);
//            }
//
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//        return users;
//
//    }
//
//    @Override
//    public List<User> getAll()  {
//      List<User> users = new ArrayList<>();
//
//        users.addAll(getAllEditor());
//        users.addAll(getAllReader());
//
//      return users ;
//    }
//
  //  @Override
    public User getOne(int id) {
        User user = new User();
        try {
            PreparedStatement preparedStatement=cnx.connect().prepareStatement("SELECT * FROM reader r  where id =?  UNION SELECT * FROM editor e where id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6).toLocalDate());
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  user;

    }

    //@Override
    public int add(User user)
    {String sql =null;
    int a = 0;
    PreparedStatement statement ;
        if(user instanceof Editor){
            sql ="insert into Editor (name,lastName,password,email,dateOfBirth)  values (?,?,?,?,?);";
        }else {
            sql ="insert into Reader(name,lastName,password,email,dateOfBirth) values (?,?,?,?,?);";

        }
        try {
            statement=cnx.connect().prepareStatement(sql);
            statement.setString(1,user.getName());
            statement.setString(2,user.getLast_name());
            statement.setString(3,user.getPassword());
            statement.setString(4,user.getEmail());
            statement.setDate(5, Date.valueOf(user.getDateOfBirth()));
          a= statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return a;
    }
    public User roleUser(String email, String password,String tableName){
        String sql="SELECT * FROM $tableName where email =? and password=?";
        //boolean a = false;
        PreparedStatement statement ;
        User user=null;

        try {
            String query =sql.replace("$tableName",tableName);
            statement=cnx.connect().prepareStatement(query);
           statement.setString(1,email);
            statement.setString(2,password);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getDate(6).toLocalDate());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user ;
    }
    public User login(String email, String password){
        User user = null ;
        if (roleUser(email,password,"editor")!=null){
            user =new Editor(roleUser(email,password,"editor"));
        }

        if (roleUser(email,password,"reader")!=null){
            user=new Reader(roleUser(email,password,"reader"));
        }
        return  user ;
}


}
