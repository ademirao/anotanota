#!/bin/bash

{
unset TESSDATA_PREFIX
PATH=${PATH}:../linux/tesseract-3.03/training/

readonly LANG=nota;
readonly FILE=$1;

mkdir -p uncombined_tessdata

tesseract ${FILE}.tiff ${FILE}.box  nobatch box.train
unicharset_extractor ${FILE}.box
echo "fontnota 0 0 0 0 0" > font_properties
mftraining -F font_properties -U unicharset -O ${LANG}.${FILE}.unicharset ${FILE}.box.tr
cntraining ${FILE}.box.tr

mv ${LANG}.${FILE}.unicharset uncombined_tessdata/

for i in inttemp normproto pffmtable shapetable; do
mv $i uncombined_tessdata/${LANG}.${FILE}.${i};
done

combine_tessdata uncombined_tessdata/${LANG}.${FILE}.
}
