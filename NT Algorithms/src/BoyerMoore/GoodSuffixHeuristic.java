
public class GoodSuffixHeuristic {

			static void search(char inputString[], char pattern[]) {
				
				int inputLength= inputString.length;
				int patLength= pattern.length;
				
				int shift[]=new int[patLength+1];
				int bound[]=new int[patLength+1];
				
				int i,s=0,flag=0;
				
				for (i = 0; i <= patLength; i++)   
					shift[i] = 0;  
				
				
				//Formation of shift table
				strong_suffix(shift, bound, pattern, patLength);
				strong_suffix2(shift,bound, pattern, patLength);
				
				//s is the pointer
				while(s<=(inputLength-patLength)) {
					int j=patLength-1;
					while(j>=0 && pattern[j]== inputString[s+j]) // the pattern is present the value of j will become -1
						j--;
					if(j<0) {
						System.out.println("Pattern at: "+s);
						flag=1;
						s+=shift[0];
					}
					else {
						s+= shift[j+1];
					}
				}
				if(flag==0) 
					System.out.println("Sorry pattern entered is not present in the string.");
			}

			private static void strong_suffix2(int[] shift, int[] bound, char[] pattern, int patLength) {
				int i, j;
			    j = bound[0];
			    for(i = 0; i <= patLength; i++)
			    {
			        if(shift[i] == 0)
			            shift[i] = j;
			  
			        if (i == j)
			            j = bound[j];
			    }
				
			}

			private static void strong_suffix(int[] shift, int[] bound, char[] pattern, int patLength) {
				int i = patLength, j = patLength + 1;
			    bound[i] = j;
			  
			    while(i > 0)
			    {
			       while(j <= patLength && pattern[i - 1] != pattern[j - 1])
			        {
			            if (shift[j] == 0)
			                shift[j] = j - i;
			  
			           j = bound[j];
			        }
			        i--; j--;
			        bound[i] = j;
			    }
				
			}
}

