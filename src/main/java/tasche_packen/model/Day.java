package tasche_packen.model;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day {

    /** Map that saves the login-data and necessary tokens to login into the ZPA-system. Out of security reasons
     *  the contents of the Map will be (filled and) deleted automatically in the @method requestZPA */
    private final Map<String, String> loginData = new HashMap<>();

    /** The list of lectures of the current day */
    private List<String> lectures = new ArrayList<>();

    private boolean zpaLoginSuccessful;

    /** Returns a list of the lectures that are demoed today */
    public List<String> getLectures() {
        /* If the login to the ZPA system wasn't successful an alternative hardcoded list will be returned */
        requestZPA();
        if (!zpaLoginSuccessful) {
            //requestZPA();
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

    /** A HTTP-GET request will be sent to the ZPA-system. The ZPA-system will return a token which will be
     *  needed for the login. The token is saved in the Map logindata */
    private void putCSRFTokenToLoginData() throws IOException
    {
        URL url = new URL("https://w3-o.cs.hm.edu:8000/login/ws_get_csrf_token/");

        try (BufferedReader fromZPA = new BufferedReader(new InputStreamReader(url.openStream())))
        {
            /* Because the ZPA-system only returns one line containing the csrfmiddlewaretoken, we don't have to
             use a clever way of filtering the token. A simple substring will do the trick every time. */
            String line = fromZPA.readLine();
            loginData.put("csrfmiddlewaretoken", line.substring(25, line.length() - 2 ));

        } catch (IOException e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "an IOException was thrown", e);
        }
    }

    /** requests the ZPA for the weekplan. After retrieving the weekplan the method fills the List lectures */
    private void requestZPA()
    {
        zpaLoginSuccessful = false; /* Will be updated after successful login*/

        /* Reads the accountdata from the textfile Accountdata.txt */
        final String filename = "src\\main\\java\\tasche_packen\\Accountdata.txt";
        try(BufferedReader accountDataReader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while((line = accountDataReader.readLine()) != null) {
                if(!line.startsWith("//")) {
                    String loginVariable = line.split("=", 2)[0];
                    String valueOfVariable = line.split("=", 2)[1];
                    loginData.put(loginVariable, valueOfVariable);
                }
            }

            putCSRFTokenToLoginData();
            zpaLogin();

            /* The first 10 letters in LocalDateTime.now give us the current Date */
            final String currentDate = LocalDateTime.now().toString().substring(0,10);
            lectures = new ArrayList<>();

            /* Requesting the schedule of this week if the login was a success*/
            if (zpaLoginSuccessful) {
                final String url = "https://w3-o.cs.hm.edu:8000/student/ws_get_week_plan/";
                //URL obj = new URL(url);
                HttpClient httpClient = HttpClients.createDefault();
                HttpPost httppost = new HttpPost(url);

                // Request parameters and other properties
                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("date", currentDate));
                params.add(new BasicNameValuePair("csrftoken", loginData.get("csrfmiddlewaretoken")));
                httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

                // Execute and get the response
                HttpResponse response = httpClient.execute(httppost);
                HttpEntity entity = response.getEntity();

                //HttpURLConnection zpa_connection = (HttpURLConnection) obj.openConnection();
                //zpa_connection.setRequestProperty("Cookie", "csrftoken=" + loginData.get("csrfmiddlewaretoken"));
                if (entity != null) {
                    /*try (JsonReader jsonReader = Json.createReader(entity.getContent())) {
                        JsonObject answer_from_ZPA = jsonReader.readObject();
                        JsonArray slots = answer_from_ZPA.getJsonArray("slots");
                        System.out.println("The answer from ZPA is: " + answer_from_ZPA);
                        System.out.println("\n The slots are: " + slots);
                        for (JsonObject slot : slots.getValuesAs(JsonObject.class)) {
                            final boolean is_regular_lecture = slot.getString("type").equals("regular");
                            final boolean class_is_today = slot.getString("date").equals(currentDate);
                            final boolean class_not_cancelled = !(is_regular_lecture &&
                                    slot.getString("plan_change").equals("true") &&
                                    slot.getBoolean("canceled"));

                            if (is_regular_lecture && class_is_today && class_not_cancelled)
                                lectures.add(answer_from_ZPA.getString("modules"));
                        }

                    }*/
                    try(BufferedReader fromZPA = new BufferedReader(new InputStreamReader(entity.getContent()))) {
                        System.out.println("This is the weekplan:");
                        while((line = fromZPA.readLine()) != null)
                            System.out.println(line);
                    }

                }
            }
        } catch (IOException e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "an IOException was thrown", e);
        }
    }

    /** With a HTTP-POST request we can login into the ZPA system. The needed parameters username, password and
     *  csrfmiddlewaretoken will be read out of the Map logindata */
    private void zpaLogin() throws IOException {

        final String url = "https://w3-o.cs.hm.edu:8000/login/ws_login/";
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);

        // Request parameters and other properties
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", loginData.get("username")));
        params.add(new BasicNameValuePair("password", loginData.get("password")));
        params.add(new BasicNameValuePair("csrftoken", loginData.get("csrfmiddlewaretoken")));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        // Execute and get the response
        HttpResponse response = httpClient.execute(httppost);
        HttpEntity entity = response.getEntity();

        if (entity != null)
            try (BufferedReader fromZPA = new BufferedReader(new InputStreamReader(entity.getContent()))) {
                String line = fromZPA.readLine();
                // the login was successful
                if (line.contains("\"error_code\": 0")) {
                    zpaLoginSuccessful = true;
                    System.out.println(line);
                    final String newTokenPattern = "\\{.*\".*csrfmiddlewaretoken\":.*?\"(\\w*)\".*";
                    loginData.put("csrfmiddlwaretoken", line.replaceFirst(newTokenPattern, "$1"));
                    }
                }

            }

    public static void main (String... args){
        System.out.println(new Day().getLectures());
    }
}
