package com.vb.robot.mover.model;


import java.util.HashMap;
import java.util.Map;

/**
 * Enum Direction for setting direction for Robot and perform direction manipulation
 * @author vivek.bharti
 *
 */
public enum Direction {

    NORTH(0), EAST(1), SOUTH(2), WEST(3);
    private static Map<Integer, Direction> map = new HashMap<Integer, Direction>();

    static {
        for (Direction directionEnum : Direction.values()) {
            map.put(directionEnum.directionIndex, directionEnum);
        }
    }

    private int directionIndex;

    private Direction(int direction) {
        this.directionIndex = direction;
    }

    /**
     * Returns value of a particular direction
     * @param directionNum
     * @return
     */
    public static Direction valueOf(int directionNum) {
        return map.get(directionNum);
    }

    /**
     * Returns the direction on the left of the current one
     */
    public Direction leftDirection() {
        return rotate(-1);
    }

    /**
     * Returns the direction on the right of the current one
     */
    public Direction rightDirection() {
        return rotate(1);
    }

    /**
     * @param step
     * @return
     */
    private Direction rotate(int step) {

        int newIndex = (this.directionIndex + step) < 0 ?
                map.size() - 1 :(this.directionIndex + step) % map.size();

        return Direction.valueOf(newIndex);
    }

}
