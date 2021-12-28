#! /bin/bash
#redis-cli -p 7000 -a 123456789 cluster meet 172.17.0.8 6379
#redis-cli -p 7000 -a 123456789 cluster meet 172.17.0.9 6379
#redis-cli -p 7000 -a 123456789 cluster meet 172.17.0.10 6379
#redis-cli -p 7000 -a 123456789 cluster meet 172.17.0.11 6379
#node_id_m=$(redis-cli -p 7000 -a 123456789 cluster nodes | grep 172.17.0.8 | awk '{print $1}')
#echo $node_id_m
#redis-cli -p 7001 -a 123456789 cluster replicate $node_id_m
#redis-cli -p 7002 -a 123456789 cluster replicate $node_id_m
#redis-cli -p 7003 -a 123456789 cluster replicate $node_id_m
firstPort=$2
for i in $(seq 1 $(($1-1)) )
do
        port=$(($i + $2))
        echo $port
        IP=$(docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' redis_$port)
        echo "将$port并入集群$firstPort"
        redis-cli -p $firstPort cluster meet $port
done
