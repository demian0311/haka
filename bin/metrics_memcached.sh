#!/bin/sh


#{ echo "stats"; sleep 1; } | telnet localhost 11211 | grep 'bytes'
{ echo "stats"; sleep 1; } | telnet localhost 11211 
