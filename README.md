# Java sockets app

A "simple" app using sockets API

# Build

Since the person who asked me to do this does'nt know how to build 
a program, read an README file or simple write anything but bad C code
i need to make instructions on how to build this with linux.

### Linux

TODO

### Windows

There's a file called build.bat to build (rly?), but here are the commands it runs:

Then, compile the java files into class files: 
* `javac src\app\App.java -cp src\ -d build\`

Then generate an executable jar:
* `cd build`
* `jar cfvm sockets.jar META-INF\MANIFEST.MF app`

To execute:
* `java -jar sockets.jar`
