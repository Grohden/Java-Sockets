javac src\app\App.java -cp src\ -d build\
cd build
jar cfvm sockets.jar manifest.txt app
cd ..