- Project Info:
    - This project is a functional java application that simulates basic SQL crud operations and also demonstrates various types of smells like Architecture smells, Design Smells and Implementation smells delibarately.

- Architecture Smell:
    - Cyclic Dependency
        - Abstraction Type: Package
        - Package(Component) Location: com.ASDCAss2.DbOperationsPrototype and com.ASDCAss2.DbOperationsPrototype.util
        - Classes Involved: DbOperationsPrototypeApplication, Utilities, RecordsContainer
        - Line Number / Smell Location: com.ASDCAss2.DbOperationsPrototype and com.ASDCAss2.DbOperationsPrototype.util
        - Explanation: The DbOperationsPrototypeApplication class from com.ASDCAss2.DbOperationsPrototype depends on FileUpdateInfo and Utilities Classes from com.ASDCAss2.DbOperationsPrototype.util component. Similarly Utilities class is dependent on Utilities Class from com.ASDCAss2.DbOperationsPrototype component. Abstractions should be acyclic with respect to dependencies, Otherwise, a change in an abstraction may result in a ripple effect across the entire design. Hence here the existence of Cyclic Dependency architecture smell is observed.

- Design Smells:
    - Rebellious Hierarchy
        - Abstraction Type: Class
        - Class Location: com.ASDCAss2.DbOperationsPrototype.query
        - Classes Involved: InsertQueryChild, CreateQueryChild, UpdateQueryChild and QueryParent.
        - Line Number / Smell Location: LIne no.14 in CreateQueryChild and line no. 15 in InsertQueryChild
        - Explanation: This smell arises when subtypes like child classes refuse or reject to perform like their supertypes that is parent class in this case. usually, subtypes form an IS-A relationship with their supertypes. But when they violate this aspect, it becomes Rebellious. Here, a method in InsertQueryChild (printQueryDetails()) is having an empty method block violating the default behaviour of the same method from parent class. Similarly, the printQueryDetails() method from CreateQueryChild class is throwing an UnsupportedOperationException delibarately all the time. Hence this also violates the natural behaviour of the method. Hence the existence of Rebellious Hierarchy is observed in the specified child classes.

     - Deficient Encapsulation
        - Abstraction Type: Class
        - Class Location: com.ASDCAss2.DbOperationsPrototype.query
        - Classes Involved: QueryParent
        - Line Number / Smell Location: Line 11 in QueryParent Class
        - Explanation: Deficient Encapsulation occurs when the declared accessibility of one or more members of an abstraction is more permissive than actually required. For example, a class that makes its fields public suffers from Deficient Encapsulation. Here, in the QueryParent class, the following parameters : "query, writtenBy, databaseName and queryType" are declared with public access modifier. hence the existence of deficient encapsulation is observed over here.

     - Cyclic Hierarchy
        - Abstraction Type: Class
        - Class Location: com.ASDCAss2.DbOperationsPrototype.query
        - Classes Involved: QueryParent and UpdateQueryChild
        - Line Number / Smell Location: Line 11 in QueryParent Class
        - Explanation: The QueryParent is an abstract parent class and the UpdateQueryChild is a child class of QueryParent. Usually, Child classes should inherit properties from parent classes and should depend on the parent class and not the other way round. But here the parent class, QueryParent is depending upon one of its child classes which is UpdateQueryChild. Hence the existence of Cyclic Hierarchy smell.

- Implementation Smells:
    - Complex Method
        - Abstraction Type: Method
        - Class Location: com.ASDCAss2.DbOperationsPrototype
        - Class Involved: DbOperationsPrototypeApplication
        - Line Number / Smell Location: 28 (main method)
        - Explanation: The method has high Cyclomatic Complexity which is 23, hence it is considered as a complex method

     - Long Parameter List:
        - Abstraction Type: Method
        - Class Location: com.ASDCAss2.DbOperationsPrototype.query
        - Classes Involved: QueryChild
        - Line Number / Smell Location: Line no. 15 : getQueryDetails method
        - Explanation: The method has 6 parameters, hence the existence of Long Parameter List smell.

     - Magic Number:
        - Class Location: com.ASDCAss2.DbOperationsPrototype
        - Class Involved: DbOperationsPrototypeApplication
        - Line Number / Smell Location: 125 and 177
        - Explanation: The method contains a magic number (2) in two places. This reduces the readability of the code and makes it difficult for the developers to understand why this constant 2 is used.
        
    - Long Method:
        - Abstraction Type: Method
        - Class Location: com.ASDCAss2.DbOperationsPrototype
        - Class Involved: DbOperationsPrototypeApplication
        - Line Number / Smell Location: 28 (main method)
        - Explanation: This method has over 163 lines of code. Hence the existence of long method smell.

        - Explanation: 
