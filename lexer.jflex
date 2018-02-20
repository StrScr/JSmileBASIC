/*
    SmileBASIC Lexic Analyzer by StrScr
    To be used with CUP.
*/

import java_cup.runtime.*;

%%

%cup

%class Lexer
%unicode
%ignorecase
%cupdebug

%column
%line

%{   
    /* Create a new java_cup.runtime.Symbol with information about
       the current token, the token will have no value in this
       case. */
    private Symbol symbol(int type) {
        //System.out.print("<"+type+":"+yytext()+">");
        return new Symbol(type, yyline, yycolumn);
    }
    
    /* Also creates a new java_cup.runtime.Symbol with information
       about the current token, but this object has a value. */
    private Symbol symbol(int type, Object value) {
        //System.out.print("<"+type+":"+yytext()+">");
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

/*
    Declarations
*/
LineEnd = \r|\n|\r\n
InputChar = [^\r\n]
Whitespace = [ \t\f]
BaseIdentifier = [A-Za-z_][A-Za-z_0-9]*
Comment = ("'"|"rem "){InputChar}*{LineEnd}?
NumberLiteral = [0-9]+

%%

<YYINITIAL>{
    /*
        Keywords
    */
    "if"        {return symbol(sym.IF);}
    "then"      {return symbol(sym.THEN);}
    "else"      {return symbol(sym.ELSE);}
    "elseif"    {return symbol(sym.ELSEIF);}
    "endif"     {return symbol(sym.THEN);}

    "to"        {return symbol(sym.TO);}
    "step"      {return symbol(sym.STEP);}
    "for"       {return symbol(sym.FOR);}
    "next"      {return symbol(sym.NEXT);}
    "while"     {return symbol(sym.WHILE);}
    "wend"      {return symbol(sym.WEND);}
    "repeat"    {return symbol(sym.REPEAT);}
    "until"     {return symbol(sym.UNTIL);}
    "continue"  {return symbol(sym.CONTINUE);}
    "break"     {return symbol(sym.BREAK);}

    "stop"      {return symbol(sym.STOP);}

    "def"       {return symbol(sym.DEF);}
    "return"    {return symbol(sym.RETURN);}
    "out"       {return symbol(sym.OUT);}
    "end"       {return symbol(sym.END);}

    "var"       {return symbol(sym.VAR);}
    "dim"       {return symbol(sym.DIM);}
    "inc"       {return symbol(sym.INC);}
    "dec"       {return symbol(sym.DEC);}
    "swap"      {return symbol(sym.SWAP);}

    "print"     {return symbol(sym.PRINT);}

    /*
        Boolean Literals
    */
    "true"  {return symbol(sym.TRUE);}
    "false" {return symbol(sym.FALSE);}

    /*
        Separators
    */
    "("     {return symbol(sym.LPAREN);}
    ")"     {return symbol(sym.RPAREN);}
    "["     {return symbol(sym.LBRACK);}
    "]"     {return symbol(sym.RBRACK);}
    ","     {return symbol(sym.COMMA);}
    ":"     {return symbol(sym.COLON);}

    /*
        Operators
    */
    "="     {return symbol(sym.EQ);}

    "=="    {return symbol(sym.EQEQ);}
    "!="    {return symbol(sym.NOTEQ);}
    "<"     {return symbol(sym.LT);}
    "<="    {return symbol(sym.LTEQ);}
    ">"     {return symbol(sym.GT);}
    ">="    {return symbol(sym.GTEQ);}

    "!"     {return symbol(sym.NOT);}
    "&&"    {return symbol(sym.ANDAND);}
    "||"    {return symbol(sym.OROR);}

    /*
        Numeric Literals
    */
    {NumberLiteral}       {return symbol(sym.NUMBER, yytext());}

    /*
        Identifiers
    */
    {BaseIdentifier}"$"   {return symbol(sym.IDENTIFIER_STRING, yytext());}//String
    {BaseIdentifier}"%"   {return symbol(sym.IDENTIFIER_DECIMAL, yytext());}//Decimal
    {BaseIdentifier}"#"?  {return symbol(sym.IDENTIFIER_INTEGER, yytext());}//Integer

    /*
        Line End
    */
    {LineEnd}   {return symbol(sym.LINEEND);}

    /*
        Ignored
    */
    {Whitespace}    {}
    {Comment}       {}
}

//Anything that doesn't match
[^]     {throw new Error("LEXIC ERROR: I don't know what this is!: "+yytext());}