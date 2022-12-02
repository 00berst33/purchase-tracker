# My Personal Project

## A spending tracker

My project will be an application for tracking the user's
spending. Set a budget for the week or month, log 
the amount and category of each transaction you make, track
how far under or over your budget you are. Statistics on
your spending history and how it differs between weeks
and months will be available. Possible features include
graphs to visualize your spending over time. It will be aimed more at tracking the kind of small
purchases that add up, like a coffee every other day
or an Amazon order, rather than big payments like
tuition and rent.

Anyone who is looking to cut down on small unnecessary
purchases will use the application. It is of interest
to me largely because I'm curious to see what results
I would get from using something like it. Of course
I can just look at my bank statement if I want to see
my purchase history, but I hope to make an application
that makes the user reflect on their spending a bit more
than one normally would.

## User stories:
- As a user, I want to be able to add a purchase to my history
- As a user, I want to be able to specify the category of a purchase
- As a user, I want to be able to set a budget for a certain time period
- As a user, I want to be able to see how my spending has changed over time
- As a user, I want to be able to save my budget and purchases made to file
- As a user, I want to be able to load my budget and purchases made from file

# Instructions for Grader:
- The first event related to adding Xs to Ys in my program is adding a purchase to a collection of purchases. You can 
generate this event by entering a valid dollar amount with the key pad, then hitting the button that says "Add 
Purchase". A window with a drop-down menu will then pop up, and ask you to specify the category of your purchase. Choose
one and hit "OK". Hitting "Cancel" will remove the value you entered from the keypad and not add the purchase to the 
system. Entering a non-valid amount, like 5.5.5, or 5.659, or hitting "Add Purchase" without having entered a number 
into the key pad, will result in an error message, and the purchase will not be added to the system.
- The second event related to adding Xs to Ys in my program is setting a budget and checking
whether the purchases you have made so far exceed this budget. You can generate this event by first entering a 
dollar amount with the key pad, like you did to add a purchase, but instead clicking the button "Set Budget" afterwards.
Next, hitting the button "Check Budget" will open a window that displays your current budget and how far under or over
it you are. Note that the window opened by "Check Budget" does not automatically update, but instead displays the
state of the program at the time you pressed the button. To get an updated window after adding more purchases, you
must close the old window by using the X in the top right, then again click "Check Budget." The button "Display
Purchases" functions the same way. 
- You can locate my visual component by observing the icon in the top right. If the user has not exceeded their budget,
the emoji will be a smiley one. If the user spends more than their budget, it will become a sad face. If the user
changes their budget so that it is again greater than the money they have spent, the emoji will return to a smiley
face. The user's budget defaults to $0, so if you start the application and add a purchase without
first setting a budget, the icon will immediately become a sad face.
- You can save the state of my application by clicking the button "Save to File". A message will
then pop up saying that the state has successfully been saved.
- You can reload the state of my application by clicking the button "Load from File". A message will
then pop up saying that the state of my application has been successfully loaded.

## Phase 4: Task 2
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

## Phase 4: Task 3
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

The classes GUI, KeyPad, ScreenPrinter, and BudgetChecker were adapted from the AlarmSystem example given in class,
and can be found at the following link: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem