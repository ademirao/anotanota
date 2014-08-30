#! /bin/bash

LETTER=$2
DIR=$(pwd)
FILE_PTRN=$(pwd)/$1

rm -fr crop_$LETTER;
mkdir -p crop_$LETTER;
(
cd crop_$LETTER;
for F in $(ls $FILE_PTRN); do 
	FILE=$(basename $F .png)
	FULL_PATH_FILE=$DIR/$FILE;
	echo $FULL_PATH_FILE

	declare -a DIMENSIONS=($(identify ${FULL_PATH_FILE}.png | cut -f 4 -d ' '  | cut -f 1 -d + | tr 'x' ' '))
	echo ${DIMENSIONS[@]}
	grep "^$LETTER" $FULL_PATH_FILE.box | while read LINE; do
	  CROP_FILE_NAME=${FILE}_$(echo $LINE| tr ' ' '_').png;
	  declare -a ITEMS=( $LINE );
	  CROP_DIM=$((${ITEMS[3]}-${ITEMS[1]}))x$((${ITEMS[4]}-${ITEMS[2]}));
	  CROP_LINE=${CROP_DIM}+${ITEMS[1]}+$((${DIMENSIONS[1]}-${ITEMS[4]}));
	  echo $CROP_FILE_NAME : ${ITEMS[@]} -\> $CROP_LINE;
	  convert -crop $CROP_LINE $FULL_PATH_FILE.png $CROP_FILE_NAME;
	done
done
)

