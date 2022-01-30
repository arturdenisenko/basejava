package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size <= 10000) {
            if (getResume(r.getUuid()) == -1) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("ERROR to save Resume with uuid -  \"" + r.getUuid() + "is in storage already");
            }
        } else {
            System.out.println("ERROR - The Storage is OverFlow now..Plz delete something");
        }
    }

    public Resume get(String uuid) {
        int index = getResume(uuid);
        if (index >= 0) {
            return storage[index];
        }
        return null;
    }

    @Override
    public void update(Resume r) {
        int index = getResume(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        }
    }

    private int getResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        System.out.println("ERROR - There is no resume with uuid - \"" + uuid + "\" in current storage");
        return -1;
    }

    public void delete(String uuid) {
        int index = getResume(uuid);
        if (index >= 0) {
            storage[index] = null;
            System.arraycopy(storage, index + 1, storage, index, size);
            size--;
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }
}
