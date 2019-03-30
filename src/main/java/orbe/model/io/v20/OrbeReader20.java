/*
 * Created on 3 oct. 06
 */
package orbe.model.io.v20;

import java.io.File;

import net.sf.doolin.oxml.OXMLReader;
import net.sf.doolin.oxml.OXMLSource;
import net.sf.doolin.oxml.source.FileOXMLSource;
import net.sf.doolin.util.CodeException;

import orbe.model.IErrors;
import orbe.model.OrbeMap;
import orbe.model.io.AbstractOrbeReader;

public class OrbeReader20<T extends OrbeMap> extends AbstractOrbeReader<T> {

	public T read(Class<T> mapClass, File file) {
		try {
			OXMLReader<T> reader = new OXMLReader<T>(new OrbeOXMLConfig());
			OXMLSource source = new FileOXMLSource(file);
			T map = reader.read(source);
			return map;
		} catch (Exception ex) {
			throw new CodeException(IErrors.IO_CANNOT_READ, ex, file, ex);
		}
	}

}
