package tasche_packen.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_CRSF_URL = "/login/ws_get_csrf_token/";

    private static final String LOGIN_URL = "/login/ws_login/";

    private static final String LOGOUT_URL = "/login/ws_logout/";

    private static final String CALENDAR_URL = "/student/ws_get_week_plan/";

    private static final String BASE_URL = "https://w3-o.cs.hm.edu:8000";

    private static final String userAgent = "User-Agent";

    private static final String setCookie = "Set-Cookie";

    private static final String cookie = "Cookie";

    /**
     * Map that saves the login-data and necessary tokens to login into the ZPA-system. Out of security reasons
     * the contents of the Map will be (filled and) deleted automatically in the @method requestZPA
     */
    private final Map<String, String> loginData = new HashMap<>();

    /**
     * The list of lectures of the current day
     */
    private List<String> lectures = new ArrayList<>();

    private final CookieManager cookieManager = new CookieManager();
    private final CookieStore cookieStore = cookieManager.getCookieStore();

    /**
     * Will be resetted on every call of the method requestZPA. It is used to check, if we need to
     * an alternative output in case the login to the ZPA was not successful
     */
    private boolean zpaLoginSuccessful;
    boolean gotWeekplan = false;

    private final Logger logger = Logger.getAnonymousLogger();

    /**
     * Returns a list of the lectures that are demoed today
     */
    public List<String> getLectures() {
        zpaLoginSuccessful = false;
        gotWeekplan = false;

        /* If the login to the ZPA system wasn't successful an alternative hardcoded list will be returned */
        requestZPA();
        logger.log(Level.INFO, "ZPALogin was successful: " + zpaLoginSuccessful);
        if (!(zpaLoginSuccessful && gotWeekplan)) {
            lectures = new ArrayList<>();

            java.util.Calendar calendar = java.util.Calendar.getInstance();
            int day = calendar.get(java.util.Calendar.DAY_OF_WEEK);
            switch (day) {
                case java.util.Calendar.MONDAY:
                    lectures.add("Netzwerke");
                    lectures.add("Datenbanksysteme");
                    break;
                case java.util.Calendar.TUESDAY:
                    lectures.add("Software");
                    lectures.add("Numerik");
                    lectures.add("Datenbanksysteme");
                    break;
                case java.util.Calendar.WEDNESDAY:
                    lectures.add("Numerik");
                    break;
                case java.util.Calendar.THURSDAY:
                    lectures.add("Algorithmen");
                    lectures.add("Wahrscheinlichkeitstheorie");
                    break;
                case java.util.Calendar.FRIDAY:
                    lectures.add("Software");
                    lectures.add("Wahrscheinlichkeitstheorie");
                    lectures.add("Algorithmen");
                    break;
                //zum Testen am Sonntag
                case java.util.Calendar.SUNDAY:
                    lectures.add("Software");
                    lectures.add("Netzwerke");

            }
        }
        return lectures;
    }

    /**
     * requests the ZPA for the weekplan. After retrieving the weekplan the method fills the List lectures
     */
    private void requestZPA() {
        /* The ZPA uses cookies to verify the user */
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
        cookieStore.removeAll();

        //**************************************************************/
        /* Please enter your ZPA-Logindata between the quotation marks */
        final String username = "";
        final String password = "";

        //**************************************************************/
        try {
            lectures = new ArrayList<>();
            loginData.put("username", username);
            loginData.put("password", password);
            putCSRFTokenToLoginData();
            zpaLogin();
            if (zpaLoginSuccessful) {
                getWeekplan();
                zpaLogout();
                adjustLectures();
            }

        } catch (IOException e) {
            logger.log(Level.WARNING, "An IOException was thrown");
        }


    }

    /**
     * A HTTP-GET request will be sent to the ZPA-system. The ZPA-system will return a token which will be
     * needed for the login. The token is saved in the Map logindata
     */
    private void putCSRFTokenToLoginData() throws IOException {
        /* Preparing the HTTP Get request to retrieve the csrftoken */
        final URL obj = new URL(BASE_URL + GET_CRSF_URL);
        final HttpURLConnection zpaconnection = (HttpURLConnection) obj.openConnection();
        zpaconnection.setRequestMethod("GET");
        zpaconnection.setRequestProperty(userAgent, USER_AGENT);
        final int responseCode = zpaconnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            final StringBuffer response = new StringBuffer();
            try (BufferedReader fromZPA = new BufferedReader(new InputStreamReader(zpaconnection.getInputStream()))) {
                String line;
                while ((line = fromZPA.readLine()) != null)
                    response.append(line);
            }

            /* Managing the cookies */
            String cookiesHeader = zpaconnection.getHeaderField(setCookie);
            List<HttpCookie> cookieList = HttpCookie.parse(cookiesHeader);
            for (HttpCookie cookie : cookieList)
                cookieManager.getCookieStore().add(null, cookie);

        } else {
            logger.log(Level.WARNING, "Was not able to get csrftoken");
        }
    }

    /**
     * With a HTTP-POST request we can login into the ZPA system. The needed parameters username, password and
     * csrfmiddlewaretoken will be read out of the Map logindata
     */
    private void zpaLogin() throws IOException {

        for (int counter = 0; !zpaLoginSuccessful && counter < 10; counter++) {
            /* Managing the cookies */
            List<HttpCookie> cookieList = cookieStore.getCookies();
            String cookiestring = "";
            for (HttpCookie cookie : cookieList)
                cookiestring += cookie.getName() + "=" + cookie.getValue() + ";";

            /* Preparing the POST request for the login */
            URL obj = new URL(BASE_URL + LOGIN_URL);
            HttpURLConnection zpaconnection = (HttpURLConnection) obj.openConnection();
            zpaconnection.setRequestMethod("POST");
            zpaconnection.setRequestProperty(userAgent, USER_AGENT);
            zpaconnection.setDoOutput(true);
            zpaconnection.setRequestProperty(cookie, cookiestring);


            String answerFromZPA = "";
            try (OutputStream toZPA = zpaconnection.getOutputStream()) {
                String loginstr = "username=" + loginData.get("username") + "&password=" + loginData.get("password");
                toZPA.write(loginstr.getBytes());
                try (final BufferedReader fromZPA = new BufferedReader(new InputStreamReader(zpaconnection.getInputStream()))) {
                    String line;
                    while ((line = fromZPA.readLine()) != null)
                        answerFromZPA += line;
                }
                zpaLoginSuccessful = answerFromZPA.contains("error_code\": 0");
            }

            final int responseCode = zpaconnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { // connection successful
                /* Managing the cookies. This is also where we get the token */
                String cookiesHeader = zpaconnection.getHeaderField(setCookie);
                if (cookiesHeader != null && !cookiesHeader.isEmpty()) {
                    cookieList = HttpCookie.parse(cookiesHeader);
                    for (HttpCookie cookie : cookieList)
                        cookieManager.getCookieStore().add(null, cookie);
                }
            } else {
                logger.log(Level.WARNING, "LOGIN request did not work");
            }
        }
    }


    private void getWeekplan() throws IOException {

        final String currentDate = LocalDateTime.now().toString().substring(0, 10);


        /* Sometimes the zpa doesn't send the weekplan or the connection fails. If thats the case, we take up to
         * 10 tries to retrieve the weekplan */
        for (int counter = 0; !gotWeekplan && counter < 10; counter++) {

            /* Preparing the HTTP Post for retrieving the weekplan */
            URL obj = new URL(BASE_URL + CALENDAR_URL + "?date=" + currentDate);
            HttpURLConnection zpaconnection = (HttpURLConnection) obj.openConnection();
            zpaconnection.setRequestMethod("GET");
            zpaconnection.setRequestProperty(userAgent, USER_AGENT);
            zpaconnection.setRequestProperty("Accept", "application/x-www-form-urlencoded");

            /* Managing the cookies */
            List<HttpCookie> cookieList = cookieStore.getCookies();
            String cookiestring = "";
            for (HttpCookie cookie : cookieList)
                cookiestring += cookie.getName() + "=" + cookie.getValue() + ";";
            zpaconnection.setRequestProperty(cookie, cookiestring);

            /* Continue with the request if the response code is OK */
            int responseCode = zpaconnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader fromZPA = new BufferedReader(new InputStreamReader(zpaconnection.getInputStream()))) {
                    String line;
                    String response = "";
                    while ((line = fromZPA.readLine()) != null) {
                        response += line;
                    }

                    /* The ZPA response will be casted to a JSONObject to retrieve all the
                     *  lectures you meet the conditions specified down below in the if clause*/
                    try {
                        JSONObject object = new JSONObject(response);
                        JSONArray slots = object.getJSONArray("slots");
                        Iterator<Object> iterator = slots.iterator();
                        gotWeekplan = true;
                        while (iterator.hasNext()) {
                            final JSONObject slot = (JSONObject) iterator.next();
                            final String slotAsString = slot.toString();
                            final String lectureRaw = slot.getJSONArray("modules").toString();
                            final String lecture = lectureRaw.substring(2, lectureRaw.length() - 2);
                            final boolean is_regular_lecture = slotAsString.contains("regular");
                            final boolean lecture_is_today = slotAsString.contains(currentDate);
                            final boolean lecture_canceled = slotAsString.contains("plan_change\": true") && slotAsString.contains("canceled\": true");
                            final boolean lecture_already_added = lectures.contains(lecture);
                            if (is_regular_lecture && lecture_is_today && !lecture_canceled && !lecture_already_added)
                                lectures.add(lecture);
                        }
                    } catch (JSONException e) {
                        logger.log(Level.WARNING, "A JSONException was thrown");
                    }
                }
            } else {
                logger.log(Level.WARNING, "getWeekplan request not worked");
            }
        }
    }

    private void zpaLogout() throws IOException {

        /* Managing the cookies */
        List<HttpCookie> cookieList = cookieStore.getCookies();
        String cookiestring = "";
        for (HttpCookie cookie : cookieList)
            cookiestring += cookie.getName() + "=" + cookie.getValue() + ";";


        /* Preparing the POST request for the logout */
        URL obj = new URL(BASE_URL + LOGOUT_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty(userAgent, USER_AGENT);
        con.setRequestProperty(cookie, cookiestring);
        con.setDoOutput(true);

        /* Sending the data necessary for the logout */
        try (OutputStream toZPA = con.getOutputStream()) {
            final String loginstr = "username=" + loginData.get("username") + "&password=" + loginData.get("password");
            toZPA.write(loginstr.getBytes());
            toZPA.flush();
        }

        final int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { //// success
            try (BufferedReader fromZPA = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String line;
                StringBuffer response = new StringBuffer();

                while ((line = fromZPA.readLine()) != null) {
                    response.append(line);
                }

                /* Managing the cookies */
                final String cookiesHeader = con.getHeaderField(setCookie);
                if (cookiesHeader != null) {
                    if ((cookieList = HttpCookie.parse(cookiesHeader)) != null)
                        for (HttpCookie cookie : cookieList)
                            cookieManager.getCookieStore().add(null, cookie);
                }
            }
        } else {
            logger.log(Level.WARNING, "LOGOUT request not worked");
        }
        cookieStore.removeAll();
    }


    /**
     * Strings in the list lectures will be adjusted to our needs
     * Strings will be cut off at their first blank and the lecture
     * "Numerische Mathematik" (now "Numerische") becomes "Numerik"
     */
    private void adjustLectures() {
        final List<String> lecturesUpdated = new ArrayList<>();
        if (lectures.remove("Numerische Mathematik"))
            lecturesUpdated.add("Numerik");
        for (String lecture : lectures) {
            if (lecture.contains(" "))
                lecturesUpdated.add(lecture.substring(0, lecture.indexOf(' ')));
            else
                lecturesUpdated.add(lecture);
        }
        lectures = lecturesUpdated;
    }
}