package net.sf.doolin.util;

import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Comparator based upon a property
 * 
 * @author CORABOEUF
 * @version $Id: PropertyComparator.java,v 1.1 2007/07/18 18:41:20 guinnessman Exp $
 */
public class PropertyComparator implements Comparator {
    /**
     * Log
     */
    private final static Log log = LogFactory.getLog(PropertyComparator.class);

    /**
     * Property name
     */
    private String property;

    /**
     * Constructor
     * 
     * @param property
     *            Property of the object to be compare of.
     */
    public PropertyComparator(String property) {
        this.property = property;
    }

    /**
     * Compares two objects
     * 
     * @param arg0
     *            First object to compare
     * @param arg1
     *            Second object to compare
     * @return Result of the comparison
     * @see Comparator#compare
     */
    @SuppressWarnings("unchecked")
	public int compare(Object arg0, Object arg1) {
        try {
            Object value0 = PropertyUtils.getProperty(arg0, property);
            Object value1 = PropertyUtils.getProperty(arg1, property);
            if (value0 instanceof Comparable) {
                return ((Comparable) value0).compareTo(value1);
            } else {
                String str0 = ObjectUtils.toString(value0, "");
                String str1 = ObjectUtils.toString(value1, "");
                return str0.compareTo(str1);
            }
        } catch (Exception ex) {
            log.error("Cannot compare " + arg0 + " and " + arg1, ex);
            return 0;
        }
    }
}
