package day11;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/java/day11/input.txt");

        try {
            List<String> input = Files.readAllLines(file.toPath());

            List<Monkey> monkeys = new ArrayList<>();

            int monkeyCount = 0;

            for(String line : input) {
                if (line.startsWith("Monkey")) {
                    monkeys.add(new Monkey());
                }
                if (line.startsWith("  Starting")) {
                    List<BigInteger> items = Arrays.stream(line.substring(18)
                                    .split(", "))
                            .map(Integer::parseInt)
                            .map(i -> BigInteger.valueOf(i))
                            .collect(Collectors.toList());

                    monkeys.get(monkeyCount).setItems(new LinkedList<>(items));
                }
                if (line.startsWith("  Operation")) {
                    String[] operation = line.substring(23).split(" ");

                    monkeys.get(monkeyCount).setOperation(Operation.of(operation[0]));
                    if (operation[1].equals("old")) {
                        monkeys.get(monkeyCount).setOperationValue(0);
                    } else {
                        monkeys.get(monkeyCount).setOperationValue(Integer.parseInt(operation[1]));
                    }
                }
                if (line.startsWith("  Test")) {
                    String test = line.substring(21);

                    monkeys.get(monkeyCount).setTest(Integer.parseInt(test));
                }
                if (line.startsWith("    If true")) {
                    char throwIfTrue = line.charAt(line.length() - 1);

                    monkeys.get(monkeyCount).setThrowIfTrue(Character.getNumericValue(throwIfTrue));
                }
                if (line.startsWith("    If false")) {
                    char throwIfFalse = line.charAt(line.length() - 1);

                    monkeys.get(monkeyCount).setThrowIfFalse(Character.getNumericValue(throwIfFalse));
                    monkeyCount++;
                }
            }

            int modulo = 1;

            for(Monkey monkey : monkeys) {
                modulo *= monkey.getTest();
            }

                for(int round=0; round<10000; round++) {
                    for(Monkey monkey : monkeys) {
                        Queue<BigInteger> monkeyItems = monkey.getItems();
                        monkey.setOperationValue(monkey.getOperationValue() % modulo);
                        while(!monkeyItems.isEmpty()) {
                            BigInteger item = monkeyItems.remove();
                            item = item.mod(BigInteger.valueOf(modulo));
                            if(monkey.getOperationValue() == 0) {
                                item = item.pow(2);
                            } else {
                                switch (monkey.getOperation()) {
                                    case ADD:
                                        item = item.add(BigInteger.valueOf(monkey.getOperationValue()));
                                        break;
                                    case MULTIPLY:
                                        item = item.multiply(BigInteger.valueOf(monkey.getOperationValue()));
                                        break;
                                    case SUBTRACT:
                                        item = item.subtract(BigInteger.valueOf(monkey.getOperationValue()));
                                        break;
                                }
                            }
                            if(item.mod(BigInteger.valueOf(monkey.getTest())).equals(BigInteger.ZERO)) {
                                monkeys.get(monkey.getThrowIfTrue()).getItems().add(item);
                            } else {
                                monkeys.get(monkey.getThrowIfFalse()).getItems().add(item);
                            }
                            monkey.incrementActivity();
                        }
                    }
                }

            for(Monkey monkey : monkeys) {
                System.out.println(monkey);
            }

            monkeys.stream().map(m -> m.getActivity()).forEach(System.out::println);

                System.out.println(monkeys.stream()
                        .map(Monkey::getActivity)
                        .sorted(Comparator.reverseOrder())
                        .limit(2).reduce(1L, (i1, i2) -> i1 * i2));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
