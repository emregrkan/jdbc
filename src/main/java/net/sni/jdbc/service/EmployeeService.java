package net.sni.jdbc.service;

import net.sni.jdbc.dao.EmployeeDao;
import net.sni.jdbc.entity.Employee;

import java.util.Optional;

public interface EmployeeService {

    <S extends Employee> S saveEmployee(S entity);
    <S extends Employee> Iterable<S> saveAllEmployees(Iterable<S> entities);
    Optional<Employee> findOneEmployee(Integer id);
    Iterable<Employee> findAllEmployees();
    boolean deleteEmployee(Employee entity);
    boolean deleteAllEmployees();

}
