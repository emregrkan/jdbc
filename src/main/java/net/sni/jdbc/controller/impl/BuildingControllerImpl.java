package net.sni.jdbc.controller.impl;

import net.sni.jdbc.controller.BuildingController;
import net.sni.jdbc.entity.Building;
import net.sni.jdbc.service.BuildingService;
import net.sni.jdbc.service.impl.BuildingServiceImpl;

import java.util.Optional;

public class BuildingControllerImpl implements BuildingController {

    private final BuildingService buildingService;

    public BuildingControllerImpl() {
        this.buildingService = new BuildingServiceImpl();
    }

    @Override
    public <S extends Building> S saveBuilding(S entity) {
        return this.buildingService.saveBuilding(entity);
    }

    @Override
    public <S extends Building> Iterable<S> saveAllBuildings(Iterable<S> entities) {
        return this.buildingService.saveAllBuildings(entities);
    }

    @Override
    public Optional<Building> findOneBuilding(Integer id) {
        return this.buildingService.findOneBuilding(id);
    }

    @Override
    public Iterable<Building> findAllBuildings() {
        return this.buildingService.findAllBuildings();
    }

    @Override
    public boolean deleteBuilding(Building entity) {
        return this.buildingService.deleteBuilding(entity);
    }

    @Override
    public boolean deleteAllBuildings() {
        return this.buildingService.deleteAllBuildings();
    }
}
