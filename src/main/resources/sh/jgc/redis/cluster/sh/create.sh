#! /bin/bash

mCount=4
mPort=7000
sCount=4
sPort=8000
./doCreate.sh m $mCount $mPort
./doCreate.sh s $sCount $sPort
./addslots.sh $mCount $mPort
./cltmet.sh $mCount $mPort
#休眠3秒等待上面的分配slot和cluster meet 命令执行完毕后，再增加从节点
sleep 3
./addSlave.sh $sCount $sPort $mPort --source
 
