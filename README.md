# tickets1-solution
This is a basic solution to the "tickets1" exercise, it involves a simple test, dependency injection concepts and basic spring
configuration. the relationships this exercise is trying to emulate are:

Trigger <--> Business Logic <--> Storage

which in our case are:

Main <--> TicketService <--> Windows file system

The solution demonstrates a loosely coupled simple design which uses dependency injection instead of mixed construction and use as
presented in the original application.
