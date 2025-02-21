package com.ws.Dao;
import java.lang.reflect.Field;
import java.util.Map;

public class GenericObjectFactory {

    // Méthode générique pour instancier dynamiquement un objet et initialiser ses champs
    public static <T> T createAndInitialize(Class<T> clazz, Map<String, Object> properties) {
        T instance = null;
        try {
            // Instanciation dynamique de la classe spécifiée
            instance = clazz.getDeclaredConstructor().newInstance();

            // Boucle sur chaque entrée de la Map pour définir les champs de l'objet
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                String fieldName = entry.getKey();
                Object fieldValue = entry.getValue();

                // Récupérer le champ et lui affecter la valeur
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true); // Rendre accessible si private
                field.set(instance, fieldValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
}
