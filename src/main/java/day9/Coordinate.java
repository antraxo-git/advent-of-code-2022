package day9;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Coordinate {
    private int x;
    private int y;

    public void incrementX() {
        ++x;
    }

    public void incrementY() {
        ++y;
    }

    public void decrementX() {
        --x;
    }

    public void decrementY() {
        --y;
    }

    public boolean isAdjacent(Coordinate anotherPosition) {
        return ((Math.abs(getX() - anotherPosition.getX())) < 2 && (Math.abs(getY() - anotherPosition.getY())) < 2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point pt = (Point)obj;
            return (x == pt.x) && (y == pt.y);
        }
        return super.equals(obj);
    }
}
