package net.sni.jdbc.service.impl;

import net.sni.jdbc.dao.EmployeeDao;
import net.sni.jdbc.dao.impl.EmployeeDaoImpl;
import net.sni.jdbc.entity.Employee;
import net.sni.jdbc.service.EmployeeService;

import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    private EmployeeServiceImpl() {
        this.employeeDao = EmployeeDaoImpl.getInstance();
    }

    public static EmployeeService getInstance() {
        return EmployeeServiceImplHolder.INSTANCE;
    }

    @Override
    public <S extends Employee> S save(S entity) {
        return this.employeeDao.save(entity);
    }

    @Override
    public <S extends Employee> Iterable<S> saveAll(Iterable<S> entities) {
        return this.employeeDao.saveAll(entities);
    }

    @Override
    public Optional<Employee> findOne(Integer id) {
        return this.employeeDao.findOne(id);
    }

    @Override
    public Iterable<Employee> findAll() {
        return this.employeeDao.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return this.employeeDao.delete(id);
    }

    @Override
    public boolean deleteAll() {
        return this.employeeDao.deleteAll();
    }

    private static class EmployeeServiceImplHolder {
        private static final EmployeeService INSTANCE = new EmployeeServiceImpl();
    }
}
