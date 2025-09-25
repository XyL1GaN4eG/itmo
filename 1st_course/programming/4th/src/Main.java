import resources.Others.HomeSufferer;
import resources.Others.Karlson;
import resources.Others.Narration;
import resources.enums.BabyBoyHadTo;
import resources.enums.Knowledge;
import resources.enums.SadBabyCharacteristic;
import resources.exceptions.ExceptionFamily;
import resources.exceptions.ExceptionMomAnxiety;
import resources.familyPack.*;
import resources.items.HouseOnTree;
import resources.items.RoomOfBabyBoy;

public class Main {
    public static void main(String[] args) throws ExceptionFamily {
        // Создаем семью с представителями разных членов
        Family f = new Family(new Mama("Мама"), new Papa("Папа"), new Siblings.Bosse("Боссе"),
                new Siblings.Betan("Бетан"), new Babyboy("Малыш"));

        // Создаем персонажей
        Karlson karlson = new Karlson();
        HomeSufferer homeSufferer = new HomeSufferer();
        Narration narration = new Narration();

        // Создаем домики
        HouseOnTree treeHouse = new HouseOnTree();
        RoomOfBabyBoy room = new RoomOfBabyBoy();
        RoomOfBabyBoy.Window window = room.new Window();   //то же что и в 47 строке


        // Получаем объект
        Mama mama = (Mama) f.getByName("Мама");
        Papa papa = (Papa) f.getByName("Папа");
        Siblings.Bosse bosse = (Siblings.Bosse) f.getByName("Боссе");    //создаем объект вложенного статического класса через внешний класс
        Siblings.Betan betan = (Siblings.Betan) f.getByName("Бетан");
        Babyboy babyboy = (Babyboy) f.getByName("Малыш");

        try {
            mama.setState(babyboy);
        } catch (ExceptionMomAnxiety e) {
            System.out.println(e.getMessage());
        }

        try {
            Siblings misterBist = (Siblings) f.getByName("Мистер Бист");   //тут пробуем добавить еще одного члена в нашу семью.
        } catch (
                ExceptionFamily e) {                                      //компилятор позволит объекту пользоваться методами семьи, но jvm выкинет ошибку при выполнении
            System.out.println(e.getLocalizedMessage());                   //если в методе гетНейм (который в фемили) написать, что метод бросает исключение
        }                                                                  //то прога при выполнении сломается, но выведет сообщение наше, а вот если обработать исключение в
        //блоке кетч, то просто выведется сообщение и прога пойдет работать дальше
        // Устанавливаем имена для других персонажей
        homeSufferer.setName("домомучительница ");
        karlson.setName("Карлсон");
        Babyboy.MelancholyBabyBoy melancholyBabyBoy = babyboy.new MelancholyBabyBoy(); //танцы с бубном чтобы создать
        // объект вложенного нестатического класса, делается через объект внешнего для него класса


        // Выводим начальную часть письма
        System.out.print(mama.getStatus() + " " + mama.getName());
        babyboy.startingLetter();
        System.out.println(f.getName() + " " + f.getStatus());

        // Выводим состояние здоровья и болезни для Боссе и Бетан
        System.out.print(bosse.getName() + " " + bosse.healthStatus() + bosse.getSicknessName() + " и ");
        System.out.print(betan + " " + betan.healthStatus() + betan.getSicknessName() + ". ");
        System.out.println(bosse.getStatus());

        // Выводим информацию о состоянии и прогнозе для Малыша
        System.out.println(babyboy.pronoun() + babyboy.getStatus() + babyboy.isItHurt() + babyboy.pronoun() + babyboy.prediction());

        // Выводим информацию о Папе и его состоянии
        System.out.println(papa.getName() + ' ' + papa.getStatus() + '.');

        // Выводим прогноз о здоровье Папы
        System.out.print(babyboy.knowledgeOfLivingDad(Knowledge.D_KNOW) + babyboy.knowledgeOfHEALTHDad() + babyboy.predictionOfDadsHealth());

        // Выводим прогноз о здоровье в зависимости от состояния Боссе и Бетан
        if (bosse.equals(betan)) {
            System.out.println(babyboy.predictionOfDadsHealthBAD());
        } else {
            System.out.println(babyboy.predictionOfDadsHealthGOOD());
        }

        // Выводим чувства Малыша к Маме
        System.out.println(babyboy.pronoun() + babyboy.missMom());

        // Выводим информацию о разговоре Малыша с Карлсоном
        System.out.println(babyboy.talkingWith() + "ь " + babyboy.pronoun() + babyboy.admission() + karlson.getName() + "ом.");

        // Выводим просьбу Малыша ограничить разговор
        System.out.print(babyboy.pronoun() + babyboy.trying() + babyboy.talkingWith() + "ь поменьше, потому что иначе ты будешь");

        //то, что надо сделать написано в блоке трай, а если вдруг этот метод из трая все-таки кинет исключение,
        //то обрабатываем его в блоке кетч. в него мы передаем наш класс-исключение и выводим сообщение.
        //по дефолту этот метод гетЛокалайздМессажд возвращает гетМессадж который ничего не возвращает, поэтому переопределяем
        //локалайзд в нашем классе-исключении(ExceptionPerson)
        // например, если подставить папу вместо бэбибоя то будет исключение
        try {
            mama.anx(babyboy);
            System.out.println(mama.getState());
        } catch (ExceptionMomAnxiety e) {
            System.out.println(e.getLocalizedMessage());
        }

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
        System.out.println(".");

        //у меланхоличного бейбибоя есть методы (гетХарактеристик и сетАкшн), которые принимают энамы,
        // а в энаме прописан метод, чтобы присваивать им значения (дефинишн)
        System.out.println("Ну что же, неужели " + melancholyBabyBoy.getCharacteristics(SadBabyCharacteristic.MELANCHOLY)
                + "ый " + melancholyBabyBoy.getName() + " - "
                + melancholyBabyBoy.getCharacteristics(SadBabyCharacteristic.UNPLEASANT_PERSON) + "? ");
        System.out.print("Ни в коем случае! " + melancholyBabyBoy.pronoun() + " "
                + melancholyBabyBoy.getCharacteristics(SadBabyCharacteristic.DEVOTED) + ", "
                + melancholyBabyBoy.getCharacteristics(SadBabyCharacteristic.ATTENTIVE) + ", ");
        System.out.println(melancholyBabyBoy.getCharacteristics(SadBabyCharacteristic.LOVIING) + " "
                + babyboy.getName().toLowerCase() + ", "
                + melancholyBabyBoy.getCharacteristics(SadBabyCharacteristic.THE_CUTEST_IN_WHOLE_WORLD) + " ");
        System.out.print("Но Меланхоличному " + babyboy.getName() + "у ");
        System.out.println(melancholyBabyBoy.setAction(BabyBoyHadTo.HAD_TO)
                + melancholyBabyBoy.setAction(BabyBoyHadTo.STOP_BEING_LOVED));
        System.out.println(melancholyBabyBoy.setAction(BabyBoyHadTo.HAD_TO) + " "
                + melancholyBabyBoy.setAction(BabyBoyHadTo.BECOME_A_DOCTOR)
                + melancholyBabyBoy.setAction(BabyBoyHadTo.BECOME_A_BUILDER));
        System.out.println("и вообще" + melancholyBabyBoy.setAction(BabyBoyHadTo.BE_RESPONSIBLE));


        //такс ну реализуем анонимный класс мужчина который реализует интерфейс меланхолия
        // , типо нам и объект его нужен, чтобы методы интерфейса переопределить
        // и целый класс для него выделять жалко, тк используем его только раз в проге
        Melancholy man = new Melancholy() {
            @Override
            public String setAction(BabyBoyHadTo action) {
                return action.definition;
            }

            @Override
            public String getName() {
                return "мужчина";
            }
        };

        System.out.println("И какой же " + man.getName() + man.setAction(BabyBoyHadTo.GROW_UP)
                + man.getCharacteristics(SadBabyCharacteristic.MELANCHOLY) + "ого " + melancholyBabyBoy.getName() + "а?");

    }

}
