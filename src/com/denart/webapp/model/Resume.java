package com.denart.webapp.model;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;
    private final String fullName;

    private final Map<ContactsType, String> contacts;
    private final Map<SectionType, AbstractSection> sections;


    public Resume(String fullName,
                  Map<ContactsType, String> contacts, Map<SectionType, AbstractSection> sections) {
        this(UUID.randomUUID().toString(), fullName, contacts, sections);
    }

    public Resume(String uuid, String fullName,
                  Map<ContactsType, String> contacts, Map<SectionType, AbstractSection> sections) {


        Objects.requireNonNull(contacts, "contacts must not be null");
        Objects.requireNonNull(sections, "sections must not be null");
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.contacts = contacts;
        this.sections = sections;
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public Map<ContactsType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!Objects.equals(uuid, resume.uuid)) return false;
        return Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    /*@Override
    public int compare(Resume o1, Resume o2) {
        int result = o1.getFullName().compareTo(o2.getFullName());
        if (result == 0) {
            return o1.getUuid().compareToIgnoreCase(o2.getUuid());
        } else {
            return result;
        }
    }
*/
}
