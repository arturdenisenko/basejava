package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class MapResumeStorage extends AbstractStorage {
    Map<Resume, Resume> resumeResumeMap = new HashMap<>();

    @Override
    protected void doSave(Resume r, Object searchKey) {
        resumeResumeMap.put(r, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        Objects.requireNonNull(searchKey);
        return resumeResumeMap.get((Resume) searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        Objects.requireNonNull(searchKey);
        resumeResumeMap.put((Resume) searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        Objects.requireNonNull(searchKey);
        resumeResumeMap.remove(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        List<Resume> resumeStream = resumeResumeMap.entrySet()
                .stream()
                .filter(entry -> uuid.equals(entry.getKey()
                        .getUuid())).map(Map.Entry::getValue).collect(Collectors.toList());
        if (resumeStream.isEmpty()) {
            return null;
        }
        return resumeStream.get(0);
    }

    @Override
    protected Boolean isExists(Object searchKey) {
        return resumeResumeMap.containsKey(searchKey);
    }

    @Override
    public void clear() {
        resumeResumeMap.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return resumeResumeMap.values().stream().sorted(RESUME_WITH_FULL_NAME_COMPARATOR).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return resumeResumeMap.size();
    }
}
