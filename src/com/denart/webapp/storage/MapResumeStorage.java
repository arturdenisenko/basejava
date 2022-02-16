package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapResumeStorage extends AbstractStorage {

    Map<Integer, Resume> resumeMap = new HashMap<>();

    @Override
    protected void doSave(Resume r, Object searchKey) {
        Integer hashCode = r.hashCode();
        resumeMap.put(hashCode, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return resumeMap.get(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {

    }

    @Override
    protected void doDelete(Object searchKey) {
        resumeMap.remove(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return resumeMap.entrySet()
                .stream()
                .filter(entry -> uuid.equals(entry.getValue().getUuid()))
                .map(Map.Entry::getKey)
                .findFirst();
    }


    @Override
    protected Boolean isExists(Object searchKey) {
        return resumeMap.containsKey(searchKey);
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return resumeMap.values().stream().sorted(RESUME_WITH_FULL_NAME_COMPARATOR).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
