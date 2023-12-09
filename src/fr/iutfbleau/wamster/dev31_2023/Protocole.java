package fr.iutfbleau.wamster.dev31_2023;
import javax.swing.*;
import java.util.*;

/** 
 * <code>Protocole</code> permet de stocker des informations sur un protocole (description, reference, menu, tests, objectif)
 * @version 1.1
 * @author Djabrail KHAPIZOV et Alexis WAMSTER
*/
public class Protocole {
    private int objectif;
    private String description;
    private String reference;
    private JTree menu;
    private ArrayList<Test> listeTest;

    /** 
     * Ce constructeur creer une liste de test vide
     * @param idOption identifiant de l'option
     * @param texteOption texte afficher par l'option
     * @param isAction true si c'est une action, false si c'est un sous menu
    */
    public Protocole() {
        this.listeTest = new ArrayList<>();
    }
    /** 
     * Ce constructeur remplis le protocole avec differentes informations
     * @param objectif l'objectif du test (l'id de l'option attendue)
     * @param description la description du test
     * @param menu l'arborescence du menu
     * @param reference la reference du protocole
    */
    public Protocole(int objectif, String description, JTree menu, String reference) {
        this.objectif = objectif;
        this.description = description;
        this.menu = menu;
        this.reference = reference;
        this.listeTest = new ArrayList<>();
    }
    /** 
     * modifie l'objectif
     * @param objectif l'objectif du test (l'id de l'option attendue)
    */
    public void addObjectif(int objectif) {
        this.objectif = objectif;
    }
    /** 
     * modifie la description
     * @param objectif la description du test
    */
    public void addDescription(String description) {
        this.description = description;
    }
    /** 
     * modifie la reference
     * @param objectif la reference du protocole
    */
    public void addReference(String reference) {
        this.reference = reference;
    }
    /** 
     * modifie le menu
     * @param objectif l'arborescence du menu
    */
    public void addMenu(JTree menu) {
        this.menu = menu;
    }
    /** 
     * ajoute un test a la liste des test a l'indice indique
     * @param numTest indice
     * @param test test
    */
    public void addTest(int numTest, Test test){
        while (this.listeTest.size() < numTest+1){
            this.listeTest.add(null);
        }
        this.listeTest.set(numTest, test);
    }
    /** 
     * Recupere l'objectif
     * @return objectif l'objectif du test (l'id de l'option attendue)
    */
    public int getObjectif() {
        return this.objectif;
    }
    /** 
     * Recupere la description
     * @return description
    */
    public String getDescription() {
        return this.description;
    }
    /** 
     * Recupere la reference
     * @return reference
    */
    public String getReference() {
        return this.reference;
    }
    /** 
     * Recupere l'arborescence du menu
     * @return l'arborescence du menu
    */
    public JTree getMenu() {
        return this.menu;
    }
    /** 
     * Recupere la liste des tests
     * @return la liste des tests
    */
    public ArrayList<Test> getTest() {
        return this.listeTest;
    }
}