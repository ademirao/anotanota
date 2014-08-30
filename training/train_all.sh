#!/bin/sh

{
(
export LANGUAGE=nota
for a in $(ls *.box) ;
do
	echo doing $a
	BASENAME=`basename $a .box`;
	tesseract ${BASENAME}.png $BASENAME box.train
	echo done $a
done

rm -f font_properties

for FONTNAME in $(ls *.box | cut -f 2 -d . | sort | uniq); do
	echo $FONTNAME 0 0 0 0 0 >> font_properties
done;

unicharset_extractor *.box

shapeclustering -F font_properties -U unicharset *.tr
mftraining -F font_properties -U unicharset -O $LANGNAME.unicharset *.tr
cntraining *.tr

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
