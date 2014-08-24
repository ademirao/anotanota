#!/bin/bash

EXP=$1;
shift

for i in $@; do 
tesseract receipt${i}.jpg -l nota ${EXP}.receipt${i} ../../../app/assets/tesseract/config
done
