package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private static int size = 0;

    public void clear() {
        //https://stackoverflow.com/questions/8585879/how-to-remove-all-elements-in-string-array-in-java
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume r) {
        if (getIndexByUuid(r.getUuid()) < 0) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("model.Resume with UUID " + r.getUuid() + " already exists");
        }
    }

    public void update(Resume r) {
        int index = getIndexByUuid(r.getUuid());
        if (index > 0) {
            storage[index] = r;
        } else {
            System.out.println("Resume with UUID " + r.getUuid() + " are not  exists");
        }
    }

    public Resume get(String uuid) {
        int index = getIndexByUuid(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("The storage with uuid " + uuid + " are not exists");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndexByUuid(uuid);
        if (index > 0) {
            System.arraycopy(storage, index + 1, storage, index, size() - index);
            size--;
        } else {
            System.out.println("Resume with UUID" + uuid + "are not  exists");
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size());
    }

    public int size() {
        return size;
    }

    /**
     * method for this class only
     */
    private int getIndexByUuid(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
