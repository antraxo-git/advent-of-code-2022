package day12;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Position extends Point {
    int steps;

    public Position(int x, int y, int steps) {
        super(x, y);
        this.steps = steps;
    }
}
