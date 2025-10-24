package object;
import java.io.Serializable;

public class Operation implements Serializable {
	
	private double op1;
    private double op2;
    private char operateur;

    public Operation(double op1, double op2, char operateur) {
        this.op1 = op1;
        this.op2 = op2;
        this.operateur = operateur;
    }
    public double getOp1() { return op1; }
    public double getOp2() { return op2; }
    public char getOperateur() { return operateur; }

}
