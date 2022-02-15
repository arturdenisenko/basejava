package com.denart.webapp.storage;

import com.denart.webapp.exception.StorageException;
import com.denart.webapp.model.Resume;
import org.junit.Test;

import static com.denart.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    public AbstractArrayStorageTest(AbstractArrayStorage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 0; i < (STORAGE_LIMIT - 3); i++) {
                storage.save(new Resume(DUMMY_FULL_NAME));
            }
        } catch (StorageException e) {
            fail("Storage overflow occurred prematurely");
        }
        storage.save(new Resume(DUMMY_FULL_NAME));
    }
}