# SAE31_2023

Ce projet realiser par Djabrail et Alexis permet de faire des tests d'ergonomie de vos menu.

---

## Compilation & execution

Vous ne pourrez pas tester ce projet car les identifiants et mot de passe pour se connecter a la base de donnee ne sont pas inscrit

- **make** permet de compiler et creer 2 archives "testeur.jar" et "developpeur.jar"
- **make testeur** permet d'executer l'archive "testeur.jar"
- **make developpeur** permet d'executer l'archive "developpeur.jar"

---

## Protocole

### Menu google slide
- **A7bK9pQ1**  Correction orthographique en allemand
- **K3h9pL7s**  Texte en minuscule
- **R2a5qB8w**  Raccourcis clavier
- **M6z4xV1c**  Diapositive mettant en avant un gros chiffre
- **G9d2oE5f**  Tourner un element verticalement

### Minecraft
- **T7l1rN6y**  Creer un monde solo
- **P4i8uQ2o**  Audio description
- **X5v3jI9k**  Cap pour le personnage
- **S8c7gY1b**  Bruit de la pluie
- **U2m6aL9p**  Copie de "speedrun 666"

---

## Programme

### Testeur
Le programme demmande de choisir un protocole (voir ci dessus).
Si le protocole que vous avez saisis ou si vous rencontrer des problemes de connexion, un message d'erreur seras envoye.
Une fois le protocole saisis, une consigne vous demmande de naviguer dans un menu pour trouver une option particuliere.
Une fois trouver vous devez confirmer le choix de l'option. (si vous ne confirmez pas vous pouvez continuer a naviguer)
Une fois que vous avez confirmer votre choix, chaque menu ouvert et chaque actions sur lequels vous aviez clique, seras enregistrer dans une base de donnee (meme si vous ne vouliez pas la selectionner).
En cas de probleme quelquonque, un message d'erreur seras afficher et vos donnee ne seront pas sauvegarde
Dans tout les cas vous serez rediriger vers la page ou on vous demmande de saisir un protocole.

### Developpeur
Le programme demmande de choisir un protocole (voir ci dessus).
Vous pouvez voir un camembert qui affiche la repartition des options selectionnee (la bonne reponse seras en vert flashi)
Un bouton vous permet d'acceder au camenbert suivant qui affiche la repartition du nombre de sous menu different deployer avant de selectionner une reponse (qui peut ne pas etre la bonne). Le nombre de clics le moins elevee du camembert seras afficher en vert flashi.
Un autre bouton vous permet d'acceder au menu de selection de protocole (pour visualiser d'autres resultat)