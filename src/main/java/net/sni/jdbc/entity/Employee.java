package net.sni.jdbc.entity;


public class Employee {

    private Integer id;
    private String name;
    private String role;
    private Building building;

    public Employee() {
    }

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public Employee(Integer id, String name, String role, Building building) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.building = building;
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

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", role='" + role + '\'' + ", building=" + building + '}';
    }
}
