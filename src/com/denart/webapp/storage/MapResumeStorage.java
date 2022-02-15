package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    Map<Resume, Resume> resumeMap = new HashMap<>();

    @Override
    protected void doSave(Resume r, Object searchKey) {
        resumeMap.put(r, r);
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
    protected Object getSearchKey(String resume) {
        return resumeMap.get(resume);
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
        return null;
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}