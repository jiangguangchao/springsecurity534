#! /bin/bash
num=`expr 16384 / $1`
for i in $(seq 0 $(($1-1)) )
do
	port=$(($i + $2))
#	echo $port
	slot_start=`expr 0 + $i \* $num`
	slot_end=`expr $slot_start + $num - 1`
	if [ "$i" -eq `expr $1 - 1` ];then
		slot_end=16383
	fi
#	echo "slot_start:"$slot_start
#	echo "slot_end:"$slot_end
	echo "开始给主节点[$port]分配slot[${slot_start}-$slot_end]"
	redis-cli -p $port cluster addslots $(seq -s ' ' $slot_start $slot_end)
	echo "主节点[$port]分配slot[${slot_start}-$slot_end]完成"
done

