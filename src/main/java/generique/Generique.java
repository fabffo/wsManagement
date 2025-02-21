package generique;
import java.lang.reflect.Constructor;

public class Generique<T> {

    private Class<T> classe;

    public Generique(Class<T> classe) {
        this.classe = classe;
    }

    public Object invokeDynamicMethod(String methodName, Object... params) {
        try {
            // Recherche de la méthode avec les types de paramètres
            Class<?>[] paramTypes = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                paramTypes[i] = params[i].getClass();
            }

            // Création dynamique de l'instance via le constructeur
            Constructor<T> constructor = classe.getDeclaredConstructor();
            T instance = constructor.newInstance(); // Utilisation de getDeclaredConstructor().newInstance()

            // Invocation de la méthode via réflexion
            return classe.getDeclaredMethod(methodName, paramTypes).invoke(instance, params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
