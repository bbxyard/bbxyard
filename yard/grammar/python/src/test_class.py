#!/usr/bin/python
import os, sys

class StringTest :
    '''This is a string test class
    just enjoy it and have fun!!'''
    def __init__(self):
        self.word = "hello"
        print "this is the constructor!!"
    def __del__(self):
        print "this is the destructor!!"
    def demo(self):
        print self.word

def main():
    st = StringTest()
    st.demo()
    print st.__doc__
    print "hello python and hello world"
    
    
if __name__ == "__main__":
    main()

        
