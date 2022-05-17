import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class RecognizeToken {

    public StringBuilder Stringforparser= new StringBuilder("");

    static String[] keyword ={"int","char","string","if","else","do","while"};
    static String[] relationalOperators={"<", "<=", "==", "<>", ">=" ,">"};
    static String[] arthmeticOperators={ "+", "-", "*", "/"};
    static String[] otherOperators={"=","(",")","{","}",";"};

    static String identifier = "[[_a-zA-Z][0-9]]+";
    static String digit = "[0-9]+";
    static String readString="([\\\"]{1}([\\w]*)[\\\"]{1})";

    List<String> token_name = new ArrayList<String>();
    List<String> value = new ArrayList<String>();


    public boolean haskeyword(String token) {
        for(int i=0;i<keyword.length;i++){
            if (keyword[i].equals(token))
                return true;
        }
        return false;
    }
    public boolean hasrelationalOp(String token) {
        for(int i=0;i<relationalOperators.length;i++){
            if (relationalOperators[i].equals(token))
                return true;
        }
        return false;
    }

    public boolean hasarithmeticOp(String token) {
        for(int i=0;i<arthmeticOperators.length;i++){
            if (arthmeticOperators[i].equals(token))
                return true;
        }
        return false;
    }

    public boolean hasotherOp(String token) {
        for(int i=0;i<otherOperators.length;i++){
            if (otherOperators[i].equals(token))
                return true;
        }
        return false;
    }

    public boolean hasstringliteral(String token){
        if(Pattern.matches(readString,token)){
            return true;
        }
        return false;
    }

    public boolean hasdigit(String token){
        if(Pattern.matches(digit,token)){
            return true;
        }
        return false;
    }

    public boolean hasidentifier(String token){
        if(Pattern.matches(identifier,token)){
            return true;
        }
        return false;
    }

    private String Comments(String comment) {
        return comment.replaceAll("//.*|/\\*.*\\*/", "");
    }


    public void readcode(){
        System.out.println("\n\t\t\t\t\t Source Code\n");
        String code;
        int L=1;
        try {
            File f=new File("src/input.txt");
            FileReader fr=new FileReader(f);
            int line= 1;
            Scanner readcode= new Scanner(fr);
            while (readcode.hasNextLine()){
                code=readcode.nextLine();
                System.out.println(L+"    "+code);
                L++;
            }
        }catch (Exception e){
        }
        System.out.println("\n");
    }

    public void readFile(){
        token_name.add("int");
        token_name.add("char");
        token_name.add("string");
        token_name.add("if");
        token_name.add("else");
        token_name.add("do");
        token_name.add("while");
        value.add("-");
        value.add("-");
        value.add("-");
        value.add("-");
        value.add("-");
        value.add("-");
        value.add("-");

        String word;
        String tokenName;
        String tokenMatch;
        File f=new File("src/input.txt");
        System.out.println("\t\t\t Recognizing Tokens And Error Handling\n");
        try{
            FileReader fr=new FileReader(f);
            int line= 1;
            Scanner readcode= new Scanner(fr);
            while (readcode.hasNextLine()){
                word = Comments(readcode.nextLine());

                StringTokenizer spaceToken = new StringTokenizer(word, " ");
                while (spaceToken.hasMoreTokens()) {
                    String token = spaceToken.nextToken();

                    if (haskeyword(token)) {
                        tokenName = token;
                        System.out.println("Lexeme : " + tokenName + " , Token Name : " + tokenName + " , at Line : " + line);
                    }
                    else if(hasdigit(token)){
                        tokenName = token;
                        if(value.contains(tokenName)){
                        }else {
                            token_name.add("in");
                            value.add(tokenName);
                        }
                        System.out.println("Lexeme : " + tokenName + " , Token Name : in , at Line : " + line);
                    }
                    else if(hasidentifier(token)) {
                        tokenName = token;
                        tokenMatch = String.valueOf(tokenName.charAt(0));
                        if (tokenMatch.matches(digit)) {
                            System.out.println("Error! ( "+tokenName+" ) Token not recognize at Line : "+line);
                        } else {
                            if (value.contains(tokenName)) {
                            } else {
                                token_name.add("id");
                                value.add(tokenName);
                            }
                            System.out.println("Lexeme : " + tokenName + " , Token Name : id , at Line : " + line);
                        }
                    }
                    else if(hasstringliteral(token)){
                        tokenName = token;
                        if(value.contains(tokenName)){
                        }else {
                            token_name.add("sl");
                            value.add(tokenName);
                        }
                        System.out.println("Lexeme : " + tokenName + " , Token Name : sl , at Line : " + line);
                    }
                    else if(hasrelationalOp(token)){
                        tokenName = token;
                        System.out.println("Lexeme : " + tokenName + " , Token Name : ro , at Line : " + line);
                    }
                    else if(hasarithmeticOp(token)){
                        tokenName = token;
                        System.out.println("Lexeme : " + tokenName + " , Token Name : ao , at Line : " + line);
                    }
                    else if(hasotherOp(token)){
                        tokenName = token;
                        System.out.println("Lexeme : " + tokenName + " , Token Name : oo , at Line : " + line);
                    }
                    else {
                        for (int i = 0; i < token.length(); i++) {
                            String c = Character.toString(token.charAt(i));
                            String tempstring="";
                            String tempidentifier="";
                            String tempmatch="";

                            if(c.equals("+")){
                                System.out.println("Lexeme : "+c+" , Token Name : ao , at Line : "+line);
                            }
                            else if(c.equals("-")){
                                System.out.println("Lexeme : "+c+" , Token Name : ao , at Line : "+line);
                            }
                            else if(c.equals("*")){
                                System.out.println("Lexeme : "+c+" , Token Name : ao , at Line : "+line);
                            }
                            else if(c.equals("/")) {
                                System.out.println("Lexeme : "+c+" , Token Name : ao , at Line : "+line);
                            }
                            else if(c.equals("(")){
                                System.out.println("Lexeme : "+c+" , Token Name : oo , at Line : "+line);
                            }
                            else if(c.equals(")")){
                                System.out.println("Lexeme : "+c+" , Token Name : oo , at Line : "+line);
                            }
                            else if(c.equals("{")){
                                System.out.println("Lexeme : "+c+" , Token Name : oo , at Line : "+line);
                            }
                            else if(c.equals("}")){
                                System.out.println("Lexeme : "+c+" , Token Name : oo , at Line : "+line);
                            }
                            else if(c.equals(";")){
                                System.out.println("Lexeme : "+c+" , Token Name : oo , at Line : "+line);
                            }
                            else if(c.equals("=")) {
                                String c1 = Character.toString(token.charAt(i + 1));
                                if (c1.equals("=")) {
                                    System.out.println("Lexeme : " +c+c1+ " , Token Name : ro , at Line : " + line);
                                    i++;
                                    continue;
                                } else {
                                    System.out.println("Lexeme : "+c+" , Token Name : ro , at Line : " + line);

                                }
                            }
                            else if(c.equals("<")){
                                String c1 = Character.toString(token.charAt(i+1));
                                if(c1.equals("=")){
                                    System.out.println("Lexeme : "+c+c1+" , Token Name : ro , at Line : "+line);
                                    i++;
                                    continue;
                                }
                                else if(c1.equals(">")){
                                    System.out.println("Lexeme : "+c+c1+" , Token Name : ro , at Line : "+line);
                                    i++;
                                    continue;
                                }
                                else {
                                    System.out.println("Lexeme : "+c+" , Token Name : ro , at Line : "+line);
                                }
                            }

                            else if(c.equals(">")){
                                String c1 = Character.toString(token.charAt(i+1));
                                if(c1.equals("=")){
                                    System.out.println("Lexeme : "+c+c1+" , Token Name : ro , at Line : "+line);
                                    i++;
                                    continue;
                                } else {
                                    System.out.println("Lexeme : "+c+" , Token Name : ro , at Line : "+line);
                                }
                            }
                            else if(c.matches("\\\"")){
                                String c1 = Character.toString(token.charAt(i+1));
                                if(c1.matches(identifier)){
                                    do{
                                        tempstring += c1;
                                        i++;
                                        if(i<token.length())
                                            c1=Character.toString(token.charAt(i+1));
                                        else
                                            break;
                                    }while (c1.matches(identifier));

                                    if(value.contains(tempstring)){
                                    }else {
                                        token_name.add("sl");
                                        value.add(tempstring);
                                    }
                                    System.out.println("Lexeme : " + tempstring + " , Token Name : sl , at Line : " + line);
                                }
                            }
                            else if(c.matches(identifier)){
                                do{
                                    tempidentifier += c;
                                    i++;
                                    if(i<token.length())
                                        c=Character.toString(token.charAt(i));
                                    else
                                        break;
                                }while (c.matches(identifier));
                                --i;
                                if(tempidentifier.matches(digit)){
                                    if(value.contains(tempidentifier)){
                                    }else {
                                        token_name.add("in");
                                        value.add(tempidentifier);
                                    }
                                    System.out.println("Lexeme : " + tempidentifier + " , Token Name : in , at Line : " + line);
                                }else {
                                    tempmatch= String.valueOf(tempidentifier.charAt(0));
                                    if(tempmatch.matches(digit)){
                                        System.out.println("Error! ( "+tempidentifier+" ) Token not recognize at Line : "+line);
                                    }else{
                                        if(value.contains(tempidentifier)){
                                        }else {
                                            token_name.add("id");
                                            value.add(tempidentifier);
                                        }
                                        System.out.println("Lexeme : " + tempidentifier + " , Token Name : id , at Line : " + line);
                                    }
                                }
                            }
                            else {
                                System.out.println("Error! ( "+c+" ) Token not recognize at Line : "+line);
                            }
                        }
                    }
                }
                line++;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("\n\n\t\t\t\t\t Symbol Table\n");
        System.out.println("Attribute Value \t|  Token Name  \t\t|  Type \t\t|  Value");
        for(int i=0;i<token_name.size();i++){
            System.out.println("\t"+i+"\t\t\t\t|  "+token_name.get(i)+"\t\t\t\t|  -\t\t\t|  "+value.get(i));
        }
    }

    public void readParserString(String getString){
        token_name.add("int");
        token_name.add("char");
        token_name.add("string");
        token_name.add("if");
        token_name.add("else");
        token_name.add("do");
        token_name.add("while");
        value.add("-");
        value.add("-");
        value.add("-");
        value.add("-");
        value.add("-");
        value.add("-");
        value.add("-");

        String word;
        String tokenName;
        String tokenMatch;
        try{
            int line= 1;
            Scanner readcode= new Scanner(getString);
            while (readcode.hasNextLine()){
                word = readcode.nextLine();
                System.out.println(line+"   "+word);
                System.out.println("\n\t\t\t Recognizing Tokens And Error Handling\n");

                StringTokenizer spaceToken = new StringTokenizer(word, " ");
                while (spaceToken.hasMoreTokens()) {
                    String token = spaceToken.nextToken();

                    if (haskeyword(token)) {
                        tokenName = token;
                        System.out.println("Lexeme : " + tokenName + " , Token Name : " + tokenName + " , at Line : " + line);
                    }
                    else if(hasdigit(token)){
                        tokenName = token;
                        if(value.contains(tokenName)){
                        }else {
                            token_name.add("in");
                            value.add(tokenName);
                        }
                        System.out.println("Lexeme : " + tokenName + " , Token Name : in , at Line : " + line);
                    }
                    else if(hasidentifier(token)) {
                        tokenName = token;
                        tokenMatch = String.valueOf(tokenName.charAt(0));
                        if (tokenMatch.matches(digit)) {
                            System.out.println("Error! ( "+tokenName+" ) Token not recognize at Line : "+line);
                        } else {
                            if (value.contains(tokenName)) {
                            } else {
                                token_name.add("id");
                                value.add(tokenName);
                            }
                            System.out.println("Lexeme : " + tokenName + " , Token Name : id , at Line : " + line);
                            Stringforparser.append("i");
                        }
                    }
                    else if(hasstringliteral(token)){
                        tokenName = token;
                        if(value.contains(tokenName)){
                        }else {
                            token_name.add("sl");
                            value.add(tokenName);
                        }
                        System.out.println("Lexeme : " + tokenName + " , Token Name : sl , at Line : " + line);
                    }
                    else if(hasrelationalOp(token)){
                        tokenName = token;
                        System.out.println("Lexeme : " + tokenName + " , Token Name : ro , at Line : " + line);
                        Stringforparser.append(tokenName);
                    }
                    else if(hasarithmeticOp(token)){
                        tokenName = token;
                        System.out.println("Lexeme : " + tokenName + " , Token Name : ao , at Line : " + line);
                        Stringforparser.append(tokenName);
                    }
                    else if(hasotherOp(token)){
                        tokenName = token;
                        System.out.println("Lexeme : " + tokenName + " , Token Name : oo , at Line : " + line);
                        Stringforparser.append(tokenName);
                    }
                    else {
                        for (int i = 0; i < token.length(); i++) {
                            String c = Character.toString(token.charAt(i));
                            String tempmatch="";
                            String tempstring="";
                            String tempidentifier="";

                            if(c.equals("+")){
                                System.out.println("Lexeme : "+c+" , Token Name : ao , at Line : " + line);
                                Stringforparser.append(c);
                            }
                            else if(c.equals("-")){
                                System.out.println("Lexeme : "+c+" , Token Name : ao , at Line : " + line);
                                Stringforparser.append(c);
                            }
                            else if(c.equals("*")){
                                System.out.println("Lexeme : "+c+" , Token Name : ao , at Line : " + line);
                                Stringforparser.append(c);
                            }
                            else if(c.equals("/")) {
                                System.out.println("Lexeme : " + c + " , Token Name : ao , at Line : " + line);
                                Stringforparser.append(c);
                            }
                            else if(c.equals("(")){
                                System.out.println("Lexeme : "+c+" , Token Name : oo , at Line : " + line);
                                Stringforparser.append(c);
                            }
                            else if(c.equals(")")){
                                System.out.println("Lexeme : "+c+" , Token Name : oo , at Line : "+line);
                                Stringforparser.append(c);
                            }
                            else if(c.equals("{")){
                                System.out.println("Lexeme : "+c+" , Token Name : oo , at Line : "+line);
                                Stringforparser.append(c);
                            }
                            else if(c.equals("}")){
                                System.out.println("Lexeme : "+c+" , Token Name : oo , at Line : "+line);
                                Stringforparser.append(c);
                            }
                            else if(c.equals(";")){
                                System.out.println("Lexeme : "+c+" , Token Name : oo , at Line : "+line);
                                Stringforparser.append(c);
                            }
                            else if(c.equals("=")) {
                                String c1 = Character.toString(token.charAt(i + 1));
                                if (c1.equals("=")) {
                                    System.out.println("Lexeme : " +c+c1+ " , Token Name : ro , at Line : " + line);
                                    Stringforparser.append(c+c1);
                                    i++;
                                    continue;
                                } else {
                                    System.out.println("Lexeme : "+c+" , Token Name : ro , at Line : " + line);
                                    Stringforparser.append(c);
                                }
                            }
                            else if(c.equals("<")){
                                String c1 = Character.toString(token.charAt(i+1));
                                if(c1.equals("=")){
                                    System.out.println("Lexeme : "+c+c1+" , Token Name : ro , at Line : "+line);
                                    Stringforparser.append(c+c1);
                                    i++;
                                    continue;
                                }
                                else if(c1.equals(">")){
                                    System.out.println("Lexeme : "+c+c1+" , Token Name : ro , at Line : "+line);
                                    Stringforparser.append(c+c1);
                                    i++;
                                    continue;
                                }
                                else {
                                    System.out.println("Lexeme : "+c+" , Token Name : ro , at Line : "+line);
                                    Stringforparser.append(c);
                                }
                            }
                            else if(c.equals(">")){
                                String c1 = Character.toString(token.charAt(i+1));
                                if(c1.equals("=")){
                                    System.out.println("Lexeme : "+c+c1+" , Token Name : ro , at Line : "+line);
                                    Stringforparser.append(c+c1);
                                    i++;
                                    continue;
                                } else {
                                    System.out.println("Lexeme : "+c+" , Token Name : ro , at Line : "+line);
                                    Stringforparser.append(c);
                                }
                            }
                            else if(c.matches("\\\"")){
                                String c1 = Character.toString(token.charAt(i+1));
                                if(c1.matches(identifier)){
                                    do{
                                        tempstring += c1;
                                        i++;
                                        if(i<token.length())
                                            c1=Character.toString(token.charAt(i+1));
                                        else
                                            break;
                                    }while (c1.matches(identifier));
                                    if(value.contains(tempstring)){
                                    }else {
                                        token_name.add("sl");
                                        value.add(tempstring);
                                    }
                                    System.out.println("Lexeme : " + tempstring + " , Token Name : sl , at Line : " + line);
                                }
                            }
                            else if(c.matches(identifier)){
                                do{
                                    tempidentifier += c;
                                    i++;
                                    if(i<token.length())
                                        c=Character.toString(token.charAt(i));
                                    else
                                        break;
                                }while (c.matches(identifier));
                                --i;
                                if(tempidentifier.matches(digit)){
                                    if(value.contains(tempidentifier)){
                                    }else {
                                        token_name.add("in");
                                        value.add(tempidentifier);
                                    }
                                    System.out.println("Lexeme : " + tempidentifier + " , Token Name : in , at Line : " + line);
                                }else {
                                    tempmatch= String.valueOf(tempidentifier.charAt(0));
                                    if(tempmatch.matches(digit)){
                                        System.out.println("Error! ( "+tempidentifier+" ) Token not recognize at Line : "+line);
                                    }else{
                                        if(value.contains(tempidentifier)){
                                        }else {
                                            token_name.add("id");
                                            value.add(tempidentifier);
                                        }
                                        System.out.println("Lexeme : " + tempidentifier + " , Token Name : id , at Line : " + line);
                                        Stringforparser.append("i");
                                    }
                                }
                            }
                            else {
                                System.out.println("Error! ( "+c+" ) Token not recognize at Line : "+line);
                            }
                        }
                    }
                }
                line++;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("\n\n\t\t\t\t\t Symbol Table\n");
        System.out.println("Attribute Value \t|  Token Name  \t\t|  Type \t\t|  Value");
        for(int i=0;i<token_name.size();i++){
            System.out.println("\t"+i+"\t\t\t\t|  "+token_name.get(i)+"\t\t\t\t|  -\t\t\t|  "+value.get(i));
        }
    }

}
