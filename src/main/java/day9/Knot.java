package day9;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Knot {
    private Coordinate position;

    public Knot(Coordinate position) {
        this.position = position;
    }

    public void incrementX() {
        position.incrementX();
    }

    public void incrementY() {
        position.incrementY();
    }

    public void decrementX() {
        position.decrementX();
    }

    public void decrementY() {
        position.decrementY();
    }

    public boolean isAdjacent(Knot anotherKnot) {
        return position.isAdjacent(anotherKnot.getPosition());
    }

    public void getToHead(Knot head) {
        Coordinate headPosition = head.getPosition();
        if(Math.abs(position.getX() - headPosition.getX()) > 1) {
            if(position.getX() < headPosition.getX()) {
                position.incrementX();
            } else {
                position.decrementX();
            }
            if(position.getY() != headPosition.getY()) {
                if(position.getY() > headPosition.getY()) {
                    position.decrementY();
                } else {
                    position.incrementY();
                }
            }

        }
        if(Math.abs(position.getY() - headPosition.getY()) > 1) {
            if(position.getY() < headPosition.getY()) {
                position.incrementY();
            } else {
                position.decrementY();
            }
            if(position.getX() != headPosition.getX()) {
                if(position.getX() > headPosition.getX()) {
                    position.decrementX();
                } else {
                    position.incrementX();
                }
            }
        }
    }

}
