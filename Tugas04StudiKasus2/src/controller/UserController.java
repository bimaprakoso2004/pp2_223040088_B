package controller;

import model.User;
import model.UserMapper;
import model.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import javax.swing.*;
import java.util.List;

public class UserController {

    public List<User> getAllUsers() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getAllUsers();
        }
    }

    public boolean addUser(User user) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.insertUser(user);
            session.commit();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding user: " + e.getMessage());
            return false;
        }
    }

    public boolean updateUser(User user) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.updateUser(user);
            session.commit();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating user: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteUser(int id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.deleteUser(id);
            session.commit();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error deleting user: " + e.getMessage());
            return false;
        }
    }
}
