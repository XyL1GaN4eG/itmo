import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class WeatherApiRequest {

    public static void main(String[] args) {
        System.out.println("йоу");
        try {
            // Формируем URL для запроса
            String url = "http://api.weatherapi.com/v1/current.json?key=b754ca0088e64db9afe102203240306&q=Saint-Petersburg&aqi=yes";

            // Создаем HTTP-запрос
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Создаем HTTP-клиент и отправляем запрос
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Выводим код ответа и тело ответа
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

            // Парсим JSON-ответ с использованием json-simple
            JSONParser parser = new JSONParser();
            JSONObject jsonResponse = (JSONObject) parser.parse(response.body());
            JSONObject current = (JSONObject) jsonResponse.get("current");
            double temperature = (double) current.get("temp_c");
            JSONObject locationObj = (JSONObject) jsonResponse.get("location");
            String location = (String) locationObj.get("name");
            JSONObject conditionObj = (JSONObject) current.get("condition");
            String condition = (String) conditionObj.get("text");

            // Создаем объект WeatherData
            WeatherData weatherData = new WeatherData(temperature, location, condition);

            // Сохраняем объект в базу данных
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("weatherPU");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            em.persist(weatherData);
            em.getTransaction().commit();

            em.close();
            emf.close();

            System.out.println("Weather data saved to database.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
