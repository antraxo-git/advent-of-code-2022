package day12;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
public class Position extends Point {
    int steps;

    public Position(int x, int y, int steps) {
        super(x, y);
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Position) {
            Position pos = (Position) o;
            return x == pos.x && y == pos.y;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        return "Position{" +
                "steps=" + steps +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
