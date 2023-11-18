package persistence.entity.attribute;

import jakarta.persistence.Entity;

import java.util.List;

public class EntityBinder {
    static {
        final String basePackage = "entity";

        EntityAttributes entityAttributes = new EntityAttributes();
        ComponentScanner scanner = new ComponentScanner();
        try {
            List<Class<?>> clazzInPersistence = scanner.scan(basePackage);
            for (Class<?> clazz : clazzInPersistence) {
                if (clazz.isAnnotationPresent(Entity.class)) {
                    entityAttributes.putEntityAttribute(clazz);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void init() {

    }
}
