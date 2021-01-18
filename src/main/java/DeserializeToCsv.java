import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.util.*;

public class DeserializeToCsv {

    public static HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> hm) {
        List<Map.Entry<Integer, Integer> > list = new LinkedList<Map.Entry<Integer, Integer> >(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }


    public static void main(String[] args) {

        try{
            //Deserialization
            ObjectInputStream in=new ObjectInputStream(new FileInputStream("student.txt"));
            HashMap<Integer, Integer> hashMap1 = (HashMap<Integer, Integer>)in.readObject();

            int i = 1;
            System.out.println("Rank\tRollno\tTotalMarks");
            HashMap<Integer, Integer> hashMap = sortByValue(hashMap1);

            File file = new File("student_marks.csv");
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);
            String[] header = { "Rank", "RollNo", "TotalMarks(300)" };
            writer.writeNext(header);

            for (Map.Entry<Integer, Integer> en : hashMap.entrySet()) {
                System.out.println(i + "\t\t" +en.getKey() +"\t\t" +en.getValue());
                String [] data = {i+"", en.getKey().toString(), en.getValue().toString()};

                writer.writeNext(data);
                i++;
            }
            writer.close();
            in.close();
            System.out.println("Deserialization successful!");
        }catch (Exception e){

        }
    }
}
