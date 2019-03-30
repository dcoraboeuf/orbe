package net.sf.doolin.util;

import java.io.File;
import java.io.FileFilter;
import java.text.ParseException;

/**
 * This pattern works with DOS like patterns. Only the "*" wildcard is
 * supported.
 * 
 * @author Damien Coraboeuf
 * @version $Id: PatternFileFilter.java,v 1.1 2007/07/18 18:41:21 guinnessman Exp $
 * @see net.sf.doolin.util.PatternFormat
 */
public class PatternFileFilter implements FileFilter {
    /**
     * Format used
     */
    private PatternFormat format;

    /**
     * Initialization
     * 
     * @param pattern
     *            Pattern to be used.
     */
    public PatternFileFilter(String pattern) {
        format = new PatternFormat(pattern);
    }

    /**
     * @see java.io.FileFilter#accept(File)
     */
    public boolean accept(File file) {
        return accept(file.getName());
    }

    /**
     * Format a string using some parameters.
     * 
     * @param params
     *            Parameters to include in the formatted string
     * @return Formatted string.
     * @see PatternFormat#format(Object[])
     */
    public String format(Object... params) {
        return format.format(params);
    }

    /**
     * Checks if a file name (or any string) complies with the pattern.
     * 
     * @param fileName
     *            String to check
     * @return Result of the check.
     * @see PatternFormat#accept(String)
     */
    public boolean accept(String fileName) {
        try {
            Object[] content = format.parse(fileName);
            String value = format.format(content);
            return fileName.equals(value);
        } catch (ParseException ex) {
            return false;
        }
    }

}
