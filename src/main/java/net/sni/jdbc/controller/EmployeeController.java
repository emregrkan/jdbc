package net.sni.jdbc.controller;

import net.sni.jdbc.entity.Employee;
import net.sni.jdbc.service.EmployeeService;

import java.util.Optional;

public interface EmployeeController {

    <S extends Employee> S saveEmployee(S entity);
    <S extends Employee> Iterable<S> saveAllEmployees(Iterable<S> entities);
    Optional<Employee> findOneEmployee(Integer id);
    Iterable<Employee> findAllEmployees();
    boolean deleteEmployee(Employee entity);
    boolean deleteAllEmployees();

}
