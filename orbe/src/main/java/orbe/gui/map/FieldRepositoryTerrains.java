/*
 * Created on Nov 13, 2006
 */
package orbe.gui.map;

import java.awt.Color;
import java.util.List;

import javax.swing.ListCellRenderer;

import org.apache.commons.lang.StringUtils;

import orbe.model.style.DefaultHexTerrain;
import orbe.model.style.HexTerrain;
import orbe.model.style.RepositoryHexTerrain;

/**
 * Edition d'une liste de terrains.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FormFieldRepositoryTerrains.java,v 1.1 2006/11/13 15:59:24
 *          guinnessman Exp $
 */
public class FieldRepositoryTerrains extends AbstractFormFieldMasterDetail<HexTerrain, RepositoryHexTerrain> {

	@Override
	protected HexTerrain createEmptyElement() {
		DefaultHexTerrain emptyTerrain = new DefaultHexTerrain();
		emptyTerrain.setId(0);
		emptyTerrain.setColor(Color.gray);
		emptyTerrain.setSymbol(null);
		emptyTerrain.setName("");
		return emptyTerrain;
	}

	@Override
	protected HexTerrain modelCreate(RepositoryHexTerrain repository) {
		return repository.create();
	}

	@Override
	protected void modelDelete(RepositoryHexTerrain repository, HexTerrain e) {
		repository.removeTerrain(e.getId());
	}

	@Override
	protected boolean modelIsInvalid(RepositoryHexTerrain repository, HexTerrain e) {
		return StringUtils.isBlank(e.getName());
	}

	@Override
	protected boolean modelIsProtected(RepositoryHexTerrain repository, HexTerrain e) {
		return (e.getId() == RepositoryHexTerrain.DEFAUL_TERRAIN_ID);
	}

	@Override
	protected List<HexTerrain> modelList(RepositoryHexTerrain repository) {
		return repository.getTerrains();
	}

	@Override
	protected ListCellRenderer getCellRenderer() {
		return new CellRendererTerrain();
	}

}
