#!/usr/bin/env bash

function show_help()
{
  IT=$(cat <<EOF

Runs a command, and times out if it doesnt complete in time

Example usage:

   # Will fail after 1 second, and shows non zero exit code result
   $ timeout 1 "sleep 2" 2> /dev/null ; echo \$?
   142

   # Will succeed, and return exit code of 0.
   $ timeout 1 sleep 0.5; echo \$?
   0

   $ timeout 1 bash -c 'echo "hi" && sleep 2 && echo "bye"' 2> /dev/null; echo \$?
   hi
   142

   $ timeout 3 bash -c 'echo "hi" && sleep 2 && echo "bye"' 2> /dev/null; echo \$?
   hi
   bye
   0
EOF
)
  echo "$IT"
  exit
}

if [ "$1" == "help" ]
then
  show_help
fi
if [ -z "$1" ]
then
  show_help
fi

#
# Mac OS-X does not come with the delightfully useful `timeout` program.
# Thankfully a rough BASH equivalent can be achieved with only 2 perl
# statements.
#
# Originally found on SO:
# http://stackoverflow.com/questions/601543/command-line-command-to-auto-kill-a-command-after-a-certain-amount-of-time
# 
perl -e 'alarm shift; exec @ARGV' "$@";
