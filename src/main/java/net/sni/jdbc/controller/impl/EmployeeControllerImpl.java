package net.sni.jdbc.controller.impl;

import net.sni.jdbc.controller.EmployeeController;
import net.sni.jdbc.entity.Employee;

import java.util.Optional;

public class EmployeeControllerImpl implements EmployeeController {
    @Override
    public <S extends Employee> S saveEmployee(S entity) {
        return null;
    }

    @Override
    public <S extends Employee> Iterable<S> saveAllEmployees(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Employee> findOneEmployee(Integer id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Employee> findAllEmployees() {
        return null;
    }

    @Override
    public boolean deleteEmployee(Employee entity) {
        return false;
    }

    @Override
    public boolean deleteAllEmployees() {
        return false;
    }
}
