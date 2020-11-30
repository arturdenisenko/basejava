package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void clear() {
        doClear();
    }

    public void update(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            doUpdate(r, index);
        }
    }


    @Override
    public void save(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            doSave(r, index);
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getSearchKey(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(index, uuid);
    }

    @Override
    public void delete(String uuid) {
        int index = getSearchKey(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(uuid, index);
        }
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return getStorageSize();
    }

    protected abstract int getStorageSize();

    protected abstract Resume doGet(int index, String uuid);

    protected abstract void doUpdate(Resume r, int index);

    protected abstract void doSave(Resume r, int index);

    protected abstract void doClear();

    protected abstract int getSearchKey(String uuid);

    protected abstract void doDelete(String uuid, int index);
}
