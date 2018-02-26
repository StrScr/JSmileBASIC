/*
    SmileBASIC Lexic Analyzer by StrScr
    To be used with CUP.
*/
%%

%class SLexer
%unicode
%ignorecase
%standalone

%column
%line

%{
    StringBuilder string = new StringBuilder();
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
StringCharacter = [^\r\n\"]

%state STRING

%%

<YYINITIAL>{
    /*
        Keywords
    */
    "if"        {System.out.println("<IF>");}
    "then"      {System.out.println("<THEN>");}
    "else"      {System.out.println("<ELSE>");}
    "elseif"    {System.out.println("<ELSEIF>");}
    "endif"     {System.out.println("<THEN>");}

    "to"        {System.out.println("<TO>");}
    "step"      {System.out.println("<STEP>");}
    "for"       {System.out.println("<FOR>");}
    "next"      {System.out.println("<NEXT>");}
    "while"     {System.out.println("<WHILE>");}
    "wend"      {System.out.println("<WEND>");}
    "repeat"    {System.out.println("<REPEAT>");}
    "until"     {System.out.println("<UNTIL>");}
    "continue"  {System.out.println("<CONTINUE>");}
    "break"     {System.out.println("<BREAK>");}

    "stop"      {System.out.println("<STOP>");}

    "def"       {System.out.println("<DEF>");}
    "return"    {System.out.println("<RETURN>");}
    "out"       {System.out.println("<OUT>");}
    "end"       {System.out.println("<END>");}

    "var"       {System.out.println("<VAR>");}
    "dim"       {System.out.println("<DIM>");}
    "inc"       {System.out.println("<INC>");}
    "dec"       {System.out.println("<DEC>");}
    "swap"      {System.out.println("<SWAP>");}

    "print"     {System.out.println("<PRINT>");}

    /*
        Boolean Literals
    */
    "true"  {System.out.println("<TRUE>");}
    "false" {System.out.println("<FALSE>");}

    /*
        Separators
    */
    "("     {System.out.println("<LPAREN>");}
    ")"     {System.out.println("<RPAREN>");}
    "["     {System.out.println("<LBRACK>");}
    "]"     {System.out.println("<RBRACK>");}
    ","     {System.out.println("<COMMA>");}
    ":"     {System.out.println("<COLON>");}

    /*
        Operators
    */
    "="     {System.out.println("<EQ>");}

    "=="    {System.out.println("<EQEQ>");}
    "!="    {System.out.println("<NOTEQ>");}
    "<"     {System.out.println("<LT>");}
    "<="    {System.out.println("<LTEQ>");}
    ">"     {System.out.println("<GT>");}
    ">="    {System.out.println("<GTEQ>");}

    "!"     {System.out.println("<NOT>");}
    "&&"    {System.out.println("<ANDAND>");}
    "||"    {System.out.println("<OROR>");}

    "+"     {System.out.println("<PLUS>");}
    "-"     {System.out.println("<MINUS>");}
    "*"     {System.out.println("<MULT>");}
    "/"     {System.out.println("<DIV>");}

    /*
        Numeric Literals
    */
    {NumberLiteral}       {System.out.println("<NUMBER, " + yytext() + ">");}

    /*
        string literal
    */
    \"      {yybegin(STRING); string.setLength(0);}

    /*
        Identifiers
    */
    {BaseIdentifier}"$"   {System.out.println("<IDENTIFIER_STRING, " + yytext() + ">");}//String
    {BaseIdentifier}"#"   {System.out.println("<IDENTIFIER_DECIMAL, " + yytext() + ">");}//Decimal
    {BaseIdentifier}"%"?  {System.out.println("<IDENTIFIER_INTEGER, " + yytext() + ">");}//Integer

    /*
        Line End
    */
    {LineEnd}   {System.out.println("<LINEEND>");}

    /*
        Ignored
    */
    {Whitespace}    {}
    {Comment}       {}
}

<STRING> {
  \"                             { yybegin(YYINITIAL); System.out.println("<STRING_LITERAL, " + string.toString() + ">"); }
  
  {StringCharacter}+             { string.append( yytext() ); }
  
  {LineEnd}                      { throw new RuntimeException("Unterminated string at end of line "+yyline);}
}

//Anything that doesn't match
[^]     {throw new Error("LEXIC ERROR on line "+yyline+", column "+yycolumn+": I don't know what this is!: "+yytext());}