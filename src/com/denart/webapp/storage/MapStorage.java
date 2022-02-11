package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

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
    protected Resume doGet(Object uuid, Object searchKey) {
        return mapStorage.get(uuid);
    }


    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object uuid, Object searchKey) {
        mapStorage.remove(uuid);
    }

    @Override
    public Resume[] getAll() {
        return mapStorage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return mapStorage.containsKey(uuid) ? 1 : -1;
    }
}
