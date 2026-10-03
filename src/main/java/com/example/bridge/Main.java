package com.example.bridge;

public class Main {
    public static void main(String[] args) {
        Renderer vectorRenderer = new VectorRenderer();
        Renderer rasterRenderer = new RasterRenderer();

        Shape circle = new Circle(5.0, vectorRenderer);
        Shape square = new Square(4.0, rasterRenderer);

        circle.draw();
        square.draw();

        // Runtime switching of the implementation.
        // The Circle object itself is unchanged.
        circle.setRenderer(rasterRenderer);
        circle.draw();

        square.setRenderer(vectorRenderer);
        square.draw();
    }
}
