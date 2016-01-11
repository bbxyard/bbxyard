#include<stdio.h>
//#include<conio.h>
#include<string.h>
#include<time.h>
#include<stdlib.h>
void a(int);
void a1();
void a2();
void a3();
void a4();
void a5();
void a6();
void a7();
void a8();
void a9();
int main()
{
int i,j;
int k=0;
char c;
char *p[]={"2015祝福","冬日墙","风车","蒲公英",
"落叶","美女证","帅哥证","随机(测试功能)","使用说明"};
while(1)
{
printf("\t**********************************************\n\t* 冷雨轩制作 *\n\t* ========== *\t\t\n\t**********************************************\n\n\n");
for(i=0; i<9; i++)
{
if(i==k)
{
printf(" \033[47;30m%s\n\033[?25l\033[0m",p[i]);
continue;
}
printf(" %s\n\033[?25l",p[i]);
}
printf("\n\n\n\t\t\t\t第一次使用请阅读使用说明！");
switch(getch())
{
case '2' :k--;if (k==-1)k=8;break;
case '8' :k++;if (k==9)k=0;break;
case '5' :a(k);break;
}

clrscr();
}
}


void a(int k)
{
clrscr();
switch (k)
{
case 0:a1();break;
case 1:a2();;break;
case 2:a3();break;
case 3:a4();break;
case 4:a5();break;
case 5:a6();break;
case 6:a7();break;
case 7:a8();break;
case 8:a9();break;
}
}


void a1()
{
char a[1000],c;
int i;
printf("2015祝福\n请输入你想说的话:\n");
gets(a);
clrscr();
printf("http://weika5.kagirl.net/kawa2/show.php?modify=yes&cardid=20002_1&words=");
for (i=0; a[i]!='\0'; i++)
{
printf("%%%X",a[i]);
}
printf("&timecookie=201501011238&from=singlemessage&isappinstalled=0");
c=getch();
}


void a2()
{
char a[1000],c;
int i;
printf("冬日墙\n请输入你想说的话:\n");
gets(a);
clrscr();
printf("http://weika5.kagirl.net/kawa2/show.php?modify=yes&cardid=1039&words=");
for (i=0; a[i]!='\0'; i++)
{
printf("%%%X",a[i]);
}
printf("&openid_u=&headimgurl_u=&nickname_u=&timecookie=201501011241&from=singlemessage&isappinstalled=0");
c=getch();
}


void a3()
{
char a[1000],c;
int i;
printf("风车\n请输入你想说的话:\n");
gets(a);
clrscr();
printf("http://weika5.kagirl.net/kawa2/show.php?modify=yes&cardid=1037&words=");
for (i=0; a[i]!='\0'; i++)
{
printf("%%%X",a[i]);
}
printf("&openid_u=&headimgurl_u=&nickname_u=&timecookie=201501011243&from=singlemessage&isappinstalled=0");
c=getch();
}


void a4()
{
char a[1000],c;
int i;
printf("蒲公英\n请输入你想说的话:\n");
gets(a);
clrscr();
printf("http://weika5.kagirl.net/kawa2/show.php?modify=yes&cardid=2107&words=");
for (i=0; a[i]!='\0'; i++)
{
printf("%%%X",a[i]);
}
printf("&openid_u=&headimgurl_u=&nickname_u=&timecookie=201501011246&from=singlemessage&isappinstalled=0");
c=getch();
}



void a5()
{
char a[1000],c;
int i;
printf("落叶\n请输入你想说的话:\n");
gets(a);
clrscr();
printf("http://weika5.kagirl.net/kawa2/show.php?modify=yes&cardid=9392&words=");
for (i=0; a[i]!='\0'; i++)
{
printf("%%%X",a[i]);
}
printf("&openid_u=&headimgurl_u=&nickname_u=&timecookie=201501011247&from=singlemessage&isappinstalled=0");
c=getch();
}

void a6()
{
char a[1000],c;
int i;
printf("美女证\n请输入美女的名字:\n");
gets(a);
clrscr();
printf("http://weika.kagirl.net/kas/showcard_1000.php?cardid=4012&createtime=2015_0_1_12_54_23_555&desc=");
for (i=0; a[i]!='\0'; i++)
{
printf("%%%X",a[i]);
}
printf("");
c=getch();
}


void a7()
{
char a[1000],c;
int i;
printf("帅哥证\n请输入你想说的话:\n");
gets(a);
clrscr();
printf("http://weika.kagirl.net/kas/showcard_1000.php?cardid=4013&createtime=2015_0_1_12_52_26_484&desc=");
for (i=0; a[i]!='\0'; i++)
{
printf("%%%X",a[i]);
}
printf("&from=singlemessage&isappinstalled=0");
c=getch();
}



void a8()
{
char c;
printf("还在开发中。。。。。。。");
printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t\t任意键返回");
c=getch();
}


void a9()
{
char c;
clrscr();
printf("使用说明:\n\n \033[0;32m2\033[0m,\033[0;32m8\033[0m键上下移动\n \033[0;32m5\033[0m 键确认选择\n链接生成后长按屏幕选择\033[0;32mCopy all\033[0m发送给QQ或者微信好友即可。\n常规界面按任意键即可返回主菜单\n\n********感谢使用********\n您的支持是我最大的动力");
c=getch();
}
