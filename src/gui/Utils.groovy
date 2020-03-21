package gui

class Utils {
    static String getFileExtension(String name)  { // returns the .something of a file (Ex .png, .pdf, .img)
        int pointIndex = name.lastIndexOf(".")

        if (pointIndex == -1) { // No . in string
            return null
        }

        if (pointIndex == name.length()-1) { // file has no extension (Ex file.)
            return null
        }

        return name.substring(pointIndex+1, name.length())
    }
}
