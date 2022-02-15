package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected void doSave(Resume r, Object searchKey) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return mapStorage.get(searchKey);
    }


    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        mapStorage.remove(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected Boolean isExists(Object searchKey) {
        return mapStorage.containsKey(searchKey);
    }
}
