import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EqaualHashcode {

	public static void main(String[] args) {

		Student s1 = new Student("H2s2");
		Student s2 = new Student("H232");

		//System.out.println(s1.equals(s2));

		Map<Student, String> map = new HashMap();

		//System.out.println(map.put(null, "C"));
		map.put(s1, "A");
		map.put(s2, "B");
		map.put(null, "V");
		map.put(null, "S");

		System.out.println(map.get(s2));
		
		map.forEach((k, v) -> System.out.println(k + " : " + (v)));
	
		System.out.println(map.size());
	}
}
