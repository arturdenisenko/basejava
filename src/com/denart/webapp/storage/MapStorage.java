package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected void doSave(Resume r, int index) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    protected Resume doGet(String uuid, int index) {
        return mapStorage.get(uuid);
    }


    @Override
    protected void doUpdate(Resume r, int index) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(String uuid, int index) {
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
    protected int getIndex(String uuid) {
        return mapStorage.containsKey(uuid) ? 1 : -1;
    }
}
