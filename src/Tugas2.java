import java.util.Arrays;
import java.util.Scanner;

public class Tugas2 {
    Scanner input = new Scanner(System.in);
    String inName, inFrom;
    int inDate, inIndex = 1;
    public String[] book = new String[1];
    String[] place = {"Malang", "Bandung", "Surabaya"};
    void cekDate(int date){
        for (int i = 0; i < book.length; i++) {
            int parsedYear = Integer.parseInt(parseBook(book[i], "date"));
            if (parsedYear < 2018 && date < 2018) {
                System.out.printf("%s, %s. %s\n", parseBook(book[i], "name"), parseBook(book[i], "from"), parseBook(book[i], "date"));
            } else {
                System.out.printf("%s, %s. %s\n", parseBook(book[i], "name"), parseBook(book[i], "from"), parseBook(book[i], "date"));
            }
        }
    }

    private void cekFrom(String from){
        for (int i = 0; i < book.length; i++) {
            if (parseBook(book[i], "from").equals(from)){
                System.out.printf("%s, %s. %s\n", parseBook(book[i],"name"), parseBook(book[i], "from"), parseBook(book[i], "date"));
            }
        }
    }

    void cekName(String name){
        for (int i = 0; i < book.length; i++) {
            if (parseBook(book[i], "name").equals(name)){
                System.out.printf("%s, %s. %s\n", parseBook(book[i],"name"), parseBook(book[i], "from"), parseBook(book[i], "date"));
            }
        }
    }

    void list(){
        System.out.printf("%-15s | %-15s | %-15s", "Name", "From", "Year");
        for (String s : book) {
            System.out.printf("\n%-15s , %-15s . %-15s", parseBook(s,"name"), parseBook(s, "from"), parseBook(s, "date"));
        }
        mainMenu();
    }

    private String parseBook(String book, String tag){
        String[] parsedBook = book.split("[,.]");
        String parsedName = parsedBook[0];
        String parsedFrom = parsedBook[1];
        String parsedDate = parsedBook[2];
        if (tag.equals("name")){
            return parsedName;
        } else if (tag.equals("from")) {
            return parsedFrom;
        } else if (tag.equals("date")) {
            return parsedDate;
        } else {
            return "null";
        }
    }

    void findBook(){
        System.out.println("Find Book");
        System.out.println("1. Find books by Region\n2. Find books by Date");
        System.out.print("Pilih menu : ");
        int findMethod = input.nextInt();
        switch (findMethod){
            case 1:
                System.out.println("Finding Book Using Region Method");
                System.out.print("Input Region : ");
                input.nextLine(); String findRegion = input.nextLine();
                cekFrom(findRegion);
                break;
            case 2:
                System.out.println("Finding Book Using Date Method");
                System.out.print("Input Region : ");
                input.nextLine(); int findDate = input.nextInt();
                cekDate(findDate);
                break;
        }
        mainMenu();
    }
    
    private void mainMenu(){
        System.out.print("\n===============================");
        System.out.println("\nWelcome to, UMM Library");
        System.out.println("1. Book Register\n2. List Book\n3. Find Book");
        System.out.print("Pilih menu: ");
        int go = input.nextInt();
        switch (go){
            case 1 : registerBook(); break;
            case 2 : list(); break;
            case 3 : findBook(); break;
        }
    }
    
    public void registerBook(){
        System.out.println("Register ");
        System.out.print("Nama : ");
        input.nextLine(); inName = input.nextLine();
        System.out.print("From : ");
        inFrom = input.nextLine();
        System.out.print("Date : ");
        inDate = input.nextInt();
        String bookName = (inName + "," + inFrom + "." + inDate);
        book = Arrays.copyOf(book, inIndex + 1);
        book[inIndex] = bookName;
        inIndex++;
        System.out.println("Buku berhasil ditambahkan");
        mainMenu();
    }
    public static void main(String[] args) {
        Tugas2 main = new Tugas2();
        main.book[0] = "This is Book,Malang.2022";
        main.mainMenu();
    }
}
