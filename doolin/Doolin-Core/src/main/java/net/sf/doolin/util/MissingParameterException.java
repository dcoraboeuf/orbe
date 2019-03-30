/*
 * Created on 13 oct. 2004
 */
package net.sf.doolin.util;

/**
 * Thrown when a parameter is missing. This exception is used by
 * {@link net.sf.doolin.util.ParameterSet}.
 * 
 * @version $Id: MissingParameterException.java,v 1.1 2007/07/18 18:41:21 guinnessman Exp $
 * @author Damien Coraboeuf
 */
public class MissingParameterException extends RuntimeException {
    /**
     * Parameter name
     */
    private String name;

    /**
     * Constructor
     * 
     * @param name
     *            Parameter name
     */
    public MissingParameterException(String name) {
        super("Missing parameter " + name);
        this.name = name;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
}
