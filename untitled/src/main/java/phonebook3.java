import java.io.*;
import java.util.*;

public class phonebook3 {
    static String text;
    static HashMap<String, String> phonebook = new HashMap<>();
    public static void OpenBook() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("untitled/src/main/java/сontacts.txt"));
            text = reader.readLine();
            while (text != null) {
                String[] text1 = text.split(";");
                phonebook.put(text1[0], text1[1]);
                text = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Возможно телефонная книга пуста");
        }
    }

    public static void ShowContacts() {
        LinkedHashMap<String, String> show = new LinkedHashMap<>();
        phonebook.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(n -> show.put(n.getKey(), n.getValue()));
//        show.forEach((k, v) -> name.add(v));
        show.forEach((k, v) -> System.out.println(k + ": " + v));
    }


    public static void addContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя контакта ");
        String name = sc.nextLine();
        String tel = null;
        for (Map.Entry<String, String> n : phonebook.entrySet()) {
            if (n.getKey().equals(name)) {
                tel = n.getValue();
            }
        }
        System.out.println("Введите телефон ");
        String telephone = sc.nextLine() + ", " + tel;
        phonebook.put(name, telephone);
    }

    public static void FindContact(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя искомого контакта ");
        String name = sc.nextLine();
        boolean flag = false;
        for (Map.Entry<String, String> n: phonebook.entrySet()) {
            if (n.getKey().equals(name)) {
                System.out.println(n.getKey() + ": " + n.getValue());
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Контакт с таким именем не найден");
        }
    }

    public static void ChangeContact (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя контакта который хотите изменить");
        String name = sc.nextLine();
        boolean flag = false;
        String oldTel = null;
        String oldName = null;
        for (Map.Entry<String, String> n: phonebook.entrySet()) {
            if (n.getKey().equals(name)) {
                oldTel = n.getValue();
                oldName = n.getKey();
                flag = true;
            }
        }
        if (flag) {
            Scanner scs = new Scanner(System.in);
            System.out.println("Выбирите:\n\t1.изменить имя\n\t2.изменить номер");
            int punct = sc.nextInt();
            switch (punct) {
                case 1 -> {
                    phonebook.remove(name);
                    System.out.println("Ведите новое имя");
                    String newName = scs.nextLine();
                    phonebook.put(newName, oldTel);
                    break;
                }
                case 2 -> {
                    System.out.println("Введите новый номер");
                    String newel = scs.nextLine();
                    phonebook.put(oldName, newel);
                    break;
                }
            }
        }
        if (!flag) {
            System.out.println("Контакт с таким именем не найден");
        }
    }

    public static void DelContact(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя контакта который хотите удалить");
        String name = sc.nextLine();
        System.out.println(phonebook.remove(name));
        System.out.println("Контакт удалён");
    }

    public static void SaveFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("untitled/src/main/java/сontacts.txt"))) {
//            AtomicReference<String> newbook = null;
            phonebook.forEach((key, value) -> {
                try {
                    writer.write(String.valueOf(key));
                    writer.write(";");
                    writer.write(String.valueOf(value));
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        boolean menu = true;
        while (menu) {
            System.out.println("Выбирите пункт меню:" +
                    "\n\t1.Открыть телефонную книгу" +
                    "\n\t2.Посмотреть контакты \n\t3.Создать контакт" +
                    "\n\t4.Найти контакт \n\t5.Изменить контакт" +
                    "\n\t6.Удалить контакт\n\t7.Сохранить файл\n\t8.выход");

            Scanner sc = new Scanner(System.in);
            System.out.println("Введите номер пункта ");
            int numPunct = sc.nextInt();

            switch (numPunct) {
                case 1: {
                    OpenBook();
                    break;
                }
                case 2: {
                    ShowContacts();
                    break;
                }
                case 3: {
                    addContact();
                    break;
                }
                case 4: {
                    FindContact();
                    break;
                }
                case 5: {
                    ChangeContact();
                    break;
                }
                case 6: {
                    DelContact();
                    break;
                }
                case 7: {
                    SaveFile();
                    break;
                }
                case 8: {
                    menu = false;
                    System.out.println("Выход");
                    break;
                }
                default: {
                    System.out.println("нет такого значения");
                    break;
                }
            }
        }
    }
}
