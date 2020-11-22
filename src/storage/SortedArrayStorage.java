package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doUpdate(Resume r, int index) {

    }

    @Override
    public void doDelete(String uuid, int index) {
        //TODO FIX, SOMETHING WRONG..
        System.arraycopy(storage, index + 1, storage, index, size() - index);
        size--;
    }

    @Override
    protected void doSave(Resume r, int index) {
        if (index < 0) {
            index++;
            storage[index] = r;
            size++;
        } else {
            index = index * -1;
            storage[index] = r;
            size++;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
