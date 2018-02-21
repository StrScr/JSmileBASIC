/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsmilebasic;

import java.io.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.parser;

/**
 *
 * @author santunez
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        buildLexer();
        buildParser();
        runFile("Code Examples/simple-loop.sb");
    
    }
    
    private static void buildParser() {
        String params[] = new String[5];

        params[0] = "-destdir";
        params[1] = "src/jsmilebasic/";
        params[2] = "-parser";
        params[3] = "parser";
        params[4] = "src/jsmilebasic/parser.cup";
        try {
            java_cup.Main.main(params);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void buildLexer() {
       
        jflex.Main.generate(new File("src/jsmilebasic/lexer.flex"));
    }
    
    private static void runFile(String file) {
        try {
            parser parser = new parser(new lexer(new FileReader(file)));
            parser.parse();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
}
