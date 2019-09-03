#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <hiredis.h>
#include <unistd.h>     // for sleep

int main(int argc, char **argv) {
    unsigned int j;
    redisContext *c;
    redisReply *reply;
    unsigned char bin_data[5] = { 'w', 'o', '\0', 'l', 'd' };
    const char *hostname = (argc > 1) ? argv[1] : "127.0.0.1";
    int port = (argc > 2) ? atoi(argv[2]) : 6379;

    struct timeval timeout = { 1, 500000 }; // 1.5 seconds
    c = redisConnectWithTimeout(hostname, port, timeout);
    if (c == NULL || c->err) {
        if (c) {
            printf("Connection error: %s\n", c->errstr);
            redisFree(c);
        } else {
            printf("Connection error: can't allocate redis context\n");
        }
        exit(1);
    }

    /* PING server */
    reply = redisCommand(c,"PING");
    printf("PING: %s\n", reply->str);
    freeReplyObject(reply);

    /* Set a key */
    reply = redisCommand(c,"SET %s %s", "foo", "hello world");
    printf("SET: %s\n", reply->str);
    freeReplyObject(reply);

    /* Set a key using binary safe API */

    reply = redisCommand(c,"SET %b %b", "bar", (size_t) 3, bin_data, sizeof(bin_data));
    printf("SET (binary API): %s\n", reply->str);
    freeReplyObject(reply);

    /* Get binary data */
    reply = redisCommand(c, "GET %b", "bar", (size_t)3);
    printf("GET (binary API): %s-%d\n", reply->str, reply->len);
    freeReplyObject(reply);

    /* Try a GET and two INCR */
    reply = redisCommand(c,"GET foo");
    printf("GET foo: %s\n", reply->str);
    freeReplyObject(reply);

    /* set timeout */
    reply = redisCommand(c, "EXPIRE foo 10");
    printf("EXPIRE foo %lld\n", reply->integer);
    freeReplyObject(reply);

    for (int i = 0; i < 15; ++i)
    {
        reply = redisCommand(c, "TTL foo");
        printf("TTL foo left time %lld(s)\n", reply->integer);
        freeReplyObject(reply);
        sleep(1);
    }
    reply = redisCommand(c, "GET foo");
    printf("LIFE should END! GET foo %s\n", reply->str);
    freeReplyObject(reply);

    reply = redisCommand(c,"INCR counter");
    printf("INCR counter: %lld\n", reply->integer);
    freeReplyObject(reply);
    /* again ... */
    reply = redisCommand(c,"INCR counter");
    printf("INCR counter: %lld\n", reply->integer);
    freeReplyObject(reply);

    /* Create a list of numbers, from 0 to 9 */
    reply = redisCommand(c,"DEL mylist");
    freeReplyObject(reply);
    for (j = 0; j < 10; j++) {
        char buf[64];

        snprintf(buf,64,"%u",j);
        reply = redisCommand(c,"LPUSH mylist element-%s", buf);
        freeReplyObject(reply);
    }

    /* Let's check what we have inside the list */
    reply = redisCommand(c,"LRANGE mylist 0 -1");
    if (reply->type == REDIS_REPLY_ARRAY) {
        for (j = 0; j < reply->elements; j++) {
            printf("%u) %s\n", j, reply->element[j]->str);
        }
    }
    freeReplyObject(reply);

    /* Disconnects and frees the context */
    redisFree(c);

    return 0;
}
