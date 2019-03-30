/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.service.support;

import net.sf.doolin.gui.service.MnemonicInfo;
import net.sf.doolin.gui.service.MnemonicInfoFactory;

public class DefaultMnemonicInfoFactory implements MnemonicInfoFactory {

	public MnemonicInfo getLabelInfo(String name) {
		return new DefaultMnemonicInfo(name);
	}

}
