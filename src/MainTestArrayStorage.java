import com.denart.webapp.model.Resume;
import com.denart.webapp.storage.SortedArrayStorage;
import com.denart.webapp.storage.Storage;

/**
 * Test for your com.denart.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {

        final String UUID_1 = "uuid1";
        final String UUID_2 = "uuid2";
        final String UUID_3 = "uuid3";
        final String UUID_4 = "uuid4";

        final String FULL_NAME_1 = "IVANOV SERGEI";
        final String FULL_NAME_2 = "PETROV NIKOLAI";
        final String FULL_NAME_3 = "VOSTRECOV PETR";
        final String FULL_NAME_4 = "POLINSKII ILYA";

        final Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_1);
        final Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2);
        final Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3);
        final Resume RESUME_4 = new Resume(UUID_4, FULL_NAME_4);

        ARRAY_STORAGE.save(RESUME_4);
        ARRAY_STORAGE.save(RESUME_2);
        ARRAY_STORAGE.save(RESUME_3);
        ARRAY_STORAGE.save(RESUME_1);


        System.out.println("Get r1: " + ARRAY_STORAGE.get(RESUME_1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(RESUME_3.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Object r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
