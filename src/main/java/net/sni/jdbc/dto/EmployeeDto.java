package net.sni.jdbc.dto;

import net.sni.jdbc.entity.Building;
import net.sni.jdbc.entity.Employee;

import java.util.Optional;

public class EmployeeDto {
    private String name;
    private String role;
    private BuildingDto buildingDto;

    public static <S extends Employee> EmployeeDto convertToDto(S employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(employee.getName());
        employeeDto.setRole(employee.getRole());
        Building building = employee.getBuilding();

        if (building != null) {
            employeeDto.setBuildingDto(BuildingDto.convertToDto(building));
        }

        return employeeDto;
    }

    public static Employee convertToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setRole(employeeDto.getRole());
        BuildingDto buildingDto = employeeDto.getBuildingDto();

        if (buildingDto != null) {
            employee.setBuilding(BuildingDto.convertToEntity(buildingDto));
        }

        return employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BuildingDto getBuildingDto() {
        return buildingDto;
    }

    public void setBuildingDto(BuildingDto buildingDto) {
        this.buildingDto = buildingDto;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", building='" + buildingDto + '\'' +
                '}';
    }
}
