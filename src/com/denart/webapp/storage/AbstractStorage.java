package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void clear() {
        doClear();
    }

    @Override
    public void save(Resume r) {
        doSave(r);
    }

    @Override
    public Resume get(String uuid) {
        return doGet(uuid);
    }

    @Override
    public void update(Resume r) {
        doUpdate(r);
    }

    @Override
    public void delete(String uuid) {
        doDelete(uuid);
    }

    @Override
    public Resume[] getAll() {
        return doGetAllResumes();
    }

    @Override
    public int size() {
        return getStorageSize();
    }

    protected abstract void doSave(Resume r);
    protected abstract void doClear();
    protected abstract Resume doGet(String uuid);
    protected abstract void doUpdate(Resume r);
    protected abstract void doDelete(String uuid);
    protected abstract Resume[] doGetAllResumes();
    protected abstract int getStorageSize();
    protected abstract int getIndex(String uuid);

}
