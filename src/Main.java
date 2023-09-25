import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String lastName;
        String firstName;
        String patronymic;
        String date;
        String phoneNumber;
        char gender;
        System.out.println("Введите данные (фамилия, имя, отчество, дата рождения, номер, пол: ");

        Scanner in = new Scanner(System.in);
        String inputStr = in.nextLine();
        in.close();

        String[] items = inputStr.split("\\s+");
        List<String> list = new ArrayList<>(Arrays.asList(items));

        if (list.size() < 6) throw new RuntimeException("Код ошибки 1. Вы ввели меньше данных, чем требуется");
        if (list.size() > 6) throw new RuntimeException("Код ошибки 2. Вы ввели больше данных, чем требуется");

        lastName = list.get(0);
        firstName = list.get(1);
        patronymic = list.get(2);
        date = list.get(3);
        if (!date.matches("^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.\\d{4}$"))
            throw new RuntimeException("Код ошибки 3. Введенная дата не соответствует формату");
        phoneNumber = list.get(4);
        if (!phoneNumber.matches("\\d+"))
            throw new RuntimeException("Код ошибки 4. Введенный номер телефона не соответствует формату");
        if (list.get(5).matches("[fm]"))
            gender = list.get(5).charAt(0);
        else throw new RuntimeException("Код ошибки 5. Введенный пол не соответствует формату (f/m)");

        try {
            FileWriter fileWriter = new FileWriter(lastName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(
                    lastName + " " +
                     firstName + " " +
                     patronymic + " " +
                     date + " " +
                     phoneNumber + " " +
                     gender
            );
            bufferedWriter.newLine();
            bufferedWriter.close();
            System.out.println("Строка успешно добавлена в файл.");
        } catch (IOException e) {
            System.err.println("Код ошибки 6. Произошла ошибка при записи в файл: " + e.getMessage());
        }
    }
}