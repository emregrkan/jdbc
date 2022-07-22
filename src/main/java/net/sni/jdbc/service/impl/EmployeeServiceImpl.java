package net.sni.jdbc.service.impl;

import net.sni.jdbc.dao.EmployeeDao;
import net.sni.jdbc.dao.impl.EmployeeDaoImpl;
import net.sni.jdbc.dto.EmployeeDto;
import net.sni.jdbc.entity.Employee;
import net.sni.jdbc.service.EmployeeService;
import net.sni.jdbc.util.Mapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDao employeeDao;

    private EmployeeServiceImpl() {
        this.employeeDao = EmployeeDaoImpl.getInstance();
    }

    public static EmployeeService getInstance() {
        return EmployeeServiceImplHolder.INSTANCE;
    }


    @Override
    public EmployeeDto save(EmployeeDto entityDto) {
        Employee employee = EmployeeDto.convertToEntity(entityDto);
        return EmployeeDto.convertToDto(this.employeeDao.save(employee));
    }

    @Override
    public Iterable<EmployeeDto> saveAll(Iterable<EmployeeDto> entityDtos) {
        List<Employee> employees = new ArrayList<>();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        for (EmployeeDto employeeDto : entityDtos) {
            employees.add(EmployeeDto.convertToEntity(employeeDto));
        }

        for (Employee employee : this.employeeDao.saveAll(employees)) {
            employeeDtos.add(EmployeeDto.convertToDto(employee));
        }

        return employeeDtos;
    }

    @Override
    public Optional<EmployeeDto> findOne(Integer id) {
        Employee employee = this.employeeDao.findOne(id).orElse(null);

        if (employee == null) {
            return Optional.empty();
        }

        return Optional.of(EmployeeDto.convertToDto(employee));
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
