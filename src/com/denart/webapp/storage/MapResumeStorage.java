package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private final Map<String, Resume> storageMap = new HashMap<>();

    @Override
    protected void doSave(Resume r, Resume searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    public List<Resume> getResumesList() {
        return new ArrayList<>(storageMap.values());
    }

    @Override
    protected Resume doGet(Resume searchKey) {
        return searchKey;
    }

    @Override
    protected void doUpdate(Resume r, Resume searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Resume searchKey) {
        storageMap.remove((searchKey).getUuid());
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storageMap.get(uuid);
    }

    @Override
    protected boolean isExists(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    public int size() {
        return storageMap.size();
    }
}
