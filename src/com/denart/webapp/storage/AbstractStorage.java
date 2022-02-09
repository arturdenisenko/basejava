package com.denart.webapp.storage;

import com.denart.webapp.exception.ExistStorageException;
import com.denart.webapp.exception.NotExistStorageException;
import com.denart.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume r) {
        if (resumeExistInStorage(r)){
            doSave(r, index);
        }

    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return doGet(uuid, index);
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            doUpdate(r, index);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            doDelete(uuid, index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private Object resumeExistInStorage(Object r) {
        int index = getIndex( r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
    }




    public abstract void clear();

    public abstract Resume[] getAll();

    public abstract int size();

    protected abstract void doSave(Resume r, int index);

    protected abstract Resume doGet(String uuid, int index);

    protected abstract void doUpdate(Resume r, int index);

    protected abstract void doDelete(String uuid, int index);

    protected abstract int getIndex(String uuid);

}
