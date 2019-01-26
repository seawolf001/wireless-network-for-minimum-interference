#! /bin/bash
# before running this  script -> turn off network-manager by  : sudo service network-manager stop
sudo ifconfig wlan2 up
sudo ip link set wlan2 down
sudo ip addr flush dev wlan2
sudo ip link set wlan2 up
# $1 is the mac address of  target AP
sudo iwconfig wlan2 essid STUDENTS-N ap $1
sudo wpa_supplicant -Dnl80211 -i wlan2 -B -c /etc/wpa_supplicant/wpa_supplicant.conf
sudo dhclient -v wlan2
# to turn on network-manager ->  sudo service network-manager  restart
