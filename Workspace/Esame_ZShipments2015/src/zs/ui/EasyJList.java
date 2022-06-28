package zs.ui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 * Semplifica l'uso di JList. Per la visualizzazione degli elementi, utilizza
 * il metodo toString.
 * 
 * @author gzann
 *
 * @param <E> Il tipo di elemento
 */
public class EasyJList<E> extends JList<E> {
	
	private static final long serialVersionUID = 1L;
	
	private DefaultListModel<E> listModel = new DefaultListModel<>();
	
/**
 * Costruisce una nuova EasyJList abilitandola alla selezione singola.
 */
	public EasyJList() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setModel(listModel);
	}
	
	/**
	 * Aggiunge un elemento.
	 * @param element L'elemendo da aggiungere
	 */
	public void addElement(E element) {
		listModel.addElement(element);
	}
	
	/**
	 * Rimuove un element
	 * @param element L'elemento da rimuovere
	 */
	public void removeElement(E element) {
		listModel.removeElement(element);
	}
	
	/**
	 * Elimina tutti gli elementi
	 */
	public void clearElements() {
		listModel.clear();
	}
}
