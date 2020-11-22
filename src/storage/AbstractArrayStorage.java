package storage;

import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void save(Resume r) {
        //TODO CHECK SAVE METHOD
        int index = getIndex(r.getUuid());
        if (index != -1) {
            System.out.println("Resume with UUID " + r.getUuid() + " already exists");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            doSave(r, index);
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Resume with UUID " + r.getUuid() + " are not  exists");
        } else {
            doUpdate(r, index);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume with UUID " + uuid + " are not  exists");
        } else {
            doDelete(uuid, index);
        }

    }


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

    protected abstract void doUpdate(Resume r, int index);

    protected abstract void doSave(Resume r, int index);

    protected abstract void doDelete(String uuid, int index);

    protected abstract int getIndex(String uuid);
}
