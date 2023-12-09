package fr.iutfbleau.wamster.dev31_2023;
import javax.swing.*;
import java.util.*;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/** 
 * <code>Arbre</code> permet de creer un JTree
 * @version 1.1
 * @author Djabrail KHAPIZOV et Alexis WAMSTER
*/
public class Arbre{

    private int fils;
    private int pere;

    private int ancienneGeneration;
    private ArrayList<DefaultMutableTreeNode> dernierPere;

    /** 
    * Ce constructeur met à la racine de l'arbre un element
    * @param element L'objet que l'on veut mettre à la racine de l'arbre
    */
    public <T> Arbre( T element){
        this.dernierPere = new ArrayList<>();
        this.dernierPere.add(new DefaultMutableTreeNode(element));
        this.ancienneGeneration = 0;
        this.fils = 1;
        this.pere = 0;
    }
    /** 
    * Permet d'ajouter à une branche de l'arbre un objet
    * Il ne se passe rien en cas de mauvais argument
    * @param element L'objet que l'on veut ajouter à l'arbre
    * @param generationActuelle entier supérieur à 0 qui correspond à la génération de l'objet par rapport à la racine
    */
    public <T> void addBranche(T element, int generationActuelle){
        if (generationActuelle > 0){
            DefaultMutableTreeNode noeudElement = new DefaultMutableTreeNode(element);
            if (this.ancienneGeneration >= generationActuelle){
                int i;
                for (i=generationActuelle; i<this.dernierPere.size();){
                    this.dernierPere.remove(i);
                }
            }
            this.dernierPere.get(this.dernierPere.size()-1).add(noeudElement);
            this.dernierPere.add(noeudElement);
            this.fils = generationActuelle+1;
            this.pere = generationActuelle-1;
            this.ancienneGeneration = generationActuelle;
        }
	}
    /** 
    * Obtention de la generation du fils de la derniere branche ajoutee
    * @return Generation du fils de la derniere branche ajoutee
    */
    public int getGenerationFils(){
        return this.fils;
    }
    /** 
    * Obtention de la generation du pere de la derniere branche ajoutee
    * @return Generation du pere de la derniere branche ajoutee
    */
    public int getGenerationPere(){
        return this.pere;
    }
    /** 
    * Obtention de la generation de la derniere branche ajoutee
    * @return Generation de la derniere branche ajoutee
    */
    public int getGenerationFrere(){
        return this.ancienneGeneration;
    }
    /** 
    * Obtention du JTree creer
    * @return JTree
    */
    public JTree getArbre(){
        return new JTree(new DefaultTreeModel(this.dernierPere.get(0)));
    }
}