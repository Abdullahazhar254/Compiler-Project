import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RecognizeToken rt = new RecognizeToken();
        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1 to perform Lexical Analysis only.");
        System.out.println("Press 2 to perform Lexical and Syntax Analysis both.");

        int option =sc.nextInt();
        sc.nextLine();

        if(option == 1){
            rt.readcode();
            rt.readFile();
        }
        else if(option == 2) {
            System.out.println("Enter a string for Parsing:");
            String str = sc.nextLine();
            rt.readParserString(str);
            StringBuilder parseString = rt.Stringforparser;
            System.out.println("\n");
            System.out.println("Result of Input String After Parsing:\n");
            SyntaxAnalyzer sa = new SyntaxAnalyzer(parseString+"$");
            sa.parsing();
            System.out.println("\n\n");
        }
        else {
            System.out.println("Wrong! Option Selected");
        }
    }
}
