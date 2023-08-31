import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your key: ");
        String key = scan.nextLine();
        System.out.println("Key entered: " + key);


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://odds.p.rapidapi.com/v4/sports/baseball_mlb/scores"))
                .header("X-RapidAPI-Key", key)
                .header("X-RapidAPI-Host", "odds.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        HttpRequest requestTwo = HttpRequest.newBuilder()
                .uri(URI.create("https://odds.p.rapidapi.com/v4/sports/upcoming/odds?regions=us&oddsFormat=decimal&markets=h2h%2Cspreads&dateFormat=iso"))
                .header("X-RapidAPI-Key", key)
                .header("X-RapidAPI-Host", "odds.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> responseTwo = HttpClient.newHttpClient().send(requestTwo, HttpResponse.BodyHandlers.ofString());
        System.out.println(responseTwo.body());
        //Starting my own code
        System.out.println(response.headers());



        String resOne = response.toString();
        String restTwo = responseTwo.toString();
        //JSON Parsing coming
        //JSONParser parser = new JSONParser();

        //Where MLB starts
        System.out.println("STARTING AT MLB");
        int location = responseTwo.body().indexOf("baseball_mlb");

        //Location of home teams
        int homeLocation = responseTwo.body().indexOf("home_team", location + 1);
        int homeLocationTwo = responseTwo.body().indexOf("home_team", homeLocation + 1);

        System.out.println(responseTwo.body().substring(location, responseTwo.body().length()));
        System.out.println(homeLocation);
        System.out.println(homeLocationTwo);



    }
}