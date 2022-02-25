package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> listStorage = new ArrayList<>();

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        listStorage.add(r);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return listStorage.get(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        listStorage.set(searchKey, r);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        listStorage.remove((searchKey).intValue());
    }
    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public List<Resume> getResumesList() {
        return listStorage;
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExists(Integer searchKey) {
        return searchKey != null;
    }
}
