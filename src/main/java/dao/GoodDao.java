package dao;

import model.Good;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDao {
    private static final String INSERT_GOODS_SQL = "INSERT INTO goods (id, name, description, owner, price) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_GOODS_BY_OWNER = "SELECT * FROM goods WHERE owner = ?;";
    private static final String SELECT_GOOD_BY_ID = "SELECT * FROM goods WHERE id = ?";
    private static final String SELECT_ALL_GOODS = "SELECT * FROM goods";
    private static final String DELETE_GOODS_BY_ID = "DELETE FROM goods WHERE id = ?;";
    private static final String DELETE_GOODS_BY_OWNER = "DELETE FROM goods WHERE owner = ?";
    private static final String UPDATE_GOODS_SQL = "UPDATE goods SET id = ?, name = ?, description = ?, owner = ?, price = ? WHERE id = ?;";
    private static final Logger logger = Logger.getLogger(GoodDao.class);

    public boolean insertGood(Good good) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GOODS_SQL);
            preparedStatement.setString(1, good.getId());
            preparedStatement.setString(2, good.getName());
            preparedStatement.setString(3, good.getDescription());
            preparedStatement.setString(4, good.getOwner());
            preparedStatement.setDouble(5, good.getPrice());
            logger.debug(preparedStatement);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.warn("InsertGoodException", e);
            return false;
        }
    }

    public Good selectGood(String goodId) {
        Good good = null;
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GOOD_BY_ID);
            preparedStatement.setString(1, goodId);
            logger.debug(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String owner = resultSet.getString("owner");
                double price = resultSet.getDouble("price");
                good = new Good(id, owner, name, description, price);
            }
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.warn("SelectGoodException", e);
        }
        return good;
    }

    public List<Good> selectAllGoods() {
        List<Good> goods = new ArrayList<Good>();
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GOODS);
            logger.debug(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            addGoodsToList(goods, resultSet);
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.warn("SelectAllGoodsException", e);
        }
        return goods;
    }

    public List<Good> selectGoodsByOwner(String goodsOwner) {
        List<Good> goods = new ArrayList<Good>();
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GOODS_BY_OWNER);
            preparedStatement.setString(1, goodsOwner);
            logger.debug(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            addGoodsToList(goods, resultSet);
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.warn("SelectGoodsByOwnerException", e);
        }
        return goods;
    }

    private void addGoodsToList(List<Good> goods, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String owner = resultSet.getString("owner");
            double price = resultSet.getDouble("price");
            goods.add(new Good(id, owner, name, description, price));
        }
    }

    public boolean deleteGoodsById(String goodId) {
        boolean rowDeleted = false;
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GOODS_BY_ID);
            preparedStatement.setString(1, goodId);
            logger.debug(preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.warn("DeleteGoodsByIdException", e);
        }
        return rowDeleted;
    }

    public boolean deleteGoodsByOwner(String goodsOwner) {
        boolean rowDeleted = false;
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GOODS_BY_OWNER);
            preparedStatement.setString(1, goodsOwner);
            logger.debug(preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.warn("DeleteGoodsByOwnerException", e);
        }
        return rowDeleted;
    }

    public boolean updateGood(Good good, String id) {
        boolean rowUpdated = false;
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GOODS_SQL);
            preparedStatement.setString(1, good.getId());
            preparedStatement.setString(2, good.getName());
            preparedStatement.setString(3, good.getDescription());
            preparedStatement.setString(4, good.getOwner());
            preparedStatement.setDouble(5, good.getPrice());
            preparedStatement.setString(6, id);
            logger.debug(preparedStatement);
            rowUpdated = preparedStatement.executeUpdate() > 0;
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.warn("UpdateGoodException", e);
        }
        return rowUpdated;
    }
}

