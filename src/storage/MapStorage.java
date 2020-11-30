package storage;

import model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {

    Map<String, Resume> resumeMap = new TreeMap<>();

    @Override
    protected int getStorageSize() {
        return resumeMap.size();
    }

    @Override
    protected Resume doGet(int index, String uuid) {
        return resumeMap.get(uuid);
    }

    @Override
    protected void doUpdate(Resume r, int index) {
        resumeMap.put(r.getUuid(), r);
    }

    @Override
    protected void doSave(Resume r, int index) {
        resumeMap.put(r.getUuid(), r);
    }

    @Override
    protected void doClear() {
        resumeMap.clear();
    }

    @Override
    protected int getSearchKey(String uuid) {
        if (resumeMap.get(uuid) != null) {
            return 1;
        } else return -1;
    }

    @Override
    protected void doDelete(String uuid, int index) {
        resumeMap.remove(uuid);
    }

    @Override
    public Resume[] getAll() {
        return resumeMap.values().toArray(new Resume[0]);
    }
}
