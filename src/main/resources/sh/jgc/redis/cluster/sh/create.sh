#! /bin/bash

mCount=3
mPortStart=7100
sCount=3
sPortStart=7200
echo "这是一个创建redis集群的脚本，该脚本通过不同的端口启动多个redis服务，集群会有${mCount}个主节点，$sCount个从节点"
echo "端口如下，其中括号中的表示从节点端口号"
for (( i = 0; i < $mCount ; i++ )); do
    echo "`expr $mPortStart + $i`(`expr $sPortStart + $i`)"
done
echo ""
echo ""

echo "----------------------------先关闭已启动的redis服务------------------------"
./shutAll.sh $mCount $mPortStart
./shutAll.sh $sCount $sPortStart
echo "----------------------------已启动的redis服务关闭完毕------------------------"
echo ""
echo ""

echo "----------------------------开始创建${mCount}个主节点------------------------"
./doCreate.sh m $mCount $mPortStart
echo "----------------------------$mCount个主节点创建完成-------------------------"
echo ""
echo ""

echo "----------------------------开始创建${sCount}个从节点------------------------"
./doCreate.sh s $sCount $sPortStart
echo "----------------------------$sCount个从节点创建完成-------------------------"
echo ""
echo ""



echo "----------------------------开始给$mCount个主节点分配slot------------------------"
./addslots.sh $mCount $mPortStart
echo "----------------------------主节点分配slot完成------------------------"
echo ""
echo ""

echo "----------------------------开始将$mCount个主节点添加到同一个集群中------------------------"
./cltmet.sh $mCount $mPortStart
echo "----------------------------$mCount个主节点添加到同一个集群中完成------------------------"
echo ""
echo ""

##休眠3秒等待上面的分配slot和cluster meet 命令执行完毕后，再增加从节点
sleep 3


echo "----------------------------开始给$mCount个主节点添加从节点------------------------"
./addSlave.sh $sCount $sPortStart $mPortStart --source
echo "----------------------------给$mCount个主节点添加从节点完成------------------------"
