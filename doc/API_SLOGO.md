# SLogo Architecture Design
Jamie Kim, Brandon Guo, Liam Pulsifer, Ryan Fu

****
1.  When does parsing need to take place and what does it need to start properly?
    * Parsing needs to happen when a user types in a command and presses "ENTER". To start properly, it should need a valid command. In order to be both flexible and dependable, error checking will need to be a part of the parsing, though error messages could simply be "command not recognized, please try again".
2.  What is the result of parsing and who receives it?
    * There will need to be a back end class like an Interpreter which can take in the parsed output (perhaps a list of numbers or another data structure). 
3.  When are errors detected and how are they reported?
    * Errors are detected when the run button is pressed, so that an exception will be caught if an error occurs. 
    * In the event of an error, a command prompt will be displayed showing what type of error was caught.
    * Some possible errors could be:
        * Syntax errors in the slogo code
            * Unrecognized input or commands
            * wrong characters
        * Logic error
        * Null pointers
        * Out of bounds exceptions
        * Unrecognized input
4.  What do commands know, when do they know it, and how do they get it?
    * What to know?
        * Given an input (command), should be able to return the correct data to the interpreter
    * When do they know it?
        * When the user presses enter 
    * How do they get it?
        * Objects are passed in through parameters (user/text input in the command line)
5.  How is the GUI updated after a command has completed execution?
    * The interpreter will decide the new positions of the visualization elements and then send them to a Visulizer class. 

# CRC Cards


# Create APIs


# Use Cases