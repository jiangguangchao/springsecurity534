#! /bin/bash
sCount=$1
sPortStart=$2
mPortStart=$3
IP=110.42.197.207
for i in $(seq 0 $(($sCount-1)) )
do
    sPort=$(($i + $sPortStart))
	mPort=$(($i + $mPortStart))
	mID=$(redis-cli -p $mPort cluster myid)
	redis-cli --cluster add-node $IP":$sPort" $IP":$mPort" --cluster-slave --cluster-master-id $mID
	sleep 2
done
