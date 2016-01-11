apr-test : apr-test.cpp
	g++ -o $@ $< `pkg-config --cflags --libs apr-1 apr-util-1`

clean:
	rm -rvf apr-test.dSYM apr-test

