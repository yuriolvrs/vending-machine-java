# Vending Machine Factory (Java)

## Overview
A graphical simulator of a Vending Machine Factory built in Java using the Swing library.  

The application allows users to create and test two types of vending machines: **Regular** (for standalone items) and **Special** (for customizable products and complex meals). The system handles the full lifecycle of a vending machine, from initial setup to consumer transactions and administrative maintenance.

## Features

- **Dual Machine Types**: Support for both Regular and Special Vending Machines.  
- **GUI Interface**: A comprehensive menu-driven system for creating, testing, and maintaining machines.  
- **Vending Operations**:  
  - Payment processing using multiple denominations (P1 to P1000)  
  - Real-time calorie tracking for single items and prepared meals  
  - Automated change calculation based on available machine currency  
  - Item preparation visualization for "Special" products (e.g., Rice Meals)  
- **Maintenance Suite**:  
  - Restocking inventory and setting individual item prices  
  - Collecting accumulated sales and replenishing change denominations  
  - Transaction summary generation showing starting vs. ending inventory and total sales  
- **Language**: Java  
- **Library**: Java Swing (GUI)  
- **Architecture**: Model-View-Controller (MVC)  

## Project Context
This project was developed for **CCPROG3 - Object-Oriented Programming** at De La Salle University.  

It demonstrates comprehensive OOP principles, including:  
- **Inheritance**: `SpecialMachine` extends `VendingMachine` to add preparation logic  
- **Encapsulation**: Managing machine state (money, items, and inventory) through private fields and public methods  
- **Polymorphism**: Handling different machine types within a unified factory simulator  
- **GUI Design**: Implementing a multi-frame user interface with event-driven programming  

## Design Highlights
- **MVC Pattern**: Separation of logic (Model), user interface (GUI), and event handling (Controller) for modularity and scalability  
- **Denomination Logic**: A dedicated `Money` class manages nine different Philippine Peso denominations to simulate realistic transaction handling  
- **Customizable Meals**: The Special Vending Machine can "prepare" products like Curry or BBQ Chicken meals by combining multiple inventory items (e.g., rice + protein + sauce) and displaying assembly steps  
- **Defensive Programming**: The system validates if sufficient change is available before finalizing any transaction to prevent machine errors
