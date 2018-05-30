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
    StringBuilder string = new StringBuilder();

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
Comment = ("rem"|("'"|"rem "){InputChar}*){LineEnd}?
NumberLiteral = [0-9]+
DecimalLiteral = {NumberLiteral}"."{NumberLiteral}
StringCharacter = [^\r\n\"]

%state STRING
%state MULTICOMMENT

%%

<YYINITIAL>{
    /*
        Keywords
    */
    "if"        {return symbol(sym.IF);}
    "then"      {return symbol(sym.THEN);}
    "else"      {return symbol(sym.ELSE);}
    "elseif"    {return symbol(sym.ELSEIF);}
    "endif"     {return symbol(sym.ENDIF);}
    "on"        {return symbol(sym.ON);}
    "case"      {return symbol(sym.CASE);}
    "default"   {return symbol(sym.DEFAULT);}
    "won"       {return symbol(sym.WON);}

    "for"       {return symbol(sym.FOR);}
    "to"        {return symbol(sym.TO);}
    "step"      {return symbol(sym.STEP);}
    "next"      {return symbol(sym.NEXT);}
    "while"     {return symbol(sym.WHILE);}
    "wend"      {return symbol(sym.WEND);}
    "repeat"    {return symbol(sym.REPEAT);}
    "until"     {return symbol(sym.UNTIL);}
    "continue"  {return symbol(sym.CONTINUE);}
    "break"     {return symbol(sym.BREAK);}

    //"stop"      {return symbol(sym.STOP);}

    "goto"      {return symbol(sym.GOTO);}
    "gosub"     {return symbol(sym.GOSUB);}

    "def"       {return symbol(sym.DEF);}
    //"common"    {return symbol(sym.COMMON);}
    "call"      {return symbol(sym.CALL);}
    "return"    {return symbol(sym.RETURN);}
    "out"       {return symbol(sym.OUT);}
    "end"       {return symbol(sym.END);}

    "var"       {return symbol(sym.VAR);}
    "dim"       {return symbol(sym.DIM);}
    "inc"       {return symbol(sym.INC);}
    "dec"       {return symbol(sym.DEC);}
    "swap"      {return symbol(sym.SWAP);}

    "print"     {return symbol(sym.PRINT);}
    "?"         {return symbol(sym.QUESTION);}

    "input"     {return symbol(sym.INPUT);}
    "linput"    {return symbol(sym.LINPUT);}

    //"read"      {return symbol(sym.READ);}
    //"data"      {return symbol(sym.DATA);}
    //"restore"   {return symbol(sym.RESTORE);}

    //"exec"      {return symbol(sym.EXEC);}
    //"use"       {return symbol(sym.USE);}

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
    ";"     {return symbol(sym.SEMICOLON);}


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

    "+"     {return symbol(sym.PLUS);}
    "-"     {return symbol(sym.MINUS);}
    "*"     {return symbol(sym.MULT);}
    "/"     {return symbol(sym.DIV);}
    "mod"   {return symbol(sym.MOD);}
    "div"   {return symbol(sym.INTEGER_DIV);}

    "and"   {return symbol(sym.BIT_AND);}
    "or"    {return symbol(sym.BIT_OR);}
    "xor"   {return symbol(sym.BIT_XOR);}
    "not"   {return symbol(sym.BIT_NOT);}

    "<<"    {return symbol(sym.LSHIFT);}
    ">>"    {return symbol(sym.RSHIFT);}

    /*
        Numeric Literals
    */
    {NumberLiteral}       {return symbol(sym.NUMBER, yytext());}
    {DecimalLiteral}      {return symbol(sym.DECIMAL, yytext());}

    /*
        string literal
    */
    \"      {yybegin(STRING); string.setLength(0);}

    /*
        Identifiers
    */
    {BaseIdentifier}"$"   {return symbol(sym.IDENTIFIER_STRING, yytext());}//String
    {BaseIdentifier}"#"   {return symbol(sym.IDENTIFIER_DECIMAL, yytext());}//Decimal
    {BaseIdentifier}"%"?  {return symbol(sym.IDENTIFIER_INTEGER, yytext());}//Integer
    "@"{BaseIdentifier}   {return symbol(sym.LABEL, yytext());}//Label

    /*
        Line End
    */
    {LineEnd}   {return symbol(sym.LINEEND);}

    /*
        Ignored
    */
    "#"                 {yybegin(MULTICOMMENT);}
    {Whitespace}        {}
    {Comment}           {}
}

<STRING> {
    \"                  {yybegin(YYINITIAL); return symbol(sym.STRING, string.toString());}
    {StringCharacter}+  {string.append(yytext());}
    {LineEnd}           {System.out.println("Error Lexico: Ln "+(yyline+1)+". String sin cerrar.");}
}

<MULTICOMMENT> {
    "/#"    {yybegin(YYINITIAL);}
    [^/\n]+ {}// eat comment in chunks
    "/"     {}// eat the lone slash
    \n      {yyline++;}
}

//Anything that doesn't match
[^]     {System.out.println("Error Lexico: Ln "+(yyline+1)+", Col "+(yycolumn+1)+". Texto desconocido: "+yytext());}