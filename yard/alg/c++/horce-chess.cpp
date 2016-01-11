/*deepsearch to solve the horse chess problem*/
#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#define ROWS    4
#define COLS    4

int chess[ROWS][COLS];

/*eight directions of x moved*/
const int x_move[] = {-2,-1,1,2,2,1,-1,-2};
/*eight directions of y moved*/
const int y_move[] = {1,2,2,1,-1,-2,-2,-1};

void print_matrix()
{
    int i = 0,j = 0;
    for (i = 0; i < ROWS; ++ i)
    {
        for (j = 0; j < COLS; ++ j)
        {
            printf("%d\t",chess[i][j]);
        }
        printf("\n\n\n");
    }
}

/*find the next point*/
int nextxy(int *x,int *y,int count)
{
    if(count > 7 && count < 0)
        return -1;
    switch(count)
    {
    case 0:
        /*check the conditions*/
        if(*x + x_move[0] < ROWS && 
         *x + x_move[0]>= 0 && 
         *y + y_move[0]< COLS && 
         *y + y_move[0]>= 0 && 
         chess[*x + x_move[0]][*y + y_move[0]] == 0)
        {
            *x += x_move[0];
            *y += y_move[0];
            break;
        }
        else/*failed*/
            return 0;
    case 1:
        if(*x + x_move[1] < ROWS && 
            *x + x_move[1]>= 0 && 
            *y + y_move[1]< COLS && 
            *y + y_move[1]>= 0 && 
         chess[*x + x_move[1]][*y + y_move[1]] == 0)
        {
            *x += x_move[1];
            *y += y_move[1];
            break;
        }
        else
            return 0;
    case 2:
        if(*x + x_move[2] < ROWS && 
            *x + x_move[2]>= 0 && 
            *y + y_move[2]< COLS && 
            *y + y_move[2]>= 0 && 
         chess[*x + x_move[2]][*y + y_move[2]] == 0)
        {
            *x += x_move[2];
            *y += y_move[2];
            break;
        }
        else
            return 0;
    case 3:
        if(*x + x_move[3] < ROWS && 
            *x + x_move[3]>= 0 && 
            *y + y_move[3]< COLS && 
            *y + y_move[3]>= 0 && 
         chess[*x + x_move[3]][*y + y_move[3]] == 0)
        {
            *x += x_move[3];
            *y += y_move[3];
            break;
        }
        else
            return 0;
    case 4:
        if(*x + x_move[4] < ROWS && 
            *x + x_move[4]>= 0 && 
            *y + y_move[4]< COLS && 
            *y + y_move[4]>= 0 && 
         chess[*x + x_move[4]][*y + y_move[4]] == 0)
        {
            *x += x_move[4];
            *y += y_move[4];
            break;
        }
        else
            return 0;
    case 5:
        if(*x + x_move[5] < ROWS && 
            *x + x_move[5]>= 0 && 
            *y + y_move[5]< COLS && 
            *y + y_move[5]>= 0 && 
         chess[*x + x_move[5]][*y + y_move[5]] == 0)
        {
            *x += x_move[5];
            *y += y_move[5];
            break;
        }
        else
            return 0;
    case 6:
        if(*x + x_move[6] < ROWS && 
            *x + x_move[6]>= 0 && 
            *y + y_move[6]< COLS && 
            *y + y_move[6]>= 0 && 
            chess[*x + x_move[6]][*y + y_move[6]] == 0)
        {
            *x += x_move[6];
            *y += y_move[6];
            break;
        }
        else
            return 0;
    case 7:
        if(*x + x_move[7] < ROWS && 
            *x + x_move[7]>= 0 && 
            *y + y_move[7]< COLS && 
            *y + y_move[7]>= 0 && 
         chess[*x + x_move[7]][*y + y_move[7]] == 0)
        {
            *x += x_move[7];
            *y += y_move[7];
            break;
        }
        else
            return 0;
    default:
        return 0;
    }
    return 1;
}
int deepsearch(int x,int y, int j)
{
    /*save the value x,y*/
    int x1 = x, y1 = y;
    int tag = 0, i = 0;
    /*save j on chess[x][y]*/
    chess[x][y] = j;

    /*recursion exit condition*/
    if(j == COLS*ROWS)
    {
        return 1;
    }
    /*find the next point in eight directions*/
    tag = nextxy(&x1,&y1,i);
    /*find the nextx,nexty */
    while (tag == 0 && i < 7)
    {
        i ++;
        tag = nextxy(&x1,&y1, i);
    }

    /*the nextxy be found*/
    while(tag)
    {
        if(deepsearch(x1,y1,j+1))
            return 1;

     /*if failed, a new finding process */
        x1 = x; y1 = y;
        i ++;
        tag = nextxy(&x1,&y1,i);
        while (tag == 0 && i < 7)
        {
            i ++;
            tag = nextxy(&x1,&y1,i);
        }
    }
    /*no direction can find next point*/
    if(tag == 0)
        chess[x][y] = 0;
    return 0;
}

int main(int argc, char* argv[])
{
    clock_t start = clock();
    deepsearch(2,0,1);
    print_matrix();
    int seconds = (clock()-start)/CLOCKS_PER_SEC;

    printf("\n%d\n",seconds);
    return 0;
}
