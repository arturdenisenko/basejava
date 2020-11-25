import model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        //Field field = r.getClass().getDeclaredFields()[0];
        //field.setAccessible(true);
        //System.out.println(field.getName());
        //field.set(r,"FFFF-fasdfads-0006346sa");
        //TODO : invoke r.toString via reflection
        Resume r = new Resume();
        Method declaredMethod = r.getClass().getDeclaredMethod("ToString", Resume.class);
        declaredMethod.invoke(r);
        System.out.println(declaredMethod.getName());
        // System.out.println(r);
    }
}
