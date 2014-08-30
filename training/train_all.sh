#!/bin/sh

{
(
export LANGNAME=nota
rm *.tr
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

rm unicharset

unicharset_extractor *.box

shapeclustering -F font_properties -U unicharset *.tr
mftraining -F font_properties -U unicharset -O $LANGNAME.unicharset *.tr
cntraining *.tr

mkdir -p tessdata
mv $LANGNAME.unicharset tessdata/$LANGNAME.unicharset
mv pffmtable tessdata/$LANGNAME.pffmtable
mv normproto tessdata/$LANGNAME.normproto
mv inttemp tessdata/$LANGNAME.inttemp
mv shapetable  tessdata/$LANGNAME.shapetable

cd tessdata
combine_tessdata $LANGNAME.
)
}
