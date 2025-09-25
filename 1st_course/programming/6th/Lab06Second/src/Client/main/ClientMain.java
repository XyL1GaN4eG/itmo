package Client.main;

import General.dataClasses.TypesOfArguments;
import General.net.Request;

import java.util.HashMap;

public class ClientMain {
    public static void main(String[] args) {
        // Создаем клиент и запрос
        Client client = new Client();
//        var in = new Scanner(System.in);
        Request request;
        HashMap<String, TypesOfArguments> collection;

        //получаем коллекцию для валидации

        request = new Request("i");

        // Отправляем запрос и выводим ответ
//        var response =  client.sendRequest(request);
        collection = (HashMap<String, TypesOfArguments>) client.sendRequest(request);
        System.out.println(collection);
        while (true) {
            request = Validation.validate(collection);

            // Отправляем запрос и выводим ответ
            System.out.println(client.sendRequest(request));
        }
    }
}
