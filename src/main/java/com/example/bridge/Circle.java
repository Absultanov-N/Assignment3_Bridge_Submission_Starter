package com.example.bridge;

public class Circle extends Shape {
    private final double radius;

    public Circle(double radius, Renderer renderer) {
        super(renderer);
        this.radius = radius;
    }

    @Override
    public void draw() {
        renderer.renderCircle(radius);
    }
}
