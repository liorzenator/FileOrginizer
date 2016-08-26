import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Orginizer 
{
	private String absPath;
	private HashMap<String, HashMap<String, String>> dict;
	private int untitledFolderNum;
	

	public Orginizer(String absPath)
	{
		this.absPath = absPath;
		ExtParser extParser = new ExtParser(false);
		this.dict = extParser.getDict();
		
		System.out.println(dict.size());
		untitledFolderNum = 1;
	}

	public void orginize()
	{
		File destination = new File(absPath);

		if (!destination.isDirectory())
		{
			throw new IllegalArgumentException("Destination is not a directory!");
		}

		// to move to a new method
		File[] files = destination.listFiles();
		String ext = "";
		String fileName = "";
		String folderName = "";

		for (File file : files)
		{
			fileName = file.getName();

			if (fileName.startsWith("."))
			{
				System.out.println(String.format("Skipping the file: %s since it starts with \".\"", fileName)); 
				continue;
			}

			int i = fileName.lastIndexOf(".");

			if (i > 0)
			{
				ext = fileName.substring(i+1).toLowerCase();
			}

			if (!"BIN".equals(ext) && !file.isDirectory())
			{
				folderName = createDirectory(ext);

				file.renameTo(new File (absPath + "/" + folderName + "/" + fileName));
				
				System.out.println(String.format("Moving the file %s to %s", fileName, folderName));
			}
		}
	}

	private String createDirectory(String ext)
	{
		String folderName = "untitled" + untitledFolderNum;
		Eanswer answer = Eanswer.NO;
		
		for (Entry<String, HashMap<String, String>> entry : dict.entrySet())
		{
			if (entry.getValue().containsKey(ext))
			{
				folderName = entry.getKey();
			}
		}

		if (folderName.contains("untitled"))
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println(String.format("Unknown extension: %s , would you like to add it? %s // %s ", ext, Eanswer.NO, Eanswer.YES));
	
			try 
			{
				answer = Eanswer.valueOf(scanner.next().toUpperCase());
				
				if (answer == Eanswer.YES)
				{
					StringBuilder sb = new StringBuilder();
					
					System.out.println("Please choose where you whould like to add it ");
					
					// TODO print to the user where he would like to add it 
					
					
				}
				else 
				{
					// add the file to untitled folder 
					
					// add the file to a new folder that the user will create
				}
			}
			catch (Exception e)
			{
				System.out.println(String.format("Please answer with %s or %s" , Eanswer.NO, Eanswer.YES));
				
				for(String dir : ex)
				{
					
				}
		
				// the name of the method for call back
			}
			finally 
			{
				scanner.close();
			}
			
			
		}

		File dir = new File(absPath + "/" + folderName);

		if (!dir.exists())
		{
			dir.mkdir();
		}

		return folderName;
	}
	
	public void printDictionary()
	{
		System.out.println("Printing supported extensions:");
		
		for (Entry<String, HashMap<String, String>> entry : dict.entrySet())
		{
			System.out.println("Table name: " + entry.getKey().toString());
			
			for (Entry<String, String> innerEntry : entry.getValue().entrySet())
			{
				System.out.println(innerEntry.getKey() + " - " + innerEntry.getValue());
			}
			
			System.out.println();
		}
	}
	
	public boolean checkIfExtensionExists(String ext)
	{
		boolean isExists = false;
		String tableName = "";
		
		for (Entry<String, HashMap<String, String>> entry : dict.entrySet())
		{
			if (entry.getValue().containsKey(ext))
			{
				isExists = true;
				tableName = entry.getKey();
			}
		}
		
		if (isExists)
		{
			System.out.println(String.format("The extension %s exists in table %s", ext, tableName));
		}
		else
		{
			System.out.println(String.format("The extension %s doesnt exist", ext));	
		}
		
		return isExists;
	}
}
