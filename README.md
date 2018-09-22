*Code challenge from a bank*

# The Problem
## Description
The task is to write a simple console version of a drawing program. 
At this time, the functionality of the program is quite limited but this might change in the future. 
In a nutshell, the program should work as follows:
 1. Create a new canvas
 2. Start drawing on the canvas by issuing various commands
 3. Quit


| Command 		| Description |    
| ------------- |-------------|    
| C w h         | Should create a new canvas of width w and height h. |    
| L x1 y1 x2 y2 | Should create a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are supported. Horizontal and vertical lines will be drawn using the 'x' character. |    
| R x1 y1 x2 y2 | Should create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). Horizontal and vertical lines will be drawn using the 'x' character. |    
| B x y c       | Should fill the entire area connected to (x,y) with "colour" c. The behaviour of this is the same as that of the "bucket fill" tool in paint programs. |    
| Q             | Should quit the program. |    

## Sample I/O
Below is a sample run of the program. User input is prefixed with enter command:

```
enter command: C 20 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------

enter command: L 1 2 6 2
----------------------
|                    |
|xxxxxx              |
|                    |
|                    |
----------------------

enter command: L 6 3 6 4
----------------------
|                    |
|xxxxxx              |
|     x              |
|     x              |
----------------------

enter command: R 14 1 18 3
----------------------
|             xxxxx  |
|xxxxxx       x   x  |
|     x       xxxxx  |
|     x              |
----------------------

enter command: B 10 3 o
----------------------
|oooooooooooooxxxxxoo|
|xxxxxxooooooox   xoo|
|     xoooooooxxxxxoo|
|     xoooooooooooooo|
----------------------

enter command: Q
```

# The Solution
## Build environment
* Eclipse project (Photon Release 4.8.0)
* JDK 1.8.0_45
* Maven (embedded in Eclipse)
* Dependencies
    * JUnit 5 and Mockito are used for testing
    * No other third party libraries

## Application execution
Open the project in Eclipse and build with Maven commands "clean build".

To run application:
* Run com.cs.codechallenge.Application    

To run tests:
* Create a JUnit "Run Configuration" and select "Run all tests"    
For details, see this <a href="run-test.png">image</a>.

To run tests and show coverage:
* Launch the JUnit "Run Configuration" using "Coverage Configuration" 


## Assumptions
* Commands
    * Only printable ASCII characters and white space (ASCII code 32) are used in input (but no validation for now)
    * Multiple delimiters (e.g. multiple white spaces) can be used between every two tokens in a command
    * Leading and Trailing white spaces are allowed in command text (will be trimmed by application)
    * BucketFill command's color character is case sensitive
    * Coordinates of lines, rectangles and bucket fill must be within canvas (so they are positive integers)
    * Start and end points of a line cannot be the same
    * Start and end points of a rectangle cannot be the same or one the same vertical/horizontal line
* Drawing
    * Only 1 canvas can be created
    * By default, max width and height of canvas are set to 50 (changable in the Application.main() via DrawCanvasCommandParser's constructor)    
    * Overlapping components are allowed
    