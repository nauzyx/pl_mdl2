import java.util.Arrays;
import java.util.Scanner;

interface library {
    void cekDate(int date);
    boolean cekFrom(String method, String from);
    boolean cekName(String method, String name);
    void list();
}
public class Tugas2 implements library {
    Scanner input = new Scanner(System.in);
    String inName, inFrom;
    int inDate, inIndex = 1, inIndexY;
    public String[][] book = {{"This is Book,Malang.2022"}};
    public String[][] place = {{"Malang"},{"Bandung"},{"Surabaya"}};
    public void cekDate(int date){
        if(date == 1){
            for (int y = 0; y < book.length; y++) {
                int parsedYear = Integer.parseInt(parseBook(book[0][y], "date"));
                if (parsedYear < 2018) {
                    System.out.printf("%s, %s. %s\n", parseBook(book[0][y], "name"), parseBook(book[0][y], "from"), parseBook(book[0][y], "date"));
                }
            }
        } else if (date == 0){
            for (int y = 0; y < book.length; y++) {
                int parsedYear = Integer.parseInt(parseBook(book[0][y], "date"));
                if (parsedYear >= 2018) {
                    System.out.printf("%s, %s. %s\n", parseBook(book[0][y], "name"), parseBook(book[0][y], "from"), parseBook(book[0][y], "date"));
                }
            }
        }
    }

    public boolean cekFrom(String method, String from){
        if(method.equals("find")){
            for (int y = 0; y < book.length; y++) {
                if (from.compareTo(parseBook(book[0][y], "from")) == 0) {
                    System.out.printf("%s, %s. %s\n", parseBook(book[0][y], "name"), parseBook(book[0][y], "from"), parseBook(book[0][y], "date"));
                }
            }
        } else if (method.equals("validate")){
            for (int y = 0; y < place.length; y++) {
                if (from.equals(place[0][y])) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean cekName(String method, String name){
        if (method.equals("find")){
            for (int y = 0; y < book.length; y++) {
                if (name.compareTo(parseBook(book[0][y], "name")) == 0) {
                    System.out.printf("%s, %s. %s\n", parseBook(book[0][y], "name"), parseBook(book[0][y], "from"), parseBook(book[0][y], "date"));
                }
            }
        } else if (method.equals("dup")){
            for (int y = 0; y < book.length; y++) {
                if (name.compareTo(parseBook(book[0][y], "name")) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void list(){
        System.out.printf("%-15s | %-15s | %-15s", "Name", "From", "Year");
        for (int y = 0; y < book.length; y++) {
            System.out.printf("\n%-15s , %-15s . %-15s", parseBook(book[0][y],"name"), parseBook(book[0][y], "from"), parseBook(book[0][y], "date"));
        }
        mainMenu();
    }

    private String parseBook(String book, String tag){
        String[] parsedBook = book.split("[,.]");
        String parsedName = parsedBook[0];
        String parsedFrom = parsedBook[1];
        String parsedDate = parsedBook[2];
        return switch (tag) {
            case "name" -> parsedName;
            case "from" -> parsedFrom;
            case "date" -> parsedDate;
            default -> "null";
        };
    }

    void findBook(){
        System.out.println("Find Book");
        System.out.println("1. Find books by Region\n2. Find books by Date");
        System.out.print("Pilih menu : ");
        int findMethod = input.nextInt();
        switch (findMethod) {
            case 1 -> {
                System.out.println("Finding Book Using Region Method");
                System.out.print("Input Region : ");
                input.nextLine();
                String findRegion = input.nextLine();
                cekFrom("find", findRegion);
            }
            case 2 -> {
                System.out.println("Finding Book Using Date Method");
                System.out.print("1. Newer ( 2018 -> Newest )\n2. Older ( 2018 -> Oldest )");
                System.out.print("\nMasukkan Menu: ");
                input.nextLine();
                int findDate = input.nextInt();
                if (findDate == 1) {
                    cekDate(0);
                } else if (findDate == 2) {
                    cekDate(1);
                }
            }
        }
        mainMenu();
    }

    public void deleteBook(){
        System.out.print("Masukkan Nama buku :");
        input.nextLine();
        String rmBook = input.nextLine();
        String[][] tempBooks = new String[0][book.length - 1];
        try {
            if(cekName("dup", rmBook)){
                for(int i = 0, k = 0; i < book.length; i++){
                    if (!rmBook.equals(parseBook(book[0][i], "name"))){
                        tempBooks[0][k++] = book[0][i];
                    }
                }
                inIndex--;
                book = Arrays.copyOf(tempBooks, inIndex);
            } else {
                throw new Exception("Buku tidak terdaftar di sistem");
            }
        } catch (Exception e){
            System.out.println(e);
            deleteBook();
        }
        mainMenu();
    }
    
    public void inputNama(){
        System.out.print("Nama : ");
        inName = input.nextLine();
        try {
            if (cekName("dup", inName)){
                throw new Exception("Nama telah ada di list book");
            }
        } catch (Exception e){
            System.out.println(e);
            inputNama();
        }
    }

    public void inputFrom(){
        System.out.print("From : ");
        inFrom = input.nextLine();
        try {
            if (!cekFrom("validate", inFrom)) {
                throw new Exception("Region tidak terdaftar pada sistem");
            }
        } catch (Exception e){
            System.out.println(e);
            inputFrom();
        }
    }

    public void inputDate(){
        System.out.print("Date : ");
        inDate = input.nextInt();
    }

    public void registerBook(){
        System.out.println("Register ");
        input.nextLine();

        inputNama();
        inputFrom();
        inputDate();

        String bookName = (inName + "," + inFrom + "." + inDate);
        book[0] = Arrays.copyOf(book[0], inIndex + 1);
        book[0][inIndex] = bookName;
        inIndex++;
        System.out.println("Buku berhasil ditambahkan");
        System.out.println(Arrays.deepToString(book));
        mainMenu();
    }

    private void mainMenu(){
        System.out.print("\n=============================================");
        System.out.println("\nWelcome to, UMM Library");
        System.out.println("CRUD with Dynamic Array Implementations");
        System.out.print("=============================================\n");
        System.out.println("1. Book Register\n2. List Book\n3. Find Book\n4. Delete Book\n0. Exit");
        System.out.print("Pilih menu: ");
        int go = input.nextInt();
        switch (go) {
            case 1 -> registerBook();
            case 2 -> list();
            case 3 -> findBook();
            case 4 -> deleteBook();
            case 0 -> System.exit(130);
        }
    }
    public static void main(String[] args) {
        Tugas2 main = new Tugas2();
        main.mainMenu();
    }
}
