import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void save(Resume r) {
        if (size <= 10000) {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        int index = getResume(uuid);
        if (index >= 0) {
            return storage[index];
        }
        return null;
    }

    private int getResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        return -1;
    }

    void delete(String uuid) {
        int index = getResume(uuid);
        if (index >= 0) {
            storage[index] = null;
            System.arraycopy(storage, index + 1, storage, index, size);
            size--;
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    int size() {
        return size;
    }
}
