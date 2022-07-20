package net.sni.jdbc;

import net.sni.jdbc.annotation.ChildDao;
import net.sni.jdbc.annotation.ParentDao;
import net.sni.jdbc.util.EntityManager;


public class Main {
    public static void main(String[] args) {
        // TODO: Database should be created
        // TODO: Put database config in net.sni.jdbc.DataSource

        EntityManager.initAnnotatedEntities(ParentDao.class);
        EntityManager.initAnnotatedEntities(ChildDao.class);
    }
}