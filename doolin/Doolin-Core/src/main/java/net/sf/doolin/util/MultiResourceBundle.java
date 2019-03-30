/*
 * Created on 10 avr. 2005
 */
package net.sf.doolin.util;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Resource bundle that scans several other resource bundles
 * 
 * @author damien
 * @version $Id: MultiResourceBundle.java,v 1.1 2007/07/18 18:41:21 guinnessman Exp $
 */
public class MultiResourceBundle extends ResourceBundle {
    /**
     * Log
     */
    private final static Log log = LogFactory.getLog(MultiResourceBundle.class);

    /**
     * List of resource bundles
     */
    private ArrayList<ResourceBundle> bundles = new ArrayList<ResourceBundle>();

    /**
     * Locale
     */
    private Locale locale;

    /**
     * Constructor
     * 
     * @param l
     *            Locale used for the bundle
     * @param paths
     *            List of paths for the different bundles
     */
    public MultiResourceBundle(Locale l, Collection<String> paths) {
        locale = l;
        for (String path : paths) {
            ResourceBundle bundle = ResourceBundle.getBundle(path, l);
            log.debug("Adding path " + path + " for locale " + path);
            bundles.add(bundle);
        }
    }

    /**
     * @see java.util.ResourceBundle#getKeys()
     */
    public Enumeration<String> getKeys() {
        Vector<String> keys = new Vector<String>();
        for (Iterator i = bundles.iterator(); i.hasNext();) {
            ResourceBundle bundle = (ResourceBundle) i.next();
            Enumeration<String> e = bundle.getKeys();
            while (e.hasMoreElements()) {
                keys.add(e.nextElement());
            }
        }
        return keys.elements();
    }

    /**
     * @see java.util.ResourceBundle#handleGetObject(String)
     */
    protected Object handleGetObject(String key) {
        for (Iterator i = bundles.iterator(); i.hasNext();) {
            ResourceBundle bundle = (ResourceBundle) i.next();
            try {
                Object value = bundle.getObject(key);
                if (value != null) {
                    return value;
                }
            } catch (MissingResourceException ex) {
            }
        }
        return null;
    }

    /**
     * @return Returns the locale.
     */
    public Locale getLocale() {
        return locale;
    }

}
