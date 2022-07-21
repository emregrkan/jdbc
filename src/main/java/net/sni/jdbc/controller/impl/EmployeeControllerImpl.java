package net.sni.jdbc.controller.impl;

import net.sni.jdbc.controller.EmployeeController;
import net.sni.jdbc.entity.Employee;
import net.sni.jdbc.service.EmployeeService;
import net.sni.jdbc.service.impl.EmployeeServiceImpl;

import java.util.Optional;

public class EmployeeControllerImpl implements EmployeeController {
    private final EmployeeService employeeService;

    private EmployeeControllerImpl() {
        this.employeeService = EmployeeServiceImpl.getInstance();
    }

    public static EmployeeController getInstance() {
        return EmployeeControllerImplHolder.INSTANCE;
    }

    @Override
    public <S extends Employee> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Employee> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Employee> findOne(Integer id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Employee> findAll() {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    private static class EmployeeControllerImplHolder {
        private static final EmployeeController INSTANCE = new EmployeeControllerImpl();
    }
}
