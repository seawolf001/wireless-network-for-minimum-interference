#!/bin/bash
# input is  a pcap file output is a text file
count=1;
while IFS='' read -r filename || [[ -n "$filename" ]]; do
    echo "reading from file: $filename"
      	#tshark -r $filename."pcap" -T fields -E separator=, -e wlan.bssid | sort | uniq -c > $filename."txt"
      	cat $filename".csv" | awk -F"," 'BEGIN{OFS=",";} {if (($7!="") && ($7!="ff:ff:ff:ff:ff:ff")) print $7}' | sort | uniq -c > $filename".txt"
      	printf "count is $count\n"
      	count=$((count+1))
done < "allpcapfilenames.txt"