import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;

public class StudentDAO{
  public boolean insert(Student s){
    boolean flag=false;
    String sql;
    
    sql="insert into student(name,age,grade)values('";
    sql=sql+s.getName();
    sql=sql+"',";
    sql=sql+s.getAge();
    sql=sql+",";
    sql=sql+s.getGrade();
    sql=sql+")";
    
    DbOperation db=new DbOperation();
    try{
      Connection con=db.getConnection();
      db.update(con,sql);
      db.close(con);
      flag=true;
    }catch(ClassNotFoundException e){
        System.out.println("数据库驱动程序不存在！");
        e.printStackTrace();
     }catch(SQLException e){
        System.out.println("数据库操作错误！");
         e.printStackTrace();
      }
      return flag;
    }
    
    
    public StudentClass getStudentClass(){
      List<Student>lst=null;
      StudentClass sc=new StudentClass();
      String sql="select name,age,grade from student";
      
      DbOperation db=new DbOperation();
      try{
        Connection con=db.getConnection();
        lst=db.getAll(con,sql);
        db.close(con);
      }catch(ClassNotFoundException e){
        System.out.println("数据库驱动程序不存在！");
        e.printStackTrace();
       } catch(SQLException e){
           System.out.println("数据库操作错误！");
           e.printStackTrace();
         }
         
         
         sc.createClass(lst);
         return sc;
     }
     
     public Student getByName(String name){
       List<Student>lst=null;
       
       
       String sql="select name,age,grade from student";
       sql=sql+" where name='"+name+"'";
       
       
       DbOperation db=new DbOperation();
       try{
           Connection con=db.getConnection();
           lst=db.getAll(con,sql);
           db.close(con);
       }catch(ClassNotFoundException e){
            System.out.println("数据库驱动程序不存在！");
            e.printStackTrace();
       }catch(SQLException e){
              System.out.println("数据库操作错误！");
               e.printStackTrace();
       }
       Student s;
       if((lst==null)||(lst.size()==0)){
           s=null;
       }
       else{
          s=lst.get(0);
       }
       return s;
       }
     }