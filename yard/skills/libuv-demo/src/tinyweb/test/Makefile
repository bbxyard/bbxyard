main3: main3.c ../membuf.c ../tinyweb3.c
	PKG_CONFIG_PATH=/usr/local/lib/pkgconfig  gcc -o $@ $^ -Wall $(shell pkg-config --cflags --libs libuv)
clean:
	-rm main3
