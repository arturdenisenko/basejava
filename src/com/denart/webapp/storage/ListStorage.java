package com.denart.webapp.storage;

import com.denart.webapp.exception.ExistStorageException;
import com.denart.webapp.exception.NotExistStorageException;
import com.denart.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected void doSave(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            storage.add(r);
        }
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected Resume doGet(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage.get(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    protected void doUpdate(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage.set(index, r);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    protected void doDelete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            storage.remove(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    protected Resume[] doGetAllResumes() {
        return  storage.toArray(Resume[]::new);
    }

    @Override
    protected int getStorageSize() {
        return storage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
