#!/bin/bash
# input is  a pcap file output is a text file
count=1
while IFS='' read -r filename || [[ -n "$filename" ]]; do
    echo "reading from file: $filename"
    echo "count is $count"
    count=$((count+1))
    sort -n -r $filename."txt" -o $filename."txt"
    echo
done < "allpcapfilenames.txt"