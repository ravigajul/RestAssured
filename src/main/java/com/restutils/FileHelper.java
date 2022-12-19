package com.restutils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileHelper {

	public static void main(String[] args) {
		String fileName = "C:\\Users\\anjal\\Downloads\\diff.txt";
		String lineToUpdate = "K3*MDQA";
		String updatedLine = "K3*Updated line!";
		checkAndUpdateFile(fileName, lineToUpdate, updatedLine);
		// TODO Auto-generated method stub
		/*
		 * Path path1; Path path2; FileWriter fw; BufferedWriter bw; PrintWriter prnt;
		 * String filesPath = "C:\\Users\\anjal\\Downloads"; final File srcFolder = new
		 * File(filesPath + File.separator + "src"); final File destFolder = new
		 * File(filesPath + File.separator + "dest"); try { fw = new
		 * FileWriter(filesPath + File.separator + "diff.txt"); bw = new
		 * BufferedWriter(fw); prnt = new PrintWriter(bw); for (final File srcFile :
		 * srcFolder.listFiles()) { if (!srcFile.isDirectory()) {
		 * System.out.println(srcFile.getName()); for (final File destFile :
		 * destFolder.listFiles()) { if (!destFile.isDirectory()) { path1 =
		 * Paths.get(srcFile.getAbsolutePath()); path2 =
		 * Paths.get(destFile.getAbsolutePath()); if
		 * (path1.getFileName().toString().equalsIgnoreCase(path2.getFileName().toString
		 * ())) { filesCompareByLine(path1, path2, prnt); break; } } } } } prnt.flush();
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

	public static void checkAndUpdateFile(String fileName, String lineToUpdate, String updatedLine) {
		try {
			// Read the file into a List of Strings
			Path path = Paths.get(fileName);
			List<String> lines = Files.readAllLines(path);

			// Update the line in the List
			for (int i = 0; i < lines.size(); i++) {
				if (lines.get(i).contains(lineToUpdate)) {
					lines.set(i, updatedLine);
					break;
				}
			}
			// Write the updated content back to the file
			Files.write(path, lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void filesCompareByLine(Path path1, Path path2, PrintWriter prnt) {
		boolean isMismatchFound = false;
		try (BufferedReader bf1 = Files.newBufferedReader(path1);
				BufferedReader bf2 = Files.newBufferedReader(path2);) {

			long lineNumber = 1;
			String line1 = "", line2 = "";
			prnt.println("Comparing the file : " + path1.getFileName());
			while ((line1 = bf1.readLine()) != null) {
				line2 = bf2.readLine();
				if (line2 == null || !line1.equals(line2)) {
					System.out.println(lineNumber + ":" + line1);
					System.out.println(lineNumber + ":" + line2 + "\n");
					prnt.println(lineNumber + ":" + line1);
					prnt.println(lineNumber + ":" + line2 + "\n");
					isMismatchFound = true;
				}
				lineNumber++;
			}
			if (bf2.readLine() == null) {
				System.out.println(-1);

			} else {
				System.out.println(lineNumber);
			}
			if (!isMismatchFound) {
				prnt.println("No Mismatches found\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
