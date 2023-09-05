import java.util.Scanner;

public class BadCharHeuristic {

	public static void main(String[] args) {
				Scanner in=new Scanner(System.in);
				
				System.out.print("Enter the String: ");
				char inputString[]= in.nextLine().toCharArray();
				
				System.out.print("Enter the pattern: ");
				char pattern[]= in.nextLine().toCharArray();
				int ch;
				System.out.println("Enter 1. For Bad Character Heuristics based pattern Searching.");
				System.out.println("Enter 2. For Good Suffix Heuristics based pattern Searching.");
                ch=in.nextInt();
                System.out.println(ch);
				switch(ch) {
				case 1: search(inputString,pattern);
				        break;
				case 2: GoodSuffixHeuristic.search(inputString,pattern); 
				        break;
				default: System.out.println("Invalid choice");        
				}
				
				
			}

			private static void search(char inputString[], char pattern[]) {
				int charArray=127;
				int inputLength= inputString.length;
				int patLength= pattern.length;
				
				int table[]=new int[charArray];
				
				int i,s=0,flag=0;
				//Construct bad table
				for (i = 0; i < charArray; i++)   
					table[i] = -1;   
				for (i = 0; i < patLength; i++)   
					table[(int) pattern[i]] = i; 
				
				//s is the pointer
				while(s<=(inputLength-patLength)) {
					int j=patLength-1;
					while(j>=0 && pattern[j]== inputString[s+j]) // the pattern is present the value of j will become -1
						j--;
					if(j<0) {
						System.out.println("Pattern at: "+s);
						flag=1;
						s+=(s+patLength < inputLength) ? patLength-table[inputString[s+patLength]]:1;
					}
					else {
						if(j-table[inputString[s+j]]>1)
							s=s+j-table[inputString[s+j]];
						else
							s+=1;
					}
				}
				if(flag==0) 
					System.out.println("Sorry pattern entered is not present in the string.");
			}
}

