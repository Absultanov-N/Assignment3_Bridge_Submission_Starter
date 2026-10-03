package com.example.bridge;

public class Square extends Shape {
    private final double side;

    public Square(double side, Renderer renderer) {
        super(renderer);
        this.side = side;
    }

    @Override
    public void draw() {
        renderer.renderSquare(side);
    }
}
