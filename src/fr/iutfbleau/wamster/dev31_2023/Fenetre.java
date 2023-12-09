package fr.iutfbleau.wamster.dev31_2023;
import javax.swing.*;
import java.awt.*;
/** 
 * <code>Fenetre</code> stocke les composants graphique à la maniere de JFrame
 * @version 1.1
 * @author Djabrail KHAPIZOV et Alexis WAMSTER
*/
public class Fenetre extends JFrame{
	private JFrame fenetre;
	private JPanel contenu;
	private GestionnairePage gestionnairePage;

	/**constructeur d'une fenetre.
	 * @param typeProgramme constante qui represente le main executer par l'utilisateur
	*/
    public Fenetre(CProgramme typeProgramme){
		this.gestionnairePage = new GestionnairePage(this, typeProgramme);

		this.fenetre = new JFrame();
		this.fenetre.setSize(700, 300);
		this.fenetre.setLocation(0, 0);
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.contenu = new JPanel();
		fenetre.add(contenu);

    }
	/**permet de rendre la fenetre visible ou non
	 * @param isVisible true si on veut que la fenetre soit visible, sinon false
	*/
	public void setVisible(boolean isVisible){
		fenetre.setVisible(isVisible);
	}
	/**change le contenus de la fenetre
	 * @param menu constante qui represente la page a afficher
	*/
	public void setPage(CMenu menu){
		this.gestionnairePage.setPage(menu);
	}
	/**change le contenus de la fenetre
	 * @param newContenu composant qui prendras tout l'espace de la fenetre
	*/
	public void setContenu(JPanel newContenu){
		this.fenetre.remove(this.contenu);
		this.fenetre.add(newContenu);
		this.fenetre.revalidate();
		this.fenetre.repaint();
		this.contenu = newContenu;
	}
	/**renvois la fenetre JFrame
	 * @return La fenetre
	*/
	public JFrame getFenetre(){
		return this.fenetre;
	}
	/**renvois le gestionnaire de page de la fenetre
	 * @return Le gestionnaire de page
	*/
	public GestionnairePage getGestionnairePage(){
		return this.gestionnairePage;
	}
}
