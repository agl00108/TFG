package com.tfg.tfgv1.rest.dto;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public class DTOPoint
{
    private double x;
    private double y;

    // Default constructor
    public DTOPoint() {}

    // Constructor using Point
    public DTOPoint(Point point)
    {
        this.x = point.getX();
        this.y = point.getY();
    }

    // Getters and setters
    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    // Method to convert DTO to Point
    public Point toPoint() {
        return new GeometryFactory().createPoint(new Coordinate(x, y));
    }
}
