#!/bin/sh
killall GeneratorWorker
cd worker1
nohup java -Xmx1G -jar GeneratorWorker.jar > ../log/worker1.log & echo $! > ../pid/run1.pid

for  i in $(seq 2 10)
do
    cd ../worker$i
    echo 'run'.'java -Xmx1G -jar GeneratorWorker.jar > ../log/worker'$i'.log'
    nohup java -Xmx1G -jar GeneratorWorker.jar > ../log/worker$i.log & echo $! > ../pid/run$i.pid
done

