#! /bin/bash

echo "检查redis集群中的主从关系，括号内表示从节点zz"
cmd="redis-cli -c -h $HOST -p 7100 -a jgc123"
master_id_arr=(` $cmd cluster nodes | grep master | awk '{print $1}'`)
master_ip_port_arr=(` $cmd cluster nodes | grep master | awk '{print $2}'`)
slave_ip_port_arr=(`$cmd cluster nodes | grep slave | awk '{print $2}'`)
master_id_in_slave_arr=(`$cmd cluster nodes | grep slave | awk '{print $4}'`)

master_size=${#master_id_arr[@]}
slave_size=${#slave_ip_port_arr[@]}

#声明一个map
declare -A myMap
for (( i = 0; i < $master_size; i++ )); do
#    myMap[$i]=`echo ${master_ip_port_arr[i]%@*}`
    master_id=${master_id_arr[$i]}
    master_ip=${master_ip_port_arr[$i]}
    if [[ $master_ip == 10.0* ]]; then
        master_ip=$HOST
    fi
    slave_ip_for_master=""
    for (( j = 0; j < $slave_size ; j++ )); do
        if [ ${master_id} == ${master_id_in_slave_arr[$j]} ]; then
#            echo "找到一个从节点 master_id:$master_id , ip_host:${master_ip_port_arr[$i]}, slave_ip_port:${slave_ip_port_arr[$j]}"
            slave_ip_for_master=${slave_ip_for_master}${slave_ip_port_arr[$j]%@*}", "

        fi
    done
    slave_ip_for_master=${slave_ip_for_master%,*}
    myMap[$i]=${master_ip_port_arr[i]%@*}"("${slave_ip_for_master}")"
    echo ${myMap[$i]}

done








