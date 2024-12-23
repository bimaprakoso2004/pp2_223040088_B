package model;

import org.apache.ibatis.annotations.*;
import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM User")
    List<User> getAllUsers();

    @Insert("INSERT INTO User(name, brand, price, stock) VALUES(#{name}, #{brand}, #{price}, #{stock})")
    void insertUser(User user);

    @Update("UPDATE User SET name=#{name}, brand=#{brand}, price=#{price}, stock=#{stock} WHERE id=#{id}")
    void updateUser(User user);

    @Delete("DELETE FROM User WHERE id=#{id}")
    void deleteUser(int id);
}