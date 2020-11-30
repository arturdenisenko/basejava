package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void save(Resume r) {
        resumeList.add(r);
    }

    @Override
    public Resume get(String uuid) {
        return resumeList.get(getIndex(uuid));
    }

    @Override
    public void delete(String uuid) {
        resumeList.remove(getIndex(uuid));
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
