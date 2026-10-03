package com.example.bridge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BridgeDemoTest {
    @Test
    void shapesCanUseDifferentRenderers() {
        Shape vectorCircle = new Circle(5, new VectorRenderer());
        Shape rasterCircle = new Circle(5, new RasterRenderer());

        assertDoesNotThrow(vectorCircle::draw);
        assertDoesNotThrow(rasterCircle::draw);
    }
}
