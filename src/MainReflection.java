import com.denart.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume();
        Field declaredField = resume.getClass().getDeclaredFields()[0];
        declaredField.setAccessible(true);
        System.out.println(declaredField.getName());
        declaredField.get(resume);
        System.out.println(resume);
        declaredField.set(resume, "new_uuid");
        System.out.println(resume);
        // TODO invoke r.toString via reflection
        //https://www.baeldung.com/java-method-reflection
        Method toString = Resume.class.getDeclaredMethod("toString");
        //toString.setAccessible(true);
        System.out.println("Invoke toString - " + toString.invoke(resume));
    }
}
