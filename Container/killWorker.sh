#!/bin/sh
for  i in $(seq 1 10)
do
	file=pid/run$i.pid
	pid=`cat $file`
	echo 'killing '$pid
	kill $pid
done

