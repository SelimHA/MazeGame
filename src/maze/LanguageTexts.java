/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

/**
 *
 * @author
 */
public class LanguageTexts {
    //Orientation: English-Hungarian-French
    //Titles:
    public static String[] SETTINGS_TEXT = {"Settings","Beállítások","Réglages"};
    public static String[] STATISTICS_TEXT = {"Statistics","Statisztikák","Statistiques"};
    //Maze
    public static String[] FATAL_ERROR_EXITING_TEXT = {"Fatal error while loading the map, exiting","Hiba a pálya betöltése során, kilépés","Erreur fatale lors du chargement de la carte, quittant"};
    
    public static String[] GIVE_UP_BUTTON_TEXT = {"Give up","Feladom","Abandonner"};
    public static String[] GIVE_UP_BUTTON_TOOL_TIP_TEXT={"Pressing this button will end the game,\n\r it will be recorded as a lost game,\n\r a path leading to finish position will also be shown","A gomb megnyomásával a játék véget ér,\r\n az eredmény elvesztettnek lesz minősítve,\r\n az út a célhoz meg fog jelenni a pályán","En appuyant sur ce bouton se terminera le jeu,\r\nil sera enregistré comme un jeu perdu,\r\nun chemin menant à la position d'arrivée sera également montré"};
    public static String[] WIN_GAME_POP_UP_MESSAGE = {"Congratulations for winning the game","Gratulálunk, Ön nyert","Félicitations pour avoir gagné le match"};
    public static String[] LOST_GAME_POP_UP_MESSAGE = {"You lost :(","Ön vesztett :(","Vous avez perdu :("};
    
    public static String[] MAZE_TITLE_GAMENAME = {"Maze","Labirintus","Labyrinthe"};
    public static String[] MAZE_TITLE_PART_MODE = {"mode","mód","mode"};
    public static String[] MAZE_TITLE_TIME_REMAINING = {"Time remaining: ","Hátralevő idő: ","Temps restant: "};
    public static String[] MAZE_TITLE_TIME_ELAPSED = {"Time elapsed: ", "Eltelt idő: ","Temps écoulé: "};
    public static String[] MAZE_TITLE_LIVES_REMAINING = {"Lives remaining: ","Hátralevő életek: ","Vies restantes: "};
    
    public static String[] QUIT_WITHOUT_SAVING = {"Would you like to save the game?\r\nIf you exit now without saving, this game will be considered as lost.","El szeretné menteni a játékot?\r\nHa mentés nélkül folytatja a kilépést akkor a játék elvesztettnek minősül.","Tu veux sauver la partie?\r\nSi vous quittez maintenant sans sauvegarder, ce jeu sera considéré comme perdu."};
    //main:
    public static String[] BAD_TIME_FORMAT_ERROR_WARNING = {"Bad time format, please enter a whole number","Nem jó időformátum, egész szám szükséges","Mauvais format d'heure, veuillez entrer un nombre entier"};
    public static String[] TOO_MUCH_TIME_WARNING = {"Time must be more than 4 seconds and less than 8000 seconds","A megadott idő nem jó. Az idő több kell, hogy legyen 4 másodpercnél és kevesebb mint 8000 másodperc","Le temps doit être supérieur à 4 secondes et inférieur à 8000 secondes."};
    public static String[] BAD_NUMBER_FORMAT_ERROR_WARNING = {"Bad number format, check if you have written an integer to number of lives","Rossz számformátum, ellenőrizze, hogy egész számot írt-e az életek számát tartalmazó mezőben.","Mauvais format de nombre, vérifiez si vous avez écrit un entier au nombre de vies."};
    public static String[] LIVES_NUMBER_OUT_OF_RANGE = {"An error has occured, the number of lives must be between 0 and 10 (inclusive)","Hiba történt, az életek számának 0 és 10 között kell lennie.","Une erreur s'est produite, le nombre de vies doit être compris entre 0 et 10 (inclus)"};
    
    public static String[]  WELCOME_TEXT = {"Welcome","Üdvözöljük!","Bienvenue!"};
    
    public static String[]  BUTTON_PLAY_TEXT = {"Play","Játék indítása","Démarrer le jeu"};
    public static String[]  BUTTON_LOAD_LAST_GAME = {"Load last saved game","Utolsó mentett játék betöltése","Charger la dernière partie sauvegardée"};
    public static String[]  BUTTON_SETTINGS_TEXT = {"Settings","Beállítások","Réglages"};
    public static String[]  BUTTON_STATISTICS_TEXT = {"Statistics","Statisztikák","Statistiques"};
    
    public static String[] ENTER_SIZE_OF_FIELD_SHORT = {"Enter field size", "Mezőméret megadása", "Entrez la taille du champ"};
    public static String[] ENTER_SIZE_OF_FIELD_LONG = {"Please enter the size of field as an integer","Kérjük adja meg a mező méretét egész számként","Veuillez entrer la taille du champ en entier"};
    public static String[] BAD_FIELD_SIZE_FORMAT = {"Bad field size format. Please enter a whole number","Rossz formátumba megadott mezőméret (egész szám szükséges)","Mauvais format de champ. Veuillez entrer un nombre entier"};
    public static String[] FIELD_SIZE_TOO_MUCH = {"Field size should be more than or equal to 10 and less than the value given in settings","A mezőméretnek 9-nél nagyobbnak valamint a beállításokban megadott értéknél kisebbnek kell lennie.","La taille du champ doit être supérieure ou égale à 10\r\n et inférieure ou égale à la valeur donnée dans les réglages."};
    
    public static String[]  NORMAL_MODE_TEXT_jOPTION = {"Normal","Normál","Normal"};
    public static String[]  TIMER_MODE_TEXT_jOPTION = {"Timer","Időzítő","Minuteur"};
    public static String[]  CHALLENGE_MODE_TEXT_jOPTION = {"Challenge","Kihívás","Défi"};
    public static String[]  NORMAL_MODE_BUBBLE_DESCRIPTION = {"In this mode you have unlimited time to find the way to a stationary finish position","Ebben a módban korlátlan ideje van, hogy eljusson a mozdulatlan célhoz","Dans ce mode, vous disposez d'un temps illimité pour trouver le passage vers une position d'arrivée stationnaire"};
    public static String[]  TIMER_MODE_BUBBLE_DESCRIPTION = {"In this mode you have to reach the stationary finish position before you run out of time (that you have given)","Ebben a módban a megadott idő előtt kell beérni a mozdulatlan célmezőbe","Dans ce mode, vous devez passer à la position d'arrivée stationnaire avant de manquer de temps (que vous avez donné)"};
    public static String[]  CHALLENGE_MODE_BUBBLE_DESCRIPTION = {"<html>In this mode you have to arrive to the finish position before you run out of lives<br>You lose a life once you run out of time.<br>Also when you lose a life the finish position will be relocalized and the map will change.","<html>Ebben a módban el kell érnie a célmezőt mielőtt elfogy az összes élete.<br>A megadott idő lejárta után elveszít egy életet.<br>Ugyanekkor a célmező áthelyeződik valamint a labirintus is megváltozik.","<html>Dans ce mode, vous devez arriver à la position d'arrivée avant que vous ne soyez à court de vies<br>Vous perdez une vie une fois que vous êtes à court de temps.<br>Aussi quand vous perdez une vie, la position d'arrivée sera relocalisée et la carte se changera."};
    
    public static String[]  SELECT_GAME_MODE_TEXT= {"Please select a game mode:","Kérem válasszon egy játékmódot:","Veuillez sélectionner un mode de jeu"};
    public static String[]  ENTER_TIME_LIMIT_TEXT = {"Please enter a time limit:","Kérem adja meg az időkorlátot: ","Veuillez saisir une limite de temps:"};
    public static String[]  ENTER_NUMBER_OF_LIVES = {"Please enter the number of lives:","Kérem adja meg az életek számát:","Veuillez entrer le nombre de vies:"};
    public static String[]  SECOND_S_TEXT = {"seconds","másodperc","secondes"};
    
    //Settings_object
    public static String IO_EXCEPTION_OCCURED = "A read-write exception occured,\n the software attempts to recreate the file containing the settings with default values and reload.\n\nEgy írás-olvasási hiba keletkezett,\n a szoftver helyreállítja a beállításokat tartalmazó fájlt és újratölt.\n\nUne exception en lecture-écriture s'est produite,\n le logiciel tente de recréer le fichier contenant les paramètres avec les valeurs par défaut et de recharger.";
    public static String ERROR_OCCURED_RELOAD = "An error has occured while loading the settings\nthe software attempts to recreate the file containing the settings with default values and reload.\n\nEgy hiba történt a beállítások betöltése közben\na szoftver helyreállítja a beállításokat tartalmazó fájlt és újratölt.\n\nUne erreur s'est produite lors du chargement des paramètres\n le logiciel tente de recréer le fichier contenant les paramètres avec les valeurs par défaut et de recharger le fichier.";
    public static String IO_EXCEPTION_OCCURED_SAVING = "A read-write exception has occured\n writing the settings file could not be completed\n\nEgy írás-olvasási hiba keletkezett,\nA beállítások fájl írása megszakadt\n\nUne exception en lecture-écriture s'est produite\n écriture du fichier de paramètres n' a pas pu être complétée";
    public static String SETTINGS_FILE_DOES_NOT_EXIST = "Settings file does not exist,\nthe software now attempts to create a new one with default values\n\nA beállításokat tartalmazó fájl nem található,\na program megpróbál írni egy újat az alapértelmezett értékekkel\n\nLe fichier Settings n'existe pas,\nle logiciel essaie maintenant d'en créer un nouveau avec des valeurs par défaut";

    //SettingsScreen:
    public static String[] BACKGROUND_COLOR_LIST_TEXT = {"Background color:","Háttér szín:","Couleur de fond:"};
    public static String[] BORDER_COLOR_LIST_TEXT = {"Border color:","Keret szín:","Couleur de la bordure:"};
    public static String[] CELL_COLOR_LIST_TEXT = {"Cell color:","Cellaszín:","Couleur de la cellule:"};
    public static String[] LAST_CELLS_COLOR_LIST_TEXT = {"Last visited cells' color:","Utoljára látogatott cellák színe:","Couleur des dernières cellules visitées:"};
    public static String[] PLAYER_COLOR_LIST_TEXT = {"Player's/Text color:","Játékos/Szöveg színe:","Couleur du joueur/texte:"};
    public static String[] LANGUAGE_LIST_TEXT = {"Languages:","Nyelvek:","Langues:"};
    public static String[] MAX_MAZE_SIZE_TEXT = {"Max. maze size: ","Maximális pályaméret: ","Taille max. du labyrinthe:"};
    public static String[] MAX_MAZE_SIZE_INFO_TEXT = {"Must be more than 9 and less than 70","9-nél nagyobbnak és 70-nél kisebbnek kell lennie a számnak","Doit être supérieur à 9 et inférieur à 70"};
    public static String[] AUTO_FILL_CHECKBOX_TEXT = {"Auto-fill time","Idő automatikus kitöltése","Remplir le temps automatiquement"};
    public static String[] DEFAULT_TIME_TEXT = {"Default time (Timer/Chalange modes):","Alapértelmezett idő (Időzítő/Kihívás módok):","Temps par défaut (mode Minuteur/Défi):"};
    public static String[] TIME_VALUE_LONG_TEXT = {"<html>If value given above is less than 5 sec<br>or more than 8000 sec auto-fill time will be disabled.","<html>Ha a megadott idő kevesebb mint 5 másodperc<br>vagy több mint 8000 másodperc<br>az automatikus kitöltés le lesz tiltva.","<html>Si la valeur indiquée ci-dessus est<br>inférieure à 5 secondes ou supérieure à 8000 secondes<br>alors l'auto-remplissage sera désactivé"};
    public static String[] LAST_VISITED_CELLS_TEXT = {"Last visited cells:","Utoljára látogatott helyek:","Cellules visitées en dernier:"};
    public static String[] CHECK_SHOW_LAST_CELLS_TEXT = {"Show last cells","Utolsó helyek megjelenítése","montrer les dernières cellules"};
    public static String[] SHOW_LAST_TEXT = {"Show last","Mutasd az utolsó","Montrer les"};
    public static String[] SHOW_LAST_CELLS_END_TEXT = {"cells","helyet","dernières cellules"};
    public static String[] ADVANCED_SETTINGS_TEXT = {"Advanced settings","Haladó beállítások","Réglages avancés"};
    public static String[] CHECK_ALLOW_BIGGER_MAP_THAN_DEFAULT = {"Allow bigger maze-size than allowed by default","Nagyobb térképméret engedélyezése az alapértelmezett maximumnál","Autoriser un labyrinthe plus grand que celui autorisé par défaut"};
    public static String[] BUTTON_SAVE_CHANGES_TEXT = {"Save","Mentés","Sauvegarder les modifications"};
    public static String[] BUTTON_CANCEL_CHANGES = {"Cancel changes","Változtatások elvetése","Annuler les modifications"};
    public static String[] CLOSE_WITHOUT_SAVING_WARNING_SETTINGS = {"Would you like to save the modifications before quitting?","Bezárás előtt el szeretné menteni a változtatásokat?","Souhaitez-vous sauvegarder les modifications avant de quitter?"};
    public static String[] WARNING_TEXT = {"Warning","Figyelmeztetés","Alerte"};
    
    public static String[] SETTINGS_TOOL_TIP_BACKGROUND_COLOR = {"Sets the background color of the Maze, it will not set the background of all window in the app","A labirintus háttérszínét állítja be.","Il définira le fond du labyrinthe à la couleur sélectionnée."};
    public static String[] SETTINGS_TOOL_TIP_BORDER_COLOR = {"Sets the border color separating the cells in the field","Beállítja a határvonalak színét az egyes cellák közt a pályán","Configure la couleur de bordure séparant les cellules dans le champ"};
    public static String[] SETTINGS_TOOL_TIP_CELL_COLOR = {"Sets the background color of each cell in the field to the specified color","Beállítja a pályán lévő összes cella háttérszínét","Configure la couleur de fond de chaque cellule du champ en fonction de la couleur spécifiée."};
    public static String[] SETTINGS_TOOL_TIP_PLAYER_COLOR = {"Sets the color of the player (\"M\") in the game","Beállítja a játékos (\"M\") színét a játékban","Définit la couleur du joueur (\"M\") dans le jeu"};
    public static String[] SETTINGS_TOOL_TIP_LANGUAGE = {"Sets the language of the application (no restart required)","Beállítja az alkalmazás nyelvét (újraindítás nem szükséges)","Définit la langue de l'application (aucun redémarrage nécessaire)"};
    public static String[] SETTINGS_TOOL_TIP_AUTOFILL_TIME = {"If ticked, and value given below is acceptable, then the game will automatically fill the time if Timer/Challenge mode is selected","Ha ki van pipálva és a megadott szám megfelel a kritériumoknak, Időzítő és Kaland mód esetén az idő automatikusan ki lesz töltve","Si cochée et si la valeur ci-dessous est acceptable, le jeu remplira automatiquement le temps si le mode Minuteur/Défi est sélectionné."};
    public static String[] SETTINGS_TOOL_TIP_SHOW_LAST_CELLS = {"<html>If ticked and given value below is accpeted, then during the game the last cells visited by the player<br>will be marked with a different color to ease going back","<html>Ha ki van pipálva valamint a lentebbi értékek helyesek, akkor a játékos által utoljára meglátogatott helyek/cellák<br>más színnel lesznek jelölve","<html>Si marqué et donné la valeur ci-dessous est accaparé, alors pendant le jeu les dernières cellules visitées par le joueur<br>seront marquées avec une couleur différente pour faciliter le retour en arrière."};
    public static String[] SETTINGS_TOOL_TIP_BACKGROUND_LAST_CELLS = {"Sets the background color for the last visited cells","Beállítja az utoljára látogatott helyek/cellák hátterét","Définit la couleur de fond pour les dernières cellules visitées"};
    public static String[] SETTINGS_TOOL_TIP_BIGGER_MAZE_THAN_DEFAULT = {"<html>If ticked, the game will disregard the maximum maze size set previously<br>Note that max maze size is still limited to below 300<br>For huge mapsizes you may have to wait several minutes and the game may lag","<html>Ha ki van pipálva, akkor a maximális mezőméretnél nagyobb értékeket is meg lehet adni<br>Megjegyzés: Mezőméret így sem haladhatja meg a 299-et<br>Nagy mezőméret esetén a pálya generálása percekig is eltarthat és a játék akadozhat","Si cette case est marquée, le jeu ne tiendra pas compte de la taille maximale du labyrinthe définie précédemment<br>Notez que la taille maximale du labyrinthe est toujours limitée à moins de 300<br>Pour les cartes géantes, vous devrez peut-être attendre plusieurs minutes et le jeu peut se dérouler lentement."};
    public static String[] SETTINGS_TOOL_TIP_MAX_MAZE_SIZE = {"Sets the default maximum size limit of the maze","Beállítja az alapértelmezett maximális mezőméret limitet","Définit la limite de taille maximale par défaut du labyrinthe"};
    //Settings_screen possible errors:
    public static String[] ERROR_DEFAULT_TIME = {"An error occured when trying to set the default time, please check the text field","Hiba történt az alapértelmezett idő beállításánál, kérem ellenőrizze a mezőt.","Une erreur s'est produite lors du réglage de l'heure par défaut, veuillez vérifier le champ de texte."};
    public static String[] ERROR_LAST_CELLS_NUMBER = {"An error occured when trying to set the number of last cells to be shown, please check the text field (number must be btw 0 and 20)","Hiba történt az utolsó helyek megjelenítésének számánál, kérem ellenőrizze, hogy a megadott érték 0 és 20 között fekszik-e.","Une erreur s'est produite lors du réglage du nombre de cellules à afficher, veuillez vérifier le champ de texte (nombre doit être compris entre 0 et 20)"};
    public static String[] ERRORS_OCCURED = {"The changes cannot be saved as the following errors occured:","A változtatásokat nem lehet elmentieni mivel az alábbi hibák felmerültek:","Les modifications ne peuvent pas être sauvegardées car les erreurs suivantes se sont produites:"};
    public static String[] ERRORS_MODIFY_PROBLEMS = {"To save the changes please, modify the errorneous data","A változtatások elmentéséhez kérem javítsa ki a hibákat","Pour sauvegarder les modifications, veuillez modifier les données erronées."};
    public static String[] SETTINGS_ERROR_MAX_MAZE_SIZE = {"Please check that you have entered a whole number to max maze size and that the number is in the specified range","Kérem ellenőrizze, hogy egész számot adott-e meg a maximális pályaméretnél, valamint, hogy a megadott szám benne van-e a tartományban.","Veuillez vérifier que vous avez entré un nombre entier jusqu' à la taille maximale du labyrinthe et que le nombre se trouve dans la plage spécifiée."};
    //Statistics_object
     public static String[] STATISTICS_FILE_DOES_NOT_EXIST = {"Statistics file does not exist,\nthe software now attempts to create a new one with default values","A statisztikákat tartalmazó fájl nem található,\na program megpróbál írni egy újat az alapértelmezett értékekkel","Le fichier Statistics n'existe pas,\nle logiciel essaie maintenant d'en créer un nouveau avec des valeurs par défaut"};
     public static String[] STATISTICS_IO_EXCEPTION_OCCURED = {"A read-write exception has occured\n writing/loading the statistics file could not be completed","Egy írás-olvasási hiba keletkezett,\nA beállítások fájl írása/olvasása megszakadt","Une exception en lecture-écriture s'est produite\n écriture/chargement du fichier de paramètres n' a pas pu être complétée"};
     public static String[] STATISTICS_ERROR_OCCURED_RESETTED = {"An error has occured while loading statistics, Statistics will be resetted","Hiba történt a statisztikák betöltése közben, fájl alaphelyzetbe lesz állítva","Une erreur s'est produite lors du chargement des statistiques, les statistiques seront réinitialisées."};
//StatisticScreen:
     public static String[] WIN_RATIO_TEXT = {"Win ratio:","Nyerési arányok:","Pourcentage de réussite:"};
     public static String[] WIN_RATIO_TOTAL = {"Total: ","Összesen: ","Totale: "};
     public static String[]  NORMAL_MODE_TEXT= {"Normal mode: ","Normál mód: ","Mode normale: "};
     public static String[]  TIMER_MODE_TEXT = {"Timer mode: ","Időzítő mód: ","Mode minuteur: "};
     public static String[]  CHALLENGE_MODE_TEXT = {"Challenge mode: ","Kihívás mód: ","Mode défi: "};
     public static String[] TOTAL_GAMES_PLAYED = {"Total games played: ","Eddig lejátszott játékok: ","Nombre de parties jouées: "};
     public static String[] TOTAL_WINS = {"Total wins: ","Megnyert játékok összesen: ","Nombre de parties gagnées: "};
     public static String[] TOTAL_LOSTS = {"Total losts: ","Elvesztett játékok összesen: ","Nombre de parties perdues:"};
     public static String[] BUTTON_RESET_STATISTICS = {"Reset statistics","Statisztikák alaphelyzetbe állítása","Réinitialiser les statistiques"};
     public static String[] RESET_STATISTICS_WARNING_MESSAGE = {"Are you sure that you want to reset the statistics?","Biztosan alaphelyzetbe akarja állítani a statisztikákat?","Êtes-vous sûr de vouloir réinitialiser les statistiques?"};
     public static String[][] ALL_COMMENTS = {{"Excellent","Kitűnő","Excellent"},{"Very good","Nagyon jó","Très bon"},{"Good job","Szép munka","Bon travail"},{"Not bad","Nem rossz","Pas mal"},{"Well, there is room for improvement","Hát, lehet még tovább fejlődni","Il y a matière à amélioration"}};
     public static String[] PROGRESS_BAR_INFO_TEXT_WINDOWS_LINUX = {"Win: Green, Lost: Red","Nyerések: Zöld, Elvesztett játékok: Piros","Gagner: Vert, Perdu: Rouge"};
     public static String[] PROGRESS_BAR_INFO_TEXT_MAC ={"Win: Blue, Lost: Grey","Nyerések: Kék, Elvesztett játékok: Szürke","Gagner: Bleu, Perdu: Gris"};
     //ALL comments: Excellent-very good- good- not bad- room to improve

     //Initializer
     public static String INITIALIZER_FAILED_TO_WRITE_FIRST_RUN_FILE = "Writing a crucial file has failed, administratorial rights possibly block the software from writing in the directory\r\nUntil the problem is resolved you will keep getting an error warning and will see the Tips window\r\n\r\nHiba történt egy fontos fájl létrehozásakor, a mappa mely a játékot tartalmazza valószínűleg írásvédelem alatt áll\r\n A probléma megoldásáig hibaüzeneteket fog kapni valamint látni fogja a tippek ablakot minden indításkor\r\n\r\nL'écriture d'un fichier crucial a failli, les droits de l'administrateur bloquent possiblement le logiciel d'écrire dans le répertoire.\r\nJusqu' à ce problème soit résolu, vous obtiendrez un message d'erreur et la fenêtre Conseils apparaîtra.";

     //FirstRun
     public static String[] FIRST_RUN_PLEASE_SELECT_LANGUAGE = {"Please select a language:","Kérem válasszon nyelvet:","Veuillez choisir une langue:"};
     public static String[] FIRST_RUN_NO_TEXT_FOUND = {"File containing text is missing","A szöveget tartalmazó fájl hiányzik","Fichier contenant du texte manquant"};
     public static String[] FIRST_RUN_APPLY_BUTTON = {"Apply","Alkalmaz","Appliquer"};
     public static String[] FIRST_RUN_PROCEED_BUTTON = {"Proceed","Tovább","Continuer"};
     public static String[] FIRST_RUN_CHANGES_NOT_SAVED = {"You have made some changes to the language which were not saved, would you like to save it?","Megváltoztatta a nyelvet, de nem alkalmazta a változtatásokat, szeretné alkalmazni?","Vous avez apporté des modifications à la langue qui n'ont pas été sauvegardées, aimeriez-vous les sauvegarder?"};
     public static String[] FIRST_RUN_ERROR ={"An error occured, can't read the file containing the text","Hiba történt, nem lehet megnyitni a sz9veget tartalmazó fájlt","Une erreur s'est produite, ne peut pas lire le fichier contenant le texte"};
}
