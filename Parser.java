import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Parser {
	public int wordCount(String fileName){
		File file = new File(fileName);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int wordCount = 0;
		while(scanner.hasNextLine()){
			while(scanner.hasNext()){
				wordCount++;
				String word = scanner.next();
			}
			String nextLine = scanner.nextLine();
		}
		return wordCount;
		
	}
	public static void main(String[] args){
		Parser ps = new Parser();
		System.out.println(ps.wordCount(args[0]));
	}
}
