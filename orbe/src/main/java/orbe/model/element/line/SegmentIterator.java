/*
 * Created on Dec 4, 2006
 */
package orbe.model.element.line;

/**
 * Iterator for each segment.
 * 
 * @param
 * <P>
 * Type de point.
 * @author Damien Coraboeuf
 * @version $Id: SegmentIterator.java,v 1.1 2006/12/04 10:46:20 guinnessman Exp $
 */
public interface SegmentIterator<P> {

	boolean onSegment(P pa, P pb);

}
