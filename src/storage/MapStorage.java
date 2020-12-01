package storage;

import model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> resumeMap = new TreeMap<>();

    @Override
    protected int getStorageSize() {
        return resumeMap.size();
    }

    @Override
    protected Resume doGet(String uuid, Object searchKey) {
        return resumeMap.get(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        resumeMap.put(r.getUuid(), r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        resumeMap.put(r.getUuid(), r);
    }

    @Override
    protected void doClear() {
        resumeMap.clear();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        if (resumeMap.get(uuid) != null) {
            return new Integer(1);
        } else return new Integer(-1);
    }

    @Override
    protected void doDelete(String uuid, Object searchKey) {
        resumeMap.remove(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return resumeMap.containsKey(searchKey);
    }

    @Override
    public Resume[] getAll() {
        return resumeMap.values().toArray(new Resume[0]);
    }
}
