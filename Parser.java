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
			System.err.println("Error: " + fileName + " not found.");
			e.printStackTrace();
		}
		
		int counter = 0; //for checking if the whole line is ignored.
		
		int wordCount = 0;
		int lineCount = 0;
		int charCount = 0;
		
		int ignoreWordCount = 0;
		int ignoreCharCount = 0;
		int ignoreLineCount = 0;
		
		int articleCount = 0;
		int sectionCount = 0;
		
		

		while(scanner1.hasNextLine()){
			String nextLine = scanner1.nextLine();
			charCount += nextLine.length();
			counter = 0;
			if(!nextLine.equalsIgnoreCase("")){
				String[] lineArray = nextLine.split(" ");
				wordCount += lineArray.length;
				if(lineArray.length == 2){
					if(lineArray[0].equals("Article")){
						articleCount++;
					}else if(lineArray[0].equals("Section")){
						sectionCount++;
					}
				}
				for(String s : lineArray){ 
					if(s.equals("")){
						wordCount--; //make sure extra space is not counted.
					}else if(s.equals("I")){
						ignoreWordCount++;
						ignoreCharCount++;
						counter++;
					}else if(s.equals("We")){
						ignoreWordCount++;
						ignoreCharCount += 2;
						counter++;
					}else if(s.equals("You")){
						ignoreWordCount++;
						ignoreCharCount += 3;
						counter++;
					}else if(s.equals("They")){
						ignoreWordCount++;
						ignoreCharCount += 4;
						counter++;
					}else if(s.equals("a")){
						ignoreWordCount++;
						ignoreCharCount++;
						counter++;
					}else if(s.equals("and")){
						ignoreWordCount++;
						ignoreCharCount += 3;
						counter++;
					}else if(s.equals("the")){
						ignoreWordCount++;
						ignoreCharCount += 3;
						counter++;
					}else if(s.equals("that")){
						ignoreWordCount++;
						ignoreCharCount += 4;
						counter++;
					}else if(s.equals("of")){
						ignoreWordCount++;
						ignoreCharCount += 2;
						counter++;
					}else if(s.equals("for")){
						ignoreWordCount++;
						ignoreCharCount += 3;
						counter++;
					}else if(s.equals("with")){
						ignoreWordCount++;
						ignoreCharCount += 4;
						counter++;
					}
				}
				if(counter == lineArray.length){  //the whole line is ignored.
					ignoreLineCount++;
				}				
			}
			lineCount++;
		}
		charCount += lineCount; //add "\n" for each line
		int properWordCount = wordCount - ignoreWordCount;
		int properCharCount = charCount - ignoreCharCount;
		int properLineCount = lineCount - ignoreLineCount;
		

			
		System.out.println("all: " + lineCount + "    " + wordCount + "   " + charCount + " " + fileName);	
		System.out.println("proper: " + properLineCount + "    " + properWordCount + "   " + properCharCount + " " + fileName);	
		System.out.println("Total Articles: " + articleCount);
		System.out.println("Total Sections: " + sectionCount);
	}
	public static void main(String[] args){
		Parser ps = new Parser();
//		String fileName = "res/constitution.txt";
//		ps.wordCount(fileName);
		ps.wordCount(args[0]);


	}
}
