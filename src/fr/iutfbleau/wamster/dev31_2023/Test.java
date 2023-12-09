package fr.iutfbleau.wamster.dev31_2023;
import java.util.*;
/** 
 * <code>Test</code> permet de stocker des informations sur un test (liste des clics)
 * @version 1.1
 * @author Djabrail KHAPIZOV et Alexis WAMSTER
*/
public class Test {

    private int idTest;
    private ArrayList<Option> listeClic;

    /** 
     * Ce constructeur creer un nouveau test en creant une liste de clic vide et un identifiant de test
     * @param idTest identifiant de test
    */
    public Test(int idTest) {
        this.idTest = idTest;
        this.listeClic = new ArrayList<>();
    }
    /** 
     * Ajoute une option cliquee a la liste de clic a un indice donne
     * @param numClic indice
     * @param option option
    */
    public void addClic(int numClic, Option option) {
        while (this.listeClic.size() < numClic+1){
            this.listeClic.add(null);
        }
        this.listeClic.set(numClic, option);
    }
    /** 
     * Recupere le nombre des differents sous menus visites dans la liste des clics 
     * @return le nombre de sous menus differents visites
     * @return -1 si la liste est vide
    */
    public int getNbSousmenuVisitee() {
        int nbClic = this.listeClic.size();
        if (nbClic > 0){
            int sousmenuVisite = 0;
            ArrayList<Option> sousMenuDifferent = new ArrayList<>();
            int numClic;
            for (numClic=0; numClic < nbClic; numClic++){
                Option currentOption = this.listeClic.get(numClic);
                if (currentOption.isAction() == false && sousMenuDifferent.contains(currentOption) == false){
                    sousMenuDifferent.add(currentOption);
                    sousmenuVisite ++;
                }
            }
            return sousmenuVisite;
        }
        return -1;
    }
    /** 
     * Recupere la derniere option de la liste des clics 
     * @return derniere option
     * @return null si la liste est vide
    */
    public Option getDerniereOption() {
        int taille = this.listeClic.size();
        if (taille > 0){
            return this.listeClic.get(taille-1);
        }
        return null;
    }
}