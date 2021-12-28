#! /bin/bash

count=$1
portStart=$2
for (( i = 0; i < $count ; i++ )); do
    port=`expr $portStart + $i`
    useFlag=portUseByRedis $port
    if [ $useFlag -eq 0 ]; then
        echo "端口[$port]没有被占用"
        continue
    elif [ $useFlag -eq 1 ]; then
        echo "端口[$port]被redis服务占用"
    elif [ $useFlag -eq 2 ]; then
        echo "端口[$port]被redis服务占用"
    fi

    

done

#判断指定端口是否被redis服务占用
portUseByRedis(){
    port=$1
    portMessage=`lsof -i:$port`
    if [ -z "$portMessage" ]; then
        return 0
    fi

    commandUse=$(lsof -i:$port | awk '{print $1}'|sed -n '2p')
    if [[ "$commandUse" == redis-ser* ]]; then
        return 1
    else
        return 2
    fi
}

getPidByPort(){
    port=$1
    pid=$(lsof -i:$port | awk '{print $2}'|sed -n '2p')
    return $pid
}

shutDownRedis() {
    port=$1
    redis-cli -p $port shutdown
    useFlag=portUseByRedis $port
    if [ $useFlag -eq 1 ]; then
        echo "端口[$port]依然被redis服务占用，强行关闭"
        pid=getPidByPort $port
        kill -9 $pid
    fi
}
