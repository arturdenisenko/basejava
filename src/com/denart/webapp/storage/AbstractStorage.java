package com.denart.webapp.storage;

import com.denart.webapp.exception.ExistStorageException;
import com.denart.webapp.exception.NotExistStorageException;
import com.denart.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume r) {
        Object index = resumeExistInStorage(r.getUuid());
        doSave(r, index);
    }

    @Override
    public Resume get(String uuid) {
        Object index = resumeNotExistInStorage(uuid);
        return doGet(uuid, index);
    }

    @Override
    public void update(Resume r) {
        Object index = resumeNotExistInStorage(r.getUuid());
        doUpdate(r, index);
    }

    @Override
    public void delete(String uuid) {
        Object index = resumeNotExistInStorage(uuid);
        doDelete(uuid, index);
    }

    private Object resumeExistInStorage(String uuid) {
        int index = (int) getIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    private Object resumeNotExistInStorage(String uuid) {
        int index = (int) getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    public abstract void clear();

    public abstract Resume[] getAll();

    public abstract int size();

    protected abstract void doSave(Resume r, Object index);

    protected abstract Resume doGet(String uuid, Object index);

    protected abstract void doUpdate(Resume r, Object index);

    protected abstract void doDelete(String uuid, Object Index);

    protected abstract Object getIndex(String uuid);

}
