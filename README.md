gumtree-adress-book
===================

Test address book project.

using
-----

- JDK 1.6
- IntelliJ Idea
- Maven 3


libs
----
- Joda Time (because your requirements contain date operations)
- Commons Lang3 (validate)
- annotations (@NotNull, @Nullable to assure consistency)


not added libs
--------------
- commons collections | lambdaj (if the project was bigger, adding one of these for collection manipulation would be wise)

usage
-----

* build the application
* from address-book module target directory run address-book.jar or address-book-jar-with-dependencies.jar

>Display Help:
>java -jar address-book-jar-with-dependencies.jar

>First Answer:
>java -jar address-book-jar-with-dependencies.jar ../../AddressBook.txt 1 M

>Second Answer:
>java -jar address-book-jar-with-dependencies.jar ../../AddressBook.txt 2

>Third Answer:
>java -jar address-book-jar-with-dependencies.jar ../../AddressBook.txt 3 "Bill McKnight" "Paul Robinson"