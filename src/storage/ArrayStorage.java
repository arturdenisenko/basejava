package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doSave(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void doDelete(String uuid, int index) {
        //TODO CHECK - SOMETHING WRONG
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    public int size() {
        return size;
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
