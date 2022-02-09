package com.denart.webapp.storage;

import com.denart.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> listStorage = new ArrayList<>();

    @Override
    protected void doSave(Resume r) {
        listStorage.add(r);
    }

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    protected Resume doGet(String uuid) {
        return listStorage.get((int) getIndex(uuid));
    }

    @Override
    protected void doUpdate(Resume r) {
        listStorage.set((int) getIndex(r.getUuid()), r);
    }

    @Override
    protected void doDelete(String uuid) {
        listStorage.remove((int)(getIndex(uuid)));
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
    protected Object getIndex(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (uuid.equals(listStorage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
