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

    private final Map<String, String> loginData = new HashMap<>();
    private List<String> lectures = new ArrayList<>();

    private void requestZPA()
    {
        /* Reads the accountdata from the textfile */
        try(BufferedReader accountDataReader = new BufferedReader(new FileReader("C:\\Users\\marti\\Desktop\\Studium\\3. Semester\\Software Engineering\\skillproject-fr-14\\tasche_packen\\src\\Accountdata.txt")))
        {
            String line;
            while((line = accountDataReader.readLine()) != null) {
                String loginVariable = line.split("=", 2)[0];
                String valueOfVariable = line.split("=", 2)[1];
                loginData.put(loginVariable,valueOfVariable);
            }

            put_CSFR_Token_to_Login_Data();

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

    public List<String> getTodayLectures() {
        requestZPA();
        return lectures;
    }
    public static void main (String... args) {
        new Calendar().requestZPA();

    }

    private void put_CSFR_Token_to_Login_Data() throws MalformedURLException
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

    private void zpa_login() throws IOException {

        final String url = "https://w3-o.cs.hm.edu:8000/login/ws_login/?username=&password=&csrfmiddlewaretoken=" + loginData.get("csrfmiddlewaretoken");
        URL obj = new URL(url);
        HttpURLConnection zpa_connection = (HttpURLConnection) obj.openConnection();

        zpa_connection.setRequestMethod("POST");
        zpa_connection.setRequestProperty( "Accept", "application/json" );
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
