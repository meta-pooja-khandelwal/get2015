public class Demo {
	public static void main(String[] args) {
		Integer i = 2;//auto boxing(it works as i=new Integer(2))
		              //(it will call function Integer.valueOf(2) method to convert int to Integer)
		
		int j=i;//un boxing(it works as i=2)
                //(it will call function Integer.intValue(2) method to convert Integer to int)
		System.out.println("j = "+j);
		int i1 = 4;//Premetive data type
		System.out.println("input i and i1 = " + i + " and " + i1);
		changeInteger(i, i1);//here the value of i1 and the reference value of i will be passed but
		                    //since the Integer is immutable so it won't update the value of original variable 
		System.out.println("2.)output i and i1 = " + i + " and " + i1);//old value will  print as an output
	}

	private static void changeInteger(Integer i, Integer i1) {
		i = 5; //auto boxing(it works as i=new Integer(2)),so new memory will be created and
		        //it will not reflect in the original variable
		       //(it will call function Integer.valueOf(2) method to convert int to Integer)
		i1 = 5;//Premetive data type,
		       //it will not reflect in the original variable
		System.out.println("1.)output i and i1 = " + i + " and " + i1);
	}
}
