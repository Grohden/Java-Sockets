# Java sockets app

A "simple" app using sockets API, it implements a basic museum "site", that allows a client to:

* Register in the site (why? dunno) 
* Remove the registry from the site
* See all the registered users
* See all the artworks in a list

# Build

Since the person who asked me to do this doesn't know how to build 
a program, read an README file or simple write anything but bad C code
i need to make simple instructions on how to build.

Here are the instructions:

First, compile the java files into class files: 
* `javac src\app\App.java -cp src\ -d build\`

Then generate an executable jar:
* `cd build`
* `jar cfvm sockets.jar manifest.txt app`

# Run

After the build, you can run the jar **on the build folder**  with:
* `java -jar sockets.jar`


# Oh, and by the way:
```
/**
 *              ,
 *             / \,,_  .'|
 *          ,{{| /}}}}/_.'            _____________________________________________
 *         }}}}` '{{'  '.            /                                             \
 *       {{{{{    _   ;, \          /            Ladies and Gentlemen,              \
 *    ,}}}}}}    /o`\  ` ;)        |                                                |
 *   {{{{{{   /           (        |                 this is ...                    |
 *   }}}}}}   |            \       |                                                |
 *  {{{{{{{{   \            \      |      _____     __  .-_'''-.   .---.  .---.     |
 *  }}}}}}}}}   '.__      _  |     |      \   _\   /  /'_( )_   \  |   |  |_ _|     |
 *  {{{{{{{{       /`._  (_\ /     |      .-./ ). /  '|(_ o _)|  ' |   |  ( ' )     |
 *   }}}}}}'      |    //___/   --=:      \ '_ .') .' . (_,_)/___| |   '-(_{;}_)    |
 *   `{{{{`       |     '--'       |     (_ (_) _) '  |  |  .-----.|      (_,_)     |
 *    }}}`                         |       /    \   \ '  \  '-   .'| _ _--.   |     |
 *                                 |       `-'`-'    \ \  `-'`   | |( ' ) |   |     |
 *                                 |      /  /   \    \ \        / (_{;}_)|   |     |
 *                                 |     '--'     '----' `'-...-'  '(_,_) '---'     |
 *                                 |                                               /
 *                                  \_____________________________________________/
 */
```
