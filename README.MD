#Goal:#

To create an app for ShoppingCart Billing Engine, which would accept a list of Items and prepare the Cart, Apply the promotions associated dynamically and generate the bill and print it in console.

{src}
::Should support the following Scenarios:

Write a program in Java (JDK 8 / Maven) and associated unit tests that
can price a basket of goods taking into account some special offers.

The goods that can be purchased, together with their normal prices are:

-   Soup – 65p per tin
-   Bread – 80p per loaf
-   Milk – £1.30 per bottle
-   Apples – £1.00 per bag

Current special offers:

-   Apples have a 10% discount off their normal price this week
-   Buy 2 tins of soup and get a loaf of bread for half price

The program should accept a list of items in the basket and output the
subtotal, the special offer discounts and the final price.

Input should be via the command line in the form
`PriceBasket item1 item2 item3 ...`

For example:

    Prciebasket Apples Milk Bread

Output should be to the console, for example:

    Subtotal: £3.10
    Apples 10% off: -10p
    Total: £3.00
    
If no special offers are applicable the code should output:

    Subtotal: £1.30
    (No offers available)
    Total proce: £1.30

The code and design should meet these requirements, but be sufficiently
flexible to allow future changes to the product list and/or discounts
applied.

The code should be well structured, commented, have error handling
and be tested.
{src}


#Technology:#
The technology stack used to implement this application are as below

~ java
~ slf4j
~ junit & mockito
~ Maven

#Build & Excecution:#

+Prerequisites required to build and run this application is
- Java 1.8 installed and setup on the local box
- Maven installed and setup on the local box

- CheckOut the project from github
- Run the cmd in console 'mvn clean install' to build and package the jar
- On successfull execution of the above step, the jar file should be available in target folder as 'ecart-1.0.jar'
- Run the jar from the target folder by using cmd ' java -jar ecart-1.0.jar Apples Apples Milk Bread Soup Soup > cartoutput.txt '

On successfully completing the execution, the output file will have the list of log statements along with the generated bill.

#Improvements:#

- Implement the presentation layer to display the spilled output
- Provide the output in a json response format to the client
- Use some rules engine, e.g EasyRules/ any Proprietary to manage the business/promotion rules



