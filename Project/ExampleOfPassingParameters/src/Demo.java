import java.util.ArrayList;
import java.util.List;


public class Demo {
	public static void main(String args[]){
		String input="pooja";
		System.out.println("input 1 = "+input); //value of string variable before changing it through a function
		changeInputString(input);
		System.out.println("output 2 = "+input);//value of string variable after changing it through a
		                                        //function(works as pass by value;original value is not modified)



		List<String> stringList=new ArrayList<String>();
		stringList.add("pooja");
		stringList.add("kiran");
		System.out.println("input stringList 1 = "+stringList);//list contents before changing it through a function
		changeInputStringList(stringList); 
		System.out.println("output stringList 2 = "+stringList);//list contents after changing it through a
		                                                        //function(works as pass by reference;original
		                                                        //list contents are modified)

		changeInputStringList1(stringList); 
		System.out.println("output stringList 5 = "+stringList);//list contents after changing it through a 
		                                                        //function(works as pass by reference;original 
		                                                        //list contents are modified,but only on the same
		                                                        //memory reference not on other)

		DemoModel obj=new DemoModel();
		obj.setName("pooja");
		System.out.println("input user name 1= "+obj.getName());//user name before changing it through a function
		changeUserName(obj);
		System.out.println("output user name 2= "+obj.getName());//user name after changing it through a function
		                                                         //(works as pass by reference;original object will be modified)


		changeUserName1(obj);
		System.out.println("output user name 5= "+obj.getName());//user name after changing it through a function
		                                                         //(works as pass by reference;original object will
		                                                         //be modified,but only on the same memory reference
		                                                         //not on other)

		
		//same concept will be applied for all permeative and non permeative  data types
	}

	private static void changeUserName1(DemoModel obj) {
		obj.setName("ashvini");
		System.out.println("output user name 3= "+obj.getName());
		obj=new DemoModel();
		obj.setName("hema");
		System.out.println("output user name 4= "+obj.getName());//new memory will be created for obj 

	}

	private static void changeUserName(DemoModel obj) {
		obj.setName("kiran");
		System.out.println("output user name 1= "+obj.getName());
	}

	private static void changeInputStringList1(List<String> stringList) {
		stringList.add("sumi");
		stringList.add("hema");
		stringList.add("ashvini");
		System.out.println("output stringList 3 = "+stringList);
		stringList=new ArrayList<String>();
		stringList.add("hema");//new memory will be created for stringList with new contents

		System.out.println("output stringList 4 = "+stringList);

	}

	private static void changeInputStringList(List<String> stringList) {
		stringList.add("sumi");
		stringList.add("ravika");
		System.out.println("output stringList 1 = "+stringList);

	}

	private static void changeInputString(String input) {
		input="kiran";
		System.out.println("output 1 = "+input);
	}


}
