import com.denart.webapp.model.Resume;

import java.util.*;

public class MainCollection {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public static void main(String[] args) {

        Collection<Resume> collection = new ArrayList();

        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);
        collection.add(RESUME_4);

        System.out.println(collection.toString());


      /*  for (Resume r : collection) {
            if (Objects.equals(r.getUuid(), UUID_1)) {
                collection.remove(r);
            }
        }*/

        Iterator<Resume> iterator = collection.iterator();

        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            //System.out.println(resume);
            if (Objects.equals(resume.getUuid(), UUID_1)) {
                iterator.remove();
            }

        }

        System.out.println(collection.toString());

        Map<String, Resume> stringResumeMap = new HashMap<>();

        stringResumeMap.put(UUID_1, RESUME_1);
        stringResumeMap.put(UUID_2, RESUME_2);
        stringResumeMap.put(UUID_3, RESUME_3);
        stringResumeMap.put(UUID_4, RESUME_4);

        //bad idea
        for (String uuid : stringResumeMap.keySet()) {
            System.out.println(stringResumeMap.get(uuid));
        }

        //good idea
        for (Map.Entry<String, Resume> entry : stringResumeMap.entrySet()) {
            System.out.println(entry.getValue());
        }


        System.out.println(stringResumeMap.toString());

        System.out.println("Arraylist");
        List<Resume> resumeList = new ArrayList<>();
        resumeList.add(RESUME_1);
        resumeList.add(RESUME_2);
        resumeList.add(RESUME_3);
        System.out.println(resumeList);
        resumeList.remove(new Integer(0));
        System.out.println(resumeList);

    }



}
