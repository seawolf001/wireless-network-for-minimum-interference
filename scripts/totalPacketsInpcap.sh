#!/bin/bash
count=1;
while IFS='' read -r filename || [[ -n "$filename" ]]; do
    echo "reading from file: $filename"
      cat $filename".csv" | awk -F"," 'BEGIN{OFS=",";} {if (($7!="") && ($7!="ff:ff:ff:ff:ff:ff")) print $7}' > $filename".txt"
      	echo -n $count >> "totalPacketsInpcap.txt"
    	echo -n " " >> "totalPacketsInpcap.txt"
    	echo -n $filename >> "totalPacketsInpcap.txt"
    	echo -n " " >> "totalPacketsInpcap.txt"
    	cat $filename".txt" | wc -l >> "totalPacketsInpcap.txt";
      	printf "count is $count\n"
      	count=$((count+1))
done < "allpcapfilenames.txt"