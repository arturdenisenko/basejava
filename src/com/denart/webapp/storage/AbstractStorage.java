package com.denart.webapp.storage;

import com.denart.webapp.exception.ExistStorageException;
import com.denart.webapp.exception.NotExistStorageException;
import com.denart.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    public final static Comparator<Resume> RESUME_WITH_FULL_NAME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExists(Object searchKey);

    protected abstract List<Resume> getSortedCollection();

    @Override
    public List<Resume> getAllSorted() {
        return getSortedCollection();
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getSearchKeyIfExist(r.getUuid());
        doSave(r, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getSearchKeyIfNotExist(uuid);
        return doGet(searchKey);
    }

    @Override
    public void update(Resume r) {
        Object searchKey = getSearchKeyIfNotExist(r.getUuid());
        doUpdate(r, searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getSearchKeyIfNotExist(uuid);
        doDelete(searchKey);
    }

    private Object getSearchKeyIfExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExists(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getSearchKeyIfNotExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExists(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
