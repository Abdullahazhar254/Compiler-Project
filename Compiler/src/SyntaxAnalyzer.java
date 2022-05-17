import java.util.Stack;

public class SyntaxAnalyzer {

    public String input="";
    private int indexOfInput=-1;

    Stack<String> stackvalue=new Stack<String>();
    String [][] table=
            {
                    {"TX",null,null,"TX",null,null}
                    ,
                    {null,"+TX",null,null,"",""}
                    ,
                    {"FY",null,null,"FY",null,null}
                    ,
                    {null,"","*FY",null,"",""}
                    ,
                    {"i",null,null,"(E)",null,null}
            };
    String [] nonterminals={"E","X","T","Y","F"};
    String [] terminals={"i","+","*","(",")","$"};

    public SyntaxAnalyzer(String input)
    {
        this.input=input;
    }

    private void push(String s) {
        this.stackvalue.push(s);
    }
    private String pop() {
        return this.stackvalue.pop();
    }
    
    private  void pushValue(String s)
    {
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            String str = String.valueOf(ch);
            push(str);
        }
    }

    private boolean hasTerminal(String s) {
        for (int i = 0; i < this.terminals.length; i++) {
            if (s.equals(this.terminals[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean hasNonTerminal(String s) {
        for(int i=0;i<this.nonterminals.length;i++)
        {
            if(s.equals(this.nonterminals[i]))
            {
                return true;
            }

        }
        return false;
    }
    private String readInput() {
        indexOfInput++;
        char ch=this.input.charAt(indexOfInput);
        String str=String.valueOf(ch);

        return str;
    }
    
    public String getValue(String nonterminal,String terminal)
    {

        int row=getnonTerminalIndex(nonterminal);
        int column=getTerminalIndex(terminal);
        String rule=this.table[row][column];
        if(rule==null)
        {
            System.out.println("Syntax Error! Input not accepted");
        }
        return rule;
    }

    private int getnonTerminalIndex(String nonterminal) {
        for(int i=0;i<this.nonterminals.length;i++)
        {
            if(nonterminal.equals(this.nonterminals[i]))
            {
                return i;
            }
        }
        return -1;
    }

    private int getTerminalIndex(String terminal) {
        for(int i=0;i<this.terminals.length;i++)
        {
            if(terminal.equals(this.terminals[i]))
            {
                return i;
            }
        }
        return -1;
    }

    public void parsing()
    {

        push("$");
        push("E");
        String token=readInput();
        String top=null;

        try {
            do {
                top = this.pop();
                if (hasNonTerminal(top)) {
                    String value = this.getValue(top, token);
                    this.pushValue(value);
                } else if (hasTerminal(top)) {
                    if (!top.equals(token)) {
                        System.out.println("Syntax Error! ");
                        break;
                    } else {
                        token = readInput();
                    }
                } else {
                    System.out.println("Syntax Error!");
                    break;
                }
                if (stackvalue.peek().equals("$")) {
                    break;
                }
            }
            while (true);
            if (token.equals("$") && stackvalue.peek().equals("$")) {
                System.out.println("Input Accepted");
            } else {
                System.out.println("Syntax Error! Input not Accepted");
            }
        } catch (Exception e) {
            System.out.println("Syntax Error!");
        }
    }
}
