package day11;

import java.util.Map;

public enum Operation {
    ADD, SUBTRACT, MULTIPLY;

    static Operation of(String sign) {
        Map<String, Operation> signsMap = Map.of("+", Operation.ADD, "*", Operation.MULTIPLY, "-", Operation.SUBTRACT);
        return signsMap.get(sign);
    }
}
