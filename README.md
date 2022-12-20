# __clevertec-test-task__

* ```git clone <username>/clevertec-test-task.git ```
* go to the folder ```cd 'your project folder'```
* paste project url from the first step
* go to the folder with project ```cd ./clevertec-test-task```

### __For start application with default parameters:__
 __In the root project folder paste:__
 * for starting tests: ```gradle build test``` 
 * for starting application: ```docker compose up```
 
 ___If you want to run___ ```docker compose up``` ___at first you should enter___:
 * ```gradle build```  
 * ___after that:___ ```docker compose up```
 * ___for tests:___ ```gradle test```

### __For start application with your own parameters:__
You should edit entering arguments in ```docker-compose.yml``` file

By default parameter ```command: 1-3 2-5 3-6 1-6 4-1 8-2 8-4 7-5 5-4 3-4 1-4 5 ```

Where:  
* first number is ```item id```
* second number is ```quantity of items```
* the last number in the row it is a ```discount card number```

```You should enter id and quantity after separator like this```  __```(id-quantity)```__

__After entering your items, and their quantities you can enter discount card number__
