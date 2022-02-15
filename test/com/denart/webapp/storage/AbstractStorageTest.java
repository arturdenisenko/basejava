package com.denart.webapp.storage;

import com.denart.webapp.exception.ExistStorageException;
import com.denart.webapp.exception.NotExistStorageException;
import com.denart.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {

    protected final Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";

    protected static final String FULL_NAME_1 = "YASKIN SERGEI";
    protected static final String FULL_NAME_2 = "PETROV NIKOLAI";
    protected static final String FULL_NAME_3 = "VOSTRECOV PETR";
    protected static final String FULL_NAME_4 = "POLINSKII ILYA";
    protected static final String DUMMY_FULL_NAME = "DUMMY";

    private static final Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_1);
    private static final Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2);
    private static final Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3);
    private static final Resume RESUME_4 = new Resume(UUID_4, FULL_NAME_4);

    private static final List<Resume> RESUMES_EQUIVALENT_LIST = new ArrayList<>();
    private static final Comparator<Resume> resumeComparator = new resumeComparator();

    {
        RESUMES_EQUIVALENT_LIST.add(RESUME_1);
        RESUMES_EQUIVALENT_LIST.add(RESUME_2);
        RESUMES_EQUIVALENT_LIST.add(RESUME_3);
    }

    protected AbstractStorageTest(AbstractStorage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        RESUMES_EQUIVALENT_LIST.sort(resumeComparator);
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }


    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void update() {
        Resume testResume = new Resume(UUID_3, FULL_NAME_3);
        storage.update(testResume);
        assertSame(testResume, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void getAll() {
        List<Resume> actualResumes = storage.getAllSorted();
        assertEquals(RESUMES_EQUIVALENT_LIST, actualResumes);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    protected static class resumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            return Comparator.comparing(Resume::getFullName)
                    .thenComparing(Resume::getUuid)
                    .compare(o1, o2);
        }
    }

}
