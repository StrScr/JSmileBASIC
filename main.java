import java.io.*;

public class Main{
    public static void main(String argv[]){
        try{
            parser p = new parser(new Lexer(new FileReader(argv[0])));
            Object result = p.parse().value;
        }catch(Exception e){
            System.out.println("(main) Error.");
        }
    }
}