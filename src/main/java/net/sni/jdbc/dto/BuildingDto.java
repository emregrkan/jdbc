package net.sni.jdbc.dto;

import net.sni.jdbc.entity.Building;

public class BuildingDto {
    private String address;
    private Integer workingEmployees;

    public static BuildingDto convertToDto(Building building) {
        final BuildingDto buildingDto = new BuildingDto();
        buildingDto.setAddress(building.getAddress());
        buildingDto.setWorkingEmployees(building.getWorkingEmployees());
        return buildingDto;
    }

    public static Building convertToEntity(BuildingDto buildingDto) {
        final Building building = new Building();
        building.setAddress(buildingDto.getAddress());
        building.setWorkingEmployees(buildingDto.getWorkingEmployees());
        return building;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getWorkingEmployees() {
        return workingEmployees;
    }

    public void setWorkingEmployees(Integer workingEmployees) {
        this.workingEmployees = workingEmployees;
    }

    @Override
    public String toString() {
        return "BuildingDto{" +
                "address='" + address + '\'' +
                ", workingEmployees=" + workingEmployees +
                '}';
    }
}
