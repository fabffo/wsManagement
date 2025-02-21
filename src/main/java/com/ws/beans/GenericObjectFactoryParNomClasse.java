package com.ws.beans;
import java.lang.reflect.Method;
import java.util.Map;

public class GenericObjectFactoryParNomClasse {

    /**
     * Crée et initialise une instance de la classe spécifiée par son nom.
     * @param className Nom de la classe (ex: "com.ws.beans.Tva" ou "com.ws.beans.Client")
     * @param properties Map des propriétés pour initialiser les champs de l'entité
     * @return Une instance initialisée de la classe spécifiée
     * @throws Exception En cas d'erreur d'instanciation ou d'accès aux méthodes
     */
    public static Object createAndInitialize(String className, Map<String, Object> properties) throws Exception {
        // Charge dynamiquement la classe spécifiée
        Class<?> clazz = Class.forName(className);

        // Crée une nouvelle instance de la classe
        Object instance = clazz.getDeclaredConstructor().newInstance();

        // Parcourt les propriétés et initialise les champs correspondants
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            String fieldName = entry.getKey();
            Object value = entry.getValue();

            // Génère le nom de la méthode setter correspondant (ex: setNom pour "nom")
            String setterName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            try {



            	Method setter = null;
            	for (Method method : clazz.getMethods()) {
            	    if (method.getName().equals(setterName) && method.getParameterCount() == 1) {
            	        setter = method;
            	        Class<?> paramType = setter.getParameterTypes()[0];

            	        if (value != null && !paramType.isInstance(value)) {
            	            try {
            	                // Conversion pour Double
            	                if (paramType == Double.class && value instanceof String) {
            	                    value = Double.parseDouble((String) value);
            	                }
            	                // Conversion pour double (primitive)
            	                else if (paramType == double.class && value instanceof String) {
            	                    value = Double.parseDouble((String) value);
            	                }
            	                // Conversion pour Integer
            	                else if (paramType == Integer.class && value instanceof String) {
            	                    value = Integer.parseInt((String) value);
            	                }
            	                // Conversion pour int (primitive)
            	                else if (paramType == int.class && value instanceof String) {
            	                    value = Integer.parseInt((String) value);
            	                }
            	                // Ajoutez des conversions supplémentaires si nécessaire
            	            } catch (NumberFormatException e) {
            	               continue;
            	            }
            	        }
            	        break;
            	    }
            	}

                // Récupère la méthode setter correspondant au champ
               // Method setter = clazz.getMethod(setterName, value.getClass());

                if (setter != null) {
                    setter.invoke(instance, value);
                } else {
                     }
            } catch (Exception e) {
                 }
        }
        return instance;
    }
}
