package net.sni.jdbc.service.impl;

import net.sni.jdbc.dao.BuildingDao;
import net.sni.jdbc.dao.impl.BuildingDaoImpl;
import net.sni.jdbc.entity.Building;
import net.sni.jdbc.service.BuildingService;

import java.util.Optional;

public class BuildingServiceImpl implements BuildingService {

    private final BuildingDao buildingDao;

    private BuildingServiceImpl() {
        this.buildingDao = BuildingDaoImpl.getInstance();
    }

    public static BuildingService getInstance() {
        return BuildingServiceImplHolder.INSTANCE;
    }

    @Override
    public <S extends Building> S save(S entity) {
        return this.buildingDao.save(entity);
    }

    @Override
    public <S extends Building> Iterable<S> saveAll(Iterable<S> entities) {
        return this.buildingDao.saveAll(entities);
    }

    @Override
    public Optional<Building> findOne(Integer id) {
        return this.buildingDao.findOne(id);
    }

    @Override
    public Iterable<Building> findAll() {
        return this.buildingDao.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return this.buildingDao.delete(id);
    }

    @Override
    public boolean deleteAll() {
        return this.buildingDao.deleteAll();
    }

    private static class BuildingServiceImplHolder {
        private static final BuildingService INSTANCE = new BuildingServiceImpl();
    }
}
