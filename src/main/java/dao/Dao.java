package dao;

import java.util.List;

public interface Dao <T>{
    void create(T object);

    void update(T object);

    void delete(int key);

    List<T> getAll();
}
