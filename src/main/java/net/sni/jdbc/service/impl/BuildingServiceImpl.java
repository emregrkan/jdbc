package net.sni.jdbc.service.impl;

import net.sni.jdbc.dao.BuildingDao;
import net.sni.jdbc.dao.impl.BuildingDaoImpl;
import net.sni.jdbc.entity.Building;
import net.sni.jdbc.service.BuildingService;

import java.util.Optional;

public class BuildingServiceImpl implements BuildingService {

    private final BuildingDao buildingDao;

    public BuildingServiceImpl() {
        this.buildingDao = BuildingDaoImpl.getInstance();
    }

    @Override
    public <S extends Building> S saveBuilding(S entity) {
        return this.buildingDao.save(entity);
    }

    @Override
    public <S extends Building> Iterable<S> saveAllBuildings(Iterable<S> entities) {
        return this.buildingDao.saveAll(entities);
    }

    @Override
    public Optional<Building> findOneBuilding(Integer id) {
        return this.buildingDao.findOne(id);
    }

    @Override
    public Iterable<Building> findAllBuildings() {
        return this.buildingDao.findAll();
    }

    @Override
    public boolean deleteBuilding(Building entity) {
        return this.buildingDao.delete(entity);
    }

    @Override
    public boolean deleteAllBuildings() {
        return this.buildingDao.deleteAll();
    }
}
