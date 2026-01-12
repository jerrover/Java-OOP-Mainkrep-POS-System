**About This Project**

This repository contains the source code for my Final Semester Exam (UAS) project in the Object-Oriented Programming (OOP) course.

The program is a command-line interface (CLI) cashier system designed for a fictional startup called "Mainkrep". It manages transactions for a drama festival, handling various items such as tickets, merchandise, and recordings. The project demonstrates the practical application of core OOP principles, including inheritance, polymorphism, abstraction, and encapsulation.

**Key Features**

Inventory Management (CRUD): Allows adding, editing, deleting, and searching for items (Tickets, Merchandise, Recordings).

**OOP Implementations:**
- Inheritance: Utilizes a base Item class with specific subclasses for different product types.
- Polymorphism: Overrides methods like printDetail() for item-specific behavior.
- Interfaces: Implements Printable and Discountable interfaces for standardized output and discount logic.
- Dynamic Discount System: logic for calculating bulk purchase discounts and randomized "Flash Sale" events based on the system date.
- Data Persistence: Automatically saves inventory data and transaction reports to .txt files.
