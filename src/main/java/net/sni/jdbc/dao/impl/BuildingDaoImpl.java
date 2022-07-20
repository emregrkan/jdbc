package net.sni.jdbc.dao.impl;

import net.sni.jdbc.annotation.ParentDao;
import net.sni.jdbc.dao.BuildingDao;
import net.sni.jdbc.data.DataSource;
import net.sni.jdbc.entity.Building;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ParentDao
public class BuildingDaoImpl implements BuildingDao {

    private static final String SQL_CREATE = "create table if not exists buildings" + "(" + "    id                serial," + "    address           text not null," + "    working_employees int default 0" + ");" + "create unique index buildings_id_uindex" + "    on buildings (id);" + "alter table buildings" + "    add constraint buildings_pk" + "        primary key (id);";
    private static final String SQL_SAVE = "insert into buildings (address, working_employees) values (?, ?)";
    private static final String SQL_SAVE_ALL = "insert into buildings (address, working_employees) values (?, ?)";
    private static final String SQL_FIND_ONE = "select * from buildings where id=?";
    private static final String SQL_FIND_ALL = "select * from buildings";
    private static final String SQL_DELETE = "delete from buildings where id=?";
    private static final String SQL_DELETE_ALL = "delete from buildings";

    private BuildingDaoImpl() {
    }

    public static BuildingDao getInstance() {
        return BuildingDaoImplHelper.buildingDao;
    }

    @Override
    public void init() {
        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.executeUpdate();
            System.out.println("Table created");
        } catch (SQLException e) {
            System.out.println("Table could not created or it already exists");
        }
    }

    @Override
    public <S extends Building> S save(S entity) {

        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE)) {
            preparedStatement.setString(1, entity.getAddress());
            preparedStatement.setInt(2, entity.getWorkingEmployees());
            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0 ? entity : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <S extends Building> Iterable<S> saveAll(Iterable<S> entities) {

        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ALL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int i = 0;

            for (S entity : entities) {
                preparedStatement.setString(1, entity.getAddress());
                preparedStatement.setInt(2, entity.getWorkingEmployees());
                preparedStatement.addBatch();
                i++;
            }

            preparedStatement.executeBatch();
            System.out.println("Saved " + i + " entities.");
            return entities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Building> findOne(Integer id) {

        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ONE)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Building building = null;

                while (resultSet.next()) {
                    String address = resultSet.getString("address");
                    int workingEmployees = resultSet.getInt("working_employees");
                    building = new Building(id, address, workingEmployees);
                }

                return Optional.ofNullable(building);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Building> findAll() {

        Building building;
        List<Building> buildings;

        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL); ResultSet resultSet = preparedStatement.executeQuery()) {
            buildings = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String address = resultSet.getString("address");
                int workingEmployees = resultSet.getInt("working_employees");
                building = new Building(id, address, workingEmployees);
                buildings.add(building);
            }

            return buildings;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Building entity) {

        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, entity.getId());
            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteAll() {

        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ALL)) {
            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static class BuildingDaoImplHelper {
        private static final BuildingDao buildingDao = new BuildingDaoImpl();
    }
}
