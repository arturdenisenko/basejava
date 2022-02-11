package com.denart.webapp.storage;

import com.denart.webapp.exception.ExistStorageException;
import com.denart.webapp.exception.NotExistStorageException;
import com.denart.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume r) {
        Object index = existInStorage(r.getUuid());
        doSave(r, index);
    }

    @Override
    public Resume get(String uuid) {
        Object index = notExistInStorage(uuid);
        return doGet(uuid, index);
    }

    @Override
    public void update(Resume r) {
        Object index = notExistInStorage(r.getUuid());
        doUpdate(r, index);
    }

    @Override
    public void delete(String uuid) {
        Object index = notExistInStorage(uuid);
        doDelete(uuid, index);
    }

    private Object existInStorage(String uuid) {
        int index = (int) getSearchKey(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    private Object notExistInStorage(String uuid) {
        int index = (int) getSearchKey(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object uuid, Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract void doDelete(Object uuid, Object searchKey);

    protected abstract Object getSearchKey(String uuid);
}
