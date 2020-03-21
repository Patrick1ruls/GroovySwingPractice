package gui

import javax.swing.filechooser.FileFilter

class PersonFileFilter extends FileFilter { // Will be for filtering personal data

    @Override
    boolean accept(File file) {
        String name = file.getName()
        String extension = Utils.getFileExtension(name)

        if (extension == null) {
            return false
        }

        if (extension == "per") {
            return true
        }
        return false
    }

    @Override
    String getDescription() {
        return "Person database files (*.per)" // .per is an arbitrary value for my made up person data type
    }
}
