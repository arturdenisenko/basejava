package com.denart.webapp.storage;

import com.denart.webapp.exception.ExistStorageException;
import com.denart.webapp.exception.NotExistStorageException;
import com.denart.webapp.model.Resume;

import java.util.Comparator;

public abstract class AbstractStorage implements Storage {

    protected final static Comparator<Resume> RESUME_WITH_FULL_NAME_COMPARATOR = new resumeComparator();

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract Boolean isExists(Object searchKey);

    @Override
    public void save(Resume r) {
        Object searchKey = existInStorage(r.getUuid());
        doSave(r, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = notExistInStorage(uuid);
        return doGet(searchKey);
    }

    @Override
    public void update(Resume r) {
        Object searchKey = notExistInStorage(r.getUuid());
        doUpdate(r, searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = notExistInStorage(uuid);
        doDelete(searchKey);
    }

    private Object existInStorage(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExists(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object notExistInStorage(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExists(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected static class resumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            return Comparator.comparing(Resume::getFullName)
                    .thenComparing(Resume::getUuid)
                    .compare(o1, o2);
        }
    }
}
