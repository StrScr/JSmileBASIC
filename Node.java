public abstract class Node{
    String desc;

    @Override
    public String toString(){
        return desc;
    }

    abstract void printTree(int depth);

    abstract Node[] getChildren();
    
    void safePrint(Node n, int depth){
        if(n!=null){
            n.printTree(depth);
        }
    }

    String levelInd(int n){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append((i+1==n)?"~":"|");
        }
        return sb.toString();
    }

    String unEscapeString(String s){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); i++)
            switch (s.charAt(i)){
                case '\n': sb.append("\\n"); break;
                case '\t': sb.append("\\t"); break;
                // ... rest of escape characters
                default: sb.append(s.charAt(i));
            }
        return sb.toString();
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
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        e1.printTree(depth+1);
        e2.printTree(depth+1);
    }
    Node[] getChildren(){
        Node[] children = {e1,e2};
        return children;
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
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        e.printTree(depth+1);
    }
    Node[] getChildren(){
        Node[] children = {e};
        return children;
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
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
    }
    Node[] getChildren(){
        return null;
    }
}

class LiteralExpr<T> extends Expr{
    T value;
    public LiteralExpr(T v){
        this.value = v;
        this.desc = unEscapeString("Literal: " + v);
    }
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
    }
    Node[] getChildren(){
        return null;
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
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        head.printTree(depth+1);
        StmntList cur = tail;
        while(cur!=null){
            cur.head.printTree(depth+1);
            cur = cur.tail;
        }
    }
    Node[] getChildren(){
        if(tail==null){
            Node[] children = {head};
            return children;
        }else{
            Node[] t = tail.getChildren();
            Node[] children = new Node[1 + t.length];
            children[0] = head;
            System.arraycopy(t, 0, children, 1, t.length);
            return children;
        }
    }
}

class SimpleStmnt extends Stmnt{
    int type;
    public SimpleStmnt(int t){
        this.type = t;
        this.desc = sym.terminalNames[t];
    }
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
    }
    Node[] getChildren(){
        return null;
    }
}

class LabelStmnt extends Stmnt{
    String name;
    public LabelStmnt(String n){
        this.name = n;
        this.desc = "Label " + n;
    }
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
    }
    Node[] getChildren(){
        return null;
    }
}

class DecStmnt extends Stmnt{
    IdExpr id;
    Expr arrSize;
    Expr asig;
    public DecStmnt(IdExpr i, Expr arrs, Expr a){
        this.id = i;
        this.arrSize = arrs;
        this.asig = a;
        this.desc = "Declaration" + ((arrs==null) ? " (Array)" : "");
    }
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        id.printTree(depth+1);
        safePrint(arrSize, depth+1);
        safePrint(asig, depth+1);
    }
    Node[] getChildren(){
        Node[] children = {id,arrSize,asig};
        return children;
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
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        id.printTree(depth+1);
        safePrint(arrPos, depth+1);
        asig.printTree(depth+1);
    }
    Node[] getChildren(){
        Node[] children = {id,arrPos,asig};
        return children;
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
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        condition.printTree(depth+1);
        onTrue.printTree(depth+1);
        safePrint(onFalse, depth+1);
    }
    Node[] getChildren(){
        Node[] children = {condition,onTrue,onFalse};
        return children;
    }
}

class OnStmnt extends Stmnt{
    Expr switchvalue;
    CaseList cases;
    public OnStmnt(Expr e, CaseList l){
        this.switchvalue = e;
        this.cases = l;
        this.desc = "On";
    }
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        switchvalue.printTree(depth+1);
        cases.printTree(depth+1);
    }
    Node[] getChildren(){
        Node[] children = {switchvalue,cases};
        return children;
    }
}

class Case extends Node{
    LiteralExpr mcase;//null if default
    StmntList list;
    public Case(LiteralExpr e, StmntList l){
        this.mcase = e;
        this.list = l;
        this.desc = "Case" + ((e==null)?" (Default)":"");
    }
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        safePrint(mcase, depth+1);
        list.printTree(depth+1);
    }
    Node[] getChildren(){
        Node[] children = {mcase,list};
        return children;
    }
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
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        control.printTree(depth+1);
        asig.printTree(depth+1);
        limit.printTree(depth+1);
        step.printTree(depth+1);
        list.printTree(depth+1);
    }
    Node[] getChildren(){
        Node[] children = {control,asig,limit,step,list};
        return children;
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
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        condition.printTree(depth+1);
        list.printTree(depth+1);
    }
    Node[] getChildren(){
        Node[] children = {condition,list};
        return children;
    }
}

class RepeatStmnt extends Stmnt{
    Expr condition;
    StmntList list;
    public RepeatStmnt(Expr c, StmntList l){
        this.condition = c;
        this.list = l;
        this.desc = "Repeat";
    }
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        condition.printTree(depth+1);
        list.printTree(depth+1);
    }
    Node[] getChildren(){
        Node[] children = {condition,list};
        return children;
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
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
    }
    Node[] getChildren(){
        return null;
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
        this.desc = "Definition: " + i;
    }
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        safePrint(arguments, depth+1);
        safePrint(returnVals, depth+1);
        list.printTree(depth+1);
    }
    Node[] getChildren(){
        Node[] children = {arguments,returnVals,list};
        return children;
    }
}

class RetStmnt extends Stmnt{
    Expr value;
    public RetStmnt(Expr v){
        this.value = v;
        this.desc = "Return";
    }
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        safePrint(value, depth+1);
    }
    Node[] getChildren(){
        Node[] children = {value};
        return children;
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
        this.desc = "Call: " + i;
    }
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        safePrint(parameters, depth+1);
        safePrint(returnVals, depth+1);
    }
    Node[] getChildren(){
        Node[] children = {parameters,returnVals};
        return children;
    }
}

class SwapStmnt extends Stmnt{
    IdExpr id1, id2;
    public SwapStmnt(IdExpr i1, IdExpr i2){
        this.id1 = i1;
        this.id2 = i2;
        this.desc = "Swap";
    }
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        id1.printTree(depth+1);
        id2.printTree(depth+1);
    }
    Node[] getChildren(){
        Node[] children = {id1,id2};
        return children;
    }
}

class PrintStmnt extends Stmnt{
    ExprList list;
    public PrintStmnt(ExprList l){
        this.list = l;
        this.desc = "Print";
    }
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        safePrint(list, depth+1);
    }
    Node[] getChildren(){
        Node[] children = {list};
        return children;
    }
}

class InputStmnt extends Stmnt{
    String prompt;
    VarList list;
    public InputStmnt(String p, VarList l){
        this.prompt = p;
        this.list = l;
        this.desc = "Input: " + p;
    }
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        safePrint(list, depth+1);
    }
    Node[] getChildren(){
        Node[] children = {list};
        return children;
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
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        head.printTree(depth+1);
        VarList cur = tail;
        while(cur!=null){
            cur.head.printTree(depth+1);
            cur = cur.tail;
        }
    }
    Node[] getChildren(){
        if(tail==null){
            Node[] children = {head};
            return children;
        }else{
            Node[] t = tail.getChildren();
            Node[] children = new Node[1 + t.length];
            children[0] = head;
            System.arraycopy(t, 0, children, 1, t.length);
            return children;
        }
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
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        head.printTree(depth+1);
        ExprList cur = tail;
        while(cur!=null){
            cur.head.printTree(depth+1);
            cur = cur.tail;
        }
    }
    Node[] getChildren(){
        if(tail==null){
            Node[] children = {head};
            return children;
        }else{
            Node[] t = tail.getChildren();
            Node[] children = new Node[1 + t.length];
            children[0] = head;
            System.arraycopy(t, 0, children, 1, t.length);
            return children;
        }
    }
}

class CaseList extends Node{
    Case head;//null means
    CaseList tail;
    public CaseList(Case h, CaseList t){
        this.head = h;
        this.tail = t;
        this.desc = "Case List";
    }
    void printTree(int depth){
        System.out.println(levelInd(depth) + this.desc);
        head.printTree(depth+1);
        CaseList cur = tail;
        while(cur!=null){
            cur.head.printTree(depth+1);
            cur = cur.tail;
        }
    }
    Node[] getChildren(){
        if(tail==null){
            Node[] children = {head};
            return children;
        }else{
            Node[] t = tail.getChildren();
            Node[] children = new Node[1 + t.length];
            children[0] = head;
            System.arraycopy(t, 0, children, 1, t.length);
            return children;
        }
    }
}