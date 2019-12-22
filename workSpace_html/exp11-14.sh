#! /bin/bash
#a exp 11-14

#a while example
clear
loop = 0
while( $loop-ne10)
do
    let loop= $loop+1
    echo "current value of loop is:$loop"
done


clear
loop = 0
until[ $loop - eq10]
do
    let loop = $loop+1
    echo "current value of loop is:$loop"
done