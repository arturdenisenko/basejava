package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> listStorage = new ArrayList<>();

    @Override
    protected void doSave(Resume r, Object searchKey) {
        listStorage.add(r);
    }

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    protected Resume doGet(Object uuid, Object searchKey) {
        return listStorage.get((int) searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        listStorage.set((int) searchKey, r);
    }

    @Override
    protected void doDelete(Object uuid, Object searchKey) {
        listStorage.remove((int) searchKey);
    }

    @Override
    public Resume[] getAll() {
        return  listStorage.toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (uuid.equals(listStorage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
