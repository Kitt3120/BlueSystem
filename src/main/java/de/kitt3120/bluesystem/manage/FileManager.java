package de.kitt3120.bluesystem.manage;

import de.kitt3120.bluesystem.Core;

import java.io.File;

/**
 * Created by kitt3120 on 09.09.2017 at 21:14.
 */
public class FileManager {

    private File root;

    public FileManager() {
        root = Core.getInstance().getDataFolder();
        root.mkdirs();
        Core.getInstance().getLogger().info("FileManager registered");
    }

    public File getFile(String name) {
        File toReturn = new File(root, name);
        if(!toReturn.getParentFile().exists()) toReturn.getParentFile().mkdirs();

        return toReturn;
    }

}
