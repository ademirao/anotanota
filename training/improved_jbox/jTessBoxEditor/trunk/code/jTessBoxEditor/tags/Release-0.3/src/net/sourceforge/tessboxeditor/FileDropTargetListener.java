package net.sourceforge.tessboxeditor;

import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.io.*;
import java.net.URI;

/**
 *  File Drop Target Listener
 *
 *@author     Quan Nguyen (nguyenq@users.sf.net)
 *@version    1.1, 23 December 2007
 *@see        http://vietpad.sourceforge.net
 */
public class FileDropTargetListener extends DropTargetAdapter {
    private Gui holder;
    private File droppedFile;
    
    /**
     *  Constructor for the FileDropTargetListener object
     *
     *
     * @param holder  instance of Gui
     */
    public FileDropTargetListener(Gui holder) {
        this.holder = holder;
    }
    
    /**
     *  Gives visual feedback
     *
     *@param  dtde  the DropTargetDragEvent
     */
    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        if (droppedFile == null) {
            DataFlavor[] flavors = dtde.getCurrentDataFlavors();
            for (int i = 0; i < flavors.length; i++) {               
                if (flavors[i].isFlavorJavaFileListType()) {
                    dtde.acceptDrag(DnDConstants.ACTION_COPY);
                    return;
                }
            }
        }
        dtde.rejectDrag();
    }
    
    /**
     *  Handles dropped files
     *
     *@param  dtde  the DropTargetDropEvent
     */
    @Override
    public void drop(DropTargetDropEvent dtde) {
        Transferable transferable = dtde.getTransferable();
        DataFlavor[] flavors = transferable.getTransferDataFlavors();
        
        final boolean LINUX = System.getProperty("os.name").equals("Linux");
        
        for (int i = 0; i < flavors.length; i++) {
            try {
                if (flavors[i].equals(DataFlavor.javaFileListFlavor) || (LINUX && flavors[i].getPrimaryType().equals("text") && flavors[i].getSubType().equals("uri-list"))) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    
                    // Missing DataFlavor.javaFileListFlavor on Linux (Bug ID: 4899516)
                    if (flavors[i].equals(DataFlavor.javaFileListFlavor)) {
                        java.util.List fileList = (java.util.List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                        droppedFile = (File) fileList.get(0);
                    } else {
                        // This workaround is for File DnD on Linux
                        String string =
                                transferable.getTransferData(DataFlavor.stringFlavor).toString().replaceAll("\r\n?", "\n");
                        URI uri = new URI(string.substring(0, string.indexOf('\n')));
                        droppedFile = new File(uri);
                    }
                    
                    // Note: On Windows, Java 1.4.2 can't recognize a Unicode file name
                    // (Bug ID 4896217). Fixed in Java 1.5.
                    
                    // Processes one dropped file at a time in a separate thread
                    new Thread() {
                        @Override
                        public void run() {
                            holder.openFile(droppedFile);
                            droppedFile = null;
                        }
                    }.start();
                    
                    dtde.dropComplete(true);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                dtde.rejectDrop();
            }
        }
        dtde.dropComplete(false);
    }
}
