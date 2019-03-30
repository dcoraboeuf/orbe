package net.sf.doolin.util;

import java.security.Principal;

/**
 * User with some authorizations
 * @author CORABOEUF
 * @version $Id: AuthorizedUser.java,v 1.1 2007/07/18 18:41:21 guinnessman Exp $
 */
public interface AuthorizedUser extends Principal
{

	/**
	 * Is the user in a role ?
	 * @param role Role to check
	 * @return <code>true</code> if the user is qualified for the role.
	 */
	boolean isInRole(String role);
}

