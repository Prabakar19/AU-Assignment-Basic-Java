import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.HashMap;
import java.util.List;

public class xmlToObject {


    static void serialize(File file){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Students.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Students studs = (Students) jaxbUnmarshaller.unmarshal(file);
            List<Student> stud = studs.getStudents();

            HashMap<Integer, Integer> hashMap = new HashMap<>();
            for (Student s : stud) {
                if(s.getMarks() < 0 || s.getMarks() > 100)
                    throw new InvalidMarkException("Mark should be between 0 and 100");
                System.out.println(s.getRoll() + " " + s.getSubject() + " " + s.getMarks());
                if(hashMap.containsKey(s.getRoll()))
                    hashMap.put(s.getRoll(), hashMap.get(s.getRoll())+s.getMarks());
                else
                    hashMap.put(s.getRoll(), s.getMarks());
            }

            //Serialization
            FileOutputStream fout = new FileOutputStream("student.txt");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(hashMap);
            out.flush();
            out.close();
            System.out.println("Serialization successful!");
        }catch(JAXBException e){
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        try{
            File file = new File("src\\main\\java\\studentData.xml");
            serialize(file);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
