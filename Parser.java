import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
		ArrayList<Integer> numOfSections = new ArrayList<Integer>(); //store the amount of sections of each article.
		int articleNumber = 0;
		
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
				if(articleNumber != 0){
					if(lineArray.length == 2){
						if(lineArray[0].equals("Section")){
							int oldValue = numOfSections.get(articleCount - 1);
							numOfSections.set(articleCount - 1, (oldValue + 1));
							sectionCount++;
						}
					}
				}
				if(lineArray.length == 2){
					if(lineArray[0].equals("Article")){
						articleCount++;
						articleNumber++;
						numOfSections.add(0); //init the position of the corresponding article in the arraylist.
					}
				}
				for(String s : lineArray){ 
					if(s.equals("")){
						wordCount--; //make sure extra space is not counted.
					}else if(s.equals("I") || s.equals("I,") || s.equals("I.")){
						ignoreWordCount++;
						ignoreCharCount++;
						counter++;
					}else if(s.equals("We") || s.equals("We,") || s.equals("We.")){
						ignoreWordCount++;
						ignoreCharCount += 2;
						counter++;
					}else if(s.equals("You") || s.equals("You,") || s.equals("You.")){
						ignoreWordCount++;
						ignoreCharCount += 3;
						counter++;
					}else if(s.equals("They") || s.equals("They,") || s.equals("They.")){
						ignoreWordCount++;
						ignoreCharCount += 4;
						counter++;
					}else if(s.equals("a") || s.equals("a,") || s.equals("a.")){
						ignoreWordCount++;
						ignoreCharCount++;
						counter++;
					}else if(s.equals("and") || s.equals("and,") || s.equals("and.")){
						ignoreWordCount++;
						ignoreCharCount += 3;
						counter++;
					}else if(s.equals("the") || s.equals("the,") || s.equals("the.")){
						ignoreWordCount++;
						ignoreCharCount += 3;
						counter++;
					}else if(s.equals("that") || s.equals("that,") || s.equals("that.")){
						ignoreWordCount++;
						ignoreCharCount += 4;
						counter++;
					}else if(s.equals("of") || s.equals("of,") || s.equals("of.")){
						ignoreWordCount++;
						ignoreCharCount += 2;
						counter++;
					}else if(s.equals("for") || s.equals("for,") || s.equals("for.")){
						ignoreWordCount++;
						ignoreCharCount += 3;
						counter++;
					}else if(s.equals("with") || s.equals("with,") || s.equals("with.")){
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
		System.out.println("Total Sections per Article:");
		for(int i = 0; i < articleCount; i++){
			System.out.println("    Article " + (i+1) + ": " + numOfSections.get(i));
		}
	}
	public static void main(String[] args){
		Parser ps = new Parser();
		ps.wordCount(args[0]);
	}
}