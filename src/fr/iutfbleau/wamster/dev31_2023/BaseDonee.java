package fr.iutfbleau.wamster.dev31_2023;
import org.mariadb.jdbc.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;

/**
 * <code>BaseDonee</code> gere tous le flux d'information qui entre ou sort de la base de donnee.
 * @version 1.1
 * @author Djabrail KHAPIZOV et Alexis WAMSTER
 */
public class BaseDonee{

	private Connection connexion;
	private PreparedStatement requeteGetFils;
	private Arbre arbre;

	/** 
	 * Constructeur qui ouvre une connexion a phpmyadmin
	*/
	public BaseDonee()throws SQLException{
		this.open();
	}
	/** 
	 * Ouvre une connexion a phpmyadmin
	 * @throws SQLException est levee en cas d'erreur
	*/
	public void open()throws SQLException{
		try{
			try{
				Class.forName("org.mariadb.jdbc.Driver");
			}
			catch(ClassNotFoundException e){
				throw new RuntimeException("ClassNotFoundException", e);
			}
		    this.connexion = DriverManager.getConnection(
		    "jdbc:mariadb://dwarves.iut-fbleau.fr/wamster",
		    "login","password");
		}
		catch(SQLException e){
		    throw new SQLException("Connexion au serveur impossible ! Verifiez votre connexion internet puis reessayez", e);
		}
	}
	/** 
	 * Ferme la connexion phpmyadmin
	 * @throws SQLException est levee en cas d'erreur
	*/
	public void close()throws SQLException{
		try{
			this.connexion.close();
		}
		catch(SQLException e){
		    throw new SQLException("Erreur de fermeture", e);
		}
	}
	/**Recupere des information sur le menu d'un protocole
	 * la connexion a phpmyadmin est automatiquement fermer a l'execution de la methode
	 * @param reference La reference du protocole a lire dans la base de donnee
	 * @return Un objet "Protocole" remplis des attributs: objectif, description, reference et menu
	 * @return null si la reference n'est pas bonne
	 * @throws SQLException est levee en cas d'erreur
	*/
    public Protocole getMenu(String reference)throws SQLException{		    
		try {
			Protocole dataProtocole = new Protocole();
			dataProtocole.addReference(reference);
			PreparedStatement requete = this.connexion.prepareStatement(
		    "SELECT idOption, texteOption, isAction, description, objectif FROM SAE31_option NATURAL JOIN  SAE31_protocole WHERE reference=?");
		    requete.setString(1, reference);
		   	ResultSet resultat = requete.executeQuery();
		    requete.close();
		    if (resultat.next()){
				dataProtocole.addObjectif(resultat.getInt("objectif"));
				dataProtocole.addDescription(resultat.getString("description"));

				int idMenu = resultat.getInt("idOption");
		    	boolean isAction = resultat.getBoolean("isAction");
				Option option = new Option(idMenu, resultat.getString("texteOption"), isAction);
				this.arbre = new Arbre(option);
				resultat.close();

				this.requeteGetFils = this.connexion.prepareStatement(
					"SELECT idOption, texteOption, isAction FROM SAE31_option WHERE numPere=?");
				if (isAction == false){
					this.recursivite(idMenu, this.arbre.getGenerationFils());
				}
				dataProtocole.addMenu(this.arbre.getArbre());
				return dataProtocole;
		    }
			else{
				this.close();
				return null;
			}
		}
		catch(SQLException e){
		    this.close();
		    throw new SQLException("probleme de select");
		}
    }

	/**Recupere des information sur les tests d'un protocole
	 * la connexion a phpmyadmin est automatiquement fermer a l'execution de la methode
	 * @param reference La reference du protocole a lire dans la base de donnee
	 * @return Un objet "Protocole" remplis des attributs: objectif, description, reference et test
	 * @return null si la reference n'est pas bonne
	 * @throws SQLException est levee en cas d'erreur
	*/
	public Protocole getTest(String reference)throws SQLException{
		try {
			Protocole dataProtocole = new Protocole();
			dataProtocole.addReference(reference);

			PreparedStatement requete = this.connexion.prepareStatement(
		    "SELECT objectif,description,referenceInt FROM SAE31_protocole WHERE reference=?");
			requete.setString(1, reference);
			ResultSet resultatProtocole = requete.executeQuery();
			requete.close();
			if (resultatProtocole.next()){
				dataProtocole.addObjectif(resultatProtocole.getInt("objectif"));
				dataProtocole.addDescription(resultatProtocole.getString("description"));
				int referenceInt = resultatProtocole.getInt("referenceInt");
				resultatProtocole.close();

				requete = this.connexion.prepareStatement(
				"SELECT idTest,numTest FROM SAE31_test WHERE referenceInt=?");
				requete.setInt(1, referenceInt);
				ResultSet resultatTest = requete.executeQuery();
				requete.close();

				PreparedStatement requete2 = this.connexion.prepareStatement(
					"SELECT numClic,idOption,texteOption,isAction FROM SAE31_clic NATURAL JOIN SAE31_option WHERE idTest=?");

				while (resultatTest.next()){
					int idTest = resultatTest.getInt("idTest");
					int numTest = resultatTest.getInt("numTest");
					Test test = new Test(idTest);
					dataProtocole.addTest(numTest, test);
					requete2.setInt(1, idTest);
					ResultSet resultatClic = requete2.executeQuery();
					while (resultatClic.next()){
						int numClic = resultatClic.getInt("numClic");
						int idOption = resultatClic.getInt("idOption");
						String texteOption = resultatClic.getString("texteOption");
						boolean isAction = resultatClic.getBoolean("isAction");
						Option option = new Option(idOption, texteOption, isAction);
						test.addClic(numClic, option);
					}

				}
				resultatTest.close();
				this.close();
				return dataProtocole;
			}
			else{
				this.close();
				return null;
			}
		}
		catch(SQLException e){
		    this.close();
		    throw new SQLException(e.getMessage());
		}
    }
	/**Stocke les clics effectues lors d'un test
	 * la connexion a phpmyadmin est automatiquement fermer a l'execution de la methode
	 * @param historiqueClic La liste des id des option qui ont ete cliquee
	 * @param idTest L'id du test auquel apartien les clics'
	 * @throws SQLException est levee en cas d'erreur
	*/
	public void insertionClic(ArrayList<Integer> historiqueClic, int idTest)throws SQLException{
		try{
			PreparedStatement requete = this.connexion.prepareStatement(
				"INSERT INTO SAE31_clic VALUES (?, ?, ?)");
			int nbClic = historiqueClic.size();
			int numClic;
			int idOption;
			for (numClic=0; numClic<nbClic; numClic++){
				idOption = historiqueClic.get(numClic);
				requete.setInt(1, idTest);
        		requete.setInt(2, numClic);
        		requete.setInt(3, idOption);
				requete.executeUpdate();
			}
			requete.close();
		}
		catch(SQLException e){
		    this.close();
		    throw new SQLException("probleme d'enregistrement de vos action'");
		}
	}
	/**renvoie un id de test unique pour une certaine reference
	 * @param reference La reference d'un protocole
	 * @return un id de test unique superieur ou egal a 0
	 * @return -1 si la reference est invalide
	 * @throws SQLException est levee en cas d'erreur
	*/
	public int createTest(String reference)throws SQLException{
		try{
			int numTest;
			int referenceInt;
			int idTest;

			PreparedStatement requete = this.connexion.prepareStatement(
		    "SELECT idTest FROM SAE31_test");
			ResultSet resultat = requete.executeQuery();
		    requete.close();
			idTest = this.maxInt(resultat, "idTest")+1;
		    if (idTest < 0){
				idTest = 0;
			}

			requete = this.connexion.prepareStatement(
		    "SELECT numTest FROM SAE31_test NATURAL JOIN SAE31_protocole WHERE reference=?");
			requete.setString(1, reference);
		   	resultat = requete.executeQuery();
		    requete.close();
			numTest = this.maxInt(resultat, "numTest")+1;
		    if (numTest < 0){
				numTest = 0;
			}

			requete = this.connexion.prepareStatement(
		    "SELECT referenceInt FROM SAE31_protocole WHERE reference=?");
			requete.setString(1, reference);
		   	resultat = requete.executeQuery();
		    requete.close();
		    if (resultat.next()){
				referenceInt = (resultat.getInt("referenceInt"));
			}
			else{
				return -1; //la reference fournis en argument est invalide
			}

			requete = this.connexion.prepareStatement(
				"INSERT INTO SAE31_test VALUES (?, ?, ?)");
        	requete.setInt(1, idTest);
        	requete.setInt(2, referenceInt);
        	requete.setInt(3, numTest);
        	requete.executeUpdate();
        	requete.close();
			return idTest;
		}
		catch(SQLException e){
		    this.close();
		    throw new SQLException("probleme d'enregistrement du test");
		}
	}


	/**ajoute recursivement des branche a l'arbre du menu
	 * @param idMenu id du menu que l'on veut construire
	 * @param generation profondeur de l'option du fils qu'on veut ajouter
	 * @throws SQLException est levee en cas d'erreur
	*/
	private void recursivite(int idMenu, int generation)throws SQLException{
		try{
			this.requeteGetFils.setInt(1, idMenu);
			ResultSet resultat = this.requeteGetFils.executeQuery();
			while (resultat.next()){
				boolean isAction = resultat.getBoolean("isAction");
				idMenu = resultat.getInt("idOption");
				Option option = new Option(idMenu, resultat.getString("texteOption"), isAction);
				this.arbre.addBranche(option, generation);
				if (isAction==false){
					this.recursivite(idMenu, generation+1);
				}
			}
		}
		catch(SQLException e){
		    this.close();
		    throw new SQLException("probleme de select");
		}
	}
	/**retourne le plus grand entier d'un attribut parmis plusieurs tuples
	 * resultat n'est plus exploitable apres l'execution de la methode
	 * @param resultat resultat d'une requete sql
	 * @param atribut nom de l'attribut que l'on veut comparer
	 * @return la plus grande valeur pour l'attribut
	 * @return Integer.MIN_VALUE si il n'y a aucun tuple dans resultat
	 * @throws SQLException est levee en cas d'erreur
	*/
	private int maxInt(ResultSet resultat, String atribut)throws SQLException{
		try{
			int max = Integer.MIN_VALUE;
			int currentValue;
			while (resultat.next()){
				currentValue = resultat.getInt(atribut);
				if (max < currentValue){
					max = currentValue;
				}
			}
			return max;
		}
		catch(SQLException e){
		    this.close();
		    throw new SQLException("probleme maxInt. resultat.next() a renvoye une erreur");
		}
	}
}
