/*
 * Created on Nov 8, 2006
 */
package orbe.gui.map.options;

import javax.swing.JComponent;

import orbe.gui.map.core.OrbeControler;

/**
 * Définition d'un composant pour une option d'outil. Ces composants sont
 * typiquement utilisés dans une palette d'options pour un outil.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ToolOption.java,v 1.2 2006/11/09 13:15:38 guinnessman Exp $
 */
public interface ToolOption {

	/**
	 * @return Titre de l'option
	 */
	String getLabel();

	/**
	 * @return Composant � utiliser pour l'affichage
	 */
	JComponent getComponent();

	/**
	 * Mise � jour du composant en fonction de l'environnement.
	 */
	void setup(OrbeControler controler);

}
