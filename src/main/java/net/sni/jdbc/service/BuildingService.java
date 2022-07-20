package net.sni.jdbc.service;

import net.sni.jdbc.dao.BuildingDao;
import net.sni.jdbc.entity.Building;

import java.util.Optional;

public interface BuildingService {

    <S extends Building> S saveBuilding(S entity);
    <S extends Building> Iterable<S> saveAllBuildings(Iterable<S> entities);
    Optional<Building> findOneBuilding(Integer id);
    Iterable<Building> findAllBuildings();
    boolean deleteBuilding(Building entity);
    boolean deleteAllBuildings();

}
