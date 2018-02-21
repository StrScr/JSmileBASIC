/* The following code was generated by JFlex 1.6.1 */

/*
    SmileBASIC Lexic Analyzer by StrScr
    To be used with CUP.
*/

import java_cup.runtime.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>C:/Users/oscar/OneDrive - Universidad Tecnologica Centroamericana/UNITEC/4-3/Compiladores I/Project/lexer.jflex</tt>
 */
class Lexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\3\1\2\1\0\1\3\1\1\22\0\1\12\1\47\1\0"+
    "\1\56\1\54\1\55\1\52\1\6\1\40\1\41\2\0\1\44\3\0"+
    "\12\5\1\45\1\0\1\50\1\46\1\51\2\0\1\31\1\34\1\33"+
    "\1\24\1\10\1\15\1\4\1\17\1\14\1\4\1\36\1\21\1\11"+
    "\1\20\1\25\1\26\1\4\1\7\1\23\1\16\1\32\1\37\1\30"+
    "\1\27\2\4\1\42\1\0\1\43\1\0\1\4\1\0\1\31\1\34"+
    "\1\33\1\24\1\10\1\15\1\4\1\17\1\14\1\4\1\36\1\21"+
    "\1\11\1\20\1\25\1\26\1\4\1\7\1\23\1\16\1\32\1\37"+
    "\1\30\1\27\2\4\1\0\1\53\263\0\2\13\115\0\1\22\u1faa\0"+
    "\1\35\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udee5\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\2\2\1\3\1\4\1\5\1\3\2\4"+
    "\1\1\4\4\1\1\11\4\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\2\1\1\20"+
    "\1\21\1\4\1\3\3\4\1\22\1\0\1\22\5\4"+
    "\1\23\1\4\2\0\3\4\1\0\11\4\1\24\1\25"+
    "\1\26\1\27\1\30\1\31\3\4\1\32\1\0\1\4"+
    "\2\33\1\34\4\4\3\0\3\4\1\35\1\36\2\37"+
    "\1\40\1\0\2\4\1\0\4\4\1\41\2\4\1\0"+
    "\1\4\2\42\1\0\1\4\1\43\1\44\1\45\1\46"+
    "\1\47\1\50\1\46\1\47\1\50\1\0\1\4\1\51"+
    "\1\0\1\4\1\0\5\4\1\44\1\0\1\4\2\52"+
    "\2\53\2\54\2\55\1\0\1\4\2\56\1\57\1\60"+
    "\2\61\1\0\1\4\1\0\1\4\2\62";

  private static int [] zzUnpackAction() {
    int [] result = new int[161];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\57\0\136\0\57\0\57\0\215\0\274\0\353"+
    "\0\u011a\0\u0149\0\u0178\0\u01a7\0\u01d6\0\u0205\0\u0234\0\u0263"+
    "\0\u0292\0\u02c1\0\u02f0\0\u031f\0\u034e\0\u037d\0\u03ac\0\u03db"+
    "\0\u040a\0\57\0\57\0\57\0\57\0\57\0\57\0\u0439"+
    "\0\u0468\0\u0497\0\u04c6\0\u04f5\0\u0524\0\57\0\57\0\57"+
    "\0\u0553\0\u0582\0\u05b1\0\u05e0\0\57\0\u060f\0\215\0\u063e"+
    "\0\u066d\0\u069c\0\u06cb\0\u06fa\0\215\0\u0729\0\u0758\0\u0787"+
    "\0\u07b6\0\u07e5\0\u0814\0\u0843\0\u0872\0\u08a1\0\u08d0\0\u08ff"+
    "\0\u092e\0\u095d\0\u098c\0\u09bb\0\u09ea\0\57\0\57\0\57"+
    "\0\57\0\57\0\57\0\u0a19\0\u0a48\0\u0a77\0\u0aa6\0\u0ad5"+
    "\0\u0b04\0\57\0\215\0\215\0\u0b33\0\u0b62\0\u0b91\0\u0bc0"+
    "\0\u0bef\0\u0c1e\0\u0c4d\0\u0c7c\0\u0cab\0\u0cda\0\215\0\215"+
    "\0\57\0\215\0\215\0\u0d09\0\u0d38\0\u0d67\0\u0d96\0\u0dc5"+
    "\0\u0df4\0\u0e23\0\u0e52\0\215\0\u0e81\0\u0eb0\0\u0edf\0\u0f0e"+
    "\0\u0f3d\0\u0f6c\0\u0f9b\0\u0fca\0\215\0\215\0\215\0\57"+
    "\0\57\0\57\0\215\0\215\0\215\0\u0ff9\0\u1028\0\215"+
    "\0\u1057\0\u1086\0\u10b5\0\u10e4\0\u1113\0\u1142\0\u1171\0\u11a0"+
    "\0\57\0\u11cf\0\u11fe\0\57\0\215\0\57\0\215\0\57"+
    "\0\215\0\57\0\215\0\u122d\0\u125c\0\57\0\215\0\215"+
    "\0\215\0\57\0\215\0\u128b\0\u12ba\0\u12e9\0\u1318\0\57"+
    "\0\215";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[161];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\6\1\5\1\13\1\14\1\15\1\16\1\6"+
    "\1\17\1\6\1\20\1\21\1\22\1\23\1\24\1\6"+
    "\1\25\1\6\1\26\1\27\1\30\1\2\1\6\1\31"+
    "\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41"+
    "\1\42\1\43\1\44\1\45\3\2\61\0\1\4\60\0"+
    "\2\6\1\0\3\6\2\0\6\6\1\0\12\6\1\0"+
    "\2\6\14\0\1\46\1\47\1\50\5\0\1\7\51\0"+
    "\1\10\1\51\1\5\54\10\4\0\2\6\1\0\1\6"+
    "\1\52\1\6\2\0\6\6\1\0\12\6\1\0\2\6"+
    "\14\0\1\46\1\47\1\50\4\0\2\6\1\0\3\6"+
    "\2\0\4\6\1\53\1\54\1\0\12\6\1\0\2\6"+
    "\14\0\1\46\1\47\1\50\15\0\1\55\2\0\1\56"+
    "\42\0\2\6\1\0\3\6\2\0\1\6\1\57\2\6"+
    "\1\60\1\6\1\0\12\6\1\0\2\6\14\0\1\46"+
    "\1\47\1\50\4\0\2\6\1\0\3\6\2\0\6\6"+
    "\1\0\2\6\1\61\3\6\1\62\3\6\1\0\2\6"+
    "\14\0\1\46\1\47\1\50\4\0\2\6\1\0\1\63"+
    "\2\6\2\0\3\6\1\64\2\6\1\0\2\6\1\65"+
    "\7\6\1\0\2\6\14\0\1\46\1\47\1\50\4\0"+
    "\2\6\1\0\1\6\1\66\1\6\2\0\6\6\1\0"+
    "\12\6\1\0\2\6\14\0\1\46\1\47\1\50\16\0"+
    "\1\67\11\0\1\70\32\0\2\6\1\0\3\6\2\0"+
    "\2\6\1\71\3\6\1\0\5\6\1\72\4\6\1\0"+
    "\2\6\14\0\1\46\1\47\1\50\4\0\2\6\1\0"+
    "\1\6\1\73\1\6\1\0\1\74\1\75\5\6\1\0"+
    "\12\6\1\0\2\6\14\0\1\46\1\47\1\50\4\0"+
    "\2\6\1\0\3\6\2\0\6\6\1\0\7\6\1\76"+
    "\2\6\1\0\2\6\14\0\1\46\1\47\1\50\4\0"+
    "\2\6\1\0\1\77\2\6\2\0\6\6\1\0\12\6"+
    "\1\0\2\6\14\0\1\46\1\47\1\50\4\0\2\6"+
    "\1\0\1\6\1\100\1\6\2\0\3\6\1\101\2\6"+
    "\1\0\12\6\1\0\2\6\14\0\1\46\1\47\1\50"+
    "\4\0\2\6\1\0\3\6\2\0\4\6\1\102\1\6"+
    "\1\0\12\6\1\0\2\6\14\0\1\46\1\47\1\50"+
    "\4\0\2\6\1\0\3\6\2\0\6\6\1\0\2\6"+
    "\1\103\7\6\1\0\2\6\14\0\1\46\1\47\1\50"+
    "\4\0\2\6\1\0\1\104\2\6\2\0\6\6\1\0"+
    "\12\6\1\0\2\6\14\0\1\46\1\47\1\50\4\0"+
    "\2\6\1\0\3\6\2\0\6\6\1\0\6\6\1\105"+
    "\3\6\1\0\2\6\14\0\1\46\1\47\1\50\46\0"+
    "\1\106\56\0\1\107\56\0\1\110\56\0\1\111\62\0"+
    "\1\112\57\0\1\113\5\0\1\5\60\0\2\6\1\0"+
    "\2\6\1\114\2\0\2\6\1\115\3\6\1\0\3\6"+
    "\1\116\6\6\1\0\2\6\14\0\1\46\1\47\1\50"+
    "\4\0\2\6\1\0\3\6\2\0\6\6\1\0\1\6"+
    "\1\117\10\6\1\0\2\6\14\0\1\46\1\47\1\50"+
    "\4\0\2\6\1\0\3\6\2\0\6\6\1\120\1\121"+
    "\11\6\1\0\2\6\14\0\1\46\1\47\1\50\33\0"+
    "\1\122\27\0\2\6\1\0\3\6\2\0\6\6\1\0"+
    "\10\6\1\123\1\6\1\0\2\6\14\0\1\46\1\47"+
    "\1\50\4\0\2\6\1\0\1\124\2\6\2\0\6\6"+
    "\1\0\12\6\1\0\2\6\14\0\1\46\1\47\1\50"+
    "\4\0\2\6\1\0\3\6\2\0\5\6\1\125\1\0"+
    "\12\6\1\0\2\6\14\0\1\46\1\47\1\50\4\0"+
    "\2\6\1\0\3\6\2\0\6\6\1\0\7\6\1\126"+
    "\2\6\1\0\2\6\14\0\1\46\1\47\1\50\4\0"+
    "\2\6\1\0\1\6\1\127\1\6\2\0\6\6\1\0"+
    "\12\6\1\0\2\6\14\0\1\46\1\47\1\50\4\0"+
    "\2\6\1\0\3\6\2\0\6\6\1\0\4\6\1\130"+
    "\5\6\1\0\2\6\14\0\1\46\1\47\1\50\10\0"+
    "\1\131\14\0\1\132\62\0\1\133\31\0\2\6\1\0"+
    "\1\6\1\134\1\6\2\0\6\6\1\0\2\6\1\135"+
    "\7\6\1\0\2\6\14\0\1\46\1\47\1\50\4\0"+
    "\2\6\1\0\3\6\2\0\6\6\1\0\6\6\1\136"+
    "\3\6\1\0\2\6\14\0\1\46\1\47\1\50\4\0"+
    "\2\6\1\0\3\6\2\0\1\6\1\137\4\6\1\0"+
    "\10\6\1\140\1\6\1\0\2\6\14\0\1\46\1\47"+
    "\1\50\11\0\1\141\51\0\2\6\1\0\2\6\1\142"+
    "\2\0\6\6\1\0\12\6\1\0\2\6\14\0\1\46"+
    "\1\47\1\50\4\0\2\6\1\0\3\6\2\0\2\6"+
    "\1\143\3\6\1\0\12\6\1\0\2\6\14\0\1\46"+
    "\1\47\1\50\4\0\2\6\1\0\3\6\1\0\1\144"+
    "\1\145\5\6\1\0\12\6\1\0\2\6\14\0\1\46"+
    "\1\47\1\50\4\0\2\6\1\0\3\6\2\0\4\6"+
    "\1\146\1\6\1\0\12\6\1\0\2\6\14\0\1\46"+
    "\1\47\1\50\4\0\2\6\1\0\3\6\1\0\1\147"+
    "\1\150\5\6\1\0\12\6\1\0\2\6\14\0\1\46"+
    "\1\47\1\50\4\0\2\6\1\0\3\6\2\0\2\6"+
    "\1\151\3\6\1\0\12\6\1\0\2\6\14\0\1\46"+
    "\1\47\1\50\4\0\2\6\1\0\3\6\2\0\4\6"+
    "\1\152\1\6\1\0\12\6\1\0\2\6\14\0\1\46"+
    "\1\47\1\50\4\0\2\6\1\0\1\6\1\153\1\6"+
    "\2\0\6\6\1\0\12\6\1\0\2\6\14\0\1\46"+
    "\1\47\1\50\4\0\2\6\1\0\1\154\2\6\2\0"+
    "\6\6\1\0\12\6\1\0\2\6\14\0\1\46\1\47"+
    "\1\50\4\0\2\6\1\0\3\6\1\10\1\0\6\6"+
    "\1\0\12\6\1\0\2\6\14\0\1\46\1\47\1\50"+
    "\4\0\2\6\1\0\3\6\2\0\6\6\1\0\7\6"+
    "\1\155\2\6\1\0\2\6\14\0\1\46\1\47\1\50"+
    "\4\0\2\6\1\0\1\6\1\156\1\6\2\0\6\6"+
    "\1\0\12\6\1\0\2\6\14\0\1\46\1\47\1\50"+
    "\4\0\2\6\1\0\3\6\1\0\1\157\1\160\5\6"+
    "\1\0\12\6\1\0\2\6\14\0\1\46\1\47\1\50"+
    "\10\0\1\161\52\0\2\6\1\0\1\6\1\162\1\6"+
    "\2\0\6\6\1\0\12\6\1\0\2\6\14\0\1\46"+
    "\1\47\1\50\4\0\2\6\1\0\3\6\2\0\6\6"+
    "\1\163\1\164\11\6\1\0\2\6\14\0\1\46\1\47"+
    "\1\50\4\0\2\6\1\0\1\6\1\165\1\6\2\0"+
    "\6\6\1\0\12\6\1\0\2\6\14\0\1\46\1\47"+
    "\1\50\4\0\2\6\1\0\3\6\2\0\4\6\1\166"+
    "\1\6\1\0\12\6\1\0\2\6\14\0\1\46\1\47"+
    "\1\50\4\0\2\6\1\0\3\6\2\0\2\6\1\167"+
    "\3\6\1\0\12\6\1\0\2\6\14\0\1\46\1\47"+
    "\1\50\26\0\1\170\56\0\1\171\56\0\1\172\34\0"+
    "\2\6\1\0\3\6\2\0\6\6\1\0\3\6\1\173"+
    "\6\6\1\0\2\6\14\0\1\46\1\47\1\50\4\0"+
    "\2\6\1\0\3\6\2\0\6\6\1\0\3\6\1\174"+
    "\6\6\1\0\2\6\14\0\1\46\1\47\1\50\4\0"+
    "\2\6\1\0\3\6\2\0\6\6\1\0\3\6\1\175"+
    "\6\6\1\0\2\6\14\0\1\46\1\47\1\50\20\0"+
    "\1\176\42\0\2\6\1\0\3\6\2\0\4\6\1\177"+
    "\1\6\1\0\12\6\1\0\2\6\14\0\1\46\1\47"+
    "\1\50\4\0\2\6\1\0\3\6\2\0\6\6\1\0"+
    "\1\6\1\200\10\6\1\0\2\6\14\0\1\46\1\47"+
    "\1\50\21\0\1\201\41\0\2\6\1\0\3\6\2\0"+
    "\5\6\1\202\1\0\12\6\1\0\2\6\14\0\1\46"+
    "\1\47\1\50\4\0\2\6\1\0\3\6\1\0\1\203"+
    "\1\204\5\6\1\0\12\6\1\0\2\6\14\0\1\46"+
    "\1\47\1\50\4\0\2\6\1\0\3\6\2\0\2\6"+
    "\1\205\3\6\1\0\12\6\1\0\2\6\14\0\1\46"+
    "\1\47\1\50\4\0\2\6\1\0\3\6\2\0\6\6"+
    "\1\0\6\6\1\206\3\6\1\0\2\6\14\0\1\46"+
    "\1\47\1\50\4\0\2\6\1\0\1\207\2\6\2\0"+
    "\6\6\1\0\12\6\1\0\2\6\14\0\1\46\1\47"+
    "\1\50\4\0\2\6\1\0\3\6\2\0\6\6\1\0"+
    "\6\6\1\210\3\6\1\0\2\6\14\0\1\46\1\47"+
    "\1\50\15\0\1\211\45\0\2\6\1\0\3\6\2\0"+
    "\1\6\1\166\4\6\1\0\12\6\1\0\2\6\14\0"+
    "\1\46\1\47\1\50\13\0\2\212\46\0\2\6\1\0"+
    "\3\6\1\0\1\212\1\213\5\6\1\0\12\6\1\0"+
    "\2\6\14\0\1\46\1\47\1\50\10\0\1\214\52\0"+
    "\2\6\1\0\1\6\1\215\1\6\2\0\6\6\1\0"+
    "\12\6\1\0\2\6\14\0\1\46\1\47\1\50\16\0"+
    "\1\216\44\0\2\6\1\0\3\6\2\0\2\6\1\217"+
    "\3\6\1\0\12\6\1\0\2\6\14\0\1\46\1\47"+
    "\1\50\10\0\1\220\52\0\2\6\1\0\1\6\1\221"+
    "\1\6\2\0\6\6\1\0\12\6\1\0\2\6\14\0"+
    "\1\46\1\47\1\50\21\0\1\222\41\0\2\6\1\0"+
    "\3\6\2\0\5\6\1\223\1\0\12\6\1\0\2\6"+
    "\14\0\1\46\1\47\1\50\4\0\2\6\1\0\3\6"+
    "\1\0\1\224\1\225\5\6\1\0\12\6\1\0\2\6"+
    "\14\0\1\46\1\47\1\50\4\0\2\6\1\0\3\6"+
    "\2\0\6\6\1\0\12\6\1\226\1\227\1\6\14\0"+
    "\1\46\1\47\1\50\4\0\2\6\1\0\3\6\2\0"+
    "\4\6\1\230\1\6\1\0\12\6\1\0\2\6\14\0"+
    "\1\46\1\47\1\50\4\0\2\6\1\0\3\6\2\0"+
    "\2\6\1\231\3\6\1\0\12\6\1\0\2\6\14\0"+
    "\1\46\1\47\1\50\15\0\1\232\45\0\2\6\1\0"+
    "\3\6\2\0\1\6\1\233\4\6\1\0\12\6\1\0"+
    "\2\6\14\0\1\46\1\47\1\50\20\0\1\234\42\0"+
    "\2\6\1\0\3\6\2\0\4\6\1\235\1\6\1\0"+
    "\12\6\1\0\2\6\14\0\1\46\1\47\1\50\32\0"+
    "\1\236\30\0\2\6\1\0\3\6\2\0\6\6\1\0"+
    "\7\6\1\237\2\6\1\0\2\6\14\0\1\46\1\47"+
    "\1\50\10\0\1\240\52\0\2\6\1\0\1\6\1\241"+
    "\1\6\2\0\6\6\1\0\12\6\1\0\2\6\14\0"+
    "\1\46\1\47\1\50";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4935];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\1\2\11\24\1\6\11\6\1\3\11"+
    "\4\1\1\11\1\0\10\1\2\0\3\1\1\0\11\1"+
    "\6\11\4\1\1\0\1\1\1\11\6\1\3\0\5\1"+
    "\1\11\2\1\1\0\2\1\1\0\7\1\1\0\3\1"+
    "\1\0\4\1\3\11\3\1\1\0\2\1\1\0\1\1"+
    "\1\0\5\1\1\11\1\0\1\1\1\11\1\1\1\11"+
    "\1\1\1\11\1\1\1\11\1\1\1\0\1\1\1\11"+
    "\3\1\1\11\1\1\1\0\1\1\1\0\1\1\1\11"+
    "\1\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[161];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    /* Create a new java_cup.runtime.Symbol with information about
       the current token, the token will have no value in this
       case. */
    private Symbol symbol(int type) {
        //System.out.print("<"+type+":"+yytext()+">");
        return new symbol(type, yyline, yycolumn);
    }
    
    /* Also creates a new java_cup.runtime.Symbol with information
       about the current token, but this object has a value. */
    private Symbol symbol(int type, Object value) {
        //System.out.print("<"+type+":"+yytext()+">");
        return new symbol(type, yyline, yycolumn, value);
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 216) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { throw new Error("LEXIC ERROR: I don't know what this is!: "+yytext());
            }
          case 51: break;
          case 2: 
            { return symbol(sym.LINEEND);
            }
          case 52: break;
          case 3: 
            { 
            }
          case 53: break;
          case 4: 
            { return symbol(sym.IDENTIFIER_INTEGER, yytext());
            }
          case 54: break;
          case 5: 
            { return symbol(sym.NUMBER, yytext());
            }
          case 55: break;
          case 6: 
            { return symbol(sym.LPAREN);
            }
          case 56: break;
          case 7: 
            { return symbol(sym.RPAREN);
            }
          case 57: break;
          case 8: 
            { return symbol(sym.LBRACK);
            }
          case 58: break;
          case 9: 
            { return symbol(sym.RBRACK);
            }
          case 59: break;
          case 10: 
            { return symbol(sym.COMMA);
            }
          case 60: break;
          case 11: 
            { return symbol(sym.COLON);
            }
          case 61: break;
          case 12: 
            { return symbol(sym.EQ);
            }
          case 62: break;
          case 13: 
            { return symbol(sym.NOT);
            }
          case 63: break;
          case 14: 
            { return symbol(sym.LT);
            }
          case 64: break;
          case 15: 
            { return symbol(sym.GT);
            }
          case 65: break;
          case 16: 
            { return symbol(sym.IDENTIFIER_STRING, yytext());
            }
          case 66: break;
          case 17: 
            { return symbol(sym.IDENTIFIER_DECIMAL, yytext());
            }
          case 67: break;
          case 18: 
            { return symbol(sym.IF);
            }
          case 68: break;
          case 19: 
            { return symbol(sym.TO);
            }
          case 69: break;
          case 20: 
            { return symbol(sym.EQEQ);
            }
          case 70: break;
          case 21: 
            { return symbol(sym.NOTEQ);
            }
          case 71: break;
          case 22: 
            { return symbol(sym.LTEQ);
            }
          case 72: break;
          case 23: 
            { return symbol(sym.GTEQ);
            }
          case 73: break;
          case 24: 
            { return symbol(sym.ANDAND);
            }
          case 74: break;
          case 25: 
            { return symbol(sym.OROR);
            }
          case 75: break;
          case 26: 
            { return symbol(sym.END);
            }
          case 76: break;
          case 27: 
            { return symbol(sym.INC);
            }
          case 77: break;
          case 28: 
            { return symbol(sym.FOR);
            }
          case 78: break;
          case 29: 
            { return symbol(sym.DEF);
            }
          case 79: break;
          case 30: 
            { return symbol(sym.DEC);
            }
          case 80: break;
          case 31: 
            { return symbol(sym.DIM);
            }
          case 81: break;
          case 32: 
            { return symbol(sym.OUT);
            }
          case 82: break;
          case 33: 
            { return symbol(sym.VAR);
            }
          case 83: break;
          case 34: 
            { return symbol(sym.ELSE);
            }
          case 84: break;
          case 35: 
            { return symbol(sym.TRUE);
            }
          case 85: break;
          case 36: 
            { return symbol(sym.THEN);
            }
          case 86: break;
          case 37: 
            { return symbol(sym.NEXT);
            }
          case 87: break;
          case 38: 
            { return symbol(sym.STEP);
            }
          case 88: break;
          case 39: 
            { return symbol(sym.STOP);
            }
          case 89: break;
          case 40: 
            { return symbol(sym.SWAP);
            }
          case 90: break;
          case 41: 
            { return symbol(sym.WEND);
            }
          case 91: break;
          case 42: 
            { return symbol(sym.FALSE);
            }
          case 92: break;
          case 43: 
            { return symbol(sym.PRINT);
            }
          case 93: break;
          case 44: 
            { return symbol(sym.WHILE);
            }
          case 94: break;
          case 45: 
            { return symbol(sym.UNTIL);
            }
          case 95: break;
          case 46: 
            { return symbol(sym.BREAK);
            }
          case 96: break;
          case 47: 
            { return symbol(sym.RETURN);
            }
          case 97: break;
          case 48: 
            { return symbol(sym.REPEAT);
            }
          case 98: break;
          case 49: 
            { return symbol(sym.ELSEIF);
            }
          case 99: break;
          case 50: 
            { return symbol(sym.CONTINUE);
            }
          case 100: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Converts an int token code into the name of the
   * token by reflection on the cup symbol class/interface sym
   *
   * This code was contributed by Karl Meissner <meissnersd@yahoo.com>
   */
  private String getTokenName(int token) {
    try {
      java.lang.reflect.Field [] classFields = sym.class.getFields();
      for (int i = 0; i < classFields.length; i++) {
        if (classFields[i].getInt(null) == token) {
          return classFields[i].getName();
        }
      }
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }

    return "UNKNOWN TOKEN";
  }

  /**
   * Same as next_token but also prints the token to standard out
   * for debugging.
   *
   * This code was contributed by Karl Meissner <meissnersd@yahoo.com>
   */
  public java_cup.runtime.Symbol debug_next_token() throws java.io.IOException {
    java_cup.runtime.Symbol s = next_token();
    System.out.println( "line:" + (yyline+1) + " col:" + (yycolumn+1) + " --"+ yytext() + "--" + getTokenName(s.sym) + "--");
    return s;
  }

  /**
   * Runs the scanner on input files.
   *
   * This main method is the debugging routine for the scanner.
   * It prints debugging information about each returned token to
   * System.out until the end of file is reached, or an error occured.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String argv[]) {
    if (argv.length == 0) {
      System.out.println("Usage : java Lexer [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          java.nio.charset.Charset.forName(encodingName); // Side-effect: is encodingName valid? 
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        Lexer scanner = null;
        try {
          java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
          java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new Lexer(reader);
          while ( !scanner.zzAtEOF ) scanner.debug_next_token();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}