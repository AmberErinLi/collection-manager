# Golf Ball Collection Manager
A Java-based collection manager that stores and organizes golf balls in a **Binary Search Tree (BST)**.  
Items are sorted using a custom comparison algorithm (color → year → description), allowing for efficient lookup, insertion, and traversal.  

This project demonstrates:

- Implementing a custom class that implements `Comparable`
- Recursively building and managing a Binary Search Tree structure
- File-based input/output using preorder traversal
- Designing readable, maintainable classes with proper encapsulation
- Writing test cases to validate behavior and edge cases

---

## 🚩 Project Overview
The system manages a real-world collection through three core components:

| Component | Description |
|----------|--------------|
| `GolfBall` | Represents an item in the collection; implements `Comparable` and supports parsing from input |
| `CollectionManager` | BST-based structure that stores items with recursive add/contains/save methods |
| `Client` | Console-based interface for interacting with the collection |

### ⚙️ Sorting Order
Golf balls are compared by:
1. **Color** (based on ROYGBIV ordering)
2. **Year acquired**
3. **Description** (alphabetical)

---

## 📁 Key Features
- **Add items** to the BST while preserving sorted structure
- **Check containment** in O(log n) average time
- **Print collection** in sorted order (inorder traversal)
- **Save and load** collections to/from text files (preorder format)
- **Recursive filter extension**: return a list of all golf balls matching a target color

Example Filter Usage:
```text
Enter color to filter: pink
[pink, 2021, "My first tournament ball", ...]
```

---

## 🧪 Testing
A `Testing.java` file includes:
- Tests for each public method (except `parse`)
- Verification of ordering, null-handling, saving/loading, and filtering

---

## 📂 Sample Input Format
A valid input file used to construct the BST must follow this repeating format:
```text
pink
2021
Special tournament ball
green
2019
Driving range
red
2023
Birthday gift
```

---

## ▶️ Running the Program
Compile and run from the command line:
```bash
javac *.java
java Client
```
Example actions from the menu:
```text
[add] add an item
[contains] check if item exists
[print] view collection
[save] export to file
[creative] filter by a property
[quit] exit
```

---

## 🧠 Skills & Concepts Demonstrated
- Recursion and tree-based algorithm design
- Comparable / comparison logic with multi-field ordering
- Data structure invariants (BST property)
- Encapsulation and separation of concerns
- File I/O and client interaction
- Automated testing mindset

## 📂 Repository Structure
The project is organized as follows:

```text
collection-manager/
├── data/                         # Example save files used to build trees
├── src/                          # Java source code
│   ├── Client.java               # Console interface for running the program
│   ├── CollectionManager.java    # Binary Search Tree that stores GolfBall objects
│   ├── GolfBall.java         # Comparable item class representing a golf ball
│   └── Testing.java              # Test file for validating class behavior
├── README.md                     # Project overview
└── .gitignore                    # Git exclusions
```
