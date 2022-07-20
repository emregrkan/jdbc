package net.sni.jdbc.dao.impl;

import net.sni.jdbc.annotation.ChildDao;
import net.sni.jdbc.dao.EmployeeDao;
import net.sni.jdbc.data.DataSource;
import net.sni.jdbc.entity.Building;
import net.sni.jdbc.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO: check find DONE
// TODO: check save

@ChildDao
public class EmployeeDaoImpl implements EmployeeDao {
    private static final String SQL_CREATE = "create table if not exists employees" + "(" + "    id          serial," + "    name        varchar(255) not null," + "    role        varchar(255)," + "    building_id int" + "        constraint building_id" + "            references buildings" + "            on delete set null" + ");" + "create unique index employees_id_uindex" + "    on employees (id);" + "alter table employees" + "    add constraint employees_pk" + "        primary key (id);";
    private static final String SQL_SAVE = "insert into employees (name, role) values (?, ?)";
    private static final String SQL_SAVE_ALL = "insert into employees (name, role) values (?, ?)";
    private static final String SQL_FIND_ONE = "select * from employees e left join buildings b on e.building_id = b.id where e.id=?";
    private static final String SQL_FIND_ALL = "select * from employees e left join buildings b on e.building_id = b.id";
    private static final String SQL_DELETE = "delete from employees where id=?";
    private static final String SQL_DELETE_ALL = "delete from employees";

    private EmployeeDaoImpl() {
    }

    public static EmployeeDao getInstance() {
        return EmployeeDaoImplHelper.employeeDao;
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
    public <S extends Employee> S save(S entity) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getRole());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("User creation failed: No ID generated.");
            }

            generatedKeys.close();

            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <S extends Employee> Iterable<S> saveAll(Iterable<S> entities) {
        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ALL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int i = 0, j = 0;

            for (S entity : entities) {
                preparedStatement.setString(1, entity.getName());
                preparedStatement.setString(2, entity.getRole());
                preparedStatement.addBatch();
                i++;
            }

            preparedStatement.executeBatch();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            for (S entity : entities) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                }
                j++;
            }

            generatedKeys.close();

            if (i != j) {
                throw new RuntimeException("ID generation failed");
            }

            return entities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Employee> findOne(Integer id) {

        Employee employee = null;

        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ONE)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    employee = getEmployee(resultSet);
                }

                return Optional.ofNullable(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Employee> findAll() {

        List<Employee> employees;

        try (Connection connection = DataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL); ResultSet resultSet = preparedStatement.executeQuery()) {
            employees = new ArrayList<>();

            while (resultSet.next()) {
                Employee employee = getEmployee(resultSet);
                employees.add(employee);
            }

            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Employee entity) {

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

    private Employee getEmployee(ResultSet resultSet) throws SQLException {

        Building building;

        int employeeId = resultSet.getInt("id");
        String employeeName = resultSet.getString("name");
        String employeeRole = resultSet.getString("role");

        int buildingId = resultSet.getInt("building_id");
        String buildingAddress = resultSet.getString("address");
        int buildingWorkingEmployees = resultSet.getInt("working_employees");

        building = new Building(buildingId, buildingAddress, buildingWorkingEmployees);
        return new Employee(employeeId, employeeName, employeeRole, building);
    }

    private static class EmployeeDaoImplHelper {
        private static final EmployeeDao employeeDao = new EmployeeDaoImpl();
    }
}
