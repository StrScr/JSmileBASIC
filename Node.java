public abstract class Node{
    String desc;

    @Override
    public String toString(){
        return desc;
    }
}

/*
    Expressions & Endings
*/

abstract class Expr extends Node{}

class BinExpr extends Expr{
    Expr e1, e2;
    int op;
    public BinExpr(Expr e1, Expr e2, int op){
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
        this.desc = sym.terminalNames[op];
    }
}

class UnExpr extends Expr{
    Expr e;
    int op;
    public UnExpr(Expr e, int op){
        this.e = e;
        this.op = op;
        this.desc = sym.terminalNames[op];
    }
}

class IdExpr extends Expr{
    int type;
    String name;
    public IdExpr(int t, String n){
        this.type = t;
        this.name = n;
        this.desc = sym.terminalNames[t] + ": " + n;
    }
}

class LiteralExpr<T> extends Expr{
    T value;
    public LiteralExpr(T v){
        this.value = v;
        this.desc = "" + v;
    }
}

/*
    Statements
*/

abstract class Stmnt extends Node{}

class StmntList extends Node{
    Stmnt head;
    StmntList tail;
    public StmntList(Stmnt h, StmntList t){
        this.head = h;
        this.tail = t;
        this.desc = "Statement List";
    }
}

class SimpleStmnt extends Stmnt{
    int type;
    public SimpleStmnt(int t){
        this.type = t;
        this.desc = sym.terminalNames[t];
    }
}

class LabelStmnt extends Stmnt{
    String name;
    public LabelStmnt(String n){
        this.name = n;
        this.desc = "Label " + n;
    }    
}

class DecStmnt extends Stmnt{
    IdExpr id;
    boolean isArray;
    Expr arrSize;
    Expr asig;
    public DecStmnt(IdExpr i, boolean arr, Expr arrs, Expr a){
        this.id = i;
        this.isArray = arr;
        this.arrSize = arrs;
        this.asig = a;
        this.desc = "Declaration" + (arr ? " (Array)" : "");
    }
}

class AssignStmnt extends Stmnt{
    IdExpr id;
    Expr arrPos;
    Expr asig;
    public AssignStmnt(IdExpr i, Expr p, Expr a){
        this.id = i;
        this.arrPos = p;
        this.asig = a;
        this.desc = "Assignment";
    }
}

class IfStmnt extends Stmnt{
    Expr condition;
    StmntList onTrue;
    StmntList onFalse;
    public IfStmnt(Expr c, StmntList t, StmntList f){
        this.condition = c;
        this.onTrue = t;
        this.onFalse = f;
        this.desc = "If";
    }
}

class OnStmnt extends Stmnt{
    //todo
}

class ForStmnt extends Stmnt{
    //might need changes?
    IdExpr control;
    Expr asig;
    Expr limit;
    Expr step;
    StmntList list;
    public ForStmnt(IdExpr c, Expr a, Expr l, Expr s, StmntList sl){
        this.control = c;
        this.asig = a;
        this.limit = l;
        this.step = s;
        this.list = sl;
        this.desc = "For";
    }
}

class WhileStmnt extends Stmnt{
    Expr condition;
    StmntList list;
    public WhileStmnt(Expr c, StmntList l){
        this.condition = c;
        this.list = l;
        this.desc = "While";
    }
}

class RepeatSmnt extends Stmnt{
    Expr condition;
    StmntList list;
    public RepeatSmnt(Expr c, StmntList l){
        this.condition = c;
        this.list = l;
        this.desc = "Repeat";
    }
}

class GotoStmnt extends Stmnt{
    String label;
    boolean sub;//Allow return?
    public GotoStmnt(String l, boolean s){
        this.label = l;
        this.sub = s;
        this.desc = (sub ? "GoSub " : "GoTo ") + label;
    }
}

class DefStmnt extends Stmnt{
    String name;
    VarList arguments;
    VarList returnVals;
    StmntList list;
    public DefStmnt(String i, VarList a, VarList r, StmntList l){
        this.name = i;
        this.arguments = a;
        this.returnVals = r;
        this.list = l;
        this.desc = "Definition";
    }
}

class RetStmnt extends Stmnt{
    Expr value;
    public RetStmnt(Expr v){
        this.value = v;
        this.desc = "Return";
    }
}

class CallStmnt extends Stmnt{
    //TODO: Call can be stmnt and/or expr!!!
    String name;
    ExprList parameters;
    VarList returnVals;
    public CallStmnt(String i, ExprList p, VarList r){
        this.name = i;
        this.parameters = p;
        this.returnVals = r;
        this.desc = "Call";
    }
}

class SwapStmnt extends Stmnt{
    IdExpr id1, id2;
    public SwapStmnt(IdExpr i1, IdExpr i2){
        this.id1 = i1;
        this.id2 = i2;
        this.desc = "Swap";
    }
}

class PrintStmnt extends Stmnt{
    ExprList list;
    public PrintStmnt(ExprList l){
        this.list = l;
        this.desc = "Print";
    }
}

class InputStmnt extends Stmnt{
    String prompt;
    VarList list;
    public InputStmnt(String p, VarList l){
        this.prompt = p;
        this.list = l;
        this.desc = "Input";
    }
}

/*
    Helper Classes
*/

class DefParam{
    VarList args;
    VarList ret;
    public DefParam(VarList a, VarList r){
        this.args = a;
        this.ret = r;
    }
}

class VarList extends Node{
    IdExpr head;
    VarList tail;
    public VarList(IdExpr h, VarList t){
        this.head = h;
        this.tail = t;
        this.desc = "Variable List";
    }
}

class ExprList extends Node{
    Expr head;
    ExprList tail;
    public ExprList(Expr h, ExprList t){
        this.head = h;
        this.tail = t;
        this.desc = "Expression List";
    }
}