### Tasche packen

Der Alexa Skill „Tasche packen“ soll dem Nutzer jeder Zeit helfen können seine Tasche für den Tag des Aufrufs zu packen, damit man nichts vergisst.

Alexa soll dabei anhand des Stundenplans von dem Nutzer wissen, was dieser zu den am Tag anstehenden Fächer mitnehmen soll. Der Nutzer kann jederzeit die Utensilien für jedes Fach individuell anpassen. Außerdem hat er die Möglichkeit bei jedem Aufruf anzugeben welche Fächer er an diesem Tag nicht besuchen möchte. Des Weiteren gibt es die Möglichkeit für den User während des laufenden Skills weitere Funktionen aufzurufen wie Beispielsweise „Hilfe“ und „Stopp“, dies ist ein wichtiger Zusatz zur einfachen Bedienung des Programms.
 
Damit der User nicht ins kalte Wasser geschmissen und er sich ohne weitere Information über das Wetter aus dem Haus bewegt, gibt es in diesem Skill „Tasche packen“ eine weitere kleine Zusatzinformation. Alexa soll in Abhängigkeit von den aktuellen Wetterverhältnissen des Tages den Nutzer auf das Mitnehmen von Schirm, Sonnenbrille oder ähnlichen Dingen hinweisen.


### Alexa API 
##### Setup
Um einen Alexa-Skill aufzusetzen muss man zunächst eine AWS-Lambda Funktion erstellen. In diese wird der Java-Code in Form einer jar-Datei eingefügt, die man zuvor mit Maven gebaut hat.
Außerdem benötigt man einen Account in der Amazon Developer Console. Dort erstellt man ebenfalls eine Funktion und verknüpft sie über den Menüpunkt 'Endpoints' mit AWS.
##### Aufbau der Dialoge
Jede Aktion wird über einen Intent aufgerufen. Bei welcher Spracheingabe dieser aufgerufen werden soll, lässt sich in der Developer Console konfigurieren. Um Werte, die eingegeben werden können, einzuschränken, gibt es sogenannte Slots, die man auch in der Developer Console hinterlegen kann. Im Code gibt es für jeden Intent einen zugehörigen IntentHandler. Dieser wird aufgerufen, wenn die passende Eingabe (evtl. mit Slot) angegeben wird. 
##### Handler
Folgende Handler sind im Skill 'Tasche packen' vorhanden:
- PackInBagStreamHandler: Dieser Handler wird bei Aufruf des Skills aufgerufen und erzeugt alle weiteren Handler.
- LaunchRequestHandler: Dieser Handler wird direkt nach dem Aufruf des Skills gestartet und heißt den User willkommen.
- WelcomeIntentHandler: Dieser Handler folgt auf den LaunchRequestHandler und fragt den User, ob er alle Fächer besuchen will.
- AidIntentHandler: Dieser Handler bietet eine Hilfe-Funktion, bei der alle möglichen Funktionen aufgezählt werden. 
- GetSubjectToChangeIntentHandler: Hier kann man einen Gegenstand zu einem Fach hinzufügen oder entfernen.
- AddOrRemoveItemIntentHandler: Dieser Handler gibt eine Rückmeldung über das erfolgreiche Hinzufügen/Entfernen eines Gegenstandes.
- GoodbyeIntentHandler: Dieser Handler beendet den Skill. 

### Datenbankanbindung
### Setup
Um die Datenbank benutzen zu können muss in der jeweiligen Lambda-Funktion zunächst die Dynamo-DB ausgewählt werden. Damit die Datenbank auch alle Berechtigungen hat müssen der lambda_basic_execution Rolle noch weitere Berechtigungen hinzugefügt werden

### ZPA-Anbindung
