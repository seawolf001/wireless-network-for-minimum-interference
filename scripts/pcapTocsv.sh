#!/bin/bash
# input is  a pcap file output is a text file
count=1;
while IFS='' read -r filename || [[ -n "$filename" ]]; do
    echo "reading from file: $filename"
	tshark -r $filename".pcap" -E separator=, -T fields -e frame.time_relative -e frame.number -e frame.len -e radiotap.datarate -e wlan.fc.type_subtype -e wlan_mgt.ssid -e wlan.bssid -e wlan_mgt.qbss.cu -e wlan_mgt.ds.current_channel -e wlan_mgt.qbss.scount -e wlan.fc.retry -e wlan.fc.moredata -e wlan.fc.frag -e wlan.duration -e wlan.ra -e wlan.ta -e wlan.sa -e wlan.da -e wlan.seq -e wlan.qos.priority -e wlan.qos.amsdupresent -e wlan.fc.type -e radiotap.dbm_antsignal > $filename".csv"
      	printf "count is $count\n"
      	count=$((count+1))
done < "allpcapfilenames.txt"