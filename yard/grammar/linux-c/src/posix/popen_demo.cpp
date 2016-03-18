#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <string.h>
#include <string>
#include "gtest/gtest.h"


typedef int (*fn_sub_proc)(void* arg);


int run_in_sub_process(char* buf, int& len, fn_sub_proc sub_run, void* sub_run_arg)
{
    pid_t pid = 0;
    int   ret = -1;
    int   fd[2] = {0};
    int   n = 0, count = 0;
    memset(buf, 0, len);
    if (pipe(fd) < 0 || (pid = fork()) < 0)
        return -1;
    if (pid > 0)
    {
        close(fd[1]); // 父进程关闭写
        count = 0;
        while ((n = read(fd[0], buf + count, len - count)) > 0 && len - count > 0)
            count += n;
        close(fd[0]); // 父进程读完成，关闭读句柄
        len = count;
        int status = 0;
        int sub_pid = waitpid(pid, &status, 0);    // 等待子进程完成
        EXPECT_EQ(pid, (pid_t)sub_pid);
        if (sub_pid > 0)
        {
            ret = -2; // 标记异常出来的...
            if (WIFEXITED(status)) {
                ret = WEXITSTATUS(status);
            } else if (WIFSIGNALED(status)) {
                printf("killed by signal %d\n", WTERMSIG(status));
            } else if (WIFSTOPPED(status)) {
                printf("stopped by signal %d\n", WSTOPSIG(status));
            } else if (WIFCONTINUED(status)) {
                printf("continued\n");
            }
        }
    }
    else
    {
        close(fd[0]); // 子进程关闭读
        if (fd[1] != STDOUT_FILENO)   // 与stdout关联
        {
            if (dup2(fd[1], STDOUT_FILENO) != STDOUT_FILENO) // auto close first! if exist stdout
            {
                return -1;
            }
            if (dup2(fd[1], STDERR_FILENO) != STDERR_FILENO) // auto close first! if exist stderr
            {
                return -2;
            }
        }
        // 执行.
        ret = sub_run(sub_run_arg);
        exit(ret);
    }
    return ret;
}

static void popen_sample_basic()
{
    FILE* fp = popen("uname -a", "r");
    char tmp[1024] = {0};
    fgets(tmp, 1024, fp);
    fprintf(stdout, "hello: %s\n", tmp);
    pclose(fp);
}


static int sub_proc_basic(void* arg)
{
    int LOOP_CNT = 10;
    fprintf(stdout, "current sub pid=%d\n", getpid());
    fprintf(stderr, "OUTPUT to stderr begin!! %s\n", "##");
    for (int i = 0; i < LOOP_CNT; ++i)
    {
        fprintf(stdout, "this is a test from sub process - %04d\n", i);
    }
    fprintf(stderr, "OUTPUT to stderr end!! %s\n", "##");
    return LOOP_CNT;
}

static int sub_proc_by_out_cmd(void* arg)
{
    const char* dir = (const char*)arg;
    char cmd[1024];
    sprintf(cmd, "ls -lhrt %s", dir);
    int ret = system(cmd);
    fprintf(stderr, "SUB-PORCESS(%d) FINISHED!!\n", getpid());
    return ret;
}

TEST(popen, sample_basic)
{
    popen_sample_basic();
}

TEST(run_in_sub_process, basic)
{
    fprintf(stdout, "[1]current pid=%d(parent)\n", getpid());
    char buf[4096] = {0};
    int ret = run_in_sub_process(buf, sizeof(buf), sub_proc_basic, NULL);
    fprintf(stdout, "=======dump sub processs result===EXIT=%d====\n", ret);
    fprintf(stdout, "%s\n", buf);
    fprintf(stdout, "=======done!!=====\n");
}

TEST(run_in_sub_process, ls)
{
    fprintf(stdout, "[2]current pid=%d(parent)\n", getpid());
    char buf[4096] = {0};
    int ret = run_in_sub_process(buf, sizeof(buf), sub_proc_by_out_cmd, "/tmp");
    fprintf(stdout, "=======dump sub processs result===EXIT=%d====\n", ret);
    fprintf(stdout, "%s\n", buf);
    fprintf(stdout, "=======done!!=====\n");
}

// int main(int argc, char* argv[])
// {
//
//
//     return 0;
// }
