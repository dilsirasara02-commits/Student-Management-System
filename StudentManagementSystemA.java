import java.util.Scanner;

abstract class Person{
    private String id;
    private String name;
    private String email;

    public Person(String id,String name,String email){
        this.id=id;
        this.name=name;
        this.email=email;
    }
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public abstract void displayInfo();
}

class Student extends Person{
    private String course;

    public Student(String studentId,String studentName,String email,String course){
        super(studentId,studentName,email);
        this.course=course;
    }
    public String getCourse(){
        return course;
    }

    public void displayInfo(){
        System.out.println("Student ID : "+getId());
        System.out.println("Name       : "+getName());
        System.out.println("Email      : "+getEmail());
        System.out.println("Course     : "+course);
    }
}


public class StudentManagementSystemA{
    public static void main(String[]args){
        Scanner input=new Scanner(System.in);

        System.out.print("Enter Student ID : ");
        String id=input.nextLine();

        System.out.print("Enter Student Name : ");
        String name=input.nextLine();

        System.out.print("Enter Email : ");
        String email=input.nextLine();

        System.out.println("Select Course");
        System.out.println("1.Java Programming");
        System.out.println("2.Database Systems");

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


       

        
    
