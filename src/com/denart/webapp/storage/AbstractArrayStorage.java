package com.denart.webapp.storage;

import com.denart.webapp.exception.StorageException;
import com.denart.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

    protected abstract Integer getSearchKey(String uuid);

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        insertElement(r, searchKey);
        size++;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        storage[(int) searchKey] = r;
    }

    @Override
    protected void doDelete(Integer searchKey) {
        fillDeletedElement((int) searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public List<Resume> doGetResumesListFromStorage() {
        return (Arrays.stream(Arrays.copyOfRange(storage, 0, size)).collect(Collectors.toList()));
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isExists(Integer searchKey) {
        return (int) searchKey >= 0;
    }
}
