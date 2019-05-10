package dao;

import model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String INSERT_USER_SQL = "INSERT INTO users (login, password, email, roleId) VALUES (?, ?, ?, ?);";
    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE  login = ?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE login = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET login = ?, password = ?, email = ?, roleId = ? WHERE login = ?;";
    private static final Logger logger = Logger.getLogger(User.class);

    public boolean insertUser(User user) {
        try (Connection connection = DBConnector.connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getHashPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRoleId());
            logger.debug(preparedStatement);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.warn("InsertUserException", e);
            return false;
        }
    }

    public User selectUser(String login) {
        User user = null;
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            logger.debug(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String loginUser = resultSet.getString("login");
                String passwordUser = resultSet.getString("password");
                String email = resultSet.getString("email");
                String rolesId = resultSet.getString("roleId");
                user = new User(loginUser, passwordUser, email, rolesId);
            }
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.warn("SelectUserException", e);
        }
        return user;
    }

    public List<User> selectAllUser() {
        List<User> users = new ArrayList<User>();
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            logger.debug(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String loginUser = resultSet.getString("login");
                String passwordUser = resultSet.getString("password");
                String email = resultSet.getString("email");
                String rolesId = resultSet.getString("roleId");
                users.add(new User(loginUser, passwordUser, email, rolesId));
            }
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.warn("SelectAllUserException", e);
        }
        return users;
    }

    public boolean deleteUser(String login) {
        boolean rowDeleted = false;
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);
            preparedStatement.setString(1, login);
            logger.debug(preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.warn("DeleteUserException", e);
        }
        return rowDeleted;
    }

    public boolean updateUser(User user, String login) {
        boolean rowUpdated = false;
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getHashPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRoleId());
            preparedStatement.setString(5, login);
            logger.debug(preparedStatement);
            rowUpdated = preparedStatement.executeUpdate() > 0;
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.warn("UpdateUserException", e);
        }
        return rowUpdated;
    }

    public boolean isExists(String login) {
        User user = selectUser(login);
        if (user != null) {
            return true;
        }
        return false;
    }

}
