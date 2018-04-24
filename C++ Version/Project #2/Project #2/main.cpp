#include <iostream>
#include <fstream>
#include <stdio.h>
#include <ctype.h>

using namespace std;

class DescentParser{
    
    public:
    
        //------------------------------------------//
        //           Variable Declarations
        //------------------------------------------//
    
        string parseStr = "";
    
        string output = "";
    
        int index = 0;
    
        //------------------------------------------//
        //           Class Functions
        //------------------------------------------//
    
        //=======================================//
        void begin(){
            
            readFile();
            
        }
    
        void Parse(string s){
        
            index = 0;
            
            parseStr = s;
            
            output = "";
            
            if (isE() && index >= parseStr.length()) {
                
                output += "The string \"" + parseStr + "\" is in the language.\n";
                
            } else {
                
                output += "The string \"" + parseStr + "\" is not in the language.\n";
                
            }
        
        }
    
        //=======================================//
        void readFile(){
            
            ifstream inputFile("input.txt");
            
            ofstream outputFile("output.txt");
            
            string line;
            
            if(inputFile.is_open()){
                
                while(getline(inputFile, line)){
                    
                    Parse(line);
                    
                    outputFile << output;
                    
                }
                
                
                
            } else {
                
                cout << "File is not open!" << endl;
            }
            
        }
    
        void writeFile() {
        
            
        
        }
        //=======================================//
        //E -> P O P | P
        bool isE(){
            
            if (isP()) {
                
                if (isO()) {
                    
                    if (isP()) {
                        
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
        bool isO(){

            if (parseStr[index] == '+' || parseStr[index] == '-' ||
                parseStr[index] == '/'){
                
                index++;
                
                return true;
                
            }
            
            
            if (parseStr[index] == '*') {
                
                index++;
                
                if (parseStr[index] == '*') {
                    
                    index++;
                    
                    return true;
                    
                }
                
                return true;
                
            }
            
            return false;
            
        }
    
        //=======================================//
        // P -> I | L | UI | UL | (E)
        bool isP(){
            
            //I
            if (isI()) {
                
                return true;
                
            }
            
            //L
            if (isL()) {
                
                return true;
                
            }
            
            //UI | UL
            if (isU()) {
                
                //UI
                if (isI()) {
                    
                    return true;
                
                //UL
                } else if (isL()) {
                    
                    return true;
                    
                } else {
                    
                    return false;
                    
                }
                
            }
            
            //(E)
            if (parseStr[index] == '(') {
                
                index++;
                
                if (isE()) {
                    
                    if(parseStr[index] == ')') {
                        
                        index++;
                        
                        return true;
                        
                    } else {
                        
                        index--;
                        
                        return false;
                        
                    }
                    
                }
                    
            }
            
            return false;
            
        }
    
        //=======================================//
        //U -> + | - | !
        bool isU() {

            if (parseStr[index] == '+' || parseStr[index] == '-' || parseStr[index] == '!') {
                
                index++;
                
                return true;
                
            }
            
            return false;
            
        }
    
        //=======================================//
        //I -> C | CI
        bool isI() {
            
            if (isC()) {
                
                if (isI()) {
                    
                    return true;
                    
                }
                
                return true;
                
            }
            
            return false;
            
        }
    
        //=======================================//
        //C -> a | b | ... | y | z
        bool isC() {
            
            if (isalpha(parseStr[index])) {
                
                index++;
                
                return true;
                
            }
            
            return false;
            
        }
    
        //=======================================//
        //L -> D | DL
        bool isL() {
            
            if (isD()) {
                
                if (isL()) {
                    
                    return true;
                    
                }
                
                return true;
            }
            
            return false;
            
        }
    
        //=======================================//
        //D -> 0 | 1 | ... | 8 | 9
        bool isD() {
            
            if (parseStr[index] >= '0' && parseStr[index] <= '9') {
                
                index++;
                
                return true;
                
            }
            
            return false;
            
        }
    
};

int main() {

    DescentParser parser;
    
    parser.begin();
    
    return 0;
    
}
