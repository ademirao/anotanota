#!/bin/sh

(

for i in $@; do
	a=${i}.tiff ;
	BASENAME=`basename $a .tiff`;
	tesseract $a  $BASENAME batch.nochop makebox
done
)

