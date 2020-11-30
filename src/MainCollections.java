import model.Resume;

import java.util.*;

public class MainCollections {
    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();

        String UUID_1 = "uuid1";
        String UUID_2 = "uuid2";
        String UUID_3 = "uuid3";
        String UUID_4 = "uuid4";

        Resume RESUME_1 = new Resume(UUID_1);
        Resume RESUME_2 = new Resume(UUID_2);
        Resume RESUME_3 = new Resume(UUID_3);
        Resume RESUME_4 = new Resume(UUID_4);

        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);
        collection.add(RESUME_4);

        Iterator<Resume> iterator = collection.iterator();
        System.out.println(collection.toString());
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            if (Objects.equals(r.getUuid(), UUID_1)) {
                iterator.remove();
            }
        }
        System.out.println(collection.toString());

        Map<String, Resume> map = new HashMap<>();

        map.put(UUID_1, RESUME_1);
        map.put(UUID_2, RESUME_2);
        map.put(UUID_3, RESUME_3);
        map.put(UUID_4, RESUME_4);

        System.out.println("Итерация через keySet");

        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }

        System.out.println("Итерация через Map.Entry");

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
