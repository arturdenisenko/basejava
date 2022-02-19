package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        Objects.requireNonNull(searchKey);
        storageMap.put((Resume) searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        Objects.requireNonNull(searchKey);
        storageMap.remove(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        List<Resume> resumeStream = storageMap.entrySet()
                .stream()
                .filter(entry -> uuid.equals(entry.getKey()
                        .getUuid())).map(Map.Entry::getValue).collect(Collectors.toList());
        if (resumeStream.isEmpty()) {
            return null;
        }
        return resumeStream.get(0);
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
