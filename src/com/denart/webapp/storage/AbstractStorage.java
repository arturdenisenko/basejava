package com.denart.webapp.storage;

import com.denart.webapp.exception.ExistStorageException;
import com.denart.webapp.exception.NotExistStorageException;
import com.denart.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume r) {
        resumeExistInStorage(r.getUuid());
        doSave(r);
    }

    @Override
    public Resume get(String uuid) {
        resumeNotExistInStorage(uuid);
        return doGet(uuid);
    }

    @Override
    public void update(Resume r) {
        resumeNotExistInStorage(r.getUuid());
        doUpdate(r);
    }

    @Override
    public void delete(String uuid) {
        resumeNotExistInStorage(uuid);
        doDelete(uuid);
    }

    private void resumeExistInStorage(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
    }

    private void resumeNotExistInStorage(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
    }


    public abstract void clear();

    public abstract Resume[] getAll();

    public abstract int size();

    protected abstract void doSave(Resume r);

    protected abstract Resume doGet(String uuid);

    protected abstract void doUpdate(Resume r);

    protected abstract void doDelete(String uuid);

    protected abstract Integer getIndex(String uuid);

}
