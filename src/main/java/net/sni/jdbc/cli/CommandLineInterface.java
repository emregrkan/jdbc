package net.sni.jdbc.cli;

import net.sni.jdbc.annotation.Entity;
import net.sni.jdbc.controller.Controller;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class CommandLineInterface {

    private static final Map<Integer, Class<?>> entities = listEntities();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String options = "\n" + "*******************************\n" + "\t\t\tOPTIONS\t\n" + "*******************************\n" + "1. List Entities\n" + "2. Select Entity\n" + "3. Entity Operations\n";

    public static void run() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> selectedEntity = null;
        Controller<?, Number> selectedEntityController = null;
        Class<?> selectedEntityDto = null;
        int option;

        do {
            System.out.println(options);

            if (selectedEntity != null) {
                System.out.println("** Selected entity: " + selectedEntity.getSimpleName() + " **\n");
            }

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("** Listing entities **");
                    entities.forEach((index, clazz) -> System.out.println(index + ". " + clazz.getSimpleName()));
                    System.out.println();
                    break;
                case 2:
                    System.out.println("** Select entity **");
                    selectedEntity = entities.get(scanner.nextInt());
                    selectedEntityController = (Controller<?, Number>) selectedEntity.getAnnotation(Entity.class).controller().getMethod("getInstance").invoke(null);
                    System.out.println("** " + selectedEntity.getSimpleName() + " selected **");
                    break;
                case 3:
                    if (selectedEntity == null) {
                        System.out.println("No entity selected");
                        break;
                    }

                    entityMethods(selectedEntity, selectedEntityController);
                default:
                    break;
            }
        } while (option != 0);

        scanner.close();
    }

    private static Map<Integer, Class<?>> listEntities() {
        int index = 0;
        final Map<Integer, Class<?>> entitiesMap = new HashMap<>();

        for (Class<?> clazz : new Reflections("net.sni.jdbc.entity").getTypesAnnotatedWith(Entity.class)) {
            entitiesMap.put(++index, clazz);
        }

        return entitiesMap;
    }


    private static void entityMethods(final Class<?> selectedEntity, final Controller<?, Number> selectedEntityController) {
        final String options = "\n" + "*******************************\n" + "\t\t" + selectedEntity.getSimpleName().toUpperCase(Locale.ENGLISH) + " OPTIONS\t\n" + "*******************************\n" + "1. Save One\n" + "2. Save All\n" + "3. Find One\n" + "4. Find All\n" + "5. Delete One\n" + "6. Delete All\n";
        int option;

        do {
            System.out.println(options);
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("** Save One **");
                    break;
                case 2:
                    System.out.println("** Save All **");
                    break;
                case 3:
                    System.out.println("** Find One **");
                    System.out.print("Enter Entity ID: ");
                    System.out.println(selectedEntityController.findOne(scanner.nextInt()).orElse(null));
                    break;
                case 4:
                    System.out.println("** Find All **");
                    selectedEntityController.findAll().forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("** Delete One **");
                    System.out.println(selectedEntityController.delete(scanner.nextInt()) ? "Deleted successfully" : "Could not be deleted");
                    break;
                case 6:
                    System.out.println("** Delete All **");
                    break;
                default:
                    break;
            }
        } while (option != 0);
    }
}
