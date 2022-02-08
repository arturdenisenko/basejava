package com.denart.webapp.storage;

import com.denart.webapp.exception.ExistStorageException;
import com.denart.webapp.exception.NotExistStorageException;
import com.denart.webapp.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new TreeMap<>();

    @Override
    protected void doSave(Resume r) {
        if (getIndex(r.getUuid()) < 0) {
            storage.put(r.getUuid(), r);
        } else {
            throw new ExistStorageException(r.getUuid());
        }
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected Resume doGet(String uuid) {
        if (getIndex(uuid) > 0) {
            return storage.get(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }


    @Override
    protected void doUpdate(Resume r) {
        if (getIndex(r.getUuid()) > 0) {
            storage.put(r.getUuid(), r);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }

    }

    @Override
    protected void doDelete(String uuid) {
        if (getIndex(uuid) > 0) {
            storage.remove(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    protected Resume[] doGetAllResumes() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    protected int getStorageSize() {
        return storage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        return storage.containsKey(uuid) ? 1 : -1;
    }
}
