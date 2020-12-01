package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void doDelete(String uuid, Object index) {
        System.arraycopy(storage, (Integer) index + 1, storage, (Integer) index, size() - (Integer) index);
        size--;
    }


    @Override
    protected void doArraySave(Resume r, Object index) {
        index = (Integer) index * -1 - 1;
        System.arraycopy(storage, (Integer) index, storage, (Integer) index + 1, size() - (Integer) index);
        storage[(Integer) index] = r;
        size++;
       /* //http://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        int insertIdx = -index - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
        storage[insertIdx] = r;
        size++;*/
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
