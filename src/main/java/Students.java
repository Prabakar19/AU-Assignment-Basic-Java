import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "students")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Students implements Serializable {

    private List<Student> students;

    public Students(){}
    public Students(List<Student> students){
        this.students = students;
    }

    @XmlElement(name="student")
    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
