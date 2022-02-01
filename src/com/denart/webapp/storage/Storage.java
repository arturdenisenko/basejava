package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

public interface   Storage {
    void clear();

    void save(Resume r);

    Resume get(String uuid);

    void update(Resume r);

    void delete(String uuid);
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll();

    int size();

}
