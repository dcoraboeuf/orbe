/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.support;

import java.util.ArrayList;
import java.util.List;

import net.sf.doolin.util.Strings;

import org.springframework.beans.factory.InitializingBean;

/**
 * Registration of strings bundles.
 * 
 * @author Damien Coraboeuf
 * @version $Id: StringBundles.java,v 1.1 2007/07/18 17:51:01 guinnessman Exp $
 */
public class StringBundles implements InitializingBean {

	private List<String> bundleList = new ArrayList<String>();

	public List<String> getBundleList() {
		return bundleList;
	}

	public void setBundleList(List<String> pathList) {
		this.bundleList = pathList;
	}

	public void afterPropertiesSet() throws Exception {
		for (String path : bundleList) {
			Strings.add(path);
		}
	}

}
