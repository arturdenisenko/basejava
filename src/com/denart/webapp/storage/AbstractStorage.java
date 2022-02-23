package com.denart.webapp.storage;

import com.denart.webapp.exception.ExistStorageException;
import com.denart.webapp.exception.NotExistStorageException;
import com.denart.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    public final static Comparator<Resume> RESUME_WITH_FULL_NAME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doUpdate(Resume r, SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExists(SK searchKey);

    public abstract List<Resume> doGetResumesListFromStorage();

    @Override
    public void save(Resume r) {
        SK searchKey = getSearchKeyIfNotExist(r.getUuid());
        doSave(r, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = getSearchKeyIfExist(uuid);
        return doGet(searchKey);
    }

    @Override
    public void update(Resume r) {
        SK searchKey = getSearchKeyIfExist(r.getUuid());
        doUpdate(r, searchKey);
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = getSearchKeyIfExist(uuid);
        doDelete(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> listFromStorage = doGetResumesListFromStorage();
        listFromStorage.sort(RESUME_WITH_FULL_NAME_COMPARATOR);
        return listFromStorage;
    }

    private SK getSearchKeyIfNotExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExists(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getSearchKeyIfExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExists(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
