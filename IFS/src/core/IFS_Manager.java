package core;

import ifs.IFS;
import ifs.IFS_Function;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class IFS_Manager {

	private ArrayList<IFS> ifsList = new ArrayList<IFS>(5);
	private static int selectedIFS = 0;

	public void addIFS(IFS ifs) {
		ifsList.add(ifs);
	}

	public void removeIFS(IFS ifs) {
		ifsList.remove(ifs);
	}

	public IFS getSelectedIFS() {
		return ifsList.get(selectedIFS);
	}

	public static void setSelectedIFS(int selectedIFS) {
		IFS_Manager.selectedIFS = selectedIFS;
	}

	public ArrayList<IFS> getIFSList() {
		return this.ifsList;
	}

	public int getSize() {
		return ifsList.size();
	}

	public void printList() {
		for (int i = 0; i < ifsList.size(); i++) {
			System.out.println(ifsList.get(i));
		}
	}

	// TODO
	public void loadIFS(String pathToFile) {

		System.out.println("Loading functions..");

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(pathToFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.startsWith("IFS: ")) {
				String ifsName = (line.substring(5));
				System.out.println(ifsName);
				line = scanner.nextLine();

				ArrayList<IFS_Function> functions = new ArrayList<IFS_Function>();
				while (line.startsWith("Function:")) {
					// System.out.println("Function " + i);
					double a = Double.parseDouble(scanner.nextLine());
					// System.out.println(a);
					double b = Double.parseDouble(scanner.nextLine());
					// System.out.println(b);
					double c = Double.parseDouble(scanner.nextLine());
					// System.out.println(c);
					double d = Double.parseDouble(scanner.nextLine());
					// System.out.println(d);
					double e = Double.parseDouble(scanner.nextLine());
					// System.out.println(e);
					double f = Double.parseDouble(scanner.nextLine());
					// System.out.println(f);
					double percent = Double.parseDouble(scanner.nextLine());
					// System.out.println(percent);
					functions.add(new IFS_Function(a, b, c, d, e, f, percent));
					line = scanner.nextLine();
				}
				boolean isStandartFunction = Boolean.parseBoolean(line);
				getIFSList().add(new IFS(ifsName, 350, 350, functions, isStandartFunction));
			}

		}

		System.out.println("Loading sucsessfull..");
		scanner.close();
	}

	public void loadDefaultIFS(String pathToFile)  {

	System.out.println("Loading standart functions..");

	Scanner scanner = null;
	try {
		scanner = new Scanner(new File(pathToFile));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	while (scanner.hasNextLine()) {
		String line = scanner.nextLine();
		if (line.startsWith("IFS: ")) {
			String ifsName = (line.substring(5));
			System.out.println(ifsName);
			line = scanner.nextLine();

			ArrayList<IFS_Function> functions = new ArrayList<IFS_Function>();
			while (line.startsWith("Function:")) {
				// System.out.println("Function " + i);
				double a = Double.parseDouble(scanner.nextLine());
				// System.out.println(a);
				double b = Double.parseDouble(scanner.nextLine());
				// System.out.println(b);
				double c = Double.parseDouble(scanner.nextLine());
				// System.out.println(c);
				double d = Double.parseDouble(scanner.nextLine());
				// System.out.println(d);
				double e = Double.parseDouble(scanner.nextLine());
				// System.out.println(e);
				double f = Double.parseDouble(scanner.nextLine());
				// System.out.println(f);
				double percent = Double.parseDouble(scanner.nextLine());
				// System.out.println(percent);
				functions.add(new IFS_Function(a, b, c, d, e, f, percent));
				line = scanner.nextLine();
			}
			boolean isStandartFunction = Boolean.parseBoolean(line);
			getIFSList().add(new IFS(ifsName, 350, 350, functions, isStandartFunction));
		}

	}

	System.out.println("Loading standart sucsessfull..");
	scanner.close();
}

	public void saveIFS(String pathToFile) {

		System.out.println("Saving functions..");

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					pathToFile));

			for (int i = 0; i < ifsList.size(); i++) {
				if (!ifsList.get(i).isStandartIFS()) {
					writer.append(getIFSList().get(i).toStringForFile());
					System.out.println(getIFSList().get(i).getName());
					writer.newLine();
				}
			}

			writer.close();
		} catch (IOException e) {
			System.out.println("InOutException while saving IFS-List");
			e.printStackTrace();
		}

		System.out.println("Saving sucsessfull..");
	}

}
