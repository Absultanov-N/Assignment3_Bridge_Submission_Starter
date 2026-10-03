# Assignment 3 — Bridge Pattern

## Topic
Shape–Renderer Bridge.

## Goal
The project demonstrates the Bridge structural design pattern by separating:
- the **Abstraction hierarchy**: `Shape`, `Circle`, `Square`;
- the **Implementor hierarchy**: `Renderer`, `VectorRenderer`, `RasterRenderer`.

`Shape` stores a mutable `Renderer` reference through composition. Therefore, the same shape object can use different renderer implementations without changing the shape classes.

## Project structure

```text
src/
├── main/java/com/example/bridge/
│   ├── Main.java
│   ├── Shape.java
│   ├── Circle.java
│   ├── Square.java
│   ├── Renderer.java
│   ├── VectorRenderer.java
│   └── RasterRenderer.java
└── test/java/com/example/bridge/
    └── BridgeDemoTest.java
```

## How the Bridge works

- `Shape` is the Abstraction.
- `Circle` and `Square` are Refined Abstractions.
- `Renderer` is the Implementor.
- `VectorRenderer` and `RasterRenderer` are Concrete Implementors.
- `Main` is the Client.

The two sides are connected with composition:

```java
protected Renderer renderer;
```

The client can compose the same abstraction object with different implementations at runtime:

```java
Shape circle = new Circle(7.0, new VectorRenderer());
circle.draw();

circle.setRenderer(new RasterRenderer());
circle.draw();
```

The second call uses the same `Circle` object; only its composed `Renderer` implementation changes. No change to `Circle` is required when the renderer changes.

## Clean Code principles

### 1. Separation of responsibilities
Before:
```java
class Circle {
    void drawVector() { /* vector-specific code */ }
    void drawRaster() { /* raster-specific code */ }
}
```

After:
```java
class Circle extends Shape {
    public void draw() {
        renderer.renderCircle(radius);
    }
}
```

`Circle` describes the shape; the renderer handles rendering.

### 2. Meaningful names
`Shape`, `Circle`, `Square`, `Renderer`, `VectorRenderer`, and `RasterRenderer` explicitly communicate their roles.

### 3. Small, focused classes
Each class has a narrow responsibility: a shape stores its own data, while a renderer performs rendering.

### 4. No duplicated rendering logic
Rendering logic is kept inside the concrete renderer classes instead of being copied into every shape.

### 5. Extensibility of the implementation side
A new renderer can implement `Renderer` without modifying `Shape`, `Circle`, or `Square`.

## Trade-off

Bridge adds more classes and indirection than a single hierarchy. The benefit is that abstraction and implementation can vary separately, so the design is easier to extend when multiple combinations are required.

## Running

JDK 17 is required.

Compile and run with IntelliJ IDEA, or from the command line:

```bash
javac -d out src/main/java/com/example/bridge/*.java
java -cp out com.example.bridge.Main
```

## UML

See `uml/bridge.puml`.
