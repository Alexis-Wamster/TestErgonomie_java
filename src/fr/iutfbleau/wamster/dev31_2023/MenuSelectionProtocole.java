package fr.iutfbleau.wamster.dev31_2023;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/** 
 * <code>MenuSelectionProtocole</code> est une vue qui contient tous les composants graphiques de la page de selection de protocole
 * @version 1.1
 * @author Djabrail KHAPIZOV et Alexis WAMSTER
*/
public class MenuSelectionProtocole{

    private JPanel contenu;
    private GestionnairePage gestionnairePage;

    /** 
     * Le constructeur creer la composition de la page
     * @param typeProgramme type de programme executer (testeur ou developpeur)
     * @param gestionnairePage le controlleur qui permet de changer les pages de la fenetre
    */
    public MenuSelectionProtocole(CProgramme typeProgramme, GestionnairePage gestionnairePage){
        this.contenu = new JPanel(new BorderLayout());
        this.gestionnairePage = gestionnairePage;

        JPanel titre = new JPanel();
        JPanel corps = new JPanel(new GridLayout(2, 1));
        JPanel input = new JPanel();
        JPanel output = new JPanel();
        corps.add(input);
        corps.add(output);
        this.contenu.add(titre, BorderLayout.NORTH);
        this.contenu.add(corps, BorderLayout.CENTER);

        String utilisateur;
        if (typeProgramme == CProgramme.TEST){
            utilisateur = "Testeur";
        }
        else{
            utilisateur = "Developpeur";
        }
        JLabel msgBienvenu = new JLabel("Bienvenue cher " + utilisateur);
        Font fontTitre = new Font("Arial", Font.BOLD, 24);
        msgBienvenu.setFont(fontTitre);
        titre.add(msgBienvenu);

        JLabel consigne = new JLabel("Selectionnez un protocole :");
        input.add(consigne);

        JTextField champProtocole = new JTextField();
        champProtocole.setPreferredSize(new Dimension(300,20));
        input.add(champProtocole);

        JLabel messageErreur = new JLabel("");
        messageErreur.setForeground(Color.RED);
        output.add(messageErreur);

        MenuSelectionProtocoleAction validation = new MenuSelectionProtocoleAction(messageErreur, typeProgramme, this.gestionnairePage);
        champProtocole.addActionListener(validation);
    }
    /** 
     * Permet de recuperer le conteneur de la page
     * @return conteneur de la page
    */
    public JPanel getContenu(){
        return this.contenu;
    }
}