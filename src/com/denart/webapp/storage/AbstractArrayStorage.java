package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " not exist");
        return null;
    }

    @Override
    public void save(Resume r) {
        if (size < STORAGE_LIMIT) {
            int index = getIndex(r.getUuid());
            if (index < 0) {
                doSave(r, index);
                size++;
            } else {
                System.out.println("ERROR to save Resume with uuid -  \"" + r.getUuid() + " is in storage already");
            }
        } else {
            System.out.println("ERROR - The Storage is OverFlow now..Plz delete something");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            doDelete(index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR - There is no resume with uuid - \"" + uuid + "\" in current storage");
        }
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        }
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void doDelete(int index);

    protected abstract void doSave(Resume r, int index);

    protected abstract int getIndex(String uuid);
}
