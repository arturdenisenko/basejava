package com.denart.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapIntegeHashStorageTest.class,
        MapUuidStorageTest.class
})
public class AllStorageTest {
}
