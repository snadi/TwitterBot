# In-Class Exercise to Practice Using Test Doubles

- Course: CS-UH-3260
- Instructor: Sarah Nadi

## Learning Objective

In this activity, you will learn how to mock an external dependency that depends on a rest API response (and thus network connectivity). You will also learn how to simulate error conditions in the environment to test a system for robustness.

*Disclaimer*: Yes, Twitter is now called X, but Twitter is such a better name :-) Accordingly, this description sticks to the old terminology of the service (tweet etc.)

## Resources

*	Twitter4J API [https://twitter4j.org](https://twitter4j.org)
*	Mockito API [https://site.mockito.org/](https://site.mockito.org/)
*	EasyMock API [http://easymock.org/](http://easymock.org/)


## Task: Unit test application logic without real interactions with Twitter

You are provided with a very basic implementation of Twitter bot, in Java. 
This bot does two simple tasks: posting a tweet and replying to a tweet.
You are using an external library, Twitter4J, to interact with the Twitter API.

Using this API means that you need to go through the process of requesting to become a Twitter developer to obtain a key and token for interacting with the API. 
Also, using the API means that you need to spam some real Twitter account every time you run your tests (which is obviously not practical). 
You also want to test whether your application can handle network connections gracefully (part 2 of this activity). 

Accordingly, at this point, you only want to test the logic of your bot and your error handling mechanisms, while assuming your application already interacted with Twitter and received some response from the API. **Thus, your goal is to use test doubles that avoid any real communication with Twitter to test your implementation and its robustness.**

### Step 1: Test Twitter Functions 

Test each of the two provided functionalities (post tweet, reply to tweet) without actually communicating with the Twitter servers. You do not need to test the main loop of the bot, just the two functions above. You will need to use [mockito](https://site.mockito.org/) for this (or easymock if you want).

### Step 2: Robustness Tactic

You want to add a robustness tactic to your implementation to avoid the application crashing immediately when communication with the Twitter API fails. You decide you will add robustness by retrying the communication with the API 3 times before gracefully reporting a meaningful error message.

***Your task for step 2:***

- implement the robustness handling for both `postTweet` and `replyToTweet`
- test that this robustness tactic actually works (i.e., there is actually 3 retries to call the failed API and the expected error message is thrown/returned)