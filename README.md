# Task 1: Refactoring Code to Comply with the Interface Segregation Principle (ISP)

## Problem Statement
The goal of this task is to analyze and refactor the provided code that violates the **Interface Segregation Principle (ISP)**. 

## Requirements
1. **Identify ISP Violations**: Analyze the provided `OfficeDevice` interface and identify where ISP is violated.
2. **Refactor The Code**: Refactor the interfaces to follow the ISP by creating separate interfaces for the specific functionalities. Update the existing classes (`Printer`, `Scanner`, `MultiFunctionPrinter`) to implement the new interfaces as needed. Modify the `DeviceClient` class to use these devices in a more flexible and decoupled way, avoiding multiple `if` statements that check the type of the device.
3  **Update Unit Tests**: Update unit tests to validate the refactor, ensuring that the classes are correctly following the ISP and that they continue to function as expected.

## Constraints
- You may create additional classes or interfaces as needed.
- The `DeviceClient` method signature must remain unchanged.
- Follow clean code principles while focusing on ISP.