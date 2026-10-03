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

`Circle` contains shape-specific information and delegates rendering:

```java
@Override
public void draw() {
    renderer.renderCircle(radius);
}
```

The renderer contains rendering-specific behavior.

### 3.2 Meaningful names

The names identify both roles directly:
- `Shape` — abstraction;
- `Renderer` — implementor;
- `VectorRenderer` / `RasterRenderer` — concrete implementations.

### 3.3 Small, focused classes

`Circle` manages circle data and delegates drawing. `VectorRenderer` and `RasterRenderer` contain rendering behavior for their respective implementations.

### 3.4 No duplicated logic between shapes

Shapes do not contain vector/raster rendering algorithms. They delegate to `Renderer`.

### 3.5 Extensibility of the implementation side

A new concrete renderer can implement `Renderer` without changing the abstraction classes:

```java
class NewRenderer implements Renderer {
    // implement renderCircle() and renderSquare()
}
```

Existing `Shape`, `Circle`, and `Square` classes remain unchanged.

## 4. Conclusion and Trade-offs

The Bridge solution introduces additional interfaces and classes compared with a simpler single-hierarchy design. This increases initial complexity.

The trade-off is separation of abstraction and implementation. The client can combine shapes with different renderers at runtime, and adding another renderer does not require changing the abstraction hierarchy.

## 5. GitHub Repository

Add the final GitHub repository URL here after publication.
