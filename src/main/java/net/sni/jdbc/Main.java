package net.sni.jdbc;

import net.sni.jdbc.annotation.ChildDao;
import net.sni.jdbc.annotation.ParentDao;
import net.sni.jdbc.cli.CommandLineInterface;
import net.sni.jdbc.util.EntityManager;

import java.lang.reflect.InvocationTargetException;


public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // TODO: Database should be created
        // TODO: Put database config in net.sni.jdbc.DataSource

        EntityManager.initAnnotatedEntities(ParentDao.class);
        EntityManager.initAnnotatedEntities(ChildDao.class);

        CommandLineInterface.run();
    }
}