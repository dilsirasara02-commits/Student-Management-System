import java.util.Scanner;

class Student{
    private String studentId;
    private String studentName;
    private String email;
    private String course;

    public Student(String studentId,String studentName,String email,String course){
        this.studentId=studentId;
        this.studentName=studentName;
        this.email=email;
        this.course=course;
    }

    public String getStudentId(){
        return studentId;
    }

    public String getStudentName(){
        return studentName;
    }

    public String getEmail(){
        return email;
    }

    public String getCourse(){
        return course;
    }

    public void displayInfo(){
        System.out.println("Student ID : "+studentId);
        System.out.println("Name       : "+studentName);
        System.out.println("Email      : "+email);
        System.out.println("Course     : "+course);
    }
}


public class StudentManagementSystem1{
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);

        System.out.print("Enter Student ID : ");
        String id=input.nextLine();

        System.out.print("Enter Student Name : ");
        String name=input.nextLine();

        System.out.print("Enter Email : ");
        String email=input.nextLine();

        System.out.println("Select Course");
        System.out.println("1. Java Programming");
        System.out.println("2. Database Systems");

        System.out.print("Enter Choice : ");
        int choice=input.nextInt();

        String course;

        if(choice==1){
            course = "Java Programming";
        }
        else{
            course="Database Systems";
        }


        Student student1=new Student(id,name,email,course);


        System.out.println("\nStudent Summary");
        student1.displayInfo();

        input.close();
    }
}