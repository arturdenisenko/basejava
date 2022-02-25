package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapUuidStorage extends AbstractStorage<String> {
    private final Map<String, Resume> mapStorage = new TreeMap<>();

    @Override
    protected void doSave(Resume r, String searchKey) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    protected Resume doGet(String searchKey) {
        return mapStorage.get(searchKey);
    }


    @Override
    protected void doUpdate(Resume r, String searchKey) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(String searchKey) {
        mapStorage.remove(searchKey);
    }

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExists(String searchKey) {
        return mapStorage.containsKey(searchKey);
    }

    @Override
    public List<Resume> getResumesList() {
        return new ArrayList<>(mapStorage.values());
    }
}
