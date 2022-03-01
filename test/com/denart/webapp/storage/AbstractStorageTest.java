package com.denart.webapp.storage;

import com.denart.webapp.exception.ExistStorageException;
import com.denart.webapp.exception.NotExistStorageException;
import com.denart.webapp.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.denart.webapp.storage.AbstractStorage.RESUME_WITH_FULL_NAME_COMPARATOR;
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

    protected static Map<ContactsType, String> CONTACTS_MAP_1;
    protected static Map<ContactsType, String> CONTACTS_MAP_2;
    protected static Map<ContactsType, String> CONTACTS_MAP_3;
    protected static Map<ContactsType, String> CONTACTS_MAP_4;

    protected static Map<SectionType, AbstractSection> SECTIONS_MAP_1;
    protected static Map<SectionType, AbstractSection> SECTIONS_MAP_3;
    protected static Map<SectionType, AbstractSection> SECTIONS_MAP_2;
    protected static Map<SectionType, AbstractSection> SECTIONS_MAP_4;

    protected final static TextSection OBJECTIVE_1 =
            new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
    protected final static TextSection OBJECTIVE_2 =
            new TextSection("Ведущий бизнес аналитик в компании EPAM");
    protected final static TextSection OBJECTIVE_3 =
            new TextSection("Middle Java Developer");
    protected final static TextSection OBJECTIVE_4 =
            new TextSection("Junior Java разработчик");

    protected final static TextSection PERSONAL_1 =
            new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
    protected final static TextSection PERSONAL_2 =
            new TextSection("Просто замечательный человек");
    protected final static TextSection PERSONAL_3 =
            new TextSection("Многранная личность и знаю много анекдотов");
    protected final static TextSection PERSONAL_4 =
            new TextSection("Восхититилен, не пью");



    private static final Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_1, CONTACTS_MAP_1, SECTIONS_MAP_1);
    private static final Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2, CONTACTS_MAP_2, SECTIONS_MAP_2);
    private static final Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3, CONTACTS_MAP_3, SECTIONS_MAP_3);
    private static final Resume RESUME_4 = new Resume(UUID_4, FULL_NAME_4, CONTACTS_MAP_4, SECTIONS_MAP_4);

    private static final List<Resume> RESUMES_EQUIVALENT_LIST = new ArrayList<>();


    static {
        CONTACTS_MAP_1.put(ContactsType.PHONE, "+52957293131");
        CONTACTS_MAP_1.put(ContactsType.SKYPE, "mySkype");
        CONTACTS_MAP_1.put(ContactsType.EMAIL, "fasdfas@mail.ru");
        CONTACTS_MAP_1.put(ContactsType.LINKEDIN, "LinkedIN.com");
        CONTACTS_MAP_1.put(ContactsType.GITHUB, "github.com");
        CONTACTS_MAP_1.put(ContactsType.STACKOVERFLOW, "stackoverflow.com");
        CONTACTS_MAP_1.put(ContactsType.HOMEPAGE, "www.go.ry");

        SECTIONS_MAP_1.put(SectionType.OBJECTIVE, OBJECTIVE_1);
        SECTIONS_MAP_1.put(SectionType.PERSONAL, PERSONAL_1);
        SECTIONS_MAP_1.put(SectionType.ACHIEVEMENT,)



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
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
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
        Resume testResume = new Resume(UUID_3, FULL_NAME_3, CONTACTS_MAP_3, SECTIONS_MAP_3);
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
    public void getAllSorted() {
        RESUMES_EQUIVALENT_LIST.sort(RESUME_WITH_FULL_NAME_COMPARATOR);
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
}
