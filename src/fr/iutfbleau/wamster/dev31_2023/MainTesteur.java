package fr.iutfbleau.wamster.dev31_2023;

/** 
 * <code>MainTesteur</code> controlleur principal qui lance l'execution du programme du testeur
 * @version 1.1
 * @author Djabrail KHAPIZOV et Alexis WAMSTER
*/

public class MainTesteur{
	/** 
 	* Lancement du programme en affichant une fenetre sur un menu ou il faut selectionner un protocole
	*/
    public static void main(String[] args){
		CProgramme typeProgramme = CProgramme.TEST;
		Fenetre fenetre = new Fenetre(typeProgramme);
		fenetre.setPage(CMenu.SELECTION_PROTOCOLE);
		fenetre.setVisible(true);
    }
}
