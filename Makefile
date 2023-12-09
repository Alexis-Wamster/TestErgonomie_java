### VARIABLES ###

BUILD = build/fr/iutfbleau/wamster/dev31_2023/
SRC = src/fr/iutfbleau/wamster/dev31_2023/
MARIADB = res/org/mariadb/jdbc/

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none -d build -classpath build:res -sourcepath src

JR = jar
JRTFLAGS = -cef fr.iutfbleau.wamster.dev31_2023.MainTesteur testeur.jar -C build fr -C res org
JRDFLAGS = -cef fr.iutfbleau.wamster.dev31_2023.MainDeveloppeur developpeur.jar -C build fr -C res org
CHMT = chmod 700 testeur.jar
CHMD = chmod 700 developpeur.jar

### REGLES ESSENTIELLES ###

### MAKE ###

${BUILD}MainTesteur.class : ${SRC}MainTesteur.java ${BUILD}Fenetre.class ${BUILD}CProgramme.class
	${JC} ${JCFLAGS} ${SRC}MainTesteur.java

${BUILD}MainDeveloppeur.class : ${SRC}MainDeveloppeur.java ${BUILD}Fenetre.class ${BUILD}CProgramme.class
	${JC} ${JCFLAGS} ${SRC}MainDeveloppeur.java



### FICHIER A INTER-DEPENDANT ###

${BUILD}GestionnairePage.class : ${SRC}GestionnairePage.java ${BUILD}CProgramme.class ${BUILD}Fenetre.class ${BUILD}MenuResultat.class ${BUILD}MenuTeste.class ${BUILD}MenuSelectionProtocole.class
	${JC} ${JCFLAGS} ${SRC}GestionnairePage.java

${BUILD}MenuSelectionProtocoleAction.class : ${SRC}MenuSelectionProtocoleAction.java ${BUILD}CProgramme.class ${BUILD}CMenu.class ${BUILD}GestionnairePage.class ${BUILD}Protocole.class ${BUILD}BaseDonee.class
	${JC} ${JCFLAGS} ${SRC}MenuSelectionProtocoleAction.java

${BUILD}MenuTesteAction.class : ${SRC}MenuTesteAction.java ${BUILD}CMenu.class ${BUILD}GestionnairePage.class ${BUILD}Option.class ${BUILD}BaseDonee.class
	${JC} ${JCFLAGS} ${SRC}MenuTesteAction.java

${BUILD}MenuResultatAction.class : ${SRC}MenuResultatAction.java ${BUILD}CMenu.class ${BUILD}GestionnairePage.class ${BUILD}MenuResultat.class
	${JC} ${JCFLAGS} ${SRC}MenuResultatAction.java

${BUILD}MenuSelectionProtocole.class : ${SRC}MenuSelectionProtocole.java ${BUILD}CProgramme.class ${BUILD}GestionnairePage.class ${BUILD}MenuSelectionProtocoleAction.class
	${JC} ${JCFLAGS} ${SRC}MenuSelectionProtocole.java

${BUILD}MenuTeste.class : ${SRC}MenuTeste.java ${BUILD}Protocole.class ${BUILD}GestionnairePage.class ${BUILD}MenuTesteAction.class
	${JC} ${JCFLAGS} ${SRC}MenuTeste.java

${BUILD}MenuResultat.class : ${SRC}MenuResultat.java ${BUILD}MakeDataCammenbert.class ${BUILD}PieChartPanel.class ${BUILD}GestionnairePage.class ${BUILD}MenuResultatAction.class
	${JC} ${JCFLAGS} ${SRC}MenuResultat.java

${BUILD}Fenetre.class : ${SRC}Fenetre.java ${BUILD}CProgramme.class ${BUILD}GestionnairePage.class
	${JC} ${JCFLAGS} ${SRC}Fenetre.java



### FICHIER A PLUSIEURS LIAISONS ###

${BUILD}Test.class : ${SRC}Test.java ${BUILD}Option.class
	${JC} ${JCFLAGS} ${SRC}Test.java

${BUILD}Protocole.class : ${SRC}Protocole.java ${BUILD}Test.class
	${JC} ${JCFLAGS} ${SRC}Protocole.java

${BUILD}BaseDonee.class : ${SRC}BaseDonee.java ${BUILD}Arbre.class ${BUILD}Protocole.class
	${JC} ${JCFLAGS} ${SRC}BaseDonee.java



### FICHIER SEUL ###

${BUILD}CProgramme.class : ${SRC}CProgramme.java
	${JC} ${JCFLAGS} ${SRC}CProgramme.java

${BUILD}CMenu.class : ${SRC}CMenu.java
	${JC} ${JCFLAGS} ${SRC}CMenu.java

${BUILD}Arbre.class : ${SRC}Arbre.java
	${JC} ${JCFLAGS} ${SRC}Arbre.java

${BUILD}Option.class : ${SRC}Option.java
	${JC} ${JCFLAGS} ${SRC}Option.java

${BUILD}PieChartPanel.class : ${SRC}PieChartPanel.java
	${JC} ${JCFLAGS} ${SRC}PieChartPanel.java

${BUILD}MakeDataCammenbert.class : ${SRC}MakeDataCammenbert.java
	${JC} ${JCFLAGS} ${SRC}MakeDataCammenbert.java



### REGLES OPTIONNELLES ###

all: ${BUILD}MainTesteur.class ${BUILD}MainDeveloppeur.class
	${JR} ${JRTFLAGS}
	${CHMT}
	${JR} ${JRDFLAGS}
	${CHMD}

testeur :
	java -jar testeur.jar

developpeur :
	java -jar developpeur.jar
	

clean :
	-rm -f ${BUILD}*.class
	-rm -f *.jar

mrproper :
	clean ${BUILD}Main*.class



### BUTS FACTICES ###

.PHONY : testeur developpeur clean mrproper

.DEFAULT_GOAL := all

### FIN ###
