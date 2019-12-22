#! /bin/bash
#a exp 11-17

if[ -f /etc/bashrc ];then
    ./etc/bashrc
fi
echo "health is better than wealth!"
export PS1 = "[ \t] \u@ \h/ w"