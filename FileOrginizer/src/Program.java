public class Program 
{
	public static void main (String[] args)
	{
		Orginizer org = new Orginizer("/Users/Lior/Downloads");
		
		org.orginize();
		
		org.printDictionary();
		
		org.checkIfExtensionExists("docx");
	}
}
