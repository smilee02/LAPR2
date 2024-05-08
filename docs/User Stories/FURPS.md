# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._



Ordering - Two ordering algorithms, one to order the clients by their TIF and other to order by their names.

Calculations - Examination of the difference between the number of new tests and the number of results available to the client and examines each subsequence

Reports - Creating and sending daily reports to the NHS with the COVID-19 data.

Predictions - Predictions about the COVID-19 cases in the following day, week and month with a linear regression algorithm.

Login - To use the application, the person must be authenticated with a password.

Tests - The application should support more variety of tests other than COVID-19 and blood tests.

Data Access - Only the specialist doctor is allowed to access all client data.

Password - Contains seven alphanumeric characters, including three capital letters and two digits.


## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._


- The user interface will be simple and very intuitive for people. The login will consist in a simple user and password system. 
- Errors will be captured and an error message will pop up.
- If a client accesses the application, it will display the results of his tests, if it is someone with special access it will display the pacients in one of two or more ways, ordered by TIF or by name.
- Simple design and straight forward to new users who aren’t used to complex interfaces.

## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._


- Can be used to work with other applications who might need the information in this app.
- Accurate calculus with the data provided by the users.
- The system should not fail more than 5 days in one year. Whenever the system fails, there should be no data loss.


## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._


 - It will not consume a large amount of resources and it will be easy and fast to navigate through. It will also have a fast response time because it won’t be doing much processes in between the menus and when looking for the information.
 - Any interface between a user and the system shall have a maximum response time of 3 seconds. 
 - The system should start up in less than 10 seconds. 

## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._ 



 - Available with all the tests necessary done, it will also be able to configurate some functions if needed, at last it is easy and simple to install.


## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._
  

 - Programmed in Java , using  Intelij IDE or NetBeans


### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._


 - Its implemented for Java 8, only supports the english language.
 - The application will be deployed to a machine with 8GB of RAM.
 - The application should run on all platforms for which there exists a Java Virtual Machine.


### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._


 - We use JavaFX 11
 - The user interface must be simple, intuitive and consistent.

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

 - None Found