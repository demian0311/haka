#!/bin/sh

echo "generating traffic"

MAX=1000

echo "MAX: $MAX"
for ii in `seq 1 $MAX`
do
      sleep .5
      curl --silent \
         --write-out "$ii/$MAX %{http_code} %{time_total} %{filename_effective}"\
         --remote-name http://localhost:8080/haka/project; echo
done
