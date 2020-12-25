package dev.offlical.spigame.collisions;

import dev.offlical.spigame.gameobjects.MapLocation;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CollisionBox {


    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    private int width;
    private int height;

    public CollisionBox(MapLocation corner1, MapLocation corner2) {

        // max and min x
        if(corner1.getX() < corner2.getX()) {
            maxX = corner2.getX();
            minX = corner1.getX();
        }
        if(corner2.getX() < corner1.getX()) {
            maxX = corner1.getX();
            minX = corner2.getX();
        }

        // max and min y
        if(corner1.getY() < corner2.getY()) {
            maxY = corner2.getY();
            minY = corner1.getY();
        }
        if(corner2.getY() < corner1.getY()) {
            maxY = corner1.getY();
            minY = corner2.getY();
        }


    }

    /*

    Create a collision box in a square shape, with a certain size, ex: 3x3, 4x4, 5x5

     */
    public CollisionBox(int squareSize) {

        if(squareSize < 0) {
            try {
                throw new Exception("Cannot create collission box of negative size!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.maxY = squareSize;
        this.maxX = squareSize;

        this.minY = -squareSize;
        this.minX = -squareSize;

        this.width = squareSize;
        this.height = squareSize;
    }
    /*

    Create a collision box with a certain width and height, ex: 3x5, 2x4, 2x3

     */
    public CollisionBox(int width, int height) {

        this.width = width;
        this.height = height;

        this.maxY = height;
        this.maxX = width;

        this.minY = -height;
        this.minX = -width;
    }

    public void relateToLocation(MapLocation location) {

        this.maxX = location.getX() + width;
        this.maxY = location.getY() + height;

        this.minX = location.getX() - width;
        this.minY = location.getY() - minY;

    }

    public boolean isColliding(CollisionBox box) {

        MapLocation maxLocation = new MapLocation(box.getMaxX(),box.getMaxY());
        MapLocation minLocation = new MapLocation(box.getMinX(),box.getMinY());

        return this.didCollide(minLocation) || this.didCollide(maxLocation);
    }

    public boolean didCollide(MapLocation location) {
        /*
        (1,1) min x, min y
        * * * * * *
        *         * (5,2)
        *         *
        *         *
        * * * * * *
                    (5,5) max x, max y


        a point that would collide with the rectangle would need to have coordinates that aren't bigger than the maxX and that aren't smaller than minX, same with the Y coordinate

         */

        return location.getX() <= maxX && location.getX() >= minX && location.getY() <= maxY && location.getY() >= minY;
    }

}