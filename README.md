README.txt

The program has been created to read the data from liczniki-db.json file. There is no path specified for the file so it should be placed in the same directory as the project source code. 

The program is built with the multi-layer architecture with the controller, service, model, and repository layers. However, for the purpose of this assignment only the service layer and controller layer have been implemented. 

To run the program, please clone the repository to your local machine using git clone and the URI of this repository. You need to start the application as a spring boot application and use client to test the REST API. Please use client such as Postman and try with the following URI:


localhost:8080/search/874829/2023-07-05%2021%3A15/2023-07-09%2022%3A30


This URI is also provided in the source code if needed. In the URI the first parameter is the meter ID number, then there is encoded start date and end date to read the data, including year, month, day, hours and minutes. In the sample URI, we will read the data for meter with ID 874829 starting on 2023-07-05 at 21:15 and ending on 2023-07-09 at 22:30. 

Response in Postman will contain JSONObject which does not have ordered values. If you would like to check values in order, please check the console as the sorted results are printed there, so it might be easier to check for accuracy. 

Response code is returned as part of the ResponseEntity returned from the controller. Error handling is implemented by informing user about incorrect meter ID in case data is not found. If the response is null, a custom error message and error code is returned to the client for better clarity. 

What I would do different:
If I had more time I would definitely focus on writing code with better clarity and more readability. Creating extensive functions is discouraged among programmers so if I had a chance to work on it further, I would certainly break down the long function inside the service layer. I would also improve naming convention for some of the variables and add more comments for better readability. Additionally, I would implement more sophisticated error handling. 
Finally, I would also like to extend the functionalities of the program and maybe work on the UI to present the data to the user in a more friendly way. 
