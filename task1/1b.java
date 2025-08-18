
public class UglyNumber {
	 public static boolean isUgly(int num) {
	        if (num <= 0) {
	            return false;
	        }         
	        
	         while (num % 2 == 0) {
	            num /= 2;
	        }
	        while (num % 3 == 0) {
	            num /= 3;
	        }
	        while (num % 5 == 0) {
	            num /= 5;
	        }
	        
	         return num == 1;
	    }

	    public static void main(String[] args) {
	        int number = 30;  
	        
	        if (isUgly(number)) {
	            System.out.println(number + " is an ugly number.");
	        } else {
	            System.out.println(number + " is not an ugly number.");
	        }
	    }
	}



