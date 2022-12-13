package day11;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
public class Monkey {
    private Queue<BigInteger> items = new LinkedList<>();
    private Operation operation;
    private int operationValue;
    private int test;
    private int throwIfTrue;
    private int throwIfFalse;
    private long activity;

    public void incrementActivity() {
        activity++;
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "items=" + items +
                ", operation=" + operation +
                ", operationValue=" + operationValue +
                ", test=" + test +
                ", throwIfTrue=" + throwIfTrue +
                ", throwIfFalse=" + throwIfFalse +
                ", activity=" + activity +
                '}';
    }
}
