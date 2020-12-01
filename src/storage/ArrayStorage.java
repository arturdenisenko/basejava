package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doArraySave(Resume r, Object searchKey) {
        storage[size] = r;
        size++;
    }

    @Override
    protected void doDelete(String uuid, Object index) {
        //TODO CHECK - SOMETHING WRONG 30:01 6 1 HW 5
        storage[(Integer) index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
