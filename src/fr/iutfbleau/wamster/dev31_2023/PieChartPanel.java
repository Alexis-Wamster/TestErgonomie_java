package fr.iutfbleau.wamster.dev31_2023;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.*;

/** 
 * <code>PieChartPanel</code> permet de dessiner un diagrame en camembert
 * @version 1.1
 * @author Djabrail KHAPIZOV et Alexis WAMSTER
*/
public class PieChartPanel extends JPanel {
    private ArrayList<String> labels;
    private ArrayList<Integer> values;
    private int highlightIndex;

    private Color[] sliceColors;

    /** 
     * Ce constructeur stocke les donnees du camembert
     * @param labels liste des differentes valeurs du camembert
     * @param values liste des effectifs correspondant aux valeurs
     * @param highlightIndex index de la valeurs attendues / de la meilleur valeur
    */
    public PieChartPanel(ArrayList<String> labels, ArrayList<Integer> values, int highlightIndex) {
        this.labels = labels;
        this.values = values;
        this.highlightIndex = highlightIndex; 
        this.sliceColors = generateSliceColors();
    }

    /** 
     * Permet de dessiner le camembert en mettant en vert la meilleur valeur
     * @param g pinceau qui permet de dessiner
    */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        double total = (double) this.getTotalValues();
        int startAngle = 0;

        int size = this.values.size();
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < size; i++) {
            double arcAngle = ((double) this.values.get(i)) / total * 360.0;
            int roundedArcAngle = (int) Math.round(arcAngle);
            g.setColor(sliceColors[i]);
            g2d.fill(new Arc2D.Double(10, 10, getWidth() - 20, getHeight() - 20, startAngle, roundedArcAngle, Arc2D.PIE));
            startAngle += roundedArcAngle;
        }

        /*int remainingDegrees = 360 - startAngle;

        if (remainingDegrees > 0) {
            int degreesPerSlice = remainingDegrees / size;

            for (int i = 0; i < size; i++) {
                g.setColor(sliceColors[i]);
                g2d.fill(new Arc2D.Double(10, 10, getWidth() - 20, getHeight() - 20, startAngle, degreesPerSlice, Arc2D.PIE));
                startAngle += degreesPerSlice;
            }
        }*/

        drawSliceLabels(g);
    }

    /** 
     * Permet de generer un tableau de couleur aleatoire pour chaque part de camembert
     * @return tableau de couleur
    */
    private Color[] generateSliceColors() {
        int nbSlices = this.values.size();
        Color[] colors = new Color[nbSlices];
        Color oldColor = Color.GREEN;
        for (int i = 0; i < nbSlices; i++) {
            if (i == this.highlightIndex){
                colors[i] = Color.GREEN;
            }
            else{
                do{
                    colors[i] = getRandomColor();
                }
                while(estNuanceDeVert(colors[i]) || isColorClose(colors[i], oldColor) || isColorClose(colors[i], Color.black));
            }
            oldColor = colors[i];
        }
        return colors;
    }

    /** 
     * Verifie si deux couleurs se ressemblent
     * @param color1 couleur 1
     * @param color1 couleur 2
     * @return true si les couleurs se ressemblent, false sinon
    */
    private static boolean isColorClose(Color color1, Color color2) {
        int threshold = 200;
        int redDiff = Math.abs(color1.getRed() - color2.getRed());
        int blueDiff = Math.abs(color1.getBlue() - color2.getBlue());
        int greenDiff = Math.abs(color1.getGreen() - color2.getGreen());
        return redDiff + blueDiff + greenDiff < threshold;
    }
    /** 
     * Verifie si une couleur est une nuance de vert
     * @param couleur couleur
     * @return true si la couleur est une nuance de vert, false sinon
    */
    public static boolean estNuanceDeVert(Color couleur) {
        int rouge = couleur.getRed();
        int vert = couleur.getGreen();
        int bleu = couleur.getBlue();
        int seuil = 50;
        return (vert - rouge > seuil) && (vert - bleu > seuil) && (vert > seuil);
    }

    /** 
     * Genere une couleur aleatoire
     * @return couleur
    */
    private Color getRandomColor() {
        return new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
    }

    /** 
     * Affiche dans chaque part de camembert, la valeur correspondante
     * @param g le pinceau qui ecrit les valeurs
    */
    private void drawSliceLabels(Graphics g) {
        int total = this.getTotalValues();
        int startAngle = 0;
        int size = this.values.size();
        for (int i = 0; i < size; i++) {
            double arcAngle = (double) this.values.get(i) / total * 360;
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int labelX = (int) (centerX + 0.6 * (getWidth() / 2) * Math.cos(Math.toRadians(startAngle + arcAngle / 2)));
            int labelY = (int) (centerY - 0.6 * (getHeight() / 2) * Math.sin(Math.toRadians(startAngle + arcAngle / 2)));

            g.setColor(Color.BLACK);
            g.drawString(this.labels.get(i).toString(), labelX, labelY);
            startAngle += arcAngle;
        }
    }
    /** 
     * Calcul l'effectif total
     * @return effectif total
    */
    private int getTotalValues(){
        int totalValues = 0;
        int size = this.values.size();
        for (int i = 0; i < size; i++) {
            totalValues += this.values.get(i);
        }
        return totalValues;
    }
}
