/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.service.support;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JLabel;

import net.sf.doolin.gui.service.MnemonicInfo;

public class DefaultMnemonicInfo implements MnemonicInfo {

	private char mnemonic = '\0';

	private int mnemonicIndex = -1;

	private String text;

	public DefaultMnemonicInfo(String name) {
		int pos = getMnemonicIndex(name);
		if (pos >= 0) {
			mnemonic = name.charAt(pos + 1);
			text = name.substring(0, pos) + name.substring(pos + 1);
			mnemonicIndex = pos;
		} else {
			text = name;
		}
	}

	/**
	 * Returns the position of a valid &amp; character.
	 */
	protected int getMnemonicIndex(String name) {
		int pos = name.indexOf('&');
		if (pos >= 0 && pos < name.length() - 1) {
			char m = name.charAt(pos + 1);
			if (Character.isWhitespace(m)) {
				throw new IllegalArgumentException("Mnemonic cannot be a space: " + name);
			} else {
				return pos;
			}
		}
		// Not found
		return -1;
	}

	public void configureButton(AbstractButton button) {
		button.setText(text);
		if (mnemonic != 0) {
			button.setMnemonic(mnemonic);
			if (mnemonicIndex >= 0) {
				button.setDisplayedMnemonicIndex(mnemonicIndex);
			}
		}
	}

	public char getMnemonic() {
		return mnemonic;
	}

	public int getMnemonicIndex() {
		return mnemonicIndex;
	}

	public String getText() {
		return text;
	}

	public void configureAction(Action action) {
		action.putValue(Action.NAME, text);
		if (mnemonic != 0) {
			action.putValue(Action.MNEMONIC_KEY, new Integer(mnemonic));
			if (mnemonicIndex >= 0) {
				action.putValue(Action.DISPLAYED_MNEMONIC_INDEX_KEY, mnemonicIndex);
			}
		}
	}

	public void configureLabel(JLabel label) {
		label.setText(text);
		if (mnemonic != 0 && mnemonicIndex >= 0) {
			label.setDisplayedMnemonicIndex(mnemonicIndex);
		}
	}

}
