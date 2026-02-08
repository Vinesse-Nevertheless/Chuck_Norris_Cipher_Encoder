# 🥋 Chuck Norris Encoder/Decoder (Java)

## 🎯 Project Overview
This tool converts standard text into a unique unary-based "Chuck Norris" code and decodes it back to plain text. This project was developed through the **Hyperskill Java Beginner track**, utilizing their staged-building approach to handle complex data transformation.

## 📜 The Rules of Chuck Norris Encryption
To understand the code, one must understand the transformation rules:

1.  **Binary Conversion:** First, the message is converted to 7-bit ASCII binary (e.g., `C` becomes `1000011`).
2.  **Unary Blocks:** The binary string is then converted into blocks of zeros:
    * **The Header:** * `0` means the following block represents binary **1**s.
        * `00` means the following block represents binary **0**s.
    * **The Content:** The second block of zeros tells you *how many* of those bits to write.
3.  **Example:** The binary `111` becomes `0 000` (A header of `0` for the digit 1, followed by three `0`s for the count).



---

## 🏗️ Authorship & Learning Process
While I implemented the logic independently, I credit the **Hyperskill** curriculum for providing the foundational binary mathematics lessons that made this implementation possible.

* **Logic Assembly:** The most challenging aspect was the "translation" logic—parsing encrypted unary blocks back into a continuous binary string. I solved this by implementing a modular `decryptToBinary` method.
* **Manual Calculation:** Rather than using library shortcuts, I chose to manually calculate binary-to-decimal sums to ensure a deep understanding of the underlying math.
* **Error Handling:** I integrated custom validation logic to catch "malformed" Chuck Norris strings (e.g., incorrect headers or incomplete bit-blocks), ensuring the program doesn't crash on bad input.

## 🛠️ Key Technical Challenges
* **Bit Boundaries:** Maintaining the strict 7-bit ASCII requirement during decoding to ensure characters were reconstructed correctly.
* **The Solution:** I used a `StringBuilder` with a modulo check `(bStr.length() + 1) % 8 == 0` to manage character spacing and boundaries.
