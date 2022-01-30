package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

public interface Storage {
    public void clear();

    public void save(Resume r);

    public Resume get(String uuid);


    public void delete(String uuid);
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll();

    public int size();

}
