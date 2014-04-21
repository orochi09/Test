import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
		
//		Scanner scanner2 = null;
//		try {
//			scanner2 = new Scanner(file);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		int wordCount = 0;
		int lineCount = 0;
		long byteCount = file.length();

		while(scanner1.hasNextLine()){
//			while(scanner1.hasNext()){
//				wordCount++;
//				String word = scanner1.next();
//			}
			String nextLine = scanner1.nextLine();
//		}
			if(!nextLine.equalsIgnoreCase("")){
				String[] lineArray = nextLine.split(" ");
				wordCount += lineArray.length;
				for(String s : lineArray){
					if(s.equals("")){
						wordCount--;
					}
				}
			}
			lineCount++;
		
//		while(scanner2.hasNextLine()){
//			lineCount++;
//			String nextLine = scanner2.nextLine();
		}
			
		System.out.println("     " + lineCount + "    " + wordCount + "   " + byteCount + " " + fileName);	
	}
	public static void main(String[] args){
		Parser ps = new Parser();
//		String fileName = "res/constitution.txt";
//		ps.wordCount(fileName);
		ps.wordCount(args[0]);

	}
}
