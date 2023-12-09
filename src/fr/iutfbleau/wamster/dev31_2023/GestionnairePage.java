package fr.iutfbleau.wamster.dev31_2023;
import javax.swing.*;
import java.awt.*;
/**
* <code>GestionnairePage</code> est un controlleur qui va changer la vue
*/
public class GestionnairePage{

    private MenuSelectionProtocole selectionProtocole;
    private Fenetre fenetre;
    private MenuTeste menuTeste;
    private MenuResultat menuResultat;

    /**
     * Constructeur 
     * @param fenetre fenetre utiliser pour l'application
     * @param typeProgramme Type de programme executer (testeur ou developpeur)
     */
    public GestionnairePage(Fenetre fenetre, CProgramme typeProgramme){
        this.selectionProtocole = new MenuSelectionProtocole(typeProgramme, this);
        this.fenetre = fenetre;
    }
    /**
     * Stocke une page affichant des menus dans les attributs de l'objet
     * @param menuTeste page affichant le menu a tester
     */
    public void addMenuTeste(MenuTeste menuTeste){
        this.menuTeste = menuTeste;
    }
    /**
     * Stocke une page affichant les resultats des tests
     * @param menuResultat page affichant les resultats des tests
     */
    public void addMenuResultat(MenuResultat menuResultat){
        this.menuResultat = menuResultat;
    }
    /**
     * change l'affichage de la fenetre en affichant une page specifique
     * @param menu page a afficher dans la fenetre
     */
    public void setPage(CMenu menu){
        if (menu == CMenu.SELECTION_PROTOCOLE){
            this.fenetre.setContenu(this.selectionProtocole.getContenu());
        }
        if (menu == CMenu.MENU_TESTE){
            this.fenetre.setContenu(this.menuTeste.getContenu());
        }
        if (menu == CMenu.RESULTAT){
            this.fenetre.setContenu(this.menuResultat.getContenu());
        }        
    }
}