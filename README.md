# Java sockets app

A "simple" app using sockets API, it implements a basic museum "site", that allows a client to:

* Register in the site (why? dunno) 
* Remove the registry from the site
* See all the registered users
* See all the artworks in a list

# Build

Since the person who asked me to do this doesn't know how to build 
a program, read an README file or simple write anything but bad C code
i need to make instructions on how to build this with linux, but im doing this in windows as well.

### Linux

TODO

### Windows

There's a file called build.bat to build (rly?), but here are the commands it runs:

First, compile the java files into class files: 
* `javac src\app\App.java -cp src\ -d build\`

Then generate an executable jar:
* `cd build`
* `jar cfvm sockets.jar META-INF\MANIFEST.MF app`

And execute:
* `java -jar sockets.jar`
