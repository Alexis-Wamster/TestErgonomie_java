package fr.iutfbleau.wamster.dev31_2023;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

/** 
 * <code>MenuResultatAction</code> est un controlleur  qui enregistre les actions de l'utilisateur (les clics)
 * @version 1.1
 * @author Djabrail KHAPIZOV et Alexis WAMSTER
*/
class MenuTesteAction implements MouseListener, TreeWillExpandListener{

    private String reference;
    private ArrayList<Integer> historiqueClic;
    private JPanel conteneurPage;
    private GestionnairePage gestionnairePage;
    private JTree tree;

    /** 
     * Ce constructeur stocke les informations necessaire a son fonctionement et fabrique un historique de clic
     * @param tree l'arborescence du menu
     * @param reference la reference du test
     * @param conteneurPage la vue
     * @param gestionnairePage le controlleur qui permet de changer de page
    */
    public MenuTesteAction(JTree tree, String reference, JPanel conteneurPage, GestionnairePage gestionnairePage){
        this.reference = reference;
        this.historiqueClic = new ArrayList<>();
        this.conteneurPage = conteneurPage;
        this.gestionnairePage = gestionnairePage;
        this.tree = tree;
    }

    /** 
     * Ajoute à l'historique des clics l'id du sous menus qui vient d'etre deployee
     * @param e information sur l'evenement
    */
    @Override
    public void treeWillExpand(TreeExpansionEvent e) throws ExpandVetoException {
        DefaultMutableTreeNode optionVisitee = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
        this.addToHistory(optionVisitee);
    }
    /** 
     * Ajoute à l'historique des clics l'id du sous menus qui vient d'etre reployee
     * @param e information sur l'evenement
    */
    @Override
    public void treeWillCollapse(TreeExpansionEvent e) throws ExpandVetoException {
    }
    /** 
     * Ajoute à l'historique des clics l'id d'une option
     * @param optionSelectionnee option a ajouter
    */
    private void addToHistory(DefaultMutableTreeNode optionSelectionnee){
        if (optionSelectionnee != null) {
            Option option = (Option) optionSelectionnee.getUserObject();
            int idOption = option.getidOption();
            this.historiqueClic.add(idOption);
        }
    }

    /** 
     * Ajoute à l'historique des clics l'action qui a ete selectionner, demmande au testeur si il confirme l'option, si oui enregistre tout les clic dans une base de donnee et renvoie sur la page d'accueil.
     * @param e information sur l'evenement
    */
    @Override
    public void mouseClicked(MouseEvent e) {
        int ligne = tree.getRowForLocation(e.getX(), e.getY());
        TreePath chemin = tree.getPathForLocation(e.getX(), e.getY());
        if (ligne != -1 && chemin != null) {
            DefaultMutableTreeNode optionSelectionnee = (DefaultMutableTreeNode) chemin.getLastPathComponent();
            if (optionSelectionnee.isLeaf()) {
                Option option = (Option) optionSelectionnee.getUserObject();
                int idOption = option.getidOption();
                String texteOption = option.getTexteOption();
                this.historiqueClic.add(idOption);

                int fermeture = JOptionPane.showConfirmDialog(this.conteneurPage,
                "Pensez vous rellement que l'option demmadee est :\n\"" + texteOption + "\"");
		        if (fermeture == JOptionPane.YES_OPTION){
                    this.enregistrement();
		        }
            }
        }
    }

    /** 
     * Enregistre tout les clic dans une base de donnee et renvoie sur la page d'accueil, en cas d'erreur, une fenetre popup s'affiche indiquant que l'enregistrement n'a pas pu etre effectue.
    */
    private void enregistrement(){
        try{
            BaseDonee baseDonee = new BaseDonee();
            int idTest = baseDonee.createTest(this.reference);
            if (idTest >= 0){
                baseDonee.insertionClic(this.historiqueClic, idTest);
            }
            baseDonee.close();
            this.gestionnairePage.setPage(CMenu.SELECTION_PROTOCOLE);
            JOptionPane.showMessageDialog(null, "Merci d'avoir participe", "Merci", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException erreur){
            JOptionPane.showMessageDialog(null, erreur.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** 
     * Ne fait rien
     * @param e information sur l'evenement
    */
    @Override
    public void mouseEntered(MouseEvent e){
    }
    /** 
     * Ne fait rien
     * @param e information sur l'evenement
    */
    @Override
    public void mouseExited(MouseEvent e){
    }
    /** 
     * Ne fait rien
     * @param e information sur l'evenement
    */
    @Override
    public void mousePressed(MouseEvent e){
    }
    /** 
     * Ne fait rien
     * @param e information sur l'evenement
    */
    @Override
    public void mouseReleased(MouseEvent e){
    }

}