#!/bin/bash

url=https://adventofcode.com/2021/day/${1}/input
echo $url

cookie=$(<adventcookie)
echo $cookie

output="app/src/main/resources/y2021/input$(printf "%02d" ${1}).txt"

curl $url \
  -H "cookie: $cookie" \
  --output $output
