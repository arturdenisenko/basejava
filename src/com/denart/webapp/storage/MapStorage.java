package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

public class MapStorage extends AbstractStorage {

    @Override
    protected void doSave(Resume r) {

    }

    @Override
    protected void doClear() {

    }

    @Override
    protected Resume doGet(String uuid) {
        return null;
    }

    @Override
    protected void doUpdate(Resume r) {

    }

    @Override
    protected void doDelete(String uuid) {

    }

    @Override
    protected Resume[] doGetAllResumes() {
        return new Resume[0];
    }

    @Override
    protected int getStorageSize() {
        return 0;
    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }
}
