import model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) {

        //Field field = r.getClass().getDeclaredFields()[0];
        //field.setAccessible(true);
        //System.out.println(field.getName());
        //field.set(r,"FFFF-fasdfads-0006346sa");
        //TODO : invoke r.toString via reflection
        Resume r = new Resume();
        System.out.println("Called by reflection:     " + doReflectionCall(r, "toString", new Object[]{}, String.class));

    }

    //https://stackoverflow.com/questions/55239721/call-objects-tostring-method-from-class-class
    @SuppressWarnings("unchecked")
    public static <T, E> E doReflectionCall(T obj, String methodName, Object[] arguments, Class<E> returnClazz) {
        try {
            Class<?> clazz = obj.getClass();
            Method myMethod = clazz.getDeclaredMethod(methodName);
            return (E) myMethod.invoke(obj, arguments);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
}
