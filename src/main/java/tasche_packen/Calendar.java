package main.java.tasche_packen;
import javax.json.*;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calendar {

    /** Map that saves the login-data and necessary tokens to login into the ZPA-system. Out of security reasons
     *  the contents of the Map will be (filled and) deleted automatically in the @method requestZPA */
    private final Map<String, String> loginData = new HashMap<>();

    /** The list of lectures of the current day */
    private List<String> lectures = new ArrayList<>();

    /** Returns a list of the lectures that are demoed today */
    public List<String> getTodayLectures() {
        //requestZPA();
        lectures = new ArrayList<>();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int day = calendar.get(java.util.Calendar.DAY_OF_WEEK);
        switch(day) {
            case java.util.Calendar.MONDAY:
                lectures.add("Netzwerke");
                lectures.add("Datenbanksysteme");
                break;
            case java.util.Calendar.TUESDAY:
                lectures.add("Software Engineering");
                lectures.add("Numerische Mathematik");
                lectures.add("Datenbanksysteme");
                break;
            case java.util.Calendar.WEDNESDAY:
                lectures.add("Numerische Mathematik");
                break;
            case java.util.Calendar.THURSDAY:
                lectures.add("Algorithmen und Datenstrukturen");
                lectures.add("Wahrscheinlichkeitstheorie und Statistik");
                break;
            case java.util.Calendar.FRIDAY:
                lectures.add("Software Engineering");
                lectures.add("Wahrscheinlichkeitstheorie und Statistik");
                lectures.add("Algorithmen und Datenstrukturen");
                break;
        }
        return lectures;
    }

    /** A HTTP-GET request will be sent to the ZPA-system. The ZPA-system will return a token which will be
     *  needed for the login. The token is saved in the Map logindata */
    private void put_CSRF_Token_to_Login_Data() throws MalformedURLException
    {
        URL url = new URL("https://w3-o.cs.hm.edu:8000/login/ws_get_csrf_token/");
        final String tokenPattern = "(\"csrfmiddlewaretoken\": \")\\w*(\")";

        try (BufferedReader fromZPA = new BufferedReader(new InputStreamReader(url.openStream())))
        {

            /* Because the ZPA-system only returns one line containing the csrfmiddlewaretoken, we don't have to
             use a clever way of filtering the token. A simple substring will do the trick everytime. */
            String line = fromZPA.readLine();
            loginData.put("csrfmiddlewaretoken", line.substring(25, line.length() - 2 ));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void requestZPA()
    {
        /* Reads the accountdata from the textfile Accountdata.txt */
        final String filename = "src\\main\\java\\tasche_packen\\Accountdata.txt";
        try(BufferedReader accountDataReader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while((line = accountDataReader.readLine()) != null) {
                String loginVariable = line.split("=", 2)[0];
                String valueOfVariable = line.split("=", 2)[1];
                loginData.put(loginVariable,valueOfVariable);
            }

            put_CSRF_Token_to_Login_Data();

            System.out.println("Die Login Daten, die in der Map stehen: ");
            System.out.println("username: " + loginData.get("username"));
            System.out.println("password: " + loginData.get("password"));
            System.out.println("csrfmiddlewaretoken: " + loginData.get("csrfmiddlewaretoken"));

            zpa_login();

            /* The first 10 letters in LocalDateTime.now give us the current Date */
            final String currentDate = LocalDateTime.now().toString().substring(0,10);
            lectures.removeAll(lectures);

            /* Requesting the schedule of this week */
            /*
            final String url = "https://w3-o.cs.hm.edu:8000/student/ws_get_week_plan/?date=" + currentDate;
            URL obj = new URL(url);
            HttpURLConnection zpa_connection = (HttpURLConnection) obj.openConnection();

            try(JsonReader jsonReader = Json.createReader(zpa_connection.getInputStream()))
            {
                JsonObject answer_from_ZPA = jsonReader.readObject();
                JsonArray slots = answer_from_ZPA.getJsonArray("slots");

                for(JsonObject slot: slots.getValuesAs(JsonObject.class)) {
                    final boolean is_regular_lecture = slot.getString("type").equals("regular");
                    final boolean class_is_today = slot.getString("date").equals(currentDate);
                    final boolean class_not_cancelled = !(is_regular_lecture &&
                            slot.getString("plan_change").equals("true") &&
                            slot.getBoolean("canceled"));

                    if ( is_regular_lecture  &&  class_is_today  &&  class_not_cancelled )
                        lectures.add(answer_from_ZPA.getString("modules"));
                }

            }
*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** With a HTTP-POST request we can login into the ZPA system. The needed parameters username, password and
     *  csrfmiddlewaretoken will be read out of the Map logindata */
    private void zpa_login() throws IOException {

        final String url = "https://w3-o.cs.hm.edu:8000/login/ws_login/";
        URL obj = new URL(url);
        HttpURLConnection zpa_connection = (HttpURLConnection) obj.openConnection();

        zpa_connection.setRequestMethod("POST");
        zpa_connection.setRequestProperty("Accept", "application/x-www-from-urlencoded" );
        zpa_connection.setRequestProperty("csrfmiddlewaretoken", loginData.get("csrfmiddlewaretoken") );
        zpa_connection.setRequestProperty("Cookie", "csrftoken=" + loginData.get("csrfmiddlewaretoken"));
        zpa_connection.setDoOutput(true);

        JsonObject objectToBeSent = Json.createObjectBuilder()
                .add("csrfmiddlewaretoken", loginData.get("csrfmiddlewaretoken"))
                .add("username", loginData.get("username"))
                .add("password", loginData.get("password"))
                .build();

        System.out.println("\nThis is the Json sent: " +objectToBeSent);

        try (JsonWriter jsonWriter = Json.createWriter(zpa_connection.getOutputStream())){
            jsonWriter.writeObject(objectToBeSent);
            try(JsonReader jsonReader = Json.createReader(zpa_connection.getInputStream())) {
                System.out.println("\nAnswer from the server: " + jsonReader.readObject());
            }
        }
    }
}
