package net.sf.doolin.util;

/**
 * Object that can be locked
 * @version $Revision: 1.1 $
 * @author Damien Coraboeuf
 */
public abstract class Lockable
{
	/**
	 * State
	 */
	private boolean locked = false;
	/**
	 * @return Returns the locked state.
	 */
	public boolean isLocked ()
	{
		return locked;
	}
	/**
	 * Locks the object
	 */
	public void lock ()
	{
		locked = true;
	}
	/**
	 * Checks the lock status
	 * @throws IllegalStateException If the object is locked
	 */
	protected void checkLocked () throws IllegalStateException
	{
		if (locked)
		{
			throw new IllegalStateException ("This object is locked");
		}
	}
}

