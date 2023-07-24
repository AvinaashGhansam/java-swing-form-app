package com.form.view;

import com.form.view.Utils;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class PersonFileFilter extends FileFilter {


    @Override
    public boolean accept(File file) {
        String name = file.getName();
        String extension = Utils.getFileExtenstion(name);

        if (file.isDirectory()) {
            return true;
        }
        
        if (extension == null) {
            return false;
        }

        return extension.equals("per");
    }

    @Override
    public String getDescription() {
        return "Person database file (*.per)";
    }
}
