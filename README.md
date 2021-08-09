# Collatz Conjecture

3 implementations of the [Collatz Conjecture](https://www.youtube.com/watch?v=094y1Z2wpJg) written
in Rust, Kotlin and Python.

All take the numbers to calculate up to and print the total amount of steps for all those numbers.

# Usage

```bash
# Rust
cd rust/
cargo build --release # Build
./target/release/collatz 1000000000 # Run

# Kotlin
cd kotlin/
gradle jar # Build
java -jar build/libs/collatz*.jar 1000000000 # Run

# Python
python ./python/main.py 1000000000 # Run
```

# Benchmarks

| Amount of steps   | Rust      | Kotlin    | Kotlin Diff   | Python    | Python Diff
| ---               | ---       | ---       | ---           | ---       | ---
| 1_000_000         | 4.8ms     | 165.8ms   | 34.53         | 1.894s    | 394.42
| 10_000_000        | 33.3ms    | 312.4ms   | 9.39          | 18.757s   | 563.72
| 100_000_000       | 351.9ms   | 1.785s    | 5.07          | (>1min)   | ∞
| 1_000_000_000     | 3.861s    | 24.911s   | 6.42          | (>1min)   | ∞
