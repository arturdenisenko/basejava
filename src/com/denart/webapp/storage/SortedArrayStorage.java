package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doDelete(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }

    @Override
    protected void doSave(Resume r, int index) {
        int insertIndex = (index + 1) * -1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
