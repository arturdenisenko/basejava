package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.*;
import java.util.stream.Collectors;

public class MapResumeStorage extends AbstractStorage {
    private final Map<Resume, Resume> storageMap = new HashMap<>();

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storageMap.put(r, r);
    }

    @Override
    protected List<Resume> getSortedCollection() {
        return storageMap.values().stream().sorted(RESUME_WITH_FULL_NAME_COMPARATOR).collect(Collectors.toList());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        Objects.requireNonNull(searchKey);
        return (Resume) searchKey;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storageMap.put((Resume) searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        Objects.requireNonNull(searchKey);
        storageMap.remove(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (Map.Entry<Resume, Resume> resumeResumeEntry : storageMap.entrySet()) {
            if (uuid.equals(resumeResumeEntry.getKey().getUuid())) {
                return resumeResumeEntry.getKey();
            }
        }
        return null;
    }

    @Override
    protected boolean isExists(Object searchKey) {
        return storageMap.containsKey(searchKey);
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
