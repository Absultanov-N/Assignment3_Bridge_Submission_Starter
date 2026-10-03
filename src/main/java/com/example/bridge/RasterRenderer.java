package com.example.bridge;

public class RasterRenderer implements Renderer {
    @Override
    public void renderCircle(double radius) {
        System.out.println("RasterRenderer: drawing a circle with radius " + radius);
    }

    @Override
    public void renderSquare(double side) {
        System.out.println("RasterRenderer: drawing a square with side " + side);
    }
}
