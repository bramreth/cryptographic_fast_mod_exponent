#!/bin/bash
testdata=test1

final_mark=0
javac asg1.java

if [ $? -ne 0 ]; then
	printf "Could not compile. Final mark 0\n"
else
	while read -r base exp mod res; do
		printf "Input: $base, $exp, $mod. Correct answer: $res.\n"
		ans=`timeout 10 java asg1 $base $exp $mod`
		if [ $? -ne 0 ]; then
			printf "Program timed out.  Mark 0\n"
		else
			printf "Provided answer: $ans. "
			if [ $ans = $res ]; then 
				printf "Correct.\n"
				final_mark=$((final_mark + 1))
			else 
				printf "Incorrect.\n"
			fi
		fi
	done < "$testdata"
	printf "Final mark = $final_mark.\n"
fi