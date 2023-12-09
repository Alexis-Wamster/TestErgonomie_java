package fr.iutfbleau.wamster.dev31_2023;
/** 
 * <code>Option</code> permet de stocker les informations d'une option (id, texte et si c'est une action ou un sous menu)
 * @version 1.1
 * @author Djabrail KHAPIZOV et Alexis WAMSTER
*/
public class Option {
    private int idOption;
    private String texteOption;
    private boolean isAction;

    /** 
     * <code>Option</code> stocke les informations d'une option
     * @param idOption identifiant de l'option
     * @param texteOption texte afficher par l'option
     * @param isAction true si c'est une action, false si c'est un sous menu
    */
    public Option(int idOption, String texteOption, boolean isAction) {
        this.idOption = idOption;
        this.texteOption = texteOption;
        this.isAction = isAction;
    }

    /** 
     * Recupere l'identifiant de l'option
     * @return identifiant
    */
    public int getidOption() {
        return this.idOption;
    }
    /** 
     * Recupere le texte de l'option
     * @return texte
    */
    public String getTexteOption() {
        return this.texteOption;
    }
    /** 
     * Recupere la nature de l'option (true=action, false=sous menu)
     * @return nature
    */
    public boolean isAction() {
        return this.isAction;
    }

    /** 
     * L'orsqu'on affiche l'objet, on ne voit que le texte de l'option
     * @return texte de l'option
    */
    @Override
    public String toString() {
        return this.texteOption;
    }

    /** 
     * permet de comparer un objet avec une option
     * @return true si les deux objet ont un attribut idOption identique ou si un entier a la meme valeur que idOption, false sinon
    */
    @Override
    public boolean equals(Object objet) {
        if (this == objet) {
            return true;
        }
        if (objet == null) {
            return false;
        }
        if (objet.getClass() == Integer.class) {
            return (this.idOption == (int) objet);
        }
        if (objet.getClass() != this.getClass()) {
            return false;
        }
        Option compare = (Option) objet;
        return (this.idOption == compare.idOption);
    }
}