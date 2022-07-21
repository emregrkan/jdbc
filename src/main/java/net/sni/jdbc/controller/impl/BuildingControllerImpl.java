package net.sni.jdbc.controller.impl;

import net.sni.jdbc.controller.BuildingController;
import net.sni.jdbc.entity.Building;
import net.sni.jdbc.service.BuildingService;
import net.sni.jdbc.service.impl.BuildingServiceImpl;

import java.util.Optional;

public class BuildingControllerImpl implements BuildingController {

    private final BuildingService buildingService;

    private BuildingControllerImpl() {
        this.buildingService = BuildingServiceImpl.getInstance();
    }

    public static BuildingController getInstance() {
        return BuildingControllerImplHolder.INSTANCE;
    }

    @Override
    public <S extends Building> S save(S entity) {
        return this.buildingService.save(entity);
    }

    @Override
    public <S extends Building> Iterable<S> saveAll(Iterable<S> entities) {
        return this.buildingService.saveAll(entities);
    }

    @Override
    public Optional<Building> findOne(Integer id) {
        return this.buildingService.findOne(id);
    }

    @Override
    public Iterable<Building> findAll() {
        return this.buildingService.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return this.buildingService.delete(id);
    }

    @Override
    public boolean deleteAll() {
        return this.buildingService.deleteAll();
    }

    private static class BuildingControllerImplHolder {
        private static final BuildingController INSTANCE = new BuildingControllerImpl();
    }
}
