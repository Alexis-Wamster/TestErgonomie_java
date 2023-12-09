package fr.iutfbleau.wamster.dev31_2023;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** 
 * <code>MenuResultat</code> est une vue qui contient tous les composants graphiques de la page resultat (camembert, boutons, textes)
 * @version 1.1
 * @author Djabrail KHAPIZOV et Alexis WAMSTER
*/
public class MenuResultat{

    private JPanel contenu;
    private GestionnairePage gestionnairePage;
    private String[] listeTitre;
    private PieChartPanel[] listeCamembert;
    private String[] listeObjectif;
    private JLabel labelTitre;
    private JLabel labelObjectif;
    private int nbCamembert = 2;
    private int numCamembert;


    /** 
     * Le constructeur creer des camembert et la composition de la page
     * @param dataProtocole des information sur le protocole (description, liste des tests, objectif)
     * @param gestionnairePage le controlleur qui permet de changer les pages de la fenetre
    */
    public MenuResultat(Protocole dataProtocole, GestionnairePage gestionnairePage){
        this.contenu = new JPanel(new BorderLayout());
        this.gestionnairePage = gestionnairePage;
        this.labelTitre = new JLabel("", JLabel.CENTER);
        this.labelObjectif = new JLabel("");
        this.numCamembert = 0;

        MakeDataCammenbert calculCammenbert = new MakeDataCammenbert(dataProtocole.getTest(), dataProtocole.getObjectif());
        this.listeTitre = new String[this.nbCamembert];
        this.listeObjectif = new String[this.nbCamembert];
        this.listeCamembert = new PieChartPanel[this.nbCamembert];

        ArrayList<String> dataDerniereOption = calculCammenbert.getDataDerniereOption();
        ArrayList<Integer> effectifDerniereOption = calculCammenbert.getEffectifDerniereOption();
        int indiceOptionAttendue = calculCammenbert.getBestDerniereOption();
        this.listeCamembert[0] = new PieChartPanel(dataDerniereOption, effectifDerniereOption, indiceOptionAttendue);
        this.listeTitre[0] = "Repartition des actions choisis par les testeurs";
        try{
            this.listeObjectif[0] = "Reponse attendue : " + dataDerniereOption.get(indiceOptionAttendue);
        }
        catch(IndexOutOfBoundsException e){
            this.listeObjectif[0] = "";
        }
        
        ArrayList<String> dataNbMenuVisite = calculCammenbert.getDataNbMenuVisite();
        ArrayList<Integer> effectifNbMenuVisite = calculCammenbert.getEffectifNbMenuVisite();
        int bestNbMenuVisite = calculCammenbert.bestNbMenuVisite();
        this.listeCamembert[1] = new PieChartPanel(dataNbMenuVisite, effectifNbMenuVisite, bestNbMenuVisite);
        this.listeTitre[1] = "Repartition du nombre de sous menus deployer avant de choisi une action";
        try{
            this.listeObjectif[1] = "Meilleur score : " + dataNbMenuVisite.get(bestNbMenuVisite);
        }
        catch(IndexOutOfBoundsException e){
            this.listeObjectif[0] = "";
        }

        this.labelTitre.setText(this.listeTitre[this.numCamembert]);
        this.labelObjectif.setText(this.listeObjectif[this.numCamembert]);
        JPanel enTete = new JPanel(new BorderLayout());
        JButton suivant = new JButton("Suivant");
        JButton accueil = new JButton("Accueil");
        JPanel texte = new JPanel(new GridLayout(3,1));
        texte.add(this.labelTitre);
        texte.add(new JLabel("Consigne : " + dataProtocole.getDescription()));
        texte.add(this.labelObjectif);
        enTete.add(accueil, BorderLayout.WEST);
        enTete.add(suivant, BorderLayout.EAST);
        enTete.add(texte, BorderLayout.CENTER);

        this.contenu.add(enTete, BorderLayout.NORTH);
        this.contenu.add(this.listeCamembert[this.numCamembert], BorderLayout.CENTER);

        MenuResultatAction action = new MenuResultatAction(this, this.gestionnairePage);
        suivant.addActionListener(action);
        accueil.addActionListener(action);
    }

    /** 
     * Cette méthode affiche remplace le camembert et son titre par le suivant
    */
    public void suivant(){
        this.contenu.remove(this.listeCamembert[this.numCamembert]);
        this.numCamembert = (this.numCamembert+1)%this.nbCamembert;
        this.labelTitre.setText(this.listeTitre[this.numCamembert]);
        this.labelObjectif.setText(this.listeObjectif[this.numCamembert]);
        this.contenu.add(this.listeCamembert[this.numCamembert], BorderLayout.CENTER);
        //this.contenu.revalidate();
        this.contenu.repaint();

    }

    /** 
     * Permet de recuperer le conteneur de la page
     * @return conteneur de la page
    */
    public JPanel getContenu(){
        return this.contenu;
    }

}