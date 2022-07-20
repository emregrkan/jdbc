package net.sni.jdbc.service.impl;

import net.sni.jdbc.dao.EmployeeDao;
import net.sni.jdbc.dao.impl.EmployeeDaoImpl;
import net.sni.jdbc.entity.Employee;
import net.sni.jdbc.service.EmployeeService;

import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl() {
        this.employeeDao = EmployeeDaoImpl.getInstance();
    }

    @Override
    public <S extends Employee> S saveEmployee(S entity) {
        return this.employeeDao.save(entity);
    }

    @Override
    public <S extends Employee> Iterable<S> saveAllEmployees(Iterable<S> entities) {
        return this.employeeDao.saveAll(entities);
    }

    @Override
    public Optional<Employee> findOneEmployee(Integer id) {
        return this.employeeDao.findOne(id);
    }

    @Override
    public Iterable<Employee> findAllEmployees() {
        return this.employeeDao.findAll();
    }

    @Override
    public boolean deleteEmployee(Employee entity) {
        return this.employeeDao.delete(entity);
    }

    @Override
    public boolean deleteAllEmployees() {
        return this.employeeDao.deleteAll();
    }
}
