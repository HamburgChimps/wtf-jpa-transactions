# WTF JPA Transactions

This repository is an attempt to replicate a bug I was seeing in a larger application where it appeared that data
was not being persisted to a database even after an explicit call to `flush()`. Unfortunately, I haven't been able
to replicate it in this simple application.

I had thought that the bug had something to do with incorrectly setting JPA transaction boundaries but no matter what
I try in this small demonstration application I can't get it to happen. Perhaps I was barking up the wrong tree.

Feel free to check out the code and run the application with `./mvnw spring-boot:run`.
