## Addition -- Front End Feature

### Estimation: before looking at the old code:
* how long do you think it will take you to complete this new feature?
   I don't think it should take long at all. Hopefully I'll just have to add a new on-click method to the turtles'
   image views allowing the user to choose a new Image file for the turtle.
* how many files will you need to add or update? Why?
    I'm hoping to just update one, the Turtle.java file. As it stands now, all turtles are visible on the screen,
    so it should be a simple process to add a FileChooser on click and change the imageView to match that file.

### Review: after completing the feature:
* How long did it take you to complete this new feature?
It took me about 25 minutes or so. 
* How many files did you need to add or update? Why?
I only needed to update the Turtle.java file. This was because our project was simply designed with one view with different
viewing components. Thus, all I needed to do was add some on click behavior to the turtle.
* Did you get it completely right on the first try?
No, I didn't. On my first try, I overwrote the existing behavior of the Turtle's on click action, so one could no longer set
the turtle to be active or not by clicking on it. I remedied this by changing the file choosing action to be on a right click, 
rather than a normal click.
### Analysis: what do you feel this exercise reveals about your project's design and documentation?

I think it shows how simple our design was, which is both a positive and a negative thing. It's positive because it means 
the code is relatively easy to understand, but it's also a negative because the ease with which I added this new feature was not
necessarily a result of good design, but rather a result of there being relatively few complex behaviors which made this change
difficult.

* was it as good (or bad) as you remembered?
Honestly, it was worse than I remembered. The system of observers and observables is really, really difficult to follow. However,
I was impressed looking back at the inheritance relationship between the different sceneElements. I remembered that as being
a very strong part of the design, and I think that was justified in this retrospective.
* what could be improved?
If I could do this again, I would implement either a publisher-subscriber design or a model-view-controller pattern. I think that
the basic observer-observable pattern wasn't sufficient in this case for the level of data transfer required between the front
and the back end.
* what would it have been like if you were not familiar with the code at all?
It would have been difficult to figure out that the Turtle's imageview was the key to all of this because there's very little 
documentation in the turtle class. However, this would be easy to fix with better comments and a better-named package
hierarchy. 