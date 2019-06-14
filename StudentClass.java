import java.util.*;
public class StudentClass{
  private List<Student>stuList;
  private int size;
  
 public StudentClass(){
   size=0;
   stuList=null;
 }
 
 public void createClass(List<Student>lst){
   stuList=lst;
   size=lst.size();
 }
 
 public void createClass(){
   String names[]={"张三","王五","李四","赵六","孙七"};
   double grades[]={67,78.5,98,76.5,90};
   int ages[]={17,18,18,19,17};
   
   size=names.length;
   
   stuList=new ArrayList<Student>();
   Student temp;
   
   for(int i=0;i<size;i++){
     temp=new Student(names[i],ages[i],grades[i]);
     stuList.add(temp);
  }
 }
 
   public void sort(){
     Student temp;
     
     for(int i=0;i<size;i++){
       for(int j=1;j<size-1;j++){
         if(stuList.get(j-1).getGrade()>stuList.get(j).getGrade()){
           temp=stuList.get(j-1);
           stuList.set(j-1,stuList.get(j));
           stuList.set(j,temp);
           }
         }
         }
         }
     public List<Map<String,String>> formatStudent(){
       List<Map<String,String>>fClass=new ArrayList<Map<String,String>>();
       Map<String,String>stu;
       for(Student s:stuList){
         stu=new HashMap<String,String>();
         stu.put("姓名",s.getName());
         stu.put("成绩",new Double(s.getGrade()).toString());
         fClass.add(stu);
         }
         return fClass;
       }
       
       public void saveToDB(){
         StudentDAO dao=new StudentDAO();
         
         for(Student s:stuList){
           dao.insert(s);
         }
       }
       
 }