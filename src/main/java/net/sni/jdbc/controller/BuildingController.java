package net.sni.jdbc.controller;

import net.sni.jdbc.entity.Building;
import net.sni.jdbc.service.BuildingService;

import java.util.Optional;

public interface BuildingController {

    <S extends Building> S saveBuilding(S entity);
    <S extends Building> Iterable<S> saveAllBuildings(Iterable<S> entities);
    Optional<Building> findOneBuilding(Integer id);
    Iterable<Building> findAllBuildings();
    boolean deleteBuilding(Building entity);
    boolean deleteAllBuildings();

}
