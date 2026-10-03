package com.example.bridge;

public class VectorRenderer implements Renderer {
    @Override
    public void renderCircle(double radius) {
        System.out.println("VectorRenderer: drawing a circle with radius " + radius);
    }

    @Override
    public void renderSquare(double side) {
        System.out.println("VectorRenderer: drawing a square with side " + side);
    }
}
