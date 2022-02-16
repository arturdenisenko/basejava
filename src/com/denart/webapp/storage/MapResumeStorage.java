package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        return resumeMap.get((Integer.valueOf((Integer) searchKey)));
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        resumeMap.put((Integer)searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        resumeMap.remove(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Optional<Integer> first = resumeMap.entrySet()
                .stream()
                .filter(entry -> uuid.equals(entry.getValue().getUuid()))
                .map(Map.Entry::getKey)
                .findFirst();
        return first;
    }


    @Override
    protected Boolean isExists(Object searchKey) {
        Optional<Integer> sk = (Optional<Integer>) searchKey;
        return !sk.isEmpty();
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
