# My Project

## A spending tracker

This project is an application for tracking the user's
spending. Set a budget for the week or month, log 
the amount and category of each transaction you make, track
how far under or over your budget you are. It will be aimed more 
at tracking the kind of small purchases that add up, like a coffee 
every other day or an Amazon order, rather than big payments like
tuition and rent.

The main purpose of creating this project was to learn about building a 
GUI and implementing saving and loading to a program.

## Examples
The following is an example of what might be printed to the console when
exiting the program: 

Fri Dec 02 04:40:35 PST 2022
Changed budget to $500.0


Fri Dec 02 04:40:50 PST 2022
Added purchase: $652.84 - Travel


Fri Dec 02 04:40:59 PST 2022
Changed budget to $750.0


Fri Dec 02 04:41:07 PST 2022
Added purchase: $58.9 - Entertainment

## Reflection
- My BudgetPrinter and ScreenPrinter classes share a lot of code, so if I were to do any
refactoring I'd want to remove this repetitive code. I would probably do so by creating a superclass that
they share and extend. Then I wouldn't have the same methods repeating in different
classes.
- I think the way the GUI class accesses data in the workroom is currently unintuitive. As it is now, if you
wanted to get back your current budget, the GUI class would first call the .getBudget() getter in the PurchaseTracker
class, which would then call the .getBudget() getter in the WorkRoom class, then you would get your budget back. So I 
have a getter that calls a getter. This step seems unnecessary, and at this stage of the project,
I actually think that most of, if not all of my PurchaseTracker class is redundant, and just acts as an extra step to
accessing the workroom. If I were to refactor the project, I would probably edit the WorkRoom class to have the
same functionality as PurchaseTracker, then delete PurchaseTracker altogether. The PurchaseTracker field and its uses
in GUI would be replaced with WorkRoom.

## Citations:
The classes found in both persistence packages, and their implementations, were adapted from the sample application
that can be found at the following link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

The classes GUI, KeyPad, ScreenPrinter, and BudgetChecker were adapted from the AlarmSystem example that can be 
found at the following link: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
