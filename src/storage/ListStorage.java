package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    List<Resume> resumeList = new ArrayList<>();

    @Override
    protected void doUpdate(Resume r, int index) {
        resumeList.set(index, r);
    }

    @Override
    protected void doSave(Resume r, int index) {
        resumeList.add(r);
    }

    @Override
    protected void doClear() {
        resumeList.clear();
    }

    @Override
    public Resume doGet(int index, String uuid) {
        return resumeList.get(index);
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[0]);
    }

    @Override
    protected int getStorageSize() {
        return resumeList.size();
    }

    @Override
    protected int getSearchKey(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (uuid.equals(resumeList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doDelete(String uuid, int index) {
        resumeList.remove(index);
    }
}
