# Overview
These are academic exercises to practice algorithms I don't usually encounter at the office.

# Test First (TDD) and Behavior Driven Development
Each enhancement (pull request) is meant to be small, but I am soloing so sometimes I make them too big. I start with the outer view or outcome of what I want the system to do. These 'outcome' tests are found in src/blackboxtest. I then proceed with test-first at each unit and tend to work top down (controller to service to domain).

# Prime Factoring, Greatest Common Factor and Least Common Multiple
Inspired by a problem on codility to provide the greatest common factor (divisor) of two numbers I created an API to take 1 or more integers. The response includes the prime factores for each input and another object for aggregate values (i.e. GCF and LCM). If you've ever gotten to help a child learn fraction addition or factoring polynomials this problem might reasonate.

I chose to version the api and I chose to respond with hetergeneous objects in the results list. These objects have a type (individual or aggregate). This design is loosely modeled after the white house api standards.

I chose to calcualte the greatest common factor by   

1. computing all the prime factors for each input, 
1. then gathering the intersection of these lists and 
1. finally computing the product of the remaining prime factors.

You could reasonably argue this is a silly approach, but it worked and is fairly readable (at least 10 minutes after writing the tests and code). The FUN part was working around how java/groovy calculates intersections between two lists.
~~~~
> [3,3].intersect([1,3])

Result: [3]
> [1,3].intersect([3,3])

Result: [3, 3]
~~~~

So I used a bit of hackery to get the work done.

# Array Fiddling (Java 8 practice)- Minimum absolute sum value within list of integers
The primary purpose for this task was to reaquaint myself with Java 8. Executive Summary: Use 8 lines of code when groovy could do it in one expressive statement.

Codility provided the basic problem: Given an array of integers find the minimum of all the absolute sums between the elements.

My solution is none too clever. Ultimately it is nested loop with the saved minimum value. I was sad to find Java 8's foreach with a lambda would not allow a non-final variable within the nested loop. I did not tinker with it very long so it could very well be my mistake.

I included no defensive programming. If the user passes in anything but an array of integers it will likely blow up.

# Running the Application
Terminal window: `./gradlew bootRun`

Intellij: Right click on the application and select run.

# Running the tests
## Unit
These are run as part of the the gradle build target: `./gradlew clean build`

## Blackbox (Acceptance)
I have not yet automated the running of these as a gradle target (nor as part of the build)  
 
1. Start the application
1. Intellij - run the test manually