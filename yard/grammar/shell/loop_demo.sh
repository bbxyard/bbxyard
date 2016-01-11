#!/bin/bash

read_file_by_while_1()
{
    echo demo method 1 ...
    cat $1 | while read line
    do
        echo $line
    done
}

read_file_by_while_2()
{
    echo demo method 2 ...
    while read line
    do
        echo $line
    done < $1
}

# test while
test_while_num()
{
    min=1
    max=100
    sum=0
    # while循环注意为方括号[],且注意空格
    while [ $min -le $max ]; do
        sum=`expr $sum + $min`
        min=`expr $min + 1`
    done
    echo "1+2+...+100 = $sum"

    # 双括号形式，内部结构有点像C的语法，注意赋值：i=$(($i+1))
    sum=0
    i=1
    while (($i <= 100))
    do
        sum=$(($sum + $i))
        i=$(($i + 1))
    done
    echo "1+2+...+100 = $sum"
}

# test for
test_for_num()
{
    for i in {1..10}; do echo $i; done
    for i in 1 2 5 8; do echo $i; done

    # ((语法循环--有点像C语法，但记得双括号
    for ((i=1; i < 100; ++i))
    do
        if ((i%3 == 0))
        then
            echo $i
            continue
        fi
    done

    # seq形式 起始从1开始
    for i in `seq 100`; do 
        ((i%3 == 0)) && echo $i 
    done
}

test_for_enum_files()
{
    #对存在的文件进行循环
    for f in `ls *.sh`; do
        name=`echo "$f" | awk -F. '{print $1}'`
        echo $f---\>$name
    done

    #查找循环（ls数据量太大的时候也可以用这种方法）
    for f in `find . -type f -name "*.sh"`; do
        name=$(echo "$f" | awk -F/ '{print $2}')
        echo $f---\>$name
    done
}

read_file_by_while_1 $0
read_file_by_while_2 $0o

echo ------------------ test for -----------
test_for_num
test_for_enum_files
test_while_num
