package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void fillDeletedElement(Integer index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected void insertElement(Resume r, Integer index) {
        storage[size] = r;
    }

    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
