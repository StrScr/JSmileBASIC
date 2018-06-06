import java.io.*;

public class Main{ 
    public static void main(String argv[]){
        try{
            parser p = new parser(new Lexer(new FileReader(argv[0])));
            Object result = p.parse().value;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Main: No se especifico un archivo.");
        }catch(FileNotFoundException e){
            System.out.println("Main: No se encontro el archivo.");
        }catch(Exception e){
            System.out.println("Main: Error fatal desconocido de compilacion.");
            e.printStackTrace();
        }
    }
}