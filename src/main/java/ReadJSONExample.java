import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class ReadJSONExample {
    public static void main(String[] args) throws IOException, ParseException
    {
        // String of JSON data
        StringBuilder jsonData = new StringBuilder();

        // Get JSON data
        URL url = new URL("https://statsapi.web.nhl.com/api/v1/teams");

        // Convert JSON data to string
        Scanner sc = new Scanner(url.openStream());
        while (sc.hasNext())
        {
            jsonData.append(sc.nextLine());
        }
        sc.close();

        // SET UP JSON PARSER
        JSONParser jsonParser = new JSONParser();

        // Convert JSON String to Java JSON object
        JSONObject jsonDataObject = (JSONObject) jsonParser.parse(jsonData.toString());

        // Go into the JSON object one level to get  the teams array. Cast it to a JSONArray.
        JSONArray teamsList = (JSONArray) jsonDataObject.get("teams");

        // Iterate over team teamList
        for (Object team : teamsList){
            // Cast it to a JSONObject before calling the parsing method.
            parseTeamObject((JSONObject) team);
        }
    }

    private static void parseTeamObject(JSONObject team)
    {
        // Each team is now a JSONObject and properties can now be accessed using .get

        // Get team object within teamList
        String teamName = (String) team.get("teamName");
        System.out.println(teamName);
    }

}
