package com.restutils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Path path1 = Paths.get("C:\\Users\\anjal\\Downloads\\tex1.txt");
		Path path2 = Paths.get("C:\\Users\\anjal\\Downloads\\tex2.txt");

		filesCompareByLine(path1, path2);
	}

	public static void filesCompareByLine(Path path1, Path path2) throws IOException {
		try (BufferedReader bf1 = Files.newBufferedReader(path1); BufferedReader bf2 = Files.newBufferedReader(path2);FileWriter f = new FileWriter("C:\\Users\\anjal\\Downloads\\diff.txt");
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) {

			long lineNumber = 1;
			String line1 = "", line2 = "";
			while ((line1 = bf1.readLine()) != null) {
				line2 = bf2.readLine();
				if (line2 == null || !line1.equals(line2)) {
					System.out.println(line1 + ":\t" + line2 + "\n");
						p.println(lineNumber+":"+line1 + ":\t" + line2 + "\n");
				}
				lineNumber++;
			}
			if (bf2.readLine() == null) {
				System.out.println(-1);
			} else {
				System.out.println(lineNumber);
			}
		}
	}

}
