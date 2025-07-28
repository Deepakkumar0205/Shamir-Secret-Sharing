
# 🔐 Simplified Shamir's Secret Sharing (Java Implementation)

This project implements a simplified version of **Shamir's Secret Sharing** algorithm using **Lagrange interpolation** to recover the constant term (`c`) from an unknown polynomial. The input is provided in a structured JSON format.

---

## 📌 Problem Statement

Given:
- A degree `m` polynomial:  
  `f(x) = aₘxᵐ + aₘ₋₁xᵐ⁻¹ + ... + a₁x + c`
- `k = m + 1` points (x, y), with y-values encoded in various number bases

You need to:
1. Decode the y-values using their respective bases.
2. Use any method (Lagrange interpolation used here) to solve for the constant term `c` of the polynomial.

---

## 🗂️ Project Structure

```
├── input1.json        # Test case 1
├── input2.json        # Test case 2
├── Main.java          # Main Java code
└── README.md          # Documentation
```

---

## 🔧 How to Run

1. **Compile the Java code**:
   ```
   javac -cp .:json-simple-1.1.1.jar Main.java
   ```

2. **Run the program**:
   ```
   java -cp .:json-simple-1.1.1.jar Main
   ```

3. **Expected Output**:
   ```
   Secret (constant term c) from input1.json: <value>
   Secret (constant term c) from input2.json: <value>
   ```

---

## 📥 Input Format (JSON)

Each input file includes:
- `keys`: Contains `n` (total points) and `k` (points needed to reconstruct)
- Each entry: `"x": {"base": "b", "value": "v"}`  
  Where x is the point's x-coordinate, and y = base-encoded v

Example:
```json
"2": {
  "base": "2",
  "value": "111"
}
```
Means point (2, 7) since `111` in base 2 = 7.

---

## 🧮 Method Used: Lagrange Interpolation

The secret `c = f(0)` is recovered by evaluating the Lagrange interpolation polynomial at x = 0.

---

## ✅ Features

- Handles arbitrary bases for y-values (e.g., binary, octal, hex, etc.)
- Uses only the minimum `k` points needed
- Uses `BigInteger` for large number precision
- Reads from external `.json` files

---

## 📚 Dependencies

- [`json-simple`](https://code.google.com/archive/p/json-simple/) (for JSON parsing)

---

## 📤 Submission

- Code pushed to GitHub ✅  
- Output printed for both test cases ✅  
- JSON input files included ✅  

---

## 📎 License

This project is developed for academic purposes.
