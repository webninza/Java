import java.util.*;
public class MyClass {
    public static void main(String args[]) {
       HashMap<String, String> sr= new HashMap<String, String>();

        sr.put("1", "1");
        sr.put("2", "2");

        Iterator<Entry<String, String>> itr = sr.entrySet().iterator();

        while(itr.hasNext()) {
            sr.remove("1");
            itr.next();
        }
    }
}
