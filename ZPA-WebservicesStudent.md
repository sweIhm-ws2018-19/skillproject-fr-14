# Webservices Student #
Dies ist die Dokumentation der Webservices für student im ZPA System.

## 1. Webservice `/student/ws_get_week_plan/` ##

### 1.1 Aufruf ###
Aufruf als HTTP-GET mit Parameter `date` im Format `yyyy-mm-dd`. Beispiel:

        https://w3-o.cs.hm.edu:8000/student/ws_get_week_plan/?date=2016-10-04

### 1.2 Response ###
Rückgabewert ist json-kodierter Datensatz. Beispiel:

        {"error_code": 0, "slots": [slot_1, ..., slot_n]}

`slots` ist eine Liste von Dictionaries (assoziativen Arrays). Beispiele für slots:

        {"rooms": ["R0.058"],
        "groups": ["IB3A", "IB3B"],
        "type": "regular",
        "teachers": ["Möncke, U."],
        "starttime": "13:30",
        "endtime": "15:00",
        "date": "2016-10-07",
        "weekday": "Fr",
        "modules": ["Datenschutz"],
        "plan_change": false,
        "descriptions": [""]}

        {"type": "regular",
        "teachers": ["Möncke, U."],
        "room_changed": true,
        "plan_change": true,
        "canceled": false,
        "moved": false,
        "date": "2016-10-06",
        "descriptions": [""],
        "rooms": ["R0.058"],
        "starttime": "17:00",
        "groups": ["IB3A", "IB3B"],
        "change_description": "",
        "endtime": "18:30",
        "weekday": "Do",
        "modules": ["Datenschutz"],
        "alt_rooms": ["R0.011"]}

        {"type": "regular",
        "teachers": ["Schwenkert, R."],
        "date": "2016-10-06",
        "descriptions": ["Praktikum, 1. Teilgruppe"],
        "rooms": ["R2.009"],
        "starttime": "08:15",
        "groups": ["IB3A"],
        "plan_change": true,
        "room_changed": false,
        "canceled": true,
        "change_description": "MVV Streik",
        "moved": false,
        "endtime": "09:45",
        "weekday": "Do",
        "modules": ["Datenbanksysteme (IB)"]}

        {"type": "replace",
        "starttime": "10:00",
        "endtime": "11:30",
        "rooms": ["R2.005"],
        "groups": ["IB3A"],
        "weekday": "Mi",
        "teachers": ["Schwenkert, R."],
        "description": "Datenbanksysteme (IB) (Ersatztermin für 06.10.2016)",
        "date": "2016-10-05"}

Die Liste der Slots umfasst alle Buchungen für die Woche, in der der übergebene Parameter `date` liegt.
Dabei beginnt die Woche mit Montag. Ist `date` ein Sonntag, so werden die Buchungen der zurückliegenden Woche
zurückgegeben.

#### 1.2.1 `error_code` ####

Liste der `error_code`:

- `0` Response enthält Slots, alles gut
- `1` Parameter `date` fehlt
- `2` Fehlerhaftes Datumsformat
- `3` Response enthält auch fehlerhafte / unvollständige Daten
- `4` Response enthält eine leere Liste `slots`
- `111` User nicht eingelogged

#### 1.2.2 Spezifikation der `slot`-Dictionaries ####

Die Reihenfolge der Attribute in einem `slot`-Element ist zufällig. Man sollte also per Key auf
die Attribute zugreifen.

Keys, die jeder `slot` enthält:

- `type` Typ des Slots (Beschreibung siehe unten)
- `date` Datum des Slots (`string` der Form `"2016-10-04"`)
- `day` Wochentag des Slots (`string` der Form `"Mo"`, ..., `"So"`)
- `starttime` Beginn des Slots (`string` der Form `"08:15"` oder `"n.a."`)
- `endtime` Ende des Slots (`string` der Form `"08:15"` oder `"n.a."`)
- `teachers` Dozenten bzw. Veranstalter bzw. Prüfungsaufsichten (eine Liste aus `string`; meist nur ein Element)
- `rooms` Räume (eine Liste aus `string`; meist nur ein Element)

Der Key `type` kann folgende Werte annehmen:

- `regular` eine Lehrveranstaltung nach Plan
- `single` eine einzelne Buchung (z.B. Veranstaltung)
- `replace` eine Ersatz-Lehrveranstaltung (z.B. in einem anderen Raum oder zu einer anderen Zeit)
- `extra` ein zusätzlicher Termin für eine Lehrveranstaltung (z.B. Vorbesprechung)
- `exam` eine Prüfungsinstanz (in einem Raum mit einer Aufsicht)
- `exam_all` eine Prüfung (mit allen Räumen und Aufsichten)

Abhängig vom Key `type` hat ein `slot` weitere Attribute.

##### Zusätzliche Attribute beim Key `type = regular` #####

- `descriptions` Nähere Beschreibungen für die Lehrveranstaltung (eine Liste aus `string`; meist nur ein Element)
- `modules` Name des Moduls (der Module) (eine Liste aus `string`; meist nur ein Element)
- `groups` Studiengruppen dieser LV (eine Liste aus `string`)
- `plan_change` Boolsche Variable. Falls `true` gibt es eine Planänderung für diese LV an diesem Tag

Falls `plan_change = true`, so gibt es folgende Attribute zusätzlich:

- `change_description` Beschreibung der Planänderung (`string`)
- `canceled` Boolean. Falls `true` fällt die LV aus
- `room_changed` Boolean. Falls `true` hat sich der Raum an diesem Termin geändert
- `moved` Boolean. Falls `true` wurde der Termin verschoben

Falls `room_changed = true` so gibt es ein weiteres Attribut:

- `alt_rooms` Die alternativen Räume (eine Liste aus `string`; meist nur ein Element)

Falls `moved = true`, so gibt es folgende weitere Attribute:

- `alt_rooms` Die alternativen Räume (eine Liste aus `string`; meist nur ein Element)
- `alt_date` Das alternative Datum (`string` der Form `"2016-10-04"`)
- `alt_time` Die alternative Start-Zeit (`string` der Form `"08:15"`)

##### Zusätzliche Attribute beim Key `type = single` #####

- `description` Beschreibung für diese Buchung (`string`)

##### Zusätzliche Attribute beim Key `type = replace` #####

- `description` Beschreibung für diese Buchung (`string`)
- `groups` Studiengruppen dieser LV (eine Liste aus `string`)

##### Zusätzliche Attribute beim Key `type = extra` #####

- `description` Beschreibung für diese Buchung (`string`)
- `groups` Studiengruppen dieser LV (eine Liste aus `string`)

##### Zusätzliche Attribute beim Key `type = exam` und `type = exam_all` #####

- `modules` Name des Moduls (eine Liste aus `string`; hier nur ein Element)
- `groups` Studiengruppen dieser Prüfung (eine Liste aus `string`)
- `main_examer` der Prüfer (Name als `string`)

Bei `type = exam` enthält `teachers` lediglich den Namen der Aufsichtsperson im Raum `rooms`.
`endtime` gibt das Ende der Prüfung in diesem Raum an (inklusive Nachteilsausgleich).

Bei `type = exam_all` enthält `teachers` die Namen aller Aufsichtsperson, `rooms` alle Prüfungsräume und `endtime` gibt das
Ende der Prüfung insgesamt an (inklusive aller Nachteilsausgleiche).



