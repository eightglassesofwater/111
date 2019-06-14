public class Student{
  private int age;
  private double grade;
  private String name;
  
  public Student(String name,int age,double grade){
    this.age=age;
    this.grade=grade;
    this.name=name;
    }
    
    public int getAge(){
      return age;
    }
    
    public double getGrade(){
      return grade;
    }
    
    public String getName(){
      return name;
  }
}