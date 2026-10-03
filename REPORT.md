# Assignment 3 — Bridge Pattern Report

## 1. Introduction

### Chosen topic
The project uses the suggested **Shape–Renderer** domain. The abstraction side contains shapes, while the implementation side contains rendering mechanisms.

### Why Bridge fits
Bridge is appropriate because there are two dimensions that can vary:
1. the shape type (`Circle`, `Square`);
2. the rendering implementation (`VectorRenderer`, `RasterRenderer`).

The design keeps these dimensions in separate hierarchies and connects them through composition.

## 2. UML Class Diagram

The UML diagram is provided in `uml/bridge.puml`.

The central relationship is:

```text
Shape ──composition──> Renderer
```

`Shape` is the Abstraction and `Renderer` is the Implementor. This relationship is composition rather than inheritance: `Shape` has a `Renderer` reference, while `Circle` and `Square` inherit from `Shape`, and `VectorRenderer` and `RasterRenderer` implement `Renderer`.

The client can switch the renderer on the same shape object:

```java
Shape circle = new Circle(5.0, new VectorRenderer());
circle.draw();
circle.setRenderer(new RasterRenderer());
circle.draw();
```

This demonstrates that the two hierarchies vary independently. A new shape can use the existing renderers, and a new renderer can be added without changing the shape hierarchy.

## 3. Clean Code

### 3.1 Separation of responsibilities

`Circle` handles circle-specific data and behavior, while rendering is delegated to the `Renderer` implementation:

```java
@Override
public void draw() {
    renderer.renderCircle(radius);
}
```

### 3.2 Meaningful names

Names such as `Renderer`, `renderCircle`, `renderSquare`, `VectorRenderer`, and `RasterRenderer` clearly communicate the responsibilities of the classes and methods:

```java
public interface Renderer {
    void renderCircle(double radius);
    void renderSquare(double side);
}
```

### 3.3 Small, focused classes

`Circle` is focused on circle-specific data and behavior. It delegates rendering instead of containing vector or raster rendering algorithms:

```java
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
```

### 3.4 No duplicated rendering logic

`Circle` does not contain separate vector and raster rendering implementations. Rendering is delegated to the `Renderer` implementations:

```java
@Override
public void draw() {
    renderer.renderCircle(radius);
}
```

### 3.5 Extensibility of the implementation side

A new renderer can implement the existing `Renderer` interface without modifying `Shape`, `Circle`, or `Square`:

```java
class NewRenderer implements Renderer {
    @Override
    public void renderCircle(double radius) {
        // new implementation
    }

    @Override
    public void renderSquare(double side) {
        // new implementation
    }
}
```

## 4. Conclusion and Trade-offs

The Bridge solution introduces additional interfaces and classes compared with a simpler single-hierarchy design. This increases initial complexity.

The trade-off is separation of abstraction and implementation. The client can combine shapes with different renderers at runtime, and adding another renderer does not require changing the abstraction hierarchy.

## 5. GitHub Repository

https://github.com/Absultanov-N/Assignment3_Bridge_Submission_Starter.git
