package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doSave(Resume r, int index) {
        index = (index + 1) * -1;
        System.arraycopy(storage, index, storage, index + 1, size-index);
        storage[index] = r;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
