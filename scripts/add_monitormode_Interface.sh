iw dev #check
sudo iw phy phy0 interface add mon0 type monitor
iw dev #check
sudo ifconfig mon0 up
iwconfig mon0
