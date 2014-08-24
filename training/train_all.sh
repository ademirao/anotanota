#!/bin/sh

{
(
readonly LANGNAME=nota
readonly FONTNAME=nota

for i in $@;
do
	a=${LANGNAME}.${FONTNAME}.exp${i}.tiff
	echo doing $a
	BASENAME=`basename $a .tiff`;
	tesseract $a $BASENAME box.train
	echo done $a
done

unicharset_extractor $LANGNAME.$FONTNAME.exp*.box
echo $FONTNAME 0 0 0 0 0 > font_properties

shapeclustering -F font_properties -U unicharset $LANGNAME.$FONTNAME.exp*.tr
mftraining -F font_properties -U unicharset -O $LANGNAME.unicharset $LANGNAME.$FONTNAME.exp*.tr
cntraining $LANGNAME.$FONTNAME.exp*.tr

mkdir -p tessdata
cp unicharset tessdata/$LANGNAME.unicharset
cp pffmtable tessdata/$LANGNAME.pffmtable
cp normproto tessdata/$LANGNAME.normproto
cp inttemp tessdata/$LANGNAME.inttemp
cp shapetable  tessdata/$LANGNAME.shapetable

cd tessdata
combine_tessdata $LANGNAME.
)
}
