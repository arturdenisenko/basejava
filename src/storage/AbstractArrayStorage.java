package storage;

import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("model.Resume with UUID " + r.getUuid() + " already exists");
        } else {
            if (size == storage.length) {
                System.out.println("Storage are overflow");
            } else {
                doSave(r);
            }
        }
    }

    protected abstract void doSave(Resume r);

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Resume with UUID " + r.getUuid() + " are not  exists");
        } else {
            doUpdate(r, index);
        }
    }

    protected abstract void doUpdate(Resume r, int index);

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume with UUID " + uuid + " are not  exists");
        } else {
            doDelete(uuid, index);
        }

    }

    protected abstract void doDelete(String uuid, int index);

    public void clear() {
        //https://stackoverflow.com/questions/8585879/how-to-remove-all-elements-in-string-array-in-java
        Arrays.fill(storage, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size());
    }

    protected abstract int getIndex(String uuid);
}
