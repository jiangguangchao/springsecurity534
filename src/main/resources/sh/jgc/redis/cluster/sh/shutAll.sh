#! /bin/bash

count=$1
portStart=$2


#判断指定端口是否被redis服务占用
portUseByRedis(){
    port=$1
    portMessage=`lsof -i:$port`
    if [ -z "$portMessage" ]; then
        echo 0
        return
    fi
    commandUse=$(lsof -i:$port | awk '{print $1}'|sed -n '2p')
    if [[ "$commandUse" == redis-ser* ]]; then
        echo 1
    else
        echo 2
    fi
}

getPidByPort(){
    port=$1
    pid=$(lsof -i:$port | awk '{print $2}'|sed -n '2p')
    echo $pid
}

shutDownRedis() {
    port=$1
    redis-cli -p $port -a jgc123 shutdown
    useFlag=`portUseByRedis $port`
    if [ $useFlag -eq 1 ]; then
        echo "端口[$port]依然被redis服务占用，强行关闭"
        pid=`getPidByPort $port`
        kill -9 $pid
    fi
}




for (( i = 0; i < $count ; i++ )); do
    port=`expr $portStart + $i`
    useFlag=`portUseByRedis $port`
#    echo "useFlag: $useFlag"
    if [ $useFlag -eq 0 ]; then
        echo "端口[$port]没有被占用"
        continue
    elif [ $useFlag -eq 1 ]; then
        echo "端口[$port]被redis服务占用,关闭redis服务"
        shutDownRedis $port
    elif [ $useFlag -eq 2 ]; then
        echo "端口[$port]被redis服务占用"
    fi
done






