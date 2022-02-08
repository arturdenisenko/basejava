package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> listStorage = new ArrayList<>();

    @Override
    protected void doSave(Resume r, int index) {
        listStorage.add(r);
    }

    @Override
    protected void doClear() {
        listStorage.clear();
    }

    @Override
    protected Resume doGet(String uuid, int index) {
        return listStorage.get(index);
    }

    @Override
    protected void doUpdate(Resume r, int index) {
        listStorage.set(index, r);
    }

    @Override
    protected void doDelete(String uuid, int index) {
        listStorage.remove(index);
    }

    @Override
    protected Resume[] doGetAllResumes() {
        return  listStorage.toArray(Resume[]::new);
    }

    @Override
    protected int getStorageSize() {
        return listStorage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (uuid.equals(listStorage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
