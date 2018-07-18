# Overview
These are academic exercises to practice algorithms I don't usually encounter at the office.

# Test First (TDD) and Behavior Driven Development
Each enhancement (pull request) is meant to be small, but I am soloing so sometimes I make them too big. I start with the outer view or outcome of what I want the system to do. These 'outcome' tests are found in src/blackboxtest. I then proceed with test-first at each unit and tend to work top down (controller to service to domain).

# Prime Factoring and GCF
Inspired by a problem on codeliity to provide the greatest common factor (divisor) of two numbers I created an API to take 1 or more integers. The response includes the prime factores for each input and another object for the greatest common factor.

I chose to version the api and I chose to respond with hetergeneous objects in the results list. I am not sure I love this design.

I chose to calcualte the greatest common factor by   
0. first computing all the prime factors for each input, 
0. then gathering the intersection of these lists and 
0. finally computing the product of the remaining values.

You could reasonably argue this is a silly approach, but mostly it worked and is fairly readable (at least 10 minutes after writing the tests and code). The FUN part was working around how java/grooy calculates intersections between two lists.
~~~~
> [3,3].intersect([1,3])

Result: [3]
> [1,3].intersect([3,3])

Result: [3, 3]
~~~~

# Next Problem

# Running the Application
Terminal window: `./gradlew bootRun`

Intellij: Right click on the application and select run.

# Running the tests
## Unit
These are run as part of the the gradle build target: `./gradlew clean build`

## Blackbox (Acceptance)
I have not yet automated the running of these as a gradle target (nor as part of the build)   
0. Start the application
0. Intellij - run the test manually