package net.sni.jdbc.dto;

import net.sni.jdbc.entity.Building;
import net.sni.jdbc.entity.Employee;

public class EmployeeDto {
    private Integer id;
    private String name;
    private String role;
    private String buildingAddress;

    public static EmployeeDto convertToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setRole(employee.getRole());
        Building building = employee.getBuilding();

        if (building != null) {
            employeeDto.setBuildingAddress(building.getAddress());
        }

        return employeeDto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBuildingAddress() {
        return buildingAddress;
    }

    public void setBuildingAddress(String buildingAddress) {
        this.buildingAddress = buildingAddress;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", buildingAddress='" + buildingAddress + '\'' +
                '}';
    }
}
