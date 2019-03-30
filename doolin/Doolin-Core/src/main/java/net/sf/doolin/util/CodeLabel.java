/*
 * Created on 9 mars 2005
 */
package net.sf.doolin.util;

import org.apache.commons.lang.StringUtils;

/**
 * Code and label
 * 
 * @author damien
 * @version $Id: CodeLabel.java,v 1.1 2007/07/18 18:41:20 guinnessman Exp $
 */
public class CodeLabel {
    private String code;

    private String label;

    /**
     * Constructor
     */
    public CodeLabel() {
    }

    /**
     * Code label constructor
     * 
     * @param aCode
     *            Code
     * @param aLabel
     *            Label
     */
    public CodeLabel(String aCode, String aLabel) {
        code = aCode;
        label = aLabel;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            The code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return Returns the label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label
     *            The label to set.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return label;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return code.hashCode();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object arg0) {
        if (arg0 instanceof CodeLabel) {
            CodeLabel codeLabel = (CodeLabel) arg0;
            return StringUtils.equals(this.code, codeLabel.code);
        } else {
            return false;
        }
    }
}
