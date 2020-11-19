import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private static int size = 0;

    void clear() {
        //https://stackoverflow.com/questions/8585879/how-to-remove-all-elements-in-string-array-in-java
        Arrays.fill(storage, null);
        size = 0;
    }

    void save(Resume r) {
        if (getIndexByUuid(r.getUuid()) < 0) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Resume with UUID " + r.getUuid() + " already exists");
        }
    }

    Resume get(String uuid) {
        int index = getIndexByUuid(uuid);
        if (index >= 0) {
            return storage[index];
        }
        return null;
    }

    void delete(String uuid) {
        int index = getIndexByUuid(uuid);
        System.arraycopy(storage, index + 1, storage, index, size() - index);
        size--;
    }

    private int getIndexByUuid(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size());
    }

    int size() {
        return size;
    }
}
