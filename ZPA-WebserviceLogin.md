# Webservices ZPA Login #
Dies ist die Dokumentation der Webservices für login und logout im ZPA System.

## 1. Webservice `/login/ws_get_csrf_token/` ##

Aufruf als HTTP-GET ohne Parameter.

        https://w3-o.cs.hm.edu:8000/login/ws_get_csrf_token/

Rückgabewert ist ein CSRF-Token im json Format. Beispiel:

        {"csrfmiddlewaretoken": "GzgGrjrWnNpCwgjJMKSLJmwGFQULrttl"}

Dieses Token muss beim nachfolgenden Aufruf eines `ws_login` als Parameter mitgegeben werden.


## 2. Webservice `/login/ws_login/` ##

Aufruf als HTTP-POST mit den POST-Parametern

- csrfmiddlewaretoken
- username
- password

        https://w3-o.cs.hm.edu:8000/login/ws_login/

Das `csrfmiddlewaretoken` muss identisch sein mit dem zuvor via `ws_get_csrf_token` erhaltenen.

Rückgabewert im json-Format:

        {"csrfmiddlewaretoken": "JhsgtZztsgdsjBjshswzjsllk", "error_code": 0}

Liste der `error_code`:

- `0` login erfolgreich
- `1` kein HTTP-POST
- `2` Parameter username fehlt
- `3` Parameter password fehlt
- `4` Authentifizierung fehlgeschlagen
- `5` Account ist inaktiv

Das neue `csrfmiddlewaretoken` muss beim nachfolgenden POST-Aufruf verwendet werden.


## 2. Webservice `/login/ws_logout/` ##

Aufruf als HTTP-POST mit dem POST-Parameter

- csrfmiddlewaretoken

        https://w3-o.cs.hm.edu:8000/login/ws_logout/

Rückgabewert im json-Format:

        {"error_code": 0}

Liste der `error_code`:

- `0` login erfolgreich
- `1` kein HTTP-POST
