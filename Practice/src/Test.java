import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Test {

	 // function to generate a random string of length n 
    static String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCD"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
  
	public static void main(String[] args) {		
		int start = (int)System.currentTimeMillis();
		ConcurrentHashMap<String,Person> map1 = new ConcurrentHashMap<String,Person>();
		ConcurrentHashMap<String,Person> map2 = new ConcurrentHashMap<String,Person>();
		 int j = 0;
		for(int i=0;i<30000;i++) {
		
			// Get the size n 
	        int n = 2; 
	       
	     String value=   Test.getAlphaNumericString(n);
	     
	     	if(value.equals("AB"))
	     	{
			Person p = new Person(j++,value);
			map1.put(i+1+"",p);			
	     	}
	     	else {
				Person p = new Person(j++,value);
				map1.put(i+1+"",p);				     		
	     	}
	     	}
//		Set<Entry<String, Person>> s = map1.entrySet(); 
//		for(Map.Entry<String,Person> map :s)
//		{
//		System.out.println(map);
//		}	
		List<Entry<String, Person>> liststring;
		map1.entrySet().stream().filter(e->e.getValue().getName().equals("AB"))
				.collect(Collectors.toList());
//		
	
		
//		ExecutorService executor = Executors.newSingleThreadExecutor();
//		executor = map1.entrySet().stream().filter(e->e.getValue().getName().equals("AB"));
//		
		System.out.println(map1.entrySet().stream().filter(e->e.getValue().getName().equals("AB")).collect(Collectors.toList()).size());
		int end = (int) System.currentTimeMillis();
//		TimestreamCount(map1)
		System.out.println(end-start);
	
	}
	
	public static int TimestreamCount(ConcurrentHashMap<String,Person> concurrent )	
	{
		concurrent.entrySet().stream().filter(e->e.getValue().getName().equals("AB")).collect(Collectors.toList()).size();
		
	return 0;
	}
}