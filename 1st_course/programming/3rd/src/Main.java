import resources.Knowledge;
import resources.Others.HomeSufferer;
import resources.Others.Karlson;
import resources.Others.Narration;
import resources.familyPack.*;
import resources.items.HouseOnTree;
import resources.items.RoomOfBabyBoy;
import resources.items.Window;

public class Main {
    public static void main(String[] args) {
        // Создаем семью с представителями разных членов
        Family f = new Family(new Mama("Мама"), new Papa("Папа"), new Bosse("Боссе"), new Betan("Бетан"), new Babyboy("Малыш"));

        // Создаем персонажей
        Karlson karlson = new Karlson();
        HomeSufferer homeSufferer = new HomeSufferer();
        Narration narration = new Narration();

        // Создаем домики
        HouseOnTree treeHouse = new HouseOnTree();
        RoomOfBabyBoy room = new RoomOfBabyBoy();
        Window window = new Window();

        // Получаем объект
        Mama mama = (Mama) f.getByName("Мама");
        Papa papa = (Papa) f.getByName("Папа");
        Bosse bosse = (Bosse) f.getByName("Боссе");
        Betan betan = (Betan) f.getByName("Бетан");
        Babyboy babyboy = (Babyboy) f.getByName("Малыш");

        // Устанавливаем имена для других персонажей
        homeSufferer.setName("домомучительница ");
        karlson.setName("Карлсон");

        // Выводим начальную часть письма
        System.out.print(mama.getStatus() + " " + mama.getName());
        babyboy.startingLetter();
        System.out.println(f.getName() + " " + f.getStatus());

        // Выводим состояние здоровья и болезни для Боссе и Бетан
        System.out.print(bosse.getName() + " " + bosse.healthStatus() + bosse.getSicknessName() + " и ");
        System.out.print(betan + " " + betan.healthStatus() + betan.getSicknessName() + ". ");
        System.out.println(bosse.getStatus());

        // Выводим информацию о состоянии и прогнозе для Малыша
        System.out.println(babyboy.me() + babyboy.getStatus() + babyboy.IsItHurt() + babyboy.me() + babyboy.Prediction());

        // Выводим информацию о Папе и его состоянии
        System.out.println(papa.getName() + ' ' + papa.getStatus() + '.');

        // Выводим прогноз о здоровье Папы
        System.out.print(babyboy.knowledgeOfLivingDad(Knowledge.DKnow) + babyboy.knowledgeOfHEALTHDad() + babyboy.predictionOfDadsHealth());

        // Выводим прогноз о здоровье в зависимости от состояния Боссе и Бетан
        if (bosse.equals(betan)) {
            System.out.println(babyboy.predictionOfDadsHealthBAD());
        } else {
            System.out.println(babyboy.predictionOfDadsHealthGOOD());
        }

        // Выводим чувства Малыша к Маме
        System.out.println(babyboy.me() + babyboy.missMom());

        // Выводим информацию о разговоре Малыша с Карлсоном
        System.out.println(babyboy.talkingWith() + "ь " + babyboy.me() + babyboy.admission() + karlson.getName() + "ом.");

        // Выводим просьбу Малыша ограничить разговор
        System.out.print(babyboy.me() + babyboy.trying() + babyboy.talkingWith() + "ь поменьше, потому что иначе ты будешь" + mama.anx(babyboy.isTalkingALot()));

        // Устанавливаем состояние домомучительницы
        homeSufferer.setIsSick(false);

        // Выводим разговор домомучительницы
        System.out.println(", а " + homeSufferer.getName() + homeSufferer.talkingWith() + " что " + homeSufferer.talking());

        // Выводим состояние Боссе и Карлсона
        System.out.print(homeSufferer.getName() + homeSufferer.getStatus() + ", " + karlson + karlson.getStatus());

        // Выводим прогноз о здоровье относительно всех персонажей
        babyboy.predictionKarlSufferer();

        // Выводим прощальные слова Малыша
        babyboy.goodByeing();

        // Выводим последние действия Малыша
        System.out.println(babyboy.getName() + babyboy.goTo(window.getName(), karlson.getName()));

        // Карлсон проводит звонок между домиком на дереве и комнатой малыша
        karlson.putCall(treeHouse.ring(), room.ring());

        // Завершаем предложение точкой
        System.out.printf(".");
    }
}
