package net.sf.doolin.util.copy;

import junit.framework.TestCase;

/**
 * Unit test for the <code>{@link CopyService}</code> class.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 * 
 */
public class TestCopy extends TestCase {

	public void testCopyDAO2TO() {
		CDAOPerson daoPerson = new CDAOPerson();
		daoPerson.setName("Damien");

		CDAOAccount daoAccount = new CDAOAccount();
		daoAccount.setLogon("damien");
		daoAccount.setPerson(daoPerson);

		CopyService service = new CopyService();
		service.registerCopier(CDAOAccount.class, CTestTOAccount.class, "person", new NewClassPropertyCopier(CTestTOPerson.class));

		CTestTOAccount toAccount = new CTestTOAccount();
		service.copy(daoAccount, toAccount);

		assertEquals("damien", toAccount.getLogon());
		assertNotNull(toAccount.getPerson());
		assertEquals("Damien", toAccount.getPerson().getName());
	}

}
