#! /bin/bash
sCount=$1
sPortStart=$2
mPortStart=$3
for i in $(seq 0 $(($sCount-1)) )
do
    sPort=$(($i + $sPortStart))
	mPort=$(($i + $mPortStart))
	mID=$(redis-cli -p $mPort -a jgc123 cluster myid )
	echo "当前添加从节点的主节点id：$mID,  port: $mPort"
	redis-cli --cluster add-node $HOST":$sPort" $HOST":$mPort" --cluster-slave --cluster-master-id $mID -a jgc123
	sleep 2
done
