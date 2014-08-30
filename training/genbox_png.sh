#!/bin/sh

(

for i in $@; do
	a=${i}.png ;
	BASENAME=`basename $a .png`;
	tesseract $a  $BASENAME batch.nochop makebox
done
)

