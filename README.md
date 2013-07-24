Computing Ramsey Numbers with Genetic Algorithms
==============

This program uses genetic algorithm techniques to make progress in computing Ramsey numbers for large graphs such as K_43. Ideas for further implementation can be found in IDEAS.md

Usage
---------
run `java AdjMatrixGraph x` where `x` is the size of the complete graph you wish to test. To run tests on K_43 (a complete graph on 43 vertices), run `java AdjMatrixGraph 43`.

The program spits out a data file at the end of its first run, you can plug these values back into the program to get more and more accurate answers. When it asks for a data file at startup, simply type in the name of the data file at the prompt. I will work on making this a command line argument in the future.

Purpose
-------

The program goes through and attempts to find iterations (colorings) of the graph that do not have any 5-cliques. This value is hardcoded at the moment for testing purposes, future versions will feature the ability to check for different sized cliques. 

Please note, that since this program checks only a subset of edges on each iteration, that just because the program reports all zeroes this is not necessarily the case. To truly verify the Ramsey number of a graph, an exhaustive search is necessary. While this is very intensive, my program allows you to narrow down the possibilities widely so you are left with "probable" graphs to test.

For instance, if one is able to show that there is a coloring of a K_43 graph with no 5-cliques, we will have proven the value of R(5,5) = 43. (Which is a really big deal, in case you didn't know.)

Contributions
-------------

Contributions and comments are welcome. See the file IDEAS.md for what I'm currently working on, and open an issue if you have an idea of something to add or change. Thanks for reading!