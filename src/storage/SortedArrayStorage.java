package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void doDelete(String uuid, int index) {
        System.arraycopy(storage, index + 1, storage, index, size() - index);
        size--;
    }

    @Override
    protected void doArraySave(Resume r, int index) {
        index = index * -1 - 1;
        System.arraycopy(storage, index, storage, index + 1, size() - index);
        storage[index] = r;
        size++;
       /* //http://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        int insertIdx = -index - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
        storage[insertIdx] = r;
        size++;*/
    }

    @Override
    protected int getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
