import com.denart.webapp.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume resume = new Resume();
        Field declaredField = resume.getClass().getDeclaredFields()[0];
        declaredField.setAccessible(true);
        System.out.println(declaredField.getName());
        declaredField.get(resume);
        System.out.println(resume);
        declaredField.set(resume, "new_uuid");
        System.out.println(resume);
        // TODO invoke r.toString via reflection
        //Method method = resume.getClass().getMethod()
    }
}
