package connection;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAO<T> {
     List<T> getAll() ;
    int add(T t);
    int update (T t);
    boolean delete(int id);
    T getOne(int id);
}
