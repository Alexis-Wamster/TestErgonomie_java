import java.io.*;
import java.util.*;

public class SqlArbre {

    public final static int TAB=4;

    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Usage: java SqlArbre <id> <pere> <numFils> <fichierSource> <fichierDestination>");
            return;
        }

        int idActuel = Integer.parseInt(args[0]);
        int idFuture = idActuel+1;
        int racine = Integer.parseInt(args[1]);
        int numFils = Integer.parseInt(args[2]);
        String fichierSource = args[3];
        String fichierDestination = args[4];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fichierSource));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierDestination));

            String ligneActuelle;
            String ligneFuture;
            String optionActuelle = null;
            String optionFuture;
            int generationFuture;
            int generationActuelle = 0;
            int willy;
            String isAction;
            ArrayList<Integer> dernierIdPere = new ArrayList<>();
            dernierIdPere.add(racine);
            dernierIdPere.add(idActuel);
            ArrayList<Integer> dernierNumFils = new ArrayList<>();
            dernierNumFils.add(numFils-1);

            if ((ligneActuelle = reader.readLine()) != null){
                optionActuelle = ligneActuelle.trim();
                generationActuelle = SqlArbre.calculGeneration(ligneActuelle);
            }

            while ((ligneFuture = reader.readLine()) != null) {
                optionFuture = ligneFuture.trim();
                generationFuture = SqlArbre.calculGeneration(ligneFuture);

                if (generationActuelle >= generationFuture){
                    for (int i=generationActuelle+1; i<dernierNumFils.size(); i++){
                        dernierNumFils.remove(i);
                        dernierIdPere.remove(i+1);
                    }
                    isAction = "true";
                }

                else{
                    dernierNumFils.add(-1);
                    dernierIdPere.add(idFuture);
                    isAction = "false";
                }

                dernierNumFils.set(generationActuelle, dernierNumFils.get(generationActuelle)+1);
                dernierIdPere.set(generationActuelle+1, idActuel);

                writer.write(String.format("insert into SAE31_option VALUES (%d, \"%s\", %d, %d, %s);\n",
                    idActuel, optionActuelle, dernierIdPere.get(generationActuelle), dernierNumFils.get(generationActuelle), isAction));

                if (generationActuelle > generationFuture){
                    for (int i=generationActuelle; i<dernierNumFils.size(); i++){
                        dernierNumFils.set(generationActuelle, -1);
                    }
                }

                idActuel = idFuture;
                generationActuelle = generationFuture;
                optionActuelle = optionFuture;
                ligneActuelle = ligneFuture;
                idFuture ++;
            }

            isAction = "true";
            dernierNumFils.set(generationActuelle, dernierNumFils.get(generationActuelle)+1);
            dernierIdPere.set(generationActuelle+1, idActuel);
            
            writer.write(String.format("insert into SAE31_option VALUES (%d, \"%s\", %d, %d, %s);\n",
                    idActuel, optionActuelle, dernierIdPere.get(generationActuelle), dernierNumFils.get(generationActuelle), isAction));

            reader.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int calculGeneration(String ligne){
        int generation = 0;
        int numCaractere = 0;
        char caractere;
        do{
            caractere = ligne.charAt(numCaractere);
            if (caractere == ' '){
                generation++;
            }
            if (caractere == '\t'){
                generation += SqlArbre.TAB;
            }
            numCaractere++;
        } while (caractere == ' ' || caractere == '\t');

        return generation/SqlArbre.TAB;
    }
}





