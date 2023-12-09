package fr.iutfbleau.wamster.dev31_2023;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** 
 * <code>MenuResultat</code> est une vue qui contient tous les composants graphiques de la page du menu a tester (le menu et la consigne)
 * @version 1.1
 * @author Djabrail KHAPIZOV et Alexis WAMSTER
*/
public class MenuTeste{

    private JPanel contenu;
    private GestionnairePage gestionnairePage;

    /** 
     * Le constructeur creer la composition de la page
     * @param dataProtocole des information sur le protocole (description, menu, reference, objectif)
     * @param gestionnairePage le controlleur qui permet de changer les pages de la fenetre
    */
    public MenuTeste(Protocole dataProtocole, GestionnairePage gestionnairePage){
        this.contenu = new JPanel(new BorderLayout());
        this.gestionnairePage = gestionnairePage;

        JTree arbreProtocole = dataProtocole.getMenu();
        String description = dataProtocole.getDescription();
        String reference = dataProtocole.getReference();
        int objectif = dataProtocole.getObjectif();

        JLabel consigne = new JLabel("<html>" + description + "</html>");
        Font fontTitre = new Font("Arial", Font.BOLD, 24);
        consigne.setFont(fontTitre);

        this.contenu.add(consigne, BorderLayout.NORTH);
        this.contenu.add(new JScrollPane(arbreProtocole));

        MenuTesteAction detecteClic = new MenuTesteAction(arbreProtocole, reference, this.contenu, this.gestionnairePage);
        arbreProtocole.addTreeWillExpandListener(detecteClic);
        arbreProtocole.addMouseListener(detecteClic);
    }
    /** 
     * Permet de recuperer le conteneur de la page
     * @return conteneur de la page
    */
    public JPanel getContenu(){
        return this.contenu;
    }
}