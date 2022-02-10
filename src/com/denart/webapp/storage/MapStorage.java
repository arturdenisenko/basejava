package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected void doSave(Resume r, Object index) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    protected Resume doGet(String uuid, Object index) {
        return mapStorage.get(uuid);
    }


    @Override
    protected void doUpdate(Resume r, Object index) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(String uuid, Object index) {
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
    protected Object getIndex(String uuid) {
        return mapStorage.containsKey(uuid) ? 1 : -1;
    }
}
