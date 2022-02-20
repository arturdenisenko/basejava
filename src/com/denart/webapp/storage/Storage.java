package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.List;

public interface Storage {

    void clear();

    void save(Resume r);

    Resume get(String uuid);

    void update(Resume r);

    void delete(String uuid);

    List<Resume> getAllSorted();

    int size();

}
