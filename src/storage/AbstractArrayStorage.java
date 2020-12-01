package storage;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void doArraySave(Resume r, Object searchKey);

    protected abstract void doDelete(String uuid, Object searchKey);

    protected abstract Integer getSearchKey(String uuid);

    public void doClear() {
        //https://stackoverflow.com/questions/8585879/how-to-remove-all-elements-in-string-array-in-java
        Arrays.fill(storage, null);
        size = 0;
    }

    public int getStorageSize() {
        return size;
    }

    public Resume doGet(String uuid, Object searchKey) {
        return storage[(Integer) searchKey];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected void doUpdate(Resume r, Object searchKey) {
        storage[(Integer) searchKey] = r;
    }

    protected void doSave(Resume r, Object searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            doArraySave(r, searchKey);
        }
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }


}
