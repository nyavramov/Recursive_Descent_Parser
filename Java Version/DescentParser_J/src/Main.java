import java.io.*;
import java.util.Scanner;

class DescentParser{
	
    //------------------------------------------//
    //           Variable Declarations
    //------------------------------------------//

    String parseStr = "";
    
    String output = "";

    int index = 0;
    
    File file;
    
    Scanner ScannyMcScanFace;
    
    
    	

    //------------------------------------------//
    //           Class Functions
    //------------------------------------------//
    
    //=======================================//
    void begin() {
        
        readFile();
        
    }
    
    //=======================================//
    void Parse(String s) {
    	
    	index = 0;
    	
    	parseStr = s;
    	
    	if (isE() && index >= parseStr.length()) {
            
            output += "The string \"" + parseStr + "\" is in the language.\n";
            
        } else {
            
            output += "The string \"" + parseStr + "\" is not in the language.\n";
            
        }
    	
    	try {
        	
        	PrintWriter printy = new PrintWriter("output.txt");
        	
        	printy.println(output);
        	
        	printy.close();
        	
        } catch (IOException e){
        	
        	System.out.println("Error!");
        	
        }
    	
    }
   
    //=======================================//
    void readFile() {
    	
    	try {
    		
    		file = new File("input.txt");
    		
    		ScannyMcScanFace = new Scanner(file);
    		
    		while(ScannyMcScanFace.hasNextLine()) {
    			
    			Parse(ScannyMcScanFace.nextLine());
    			
    		}
    		
    	} catch (FileNotFoundException e) {
    		
    		System.out.println("No such file exists!");
    		
    	}
    	
    }
    
    //=======================================//
    //E -> P O P | P
    boolean isE() {
    	
    	if(isP()) {
    		
    		if(isO()) {
    			
    			if(isP()) {
    				
    				return true;
    				
    			} else {
    				
    				return false;
    				
    			}
    			
    		}
    		
    		return true;
    		
    	}
    	
    	return false;
    	
    }
    
    //=======================================//
    //O -> + | - | * | / | **
    boolean isO() {
    	
    	if( (index < parseStr.length()) && (parseStr.charAt(index) == '+' ||
    			parseStr.charAt(index) == '-' || parseStr.charAt(index) == '/') ) {
    		
    		index++;
    		
    		return true;
    		
    	}
    	
    	if(index < parseStr.length() && (parseStr.charAt(index) == '*')) {
    		
    		index++;
    		
    		if(index < parseStr.length() && parseStr.charAt(index) == '*') {
    			
    			index++;
    			
    			return true;
    			
    		} 
    		
    		return true;
    		
    	}
    	
    	return false;
    	
    }
    
    //=======================================//
    // P -> I | L | UI | UL | (E)
    boolean isP(){
    	
    	if(!(index < parseStr.length())) {
    		
    		return false;
    		
    	}
    	
    	
    	//I
    	if(isI()) {
    		
    		return true;
    		
    	}
    	
    	//L
    	if(isL()) {
    		
    		return true;
    		
    	}
    	
    	//UI | UL
    	if(isU()) {
    		
    		//UI
    		if(isI()) {
    			
    			return true;
    		
    		//UL
    		} else if (isL()) {
    			
    			return true;
    			
    		} else {
    			
    			return false;
    			
    		}
     		
    	}
    	
    	if(index < parseStr.length() && parseStr.charAt(index) == '(') {

    		index++;
    		
    		if(isE()) {
    			
    			if(index < parseStr.length() && parseStr.charAt(index) == ')') {
    				
    				index++;
    				
    				return true;
    				
    			} else {
    				
    				index--;
    				
    				return false;
    				
    			}
    			
    		} else {
    			
    			return false;
    			
    		}
    		
    	}
    	
    	return false;
    	
    }
    
    //=======================================//
    //U -> + | - | !
    boolean isU() {
    	
    	
    	if(index < parseStr.length() && (parseStr.charAt(index) == '+' || parseStr.charAt(index) == '-' || 
    			parseStr.charAt(index) == '!')) {
    		
    		index++;
    		
    		return true;
    		
    	}
    	
    	return false;
    	
    }
    
    //=======================================//
    //I -> C | CI
    boolean isI() {
    	
    	if(isC()){
    		
    		if(isI()){
    			
    			return true;
    			
    		}
    		
    		return true;
    	}
    	
    	return false;
    }
    
    //=======================================//
    //C -> a | b | ... | y | z
    boolean isC() {
    	
    	if(index < parseStr.length() && Character.isLetter(parseStr.charAt(index) ) ) {
    		
    		index++;
    		
    		return true;
    		
    	}
    	
    	return false;
    }
    
    //=======================================//
    //L -> D | DL
    boolean isL() {
    	
    	if(isD()) {
    		
    		if(isL()) {
    			
    			return true;
    			
    		} 
    		
    		return true;
    		
    	}
    	
    	return false;
    	
    }
    
    //=======================================//
    //D -> 0 | 1 | ... | 8 | 9
    boolean isD() {
    	
    	if(index < parseStr.length() && parseStr.charAt(index) >= '0' && parseStr.charAt(index) <= '9') {
    		
    		index++;
    		
    		return true;
    		
    	}
    	
    	return false;
    }
    
}

public class Main {

	public static void main(String[] args) throws Exception {
		
		DescentParser parsey = new DescentParser();
		
		parsey.begin();
		
	}

}

