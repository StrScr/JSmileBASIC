import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public abstract class Node {
    String desc;
    int line, col;

    protected Node(int l, int c) {
        this.line = l;
        this.col = c;
    }

    @Override
    public String toString() {
        return desc;
    }

    abstract void printTree(int depth);

    abstract Node[] getChildren();

    abstract boolean semanticTest(Scope curscope, VarTable curtable);

    void semanticError(String message) {
        System.out.println("Error Semantico: Ln " + (line + 1) + ", Col " + (col + 1) + ": " + message);
    }

    void safePrint(Node n, int depth) {
        if (n != null) {
            n.printTree(depth);
        }
    }

    String levelInd(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append((i + 1 == n) ? "~" : "|");
        }
        return sb.toString();
    }

    String unEscapeString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
            switch (s.charAt(i)) {
            case '\n':
                sb.append("\\n");
                break;
            case '\t':
                sb.append("\\t");
                break;
            // ... rest of escape characters
            default:
                sb.append(s.charAt(i));
            }
        return sb.toString();
    }

    abstract void generateCode(MidCode mc);
}

/*
 * Expressions & Endings
 */

abstract class Expr extends Node {
    enum SBType {
        SB_INT, SB_DOUBLE, SB_STRING, SB_INT_ARR, SB_DOUBLE_ARR, SB_STRING_ARR, SB_TYPE_ERROR
    }

    protected Expr(int l, int c) {
        super(l, c);
    }

    static SBType symToEnum(int symbol) {
        switch (symbol) {
        case sym.IDENTIFIER_INTEGER: {
            return SBType.SB_INT;
        }
        case sym.IDENTIFIER_DECIMAL: {
            return SBType.SB_DOUBLE;
        }
        case sym.IDENTIFIER_STRING: {
            return SBType.SB_STRING;
        }
        default: {
            return SBType.SB_TYPE_ERROR;
        }
        }
    }

    static SBType toArrayType(SBType simple) {
        switch (simple) {
        case SB_INT: {
            return SBType.SB_INT_ARR;
        }
        case SB_DOUBLE: {
            return SBType.SB_DOUBLE_ARR;
        }
        case SB_STRING: {
            return SBType.SB_STRING_ARR;
        }
        default: {
            return SBType.SB_TYPE_ERROR;
        }
        }
    }

    static SBType fromArrayType(SBType complex) {
        switch (complex) {
        case SB_INT_ARR: {
            return SBType.SB_INT;
        }
        case SB_DOUBLE_ARR: {
            return SBType.SB_DOUBLE;
        }
        case SB_STRING_ARR: {
            return SBType.SB_STRING;
        }
        default: {
            return SBType.SB_TYPE_ERROR;
        }
        }
    }

    static boolean areCompatibleTypes(SBType t1, SBType t2) {
        return resultingType(t1, t2) != SBType.SB_TYPE_ERROR;
    }

    static SBType resultingType(SBType t1, SBType t2) {
        // Check if they're both string
        if (t1 == SBType.SB_STRING && t2 == SBType.SB_STRING) {
            return SBType.SB_STRING;
        }
        // Check if the first is numeric
        if (t1 == SBType.SB_INT || t1 == SBType.SB_DOUBLE) {
            // If the first is numeric, the second should be, too
            if (t2 == SBType.SB_INT || t2 == SBType.SB_DOUBLE) {
                // Resulting type should be biggest of both. AKA decimal if one of them is
                // decimal
                return (t1 == SBType.SB_DOUBLE || t2 == SBType.SB_DOUBLE) ? SBType.SB_DOUBLE : SBType.SB_INT;
            } else {
                return SBType.SB_TYPE_ERROR;
            }
        }
        // Operations between arrays are not possible, so no further checks needed
        return SBType.SB_TYPE_ERROR;
    }

    SBType getSBType() {
        return symToEnum(getType());
    }

    abstract int getType();
}

class BinExpr extends Expr {
    Expr e1, e2;
    int op;
    static Map<Integer, String> opNames;

    static {
        //TODO Complete for all operators
        opNames = new HashMap<Integer, String>();
        opNames.put(sym.PLUS, "+");
        opNames.put(sym.MINUS, "-");
        opNames.put(sym.MULT, "*");
        opNames.put(sym.DIV, "/");
    }

    public BinExpr(int l, int c, Expr e1, Expr e2, int op) {
        super(l, c);
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
        this.desc = sym.terminalNames[op];
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        e1.printTree(depth + 1);
        e2.printTree(depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { e1, e2 };
        return children;
    }

    boolean semanticTest(Scope curscope, VarTable curtable) {
        // Expressions must be compatible
        boolean valid;
        int t = getType();
        if (t == sym.error) {
            valid = false;
            semanticError("Expresiones son de tipos incompatibles.");// NOTE Could print many times in long expressions
        } else {
            // If resulting type is string, only concatenation is available
            if (t == sym.IDENTIFIER_STRING && op != sym.PLUS) {
                valid = false;
                semanticError("Expresion entre cadenas solo puede ser concatenacion (+).");
            } else {
                // Expression is string concatenation OR numeric (should be compatible for all
                // operations).
                valid = true;
            }
        }
        return valid && e1.semanticTest(curscope, curtable) && e2.semanticTest(curscope, curtable);
    }

    int getType() {// TODO use native Expr function instead
        // Get resulting type from combination of both sides of expression
        // Check if both are numeric
        int t1 = e1.getType();
        int t2 = e2.getType();
        if ((t1 == sym.IDENTIFIER_INTEGER || t1 == sym.IDENTIFIER_DECIMAL)
                && (t2 == sym.IDENTIFIER_INTEGER || t2 == sym.IDENTIFIER_DECIMAL)) {
            // Resulting type should be biggest of both sides. AKA decimal if one of them is
            // decimal
            return (t1 == sym.IDENTIFIER_DECIMAL || t2 == sym.IDENTIFIER_DECIMAL) ? sym.IDENTIFIER_DECIMAL
                    : sym.IDENTIFIER_INTEGER;
        } else {
            // Check if both are strings
            if (t1 == sym.IDENTIFIER_STRING && t2 == sym.IDENTIFIER_STRING) {
                return sym.IDENTIFIER_STRING;
            } else {
                // Types are incompatible OR at least one was already error
                return sym.error;
            }
        }
    }

    void generateCode(MidCode mc){
        e1.generateCode(mc);
        Object l1 = mc.props.remove("lugar");
        e2.generateCode(mc);
        Object l2 = mc.props.remove("lugar");
        Temporal res = mc.newTemp();
        mc.GenAsigOp(res, opNames.get(op), l1, l2);
        mc.props.put("lugar", res);
    }
}

class UnExpr extends Expr {
    Expr e;
    int op;

    public UnExpr(int l, int c, Expr e, int op) {
        super(l, c);
        this.e = e;
        this.op = op;
        this.desc = sym.terminalNames[op];
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        e.printTree(depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { e };
        return children;
    }

    boolean semanticTest(Scope curscope, VarTable curtable) {
        // Expression must be numeric
        int t = e.getType();
        boolean valid;
        if (t == sym.IDENTIFIER_INTEGER || t == sym.IDENTIFIER_DECIMAL) {
            valid = true;
        } else {
            semanticError("Operaciones unarias solo aplican a tipos numericos.");
            valid = false;
        }
        return valid && e.semanticTest(curscope, curtable);
    }

    int getType() {
        return e.getType();
    }

    void generateCode(MidCode mc){
        e.generateCode(mc);
        //TODO Only generates for unary minus, not other unary operators
        mc.add(new MidCode.CodeQuad("=u-", mc.props.remove("lugar"), null, mc.newTemp()));
    }
}

class IdExpr extends Expr {
    int type;
    String name;

    public IdExpr(int l, int c, int t, String n) {
        super(l, c);
        this.type = t;
        this.name = n;
        // this.desc = sym.terminalNames[t] + ": " + n;
        this.desc = "Variable: " + n;
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
    }

    Node[] getChildren() {
        return null;
    }

    boolean semanticTest(Scope curscope, VarTable curtable) {
        // Check if variable exists
        boolean valid;
        try {
            curtable.getVariable(name, curscope);
            valid = true;
        } catch (Exception e) {
            semanticError("La variable " + name + " no existe dentro de este ambito.");
            valid = false;
        }
        return valid;
    }

    int getType() {
        return type;
    }

    void generateCode(MidCode mc){
        //TODO Proper return?
        mc.props.put("lugar", name);
    }
}

class LiteralExpr<T> extends Expr {
    T value;
    int type;

    public LiteralExpr(int l, int c, T v, int t) {
        super(l, c);
        this.value = v;
        this.type = t;
        this.desc = unEscapeString("Literal: " + v);
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
    }

    Node[] getChildren() {
        return null;
    }

    boolean semanticTest(Scope curscope, VarTable curtable) {
        // Nothing
        return true;
    }

    int getType() {
        return type;
    }

    void generateCode(MidCode mc){
        mc.props.put("lugar", value);
    }
}

class CallExpr extends Expr {// TODO Proper semantic analysis
    // Expression wrapper for Call statement
    String name;
    ExprList parameters;

    public CallExpr(int l, int c, String i, ExprList p) {
        super(l, c);
        this.name = i;
        this.parameters = p;
        this.desc = "Call (Expr): " + i;
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        safePrint(parameters, depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { parameters };
        return children;
    }

    boolean semanticTest(Scope curscope, VarTable curtable) {// TODO
        // Check if function exists (Simpler, since no overloaded or nested functions)
        // Check if it's being called with correct arguments
        boolean valid = true;
        return valid && parameters.semanticTest(curscope, curtable);
    }

    int getType() {// TODO
        // Check return type for function
        return sym.IDENTIFIER_INTEGER;
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

/*
 * Statements
 */

abstract class Stmnt extends Node {
    protected Stmnt(int l, int c) {
        super(l, c);
    }

    boolean semanticTest(Scope curscope, VarTable curtable) {
        // General semantic test
        // All children of the current statement get tested
        Node[] children = getChildren();
        if (children == null) {
            return true;
        } else {
            boolean valid = true;
            for (Node n : children) {
                if (n == null) {
                    System.out.println("DEBUG: Attempted semantic test on null child: " + desc);
                    continue;
                }
                curscope = curscope.levelDown();
                valid = valid && n.semanticTest(curscope, curtable);
                curscope = curscope.levelUp();
                if (!valid) {
                    break;
                }
            }
            return valid;
        }
    }
}

class StmntList extends Node {
    Stmnt head;
    StmntList tail;

    public StmntList(int l, int c, Stmnt h, StmntList t) {
        super(l, c);
        this.head = h;
        this.tail = t;
        this.desc = "Statement List";
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        head.printTree(depth + 1);
        StmntList cur = tail;
        while (cur != null) {
            cur.head.printTree(depth + 1);
            cur = cur.tail;
        }
    }

    Node[] getChildren() {
        if (tail == null) {
            Node[] children = { head };
            return children;
        } else {
            Node[] t = tail.getChildren();
            Node[] children = new Node[1 + t.length];
            children[0] = head;
            System.arraycopy(t, 0, children, 1, t.length);
            return children;
        }
    }

    boolean semanticTest(Scope curscope, VarTable curtable) {
        boolean valid = head.semanticTest(curscope, curtable);
        if (tail == null) {
            return valid;
        } else {
            return valid && tail.semanticTest(curscope, curtable);
        }
    }

    void generateCode(MidCode mc){
        head.generateCode(mc);
        if(tail!=null){
            tail.generateCode(mc);
        }
    }
}

class SimpleStmnt extends Stmnt {
    int type;

    public SimpleStmnt(int l, int c, int t) {
        super(l, c);
        this.type = t;
        this.desc = sym.terminalNames[t];
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
    }

    Node[] getChildren() {
        return null;
    }

    void generateCode(MidCode mc){
        //TODO Have to generate different code for each type
    }
}

class LabelStmnt extends Stmnt {
    String name;

    public LabelStmnt(int l, int c, String n) {
        super(l, c);
        this.name = n;
        this.desc = "Label " + n;
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
    }

    Node[] getChildren() {
        return null;
    }

    void generateCode(MidCode mc){
        mc.GenLabel(new CustomLabel(name));
    }
}

class DecStmnt extends Stmnt {
    IdExpr id;
    Expr arrSize;
    Expr asig;

    public DecStmnt(int l, int c, IdExpr i, Expr arrs, Expr a) {
        super(l, c);
        this.id = i;
        this.arrSize = arrs;
        this.asig = a;
        this.desc = "Declaration" + ((arrs == null) ? " (Array)" : "");
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        id.printTree(depth + 1);
        safePrint(arrSize, depth + 1);
        safePrint(asig, depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { id, arrSize, asig };
        return children;
    }

    @Override
    boolean semanticTest(Scope curscope, VarTable curtable) {// TODO Handle arrays
        // Add variable to table. Function validates everything else.
        // Returns true if successful
        boolean valid = curtable.addVariable(id.name, id.type, curscope);
        if (!valid) {
            semanticError("La variable " + id.name + " ya existe dentro de este ambito.");
        }
        // If declaration includes assignment, check assignment type compatibility
        if (asig != null) {
            if (!Expr.areCompatibleTypes(id.getSBType(), asig.getSBType())) {
                valid = false;
                semanticError("Tipos incompatibles en inicialización de " + id.name + ".");
            }
        }
        return valid;
    }

    void generateCode(MidCode mc){
        // Declaraion should not generate code
    }
}

class AssignStmnt extends Stmnt {
    IdExpr id;
    Expr arrPos;
    Expr asig;

    public AssignStmnt(int l, int c, IdExpr i, Expr p, Expr a) {
        super(l, c);
        this.id = i;
        this.arrPos = p;
        this.asig = a;
        this.desc = "Assignment";
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        id.printTree(depth + 1);
        safePrint(arrPos, depth + 1);
        asig.printTree(depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { id, arrPos, asig };
        return children;
    }

    @Override
    boolean semanticTest(Scope curscope, VarTable curtable) {
        // Check if variable is declared
        boolean valid = true;
        try {
            curtable.getVariable(id.name, curscope);
        } catch (Exception e) {
            valid = false;
            semanticError("La variable " + id.name + " no existe dentro de este ambito.");
        }
        // Check assignment type compatibility
        if (!Expr.areCompatibleTypes(id.getSBType(), asig.getSBType())) {
            valid = false;
            semanticError("Tipos incompatibles en asignación de " + id.name + ".");
        }
        // If it includes an array index, check that too
        if (arrPos != null) {
            valid = valid && arrPos.semanticTest(curscope, curtable);
        }
        return valid && id.semanticTest(curscope, curtable) && asig.semanticTest(curscope, curtable);
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

class IfStmnt extends Stmnt {
    Expr condition;
    StmntList onTrue;
    StmntList onFalse;

    public IfStmnt(int l, int c, Expr cond, StmntList t, StmntList f) {
        super(l, c);
        this.condition = cond;
        this.onTrue = t;
        this.onFalse = f;
        this.desc = "If";
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        condition.printTree(depth + 1);
        onTrue.printTree(depth + 1);
        safePrint(onFalse, depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { condition, onTrue, onFalse };
        return children;
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

class OnStmnt extends Stmnt {
    Expr switchvalue;
    CaseList cases;

    public OnStmnt(int l, int c, Expr e, CaseList list) {
        super(l, c);
        this.switchvalue = e;
        this.cases = list;
        this.desc = "On";
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        switchvalue.printTree(depth + 1);
        cases.printTree(depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { switchvalue, cases };
        return children;
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

class Case extends Node {
    LiteralExpr mcase;// null if default
    StmntList list;

    public Case(int l, int c, LiteralExpr e, StmntList list) {
        super(l, c);
        this.mcase = e;
        this.list = list;
        this.desc = "Case" + ((e == null) ? " (Default)" : "");
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        safePrint(mcase, depth + 1);
        list.printTree(depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { mcase, list };
        return children;
    }

    boolean semanticTest(Scope curscope, VarTable curtable) {
        if (mcase == null) {
            return list.semanticTest(curscope, curtable);
        } else {// Checking the literal expression shouldn't be necessary (returns constant
                // true), but we do it anyways.
            return mcase.semanticTest(curscope, curtable) && list.semanticTest(curscope, curtable);
        }
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

class ForStmnt extends Stmnt {
    // might need changes?
    IdExpr control;
    Expr asig;
    Expr limit;
    Expr step;
    StmntList list;

    public ForStmnt(int l, int c, IdExpr contr, Expr a, Expr lim, Expr s, StmntList sl) {
        super(l, c);
        this.control = contr;
        this.asig = a;
        this.limit = lim;
        this.step = s;
        this.list = sl;
        this.desc = "For";
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        control.printTree(depth + 1);
        asig.printTree(depth + 1);
        limit.printTree(depth + 1);
        step.printTree(depth + 1);
        list.printTree(depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { control, asig, limit, step, list };
        return children;
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

class WhileStmnt extends Stmnt {
    Expr condition;
    StmntList list;

    public WhileStmnt(int l, int c, Expr cond, StmntList list) {
        super(l, c);
        this.condition = cond;
        this.list = list;
        this.desc = "While";
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        condition.printTree(depth + 1);
        list.printTree(depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { condition, list };
        return children;
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

class RepeatStmnt extends Stmnt {
    Expr condition;
    StmntList list;

    public RepeatStmnt(int l, int c, Expr cond, StmntList list) {
        super(l, c);
        this.condition = cond;
        this.list = list;
        this.desc = "Repeat";
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        condition.printTree(depth + 1);
        list.printTree(depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { condition, list };
        return children;
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

class GotoStmnt extends Stmnt {
    String label;
    boolean sub;// Allow return?

    public GotoStmnt(int l, int c, String lbl, boolean s) {
        super(l, c);
        this.label = lbl;
        this.sub = s;
        this.desc = (sub ? "GoSub " : "GoTo ") + label;
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
    }

    Node[] getChildren() {
        return null;
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

class DefStmnt extends Stmnt {
    String name;
    VarList arguments;
    VarList returnVals;
    StmntList list;

    public DefStmnt(int l, int c, String i, VarList a, VarList r, StmntList list) {
        super(l, c);
        this.name = i;
        this.arguments = a;
        this.returnVals = r;
        this.list = list;
        this.desc = "Definition: " + i;
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        safePrint(arguments, depth + 1);
        safePrint(returnVals, depth + 1);
        list.printTree(depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { arguments, returnVals, list };
        return children;
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

class RetStmnt extends Stmnt {
    Expr value; // Null means empty return

    public RetStmnt(int l, int c, Expr v) {
        super(l, c);
        this.value = v;
        this.desc = "Return";
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        safePrint(value, depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { value };
        return children;
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

class CallStmnt extends Stmnt {
    // Note: Call can be stmnt or expr
    String name;
    ExprList parameters;
    VarList returnVals;

    public CallStmnt(int l, int c, String i, ExprList p, VarList r) {
        super(l, c);
        this.name = i;
        this.parameters = p;
        this.returnVals = r;
        this.desc = "Call: " + i;
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        safePrint(parameters, depth + 1);
        safePrint(returnVals, depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { parameters, returnVals };
        return children;
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

class SwapStmnt extends Stmnt {
    IdExpr id1, id2;

    public SwapStmnt(int l, int c, IdExpr i1, IdExpr i2) {
        super(l, c);
        this.id1 = i1;
        this.id2 = i2;
        this.desc = "Swap";
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        id1.printTree(depth + 1);
        id2.printTree(depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { id1, id2 };
        return children;
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

class PrintStmnt extends Stmnt {
    ExprList list;

    public PrintStmnt(int l, int c, ExprList list) {
        super(l, c);
        this.list = list;
        this.desc = "Print";
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        safePrint(list, depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { list };
        return children;
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

class InputStmnt extends Stmnt {
    String prompt;
    VarList list;

    public InputStmnt(int l, int c, String p, VarList list) {
        super(l, c);
        this.prompt = p;
        this.list = list;
        this.desc = "Input: " + p;
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        safePrint(list, depth + 1);
    }

    Node[] getChildren() {
        Node[] children = { list };
        return children;
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

/*
 * Helper Classes
 */

class DefParam {
    VarList args;
    VarList ret;

    public DefParam(VarList a, VarList r) {
        this.args = a;
        this.ret = r;
    }
}

class VarList extends Node {
    IdExpr head;
    VarList tail;

    public VarList(int l, int c, IdExpr h, VarList t) {
        super(l, c);
        this.head = h;
        this.tail = t;
        this.desc = "Variable List";
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        head.printTree(depth + 1);
        VarList cur = tail;
        while (cur != null) {
            cur.head.printTree(depth + 1);
            cur = cur.tail;
        }
    }

    Node[] getChildren() {
        if (tail == null) {
            Node[] children = { head };
            return children;
        } else {
            Node[] t = tail.getChildren();
            Node[] children = new Node[1 + t.length];
            children[0] = head;
            System.arraycopy(t, 0, children, 1, t.length);
            return children;
        }
    }

    boolean semanticTest(Scope curscope, VarTable curtable) {
        // Variables in a function declaration are not previously declared.
        // Add these variables within function scope.
        VarList l = this;
        boolean valid = true;
        do {
            boolean added = curtable.addVariable(l.head.name, l.head.getType(), curscope);
            if (!added) {
                semanticError("Variable " + l.head.name + " ya existe en la lista de variables.");
            }
            valid = valid && added;
            l = l.tail;
        } while (l != null);
        return valid;
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

class ExprList extends Node {
    Expr head;
    ExprList tail;

    public ExprList(int l, int c, Expr h, ExprList t) {
        super(l, c);
        this.head = h;
        this.tail = t;
        this.desc = "Expression List";
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        head.printTree(depth + 1);
        ExprList cur = tail;
        while (cur != null) {
            cur.head.printTree(depth + 1);
            cur = cur.tail;
        }
    }

    Node[] getChildren() {
        if (tail == null) {
            Node[] children = { head };
            return children;
        } else {
            Node[] t = tail.getChildren();
            Node[] children = new Node[1 + t.length];
            children[0] = head;
            System.arraycopy(t, 0, children, 1, t.length);
            return children;
        }
    }

    boolean semanticTest(Scope curscope, VarTable curtable) {
        boolean valid = head.semanticTest(curscope, curtable);
        if (tail == null) {
            return valid;
        } else {
            return valid && tail.semanticTest(curscope, curtable);
        }
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

class CaseList extends Node {
    Case head;// null means ???
    CaseList tail;

    public CaseList(int l, int c, Case h, CaseList t) {
        super(l, c);
        this.head = h;
        this.tail = t;
        this.desc = "Case List";
    }

    void printTree(int depth) {
        System.out.println(levelInd(depth) + this.desc);
        head.printTree(depth + 1);
        CaseList cur = tail;
        while (cur != null) {
            cur.head.printTree(depth + 1);
            cur = cur.tail;
        }
    }

    Node[] getChildren() {
        if (tail == null) {
            Node[] children = { head };
            return children;
        } else {
            Node[] t = tail.getChildren();
            Node[] children = new Node[1 + t.length];
            children[0] = head;
            System.arraycopy(t, 0, children, 1, t.length);
            return children;
        }
    }

    boolean semanticTest(Scope curscope, VarTable curtable) {
        boolean valid = head.semanticTest(curscope, curtable);
        if (tail == null) {
            return valid;
        } else {
            return valid && tail.semanticTest(curscope, curtable);
        }
    }

    void generateCode(MidCode mc){
        //TODO
    }
}

/*
 * Semantic Analysis Classes TODO Maybe use custom error/exception classes
 */

class VarTable {// TODO Include functions and array types
    ArrayList<VarEntry> table;

    public VarTable() {
        table = new ArrayList<VarEntry>();
    }

    boolean addVariable(String identifier, int type, Scope varscope) {
        identifier = identifier.toUpperCase();
        boolean added = false;
        // Check if variable already exists first.
        try {
            getVariable(identifier, varscope); // Throws error if no variable exists within scope
        } catch (Exception e) {
            table.add(new VarEntry(identifier, type, varscope));
            System.out.println("DEBUG: Variable added:" + identifier);
            added = true;
        }
        // Return if the variable was added
        return added;
    }

    int getVariable(String identifier, Scope curscope) throws Exception {// TODO Should optimize this for single loop.
        // Find all variables with same identifier
        // System.out.println("DEBUG: Var Lookup:"+identifier);
        identifier = identifier.toUpperCase();
        ArrayList<VarEntry> matches = new ArrayList<VarEntry>();
        for (VarEntry e : table) {
            // System.out.println("DEBUG: VarTable Lookup:" + e.identifier);
            if (e.identifier.equals(identifier)) {
                matches.add(e);
            }
        }
        // If no matches were found, throw an exception
        if (matches.isEmpty()) {
            throw new Exception();
        }
        // Within matching variables, find one with valid scope
        VarEntry found = null;
        for (VarEntry e : matches) {
            // System.out.println("DEBUG: VarTable Scope:"+e.identifier);
            if (e.scope.containsScope(curscope)) {
                // System.out.println("DEBUG: Scope " + e.scope.getTotalScope());
                // No nested variables allowed, so no further validation required
                found = e;
                break;
            }
        }
        // If no suiting variable was found, throw an exception
        if (found == null) {
            throw new Exception();
        }
        return found.type;
    }

    class VarEntry {
        String identifier;
        int type;
        Scope scope;

        public VarEntry(String identifier, int type, Scope varscope) {
            this.identifier = identifier;
            this.type = type;
            this.scope = varscope;
        }
    }
}

class Scope {
    Scope up;
    ArrayList<Scope> down;
    int mynum;
    int lastdown;
    int curoffset;

    public Scope() {
        this.up = null;
        this.down = new ArrayList<Scope>();
        this.mynum = 0;
        this.lastdown = 0;
        this.curoffset = 0;
    }

    public Scope(Scope parent, int num, int offset) {
        this.up = parent;
        this.down = new ArrayList<Scope>();
        this.mynum = num;
        this.lastdown = 0;
        this.curoffset = offset;
    }

    Scope levelDown() {
        lastdown++;
        Scope next = new Scope(this, lastdown, curoffset);
        down.add(next);
        return next;
    }

    Scope levelUp() {
        return up;
    }

    void addOffset(int o) {
        // Increase offset by 'o' bytes
        // Only meant to be used by VarTables
        curoffset += o;
    }

    boolean containsScope(Scope s) {
        // Returns wether sent scope is contained within current scope
        return s.getTotalScope().startsWith(this.getTotalScope());
    }

    String getTotalScope() {
        if (up == null) {
            return "G"; // Global Scope
        } else {
            return up.getTotalScope() + "." + mynum;
        }
    }
}

class Temporal {
    int id;

    public Temporal(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "temp" + id;
    }
}

class Label{
    int id;

    public Label(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "etiq" + id;
    }
}

class CustomLabel extends Label{
    String name;

    public CustomLabel(String n){
        super(-1);
        this.name = n;
    }

    @Override
    public String toString(){
        return "cust_" + name;
    }
}

class MidCode {
    int nextline;
    int nexttemp;
    protected int nextlabel;
    Map<String, Object> props;
    ArrayList<CodeQuad> quadlist;

    public MidCode() {
        this.nextline = 1;
        this.nexttemp = 0;
        this.nextlabel = 0;
        this.props = new HashMap<String, Object>();
        this.quadlist = new ArrayList<CodeQuad>();
    }

    Temporal newTemp(){
        return new Temporal(nexttemp++);
    }

    Label newEtiq(){
        return new Label(nextlabel++);
    }

    CodeQuad add(CodeQuad cq) {
        quadlist.add(cq);
        nextline++;
        return cq;
    }

    CodeQuad GenAsigOp(Object res, String op, Object arg1, Object arg2){
        switch (op) {
            case "+":{
                return add(new CodeQuad("=+", arg1, arg2, res));
            }
            case "-":{
                return add(new CodeQuad("=-", arg1, arg2, res));
            }
            case "*":{
                return add(new CodeQuad("=*", arg1, arg2, res));
            }
            case "/":{
                return add(new CodeQuad("=/", arg1, arg2, res));
            }
            default:{
                System.out.println("Error de Generacion Intermedia: Operador especificado para asignacion desconocido: " + op);
                break;
            }
        }
        return null;
    }

    CodeQuad GenAsig(Object left, Object right) {
        return add(new CodeQuad(":=", right, null, left));
    }

    CodeQuad GenJump(Label l) {
        return add(new CodeQuad("jmp", l, null, null));
    }

    CodeQuad GenLabel(Label l){
        return add(new CodeQuad("@", l, null, null));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CodeQuad c : quadlist) {
            sb.append(c.toString() + "\n");
        }
        return sb.toString();
    }

    class CodeQuad {
        String e1;
        Object e2, e3, e4;

        /**
         * Defines a code quadruple. Only the operator is obligatory; the rest can be
         * null. Format: (operator, arg1, arg2, result)
         * 
         * @param oper The operator, written as a string. E.g.: ":=", "goto", etc.
         * @param arg1 The first argument. Can be anything, depending on operator.
         * @param arg2 The second argument. Same as the first.
         * @param res  The result. Will usually be something you can store to (E.g.:
         *             variable, temporal), but can vary.
         */
        public CodeQuad(String oper, Object arg1, Object arg2, Object res) {
            this.e1 = oper;
            this.e2 = arg1;
            this.e3 = arg2;
            this.e4 = res;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(e1);
            if (e2 != null) {
                sb.append(", " + e2.toString());
            }
            if (e3 != null) {
                sb.append(", " + e3.toString());
            }
            if (e4 != null) {
                sb.append(", " + e4.toString());
            }
            return sb.toString();
        }
    }
}