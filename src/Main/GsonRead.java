package Main;

import Company.MyPojo;
import Company.Security;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class GsonRead {
    private static final String FILENAME = "test.json";
    static List<MyPojo> companies;



    public static void main(String[] args) throws ParseException, IOException {
        if (Files.exists(Paths.get(FILENAME))) {
            Type companiesListType = new TypeToken<List<MyPojo>>() {
            }.getType();
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(FILENAME));

                companies = new Gson().fromJson(bufferedReader, companiesListType);
                System.out.println("Выводим на экран все имеющиеся компании:");
                System.out.println(companies);

                System.out.println("\n" + "Выводим на экран все ценные бумаги, просроченные на текущий момент:");

//в данном случае выводит не самое полное имя компании, поэтому сделал через цикл ниже

          /*      securities = new ArrayList<>();
                LocalDate today = LocalDate.now(ZoneId.of("Europe/Moscow"));
                companies.stream()
                        .flatMap(c -> Arrays.stream(c.getSecurities())).filter(x -> LocalDate.parse(x.getDate_to(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)).isBefore(today))
                        .forEach(System.out::println);
                long counts = companies.stream()
                        .flatMap(c -> Arrays.stream(c.getSecurities())).filter(x -> LocalDate.parse(x.getDate_to(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)).isBefore(today))
                        .count();
               System.out.println("\n" + "Суммарное число просроченных бумаг " + counts);
               */

                LocalDate today = LocalDate.now(ZoneId.of("Europe/Moscow"));
                for (MyPojo comp : companies) {
                    for (Security security : comp.getSecurities()) {
                        if (LocalDate.parse(security.getDate_to(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)).isBefore(today)) {
                            System.out.println("Class Security: Код = " + security.getCode() + ", полное имя владельца = " + comp.getname_full() + ", дата истечения = " + security.getDate_to());
                        }
                    }
                }
                long counts = companies.stream()
                        .flatMap(c -> Arrays.stream(c.getSecurities())).filter(x -> LocalDate.parse(x.getDate_to(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)).isBefore(today))
                        .count();
                System.out.println("\n" + "Суммарное число просроченных бумаг " + counts);

            } catch (IOException e) {

            }
        } else {
            System.err.println("Файла не существует. Проверьте адрес файла.");
            System.exit(0);
        }

//Запросы пользователя

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(inputStreamReader);
        String line;
        System.out.println("\n" + "Введите дату в формате «ДД.ММ.ГГ», «ДД.ММ.ГГГГ» (без кавычек) для получения списка организаций, основанных после введенной даты.");
        System.out.println("Введите код валюты в формате «EU», «USD», «RUB» (без кавычек) для получения информации о ценных бумагах. Для завершения программы введите «exit».");
        try {
            while (!(line = br.readLine()).equals("exit")) {
                if (line.equals("EU")) {
                    System.out.println("Начало списка ценных бумаг, использующих EU");
                    companies.stream()
                            .flatMap(c -> Arrays.stream(c.getSecurities())).filter(x -> x.getCurrency().getCode().equals("EU"))
                            .forEach(System.out::println);
                    System.out.println("\n" +"Конец списка ценных бумаг, использующих EU. Для выхода из программы введите exit.");
                } else if (line.equals("USD")) {
                    System.out.println("Начало списка ценных бумаг, использующих USD");
                    companies.stream()
                            .flatMap(c -> Arrays.stream(c.getSecurities())).filter(x -> x.getCurrency().getCode().equals("USD"))
                            .forEach(System.out::println);
                    System.out.println("\n" +"Конец списка ценных бумаг, использующих USD. Для выхода из программы введите exit.");
                } else if (line.equals("RUB")) {
                    System.out.println("Начало списка ценных бумаг, использующих RUB");
                    companies.stream()
                            .flatMap(c -> Arrays.stream(c.getSecurities())).filter(x -> x.getCurrency().getCode().equals("RUB"))
                            .forEach(System.out::println);
                    System.out.println("\n" +"Конец списка ценных бумаг, использующих RUB. Для выхода из программы введите exit.");

                } else if (line.length()==10){
                    try {
                        LocalDate localDate = LocalDate.parse(line, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                        System.out.println("Original string: " + line);
                        System.out.println("Parsed date    : " + localDate.toString());
                        companies.stream()
                                .filter(x -> LocalDate.parse(x.getEgrul_date(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).isAfter(localDate))
                                .forEach(System.out::println);

                    } catch (Exception e) {
                        System.err.println("Вы ввели дату в неверном формате. Для выхода из программы введите exit.");
                    }
                }
                else if (line.length()==8){
                    try {
                        LocalDate localDate1 = LocalDate.parse(line, DateTimeFormatter.ofPattern("dd.MM.yy"));
                        System.out.println("Original string: " + line);
                        System.out.println("Parsed date    : " + localDate1.toString());
                        companies.stream()
                                .filter(x -> LocalDate.parse(x.getEgrul_date(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).isAfter(localDate1))
                                .forEach(System.out::println);

                    } catch (Exception e) {
                        System.err.println("Вы ввели дату в неверном формате. Для выхода из программы введите exit.");
                    }
                }
                else{
                    System.err.println("Проверьте правильность введенных данных.");
                    System.err.println("Возможные варианты: EU, USD, RUB, дата в формате «ДД.ММ.ГГ», «ДД.ММ.ГГГГ»");
                    System.err.println("Для выхода из программы введите exit.");
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

