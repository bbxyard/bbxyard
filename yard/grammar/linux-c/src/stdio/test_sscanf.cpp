#include <stdio.h>
#include "gtest/gtest.h"

TEST(sscanf, number)
{
    int buf[3] = {0};
    int cnt = sscanf("1 2 3", "%d %d %d", buf, buf + 1, buf + 2);
    for (int i = 0; i < cnt; ++i)
    {
        EXPECT_EQ(i + 1, buf[i]) << "Good!!";
        fprintf(stderr, "parsed buf[%d]=#%d#\n", i, buf[i]);
    }
}

TEST(sscanf, parse_attr)
{
    const char* str = " \t key1 \t= \"value1\"";
    char kbuf[256] = {0};
    char vbuf[256] = {0};
    int cnt = sscanf(str, "%*[ \t]%[^ \t=]%*[\t =\"]%[^\"]", kbuf, vbuf);
    fprintf(stderr, "After parsed[%d]: key=#%s#; value=#%s#\n", cnt, kbuf, vbuf);
    EXPECT_STREQ("key1", kbuf);
    EXPECT_STREQ("value1", vbuf);
}

// parse Content-Disposition: form-data; name="fin"; filename="README.md"
// key need to parse: "fin"; filename="README.md"
TEST(sscanf, parse_http_content_attr)
{
    char part1[256] = {0};
    char part2[256] = {0};
    const char* str1 = "\"fin\"";
    const char* str2 = "\"fin\"; filename=\"README.md\"";
    const char* fmt  = "%*[\" \t]%[^\"]%*[\"; \t]%s";
    // 解出1个参数
    int cnt = sscanf(str1, fmt, part1, part2);
    EXPECT_EQ(1, cnt);
    EXPECT_STREQ("fin", part1);
    EXPECT_STREQ("", part2);
    fprintf(stderr, "After parsed[%d]: part1=#%s#; part2=#%s#\n", cnt, part1, part2);
    // 解出2个参数
    cnt = sscanf(str2, fmt, part1, part2);
    EXPECT_EQ(2, cnt);
    EXPECT_STREQ("fin", part1);
    EXPECT_STREQ("filename=\"README.md\"", part2);
    fprintf(stderr, "After parsed[%d]: part1=#%s#; part2=#%s#\n", cnt, part1, part2);
}

TEST(sscanf, basic)
{
    char name[256] = {0};
    const char* psz = "Name:   \"binary_name\"";
    sscanf(psz, "%*s \"%s\"", name);
    printf("name=%s\n", name);
    EXPECT_EQ(315, 315);
}

int main0()
{
    const char* psz = "MemTotal:       507480 kB";
    int val = 0;
    psz = strchr(psz, ':') + 1;
    sscanf(psz, "%d", &val);
    printf("val=%d\n");

    char name[256];
    psz = "Name:   binary_name";
    sscanf(psz, "%*s %s", name);
    printf("name=%s\n", name);
    return 0;
}
