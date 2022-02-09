package com.denart.webapp.storage;

import com.denart.webapp.exception.StorageException;
import com.denart.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected void doSave(Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r,(int) getIndex(r.getUuid()));
            size++;
        }
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume doGet(String uuid) {
        return storage[(int) getIndex(uuid)];
    }

    @Override
    protected void doUpdate(Resume r) {
        storage[(int) getIndex(r.getUuid())] = r;
    }

    @Override
    protected void doDelete(String uuid) {
        fillDeletedElement((int) getIndex(uuid));
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

    protected abstract Object getIndex(String uuid);
}
