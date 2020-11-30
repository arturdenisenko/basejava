package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractStorageTest {

    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    private static final String UUID_4 = "uuid4";

    private final Resume RESUME_1 = new Resume(UUID_1);
    private final Resume RESUME_2 = new Resume(UUID_2);
    private final Resume RESUME_3 = new Resume(UUID_3);

    private final Resume RESUME_4 = new Resume(UUID_4);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void update() throws IllegalAccessException {
        /*Field field = RESUME_1.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        field.set(RESUME_1, "uuid");
        storage.update(RESUME_1);
        assertEquals("uuid", storage.get("uuid").getUuid());*/
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertTrue(newResume == storage.get(UUID_1));

    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }


    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }


    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void getAll() {
        Resume[] testStorage = storage.getAll();
        assertEquals(3, testStorage.length);
        assertEquals(RESUME_1, testStorage[0]);
        assertEquals(RESUME_2, testStorage[1]);
        assertEquals(RESUME_3, testStorage[2]);
    }

    @Test(expected = StorageException.class)
    public void saveOverFlow() {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail("Overflow happened ahead of time" + e.getMessage());
        }
        storage.save(new Resume());
    }

    //Check size, avoid of duplicate code
    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }
}