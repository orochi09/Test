import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Parser {
	public void wordCount(String fileName){
		File file = new File(fileName);
		Scanner scanner1 = null;
		try {
			scanner1 = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int wordCount = 0;
		int lineCount = 0;
//		long byteCount = file.length();
		int charCount = 0;
		
		int ignoreWordCount = 0;
		int ignoreCharCount = 0;
		int ignoreLineCount = 0;
		

		while(scanner1.hasNextLine()){
			String nextLine = scanner1.nextLine();
			charCount += nextLine.length();
			if(!nextLine.equalsIgnoreCase("")){
				String[] lineArray = nextLine.split(" ");
				wordCount += lineArray.length;
				for(String s : lineArray){
					if(s.equals("")){
						wordCount--;
					}else if(s.equals("I")){
						ignoreWordCount++;
						ignoreCharCount++;
					}else if(s.equals("We")){
						ignoreWordCount++;
						ignoreCharCount += 2;
					}else if(s.equals("You")){
						ignoreWordCount++;
						ignoreCharCount += 3;
					}else if(s.equals("They")){
						ignoreWordCount++;
						ignoreCharCount += 4;
					}else if(s.equals("a")){
						ignoreWordCount++;
						ignoreCharCount++;
					}else if(s.equals("and")){
						ignoreWordCount++;
						ignoreCharCount += 3;
					}else if(s.equals("the")){
						ignoreWordCount++;
						ignoreCharCount += 3;
					}else if(s.equals("that")){
						ignoreWordCount++;
						ignoreCharCount += 4;
					}else if(s.equals("of")){
						ignoreWordCount++;
						ignoreCharCount += 2;
					}else if(s.equals("for")){
						ignoreWordCount++;
						ignoreCharCount += 3;
					}else if(s.equals("with")){
						ignoreWordCount++;
						ignoreCharCount += 4;
					}
//					if(s.equals("I") || s.equals("We") || s.equals("You") || s.equals("They") || 
//							s.equals("a") || s.equals("and") || s.equals("the") || s.equals("that")
//							 || s.equals("of") || s.equals("for") || s.equals("with")){
//						ignoreCount++;
//					}
				}
			}
			lineCount++;
		}
		charCount += lineCount; //add "\n" for each line
		int properWordCount = wordCount - ignoreWordCount;
		int properCharCount = charCount - ignoreCharCount;
			
		System.out.println("all: " + lineCount + "    " + wordCount + "   " + charCount + " " + fileName);	
		System.out.println("proper: " + lineCount + "    " + properWordCount + "   " + properCharCount + " " + fileName);	
//		System.out.println(charCount);
	}
	public static void main(String[] args){
		Parser ps = new Parser();
//		String fileName = "res/constitution.txt";
//		ps.wordCount(fileName);
		ps.wordCount(args[0]);


	}
}
