import java.util.List;
import java.util.Map;

public class DisplayUtils{
  public static String display(List<Map<String,String>>conList){
    
    StringBuilder strResult=new StringBuilder();
    
    for(Map<String,String>map: conList){
      for(Map.Entry entry:map.entrySet()){
        strResult.append(entry.getKey()+":"+entry.getValue()+"\t");
      }
      strResult.append("\r\n");
      }
      return strResult.toString();
      }
    }