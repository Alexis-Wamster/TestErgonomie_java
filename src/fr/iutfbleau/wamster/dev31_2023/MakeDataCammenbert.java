package fr.iutfbleau.wamster.dev31_2023;
import java.util.*;
/** 
 * <code>MakeDataCammenbert</code> est un modele qui creer des tableaux valeurs et effectifs à partir de tous les tests effectues.
 * Cela permet de les exploiter dans des diagrammes
 * @version 1.1
 * @author Djabrail KHAPIZOV et Alexis WAMSTER
*/

public class MakeDataCammenbert{

    private ArrayList<Test> listeTest;

    private ArrayList<Integer> effectifDerniereOption;
    private ArrayList<Option> dataDerniereOption;
    private ArrayList<String> stringDataDerniereOption;
    private int bestDerniereOption;

    private ArrayList<Integer> effectifNbMenuVisite;
    private ArrayList<Integer> dataNbMenuVisite;
    private ArrayList<String> stringDataNbMenuVisite;
    private int bestNbMenuVisite;

    /** 
    * Ce constructeur remplis des tableaux valeurs et effectifs à partir d'une liste de tests.
    * Elle stocke également l'indice de la meilleur valeur / de la valeur attendue.
    * @param listeTest Liste des tests d'un protocole
    * @param objectif id de l'option qui etait attendus pour le protocole
    */
    public MakeDataCammenbert(ArrayList<Test> listeTest, int objectif){
        this.listeTest = listeTest;
        
        this.remplissageTableaux();//objectif);

        MakeDataCammenbert.trierData(this.dataNbMenuVisite, this.effectifNbMenuVisite);
        this.bestNbMenuVisite = MakeDataCammenbert.findIndiceMinimum(this.dataNbMenuVisite);
        this.stringDataNbMenuVisite = MakeDataCammenbert.toStringList(this.dataNbMenuVisite);

        MakeDataCammenbert.trierEffectif(this.dataDerniereOption, this.effectifDerniereOption);
        this.bestDerniereOption = MakeDataCammenbert.getIndice(this.dataDerniereOption, objectif);
        this.stringDataDerniereOption = MakeDataCammenbert.toStringList(this.dataDerniereOption);
    }
    /** 
    * Permet de remplir les tableaux (valeurs et effectifs) de la derniere option selectionnee et du nombre de sous menus deployes
    */
    private void remplissageTableaux(){//int objectif){
        int nbTest = this.listeTest.size();
        int numTest;
        this.dataNbMenuVisite = new ArrayList<>();
        this.effectifNbMenuVisite = new ArrayList<>();
        this.dataDerniereOption = new ArrayList<>();
        this.effectifDerniereOption = new ArrayList<>();

        for (numTest=0; numTest<nbTest; numTest++){
            Test currentTest = this.listeTest.get(numTest);
            if (currentTest != null){
                Option derniereOption = currentTest.getDerniereOption();
                if (derniereOption != null){
                    int index = this.dataDerniereOption.indexOf(derniereOption);
                    if (index == -1){
                        this.effectifDerniereOption.add(1);
                        this.dataDerniereOption.add(derniereOption);
                    }
                    else{
                        this.effectifDerniereOption.set(index, this.effectifDerniereOption.get(index)+1);
                    }
                }

                //if (derniereOption.equals(objectif)){
                    int nbMenuVisitee = currentTest.getNbSousmenuVisitee();
                    if (nbMenuVisitee != -1){
                        int index = this.dataNbMenuVisite.indexOf(nbMenuVisitee);
                        if (index == -1){
                            this.effectifNbMenuVisite.add(1);
                            this.dataNbMenuVisite.add(nbMenuVisitee);
                        }
                        else{
                            this.effectifNbMenuVisite.set(index, this.effectifNbMenuVisite.get(index)+1);
                        }
                    }
                //}
            }
        }
    }
    /** 
    * Permet de trouver l'entier le plus petit dans une liste d'entier
    * @param liste liste d'entier
    * @return le plus petit entier trouver
    * @return -1 si le tableau est vide
    */
    private static int findIndiceMinimum(ArrayList<Integer> liste) {
        int taille = liste.size();
        if (taille > 0){
            int minimum = liste.get(0);
            int indiceMinimum = 0;
            int i;
            for (i = 1; i < taille; i++) {
                int currentInt = liste.get(i);
                if (currentInt < minimum) {
                    minimum = currentInt;
                    indiceMinimum = i;
                }
            }
            return indiceMinimum;
        }
        return -1;
    }
    /** 
    * Permet de trouver l'indice d'un element dans une liste
    * @param liste liste d'element
    * @param aTrouver Element a chercher
    * @return l'indice du premier element correspondant dans la liste
    * @return -1 si la liste est vide ou que l'element a chercher n'est pas dans la liste
    */
    private static <T, P> int getIndice(ArrayList<T> liste, P aTrouver) {
        int taille = liste.size();
        if (taille > 0) {
            for (int i = 0; i < taille; i++) {
                T element = liste.get(i);
                if (element.equals(aTrouver)) {
                    return i;
                }
            }
        }
        return -1;
    }
    /** 
    * Permet de trier deux liste d'entier (valeur et effectif)  dans l'ordre croissant des entier du tableau des valeur
    * @param data liste des valeurs
    * @param effectif liste des effectifs correspondant aux valeurs
    */
    private static void trierData(ArrayList<Integer> data, ArrayList<Integer> effectif) {
        int taille = data.size();
        if (taille > 0 && taille == effectif.size()){
            int i;
            for (i=0; i < taille-1; i++) {
                int j;
                for (j=i+1; j < taille; j++) {
                    int dataI = data.get(i);
                    int dataJ = data.get(j);
                    int effectifI = effectif.get(i);
                    int effectifJ = effectif.get(j);
                    if (dataI > dataJ) {
                        data.set(i, dataJ);
                        data.set(j, dataI);
                        effectif.set(i, effectifJ);
                        effectif.set(j, effectifI);
                    }
                }
            }
        }
    }
    /** 
    * Permet de trier deux liste d'entier (valeur et effectif)  dans l'ordre croissant des effectifs
    * @param data liste des valeurs
    * @param effectif liste des effectifs correspondant aux valeurs
    */
    private static <T> void trierEffectif(ArrayList<T> data, ArrayList<Integer> effectif) {
        int taille = data.size();
        if (taille > 0 && taille == effectif.size()){
            int i;
            for (i=0; i < taille-1; i++) {
                int j;
                for (j=i+1; j < taille; j++) {
                    T dataI = data.get(i);
                    T dataJ = data.get(j);
                    int effectifI = effectif.get(i);
                    int effectifJ = effectif.get(j);
                    if (effectifI > effectifJ) {
                        data.set(i, dataJ);
                        data.set(j, dataI);
                        effectif.set(i, effectifJ);
                        effectif.set(j, effectifI);
                    }
                }
            }
        }
    }
    /** 
    * Permet de convertir une liste d'objet en liste de String avec toString()
    * @param data liste d'objet
    * @return liste de String
    */
    private static <T> ArrayList<String> toStringList(ArrayList<T> data) {
        ArrayList<String> listeString = new ArrayList<>();
        int taille = data.size();
        int i;
        for (i=0; i < taille; i++) {
            listeString.add(data.get(i).toString());
        }
        return listeString;
    }
    /** 
    * Permet de recuperer la liste des differentes dernieres options selectionnees
    * @return liste des dernieres options
    */
    public ArrayList<String> getDataDerniereOption(){
        return this.stringDataDerniereOption;
    }
    /** 
    * Permet de recuperer la liste des effectifs (correspondants au tableau dataDerniereOption)
    * @return liste des effectifs
    */
    public ArrayList<Integer> getEffectifDerniereOption(){
        return this.effectifDerniereOption;
    }
    /** 
    * Permet de recuperer l'indice de l'option attendus (correspondant au tableau dataDerniereOption)
    * @return indice
    */
    public int getBestDerniereOption(){
        return this.bestDerniereOption;
    }
    /** 
    * Permet de recuperer la liste des differents nombre de sous menus visites
    * @return liste des nombre de sous menus visites
    */
    public ArrayList<String> getDataNbMenuVisite(){
        return this.stringDataNbMenuVisite;
    }
    /** 
    * Permet de recuperer la liste des effectifs (correspondants au tableau dataNbMenuVisite)
    * @return liste des effectifs
    */
    public ArrayList<Integer> getEffectifNbMenuVisite(){
        return this.effectifNbMenuVisite;
    }
    /** 
    * Permet de recuperer l'indice du plus petit nombre de sous menus visitee (dans le tableau dataNbMenuVisite)
    * @return indice
    */
    public int bestNbMenuVisite(){
        return this.bestNbMenuVisite;
    }
}