package UserInterface;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class MyDragDropListener implements DropTargetListener
{
	@Override
	public void drop(DropTargetDropEvent event)
	{
		// Accept copy drops
		event.acceptDrop(DnDConstants.ACTION_COPY);
		// Get the transfer which can provide the dropped item data
		Transferable transferable = event.getTransferable();
		// Get the data formats of the dropped item
		DataFlavor[] flavors = transferable.getTransferDataFlavors();
		// Loop through the flavors
		for (DataFlavor flavor : flavors)
		{
			try
			{
				// If the drop items are files
				if (flavor.isFlavorJavaFileListType())
				{
					// Get all of the dropped files
					List<File> files = castList(File.class, (Collection<?>) transferable.getTransferData(flavor));
					//List<File> files = (List) transferable.getTransferData(flavor);
					// Loop them through
					for (File file : files)
					{
						// Print out the file path
						System.out.println("File path is '" + file.getPath() + "'.");
					}
				}
			}
			catch (Exception e)
			{
				// Print out the error stack
				e.printStackTrace();
			}
		}
		// Inform that the drop is complete
		event.dropComplete(true);
	}
	
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) 
	{
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}

	@Override
	public void dragEnter(DropTargetDragEvent event)
	{
	}

	@Override
	public void dragExit(DropTargetEvent event)
	{
	}

	@Override
	public void dragOver(DropTargetDragEvent event)
	{
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent event)
	{
	}
}
