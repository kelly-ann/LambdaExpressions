package org.kelly_ann;

import java.io.File;
import java.io.FileFilter;


public class WithLambda {
	
	public static void main(String[] args) {
		
		//lambda function
		FileFilter filter = (File file) -> file.getName().endsWith(".java");
		
		File dir = new File("C://Users//Kelly-Ann//workspace//LambdaExpressions//src//org//kelly_ann");
		
		File[] files = dir.listFiles(filter);
		
		for (File f : files) {
			System.out.println(f);
		}
		
	}
	
}
