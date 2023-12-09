package fr.iutfbleau.wamster.dev31_2023;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/** 
 * <code>MenuResultatAction</code> est un controlleur  qui effectue des actions lorsqu'on entre un protocole
 * @version 1.1
 * @author Djabrail KHAPIZOV et Alexis WAMSTER
*/
public class MenuSelectionProtocoleAction implements ActionListener {

    private JLabel messageErreur;
    private CProgramme typeProgramme;
    private GestionnairePage gestionnairePage;

    /** 
     * Ce constructeur stocke les informations necessaire a son fonctionement
     * @param messageErreur la zone de texte ou va s'afficher un message d'erreur en cas d'erreur
     * @param typeProgramme le type de programme lancer (testeur ou developpeur)
     * @param gestionnairePage le controlleur qui permet de changer de page
    */
    public MenuSelectionProtocoleAction(JLabel messageErreur, CProgramme typeProgramme, GestionnairePage gestionnairePage){
        this.messageErreur = messageErreur;
        this.typeProgramme = typeProgramme;
        this.gestionnairePage = gestionnairePage;
    }
    /** 
     * Permet de changer de page ou afficher un message d'erreur dans la fenetre lorsque l'utilisateur entre un protocole
     * @param evenement information sur l'evenement
    */
    public void actionPerformed(ActionEvent evenement){
        String protocole = evenement.getActionCommand();
        try{
            BaseDonee infoProtocole = new BaseDonee();
            if (this.typeProgramme == CProgramme.TEST){
                Protocole dataProtocole = infoProtocole.getMenu(protocole);
                if (dataProtocole == null){
                    this.messageErreur.setText("Le protocole \"" + protocole + "\" n'existe pas");
                }
                else{
                    this.gestionnairePage.addMenuTeste(new MenuTeste(dataProtocole, this.gestionnairePage));
                    this.gestionnairePage.setPage(CMenu.MENU_TESTE);
                }
            }
            else{
                Protocole dataProtocole = infoProtocole.getTest(protocole);
                if (dataProtocole == null){
                    this.messageErreur.setText("Le protocole \"" + protocole + "\" n'existe pas");
                }
                else{
                    this.gestionnairePage.addMenuResultat(new MenuResultat(dataProtocole, this.gestionnairePage));
                    this.gestionnairePage.setPage(CMenu.RESULTAT);
                }
            }
        }
        catch(RuntimeException e){
            this.messageErreur.setText("Erreur : " + e.getMessage());
        }
        catch(SQLException e){
            this.messageErreur.setText("Erreur : " + e.getMessage());
        }
    }
}