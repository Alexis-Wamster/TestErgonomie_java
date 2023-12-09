package fr.iutfbleau.wamster.dev31_2023;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/** 
 * <code>MenuResultatAction</code> est un controlleur  qui effectue des actions lorsqu'on clique sur des boutons
 * @version 1.1
 * @author Djabrail KHAPIZOV et Alexis WAMSTER
*/

public class MenuResultatAction implements ActionListener{
    private MenuResultat menu;
    private GestionnairePage gestionnairePage;

    /** 
     * Ce constructeur stocke les informations necessaire a son fonctionement
     * @param menu la vue resultat
     * @param gestionnairePage le controlleur qui permet de changer de page
    */
    public MenuResultatAction(MenuResultat menu, GestionnairePage gestionnairePage){
	    this.menu = menu;
        this.gestionnairePage = gestionnairePage;
    }
    /** 
     * Permet de passer au camembert suivant ou revenir a la page d'accueil en fonction du bouton selectionner
     * @param evenement information sur l'evenement
    */
    @Override
    public void actionPerformed(ActionEvent evenement){
        String bouton = evenement.getActionCommand();
        if (bouton=="Suivant"){
            this.menu.suivant();
        }
        if (bouton=="Accueil"){
            this.gestionnairePage.setPage(CMenu.SELECTION_PROTOCOLE);
        }
    }
}