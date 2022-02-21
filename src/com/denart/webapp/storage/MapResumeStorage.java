package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> storageMap = new HashMap<>();

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    public List<Resume> doGetResumesListFromStorage() {
        return new ArrayList<>(storageMap.values());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storageMap.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storageMap.get(uuid);
    }

    @Override
    protected boolean isExists(Object searchKey) {
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
