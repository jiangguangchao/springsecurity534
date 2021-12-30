#! /bin/bash

basePath=/jgc/redis/cluster
mCount=$2
mPortStart=$3
type=$1
typeName="主"
typePath=$basePath"/master"
if [ "$type" = "s" ]; then
    type="从"
    typePath=$basePath"/slave"
fi

cd $typePath
rm -rf *
for (( i = 0; i < $mCount; i++ )); do
    port=`expr $i + $mPortStart`
    echo "开始准备$typeName节点[$port]需要的文件夹和配置文件"
    cd $typePath
    mkdir $port
    cp $basePath/baseConf/redis.conf $port/redis.conf
    sed -i "/^bind/c # bind $HOST" $port/redis.conf
    sed -i "/^port/c port $port" $port/redis.conf
    sed -i "/^daemonize/c daemonize yes" $port/redis.conf
    sed -i "/^protected-mode/c protected-mode yes" $port/redis.conf
    sed -i "/^# requirepass/c requirepass jgc123" $port/redis.conf
    sed -i "/^# cluster-enabled/c cluster-enabled yes" $port/redis.conf
    sed -i "/^logfile/c logfile log.log" $port/redis.conf
    sed -i "/^pidfile/c pidfile pid.pid" $port/redis.conf
    sed -i "/^# masterauth/c masterauth jgc123" $port/redis.conf
    echo "$typeName节点[$port]需要的文件夹和配置文件准备完毕.."
    echo "开始启动$typeName节点[$port]"
    sleep 1
    cd $port
    redis-server redis.conf
done
