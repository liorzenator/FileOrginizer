import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExtParser 
{
	private final String DICT_PATH = "/Users/Lior/Desktop/Extensions/Dictionary";
	private final String TO_PARSE_PATH = "/Users/Lior/Desktop/Extensions/ToParse";
	private final String DIRS = "Directories-HM";
	private HashMap<String, HashMap<String, String>> dict = new HashMap<>();
	private ArrayList<String> directories;
	
	public ExtParser(boolean override) 
	{
		parse(override);
		loadData();
		directories = new ArrayList<>();
	}
	
	private void loadData() 
	{
		System.out.print("Loading dictionary");
		
		File dir = new File(DICT_PATH);

		for (File file : dir.listFiles())
		{
			System.out.print(".");
			
			if (file.getName().endsWith("new.txt"))
			{
				createHashMap(file.getPath());
			}
		}
		
		System.out.println("Done");
	}

	public void parse(boolean override)
	{
		String fileName = "";
		
		for (File file : new File(TO_PARSE_PATH).listFiles())
		{
			fileName = file.getName();
			
			if (!new File(DICT_PATH+ "/" + fileName +"-new.txt").exists())
			{
				try(BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()))) 
				{
					StringBuilder sb = new StringBuilder();
					String line = br.readLine();
					int i; 
					String[] words;
					
					while (line != null) 
					{
						
						if (line.equals("docx"))
						{
							System.out.println("BLA");
						}
						
						line = line.trim();
						
						words = line.split(" ");
						
						if (line.contains("file icon"))
						{
							line = line.replaceAll("file icon", "");
							line = words[0];
						}
						
						line = line.replace('\t', ' ');
						i = line.lastIndexOf("file extension");

						if (i >= 0)
						{
							line = line.substring(i + 15);
						}

						sb.append(line);
						sb.append(System.lineSeparator());
						line = br.readLine();
					}

					String everything = sb.toString();

					writeToFile(everything, fileName);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			else 
			{
				System.out.println(String.format("Parsed version of the %s already exists", file.getName()));
			}
		}
	}

	private void createHashMap(String fileDest)
	{
		HashMap<String, String> ext = new HashMap<>();

		try(BufferedReader br = new BufferedReader(new FileReader(fileDest))) 
		{
			String line = br.readLine().trim();
			int i;
			String extension;
			String definition;
			String dir;
			
			while (line != null && !line.equals("") && !line.equals(" ") && !line.equals("\n")) 
			{
				i = line.indexOf(' ');
				
				extension = line;
				
				if (i < 0)
				{
					line = br.readLine();
					definition = line;
				}
				else
				{
					extension = line.substring(0, i);
					definition = line.substring(i + 1);
				}

				ext.put(extension, definition);
				line = br.readLine();
			}

			dir = new File(fileDest).getName().split(" ")[0];
			
			dict.put(dir, ext);;
			directories.add(dir);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeToFile(String everything, String fileName)
			throws FileNotFoundException, UnsupportedEncodingException 
	{
		PrintWriter writer = new PrintWriter(DICT_PATH + "/" + fileName + "-new.txt", "UTF-8");
		writer.println(everything);
		writer.close();
	}

	public HashMap<String, HashMap<String, String>> getDict() {
		return dict;
	}
	
	public ArrayList<String> getDirectoriesName()
	{
		return directories;
	}
}
