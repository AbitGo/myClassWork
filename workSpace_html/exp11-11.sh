#! /bin/bash
#a exp 11-11
echo    "input a directory,please"
read dir_name
if cd $dir_name > /dev/null 2>&1;then
    echo "enter directory:$dir_name successd"
else
    echo "enter directory:$dir_name failed"
fi
