/*
 * Created on Oct 9, 2006
 */
package orbe.model.style;

public class DefaultHexSymbol implements HexSymbol {

	private int id;

	private String name;

	private String imageId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("(").append(id).append(",").append(name).append(")").toString();
	}

}
