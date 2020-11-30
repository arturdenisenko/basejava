package storage;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void doClear() {
        //https://stackoverflow.com/questions/8585879/how-to-remove-all-elements-in-string-array-in-java
        Arrays.fill(storage, null);
        size = 0;
    }

    public int getStorageSize() {
        return size;
    }

    public Resume doGet(int index, String uuid) {
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected void doUpdate(Resume r, int index) {
        storage[index] = r;
    }

    protected void doSave(Resume r, int index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            doArraySave(r, index);
        }
    }

    protected abstract void doArraySave(Resume r, int index);

    protected abstract void doDelete(String uuid, int index);
}
