#!/bin/bash
# maintain script


function compile()
{
    g++ -o http-request http-request.cpp $(pkg-config --cflags --libs libevent)
}

