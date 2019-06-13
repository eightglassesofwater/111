import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbOperation{
  public DbOperation(){
    }
    
  public Connection getConnection()
    throws ClassNotFoundException,SQLException{
     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      String conStr="jdbc:mysql://localhost:3306/javadb";
      Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433","sa","123");
			Statement stmt = conn.createStatement();
      
      return conn;
    }
    
    
    public List<Student>getAll(Connection conn,String sql){
      List<Student>result=new ArrayList<Student>();
      
      
      Student temp;
      String name;
      int age;
      double grade;
      
      
      try{
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        
        while(rs.next()){
          name=rs.getString("name");
          age=rs.getInt("age");
          grade=rs.getDouble("grade");
          temp=new Student(name,age,grade);
          result.add(temp);
       }
      }catch(SQLException el){
        System.err.println(el);
      }
        return result;
     }
     
     
     public void update(Connection conn,String sql)throws SQLException{
       Statement st=conn.createStatement();
       st.executeUpdate(sql);
       
       st.close();
     }
     
     public void close(Connection conn)throws SQLException{
       conn.close();
     }
     
 }