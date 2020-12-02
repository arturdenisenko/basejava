package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> resumeList = new ArrayList<>();

    @Override
    protected void doUpdate(Resume r, Object index) {
        resumeList.set((Integer) index, r);
    }

    @Override
    protected void doSave(Resume r, Object index) {
        resumeList.add(r);
    }

    @Override
    protected void doClear() {
        resumeList.clear();
    }

    @Override
    public Resume doGet(String uuid, Object index) {
        return resumeList.get((Integer) index);
    }

    @Override
    public List<Resume> getAllSorted() {
        return resumeList;
    }

    @Override
    protected int getStorageSize() {
        return resumeList.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (uuid.equals(resumeList.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void doDelete(String uuid, Object index) {
        resumeList.remove(((Integer) index).intValue());
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
