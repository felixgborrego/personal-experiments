# Mandelbrot Plotting

Coding example from [O'Relly Programming Rust](https://www.oreilly.com/library/view/programming-rust/9781491927274/) 
to render a Mandelbrot as png.

## Usage

```bash
cargo build --release
time ./target/release/mandelbrot-plotting example_mandelbrot.png 4000x3000 -1.20,0.35 -1,0.20
```

![example_mandelbrot](example_mandelbrot.png)

