#!/bin/sh

(
readonly LANGNAME=nota
readonly FONTNAME=nota

for i in $@; do
	a=$LANGNAME.$FONTNAME.exp${i}.tiff ;
	BASENAME=`basename $a .tiff`;
	tesseract $a  $BASENAME batch.nochop makebox
done
)

