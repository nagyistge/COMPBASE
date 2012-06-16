ALL=g.raphael.all

YUIC=../../../../../../yuicompressor-2.4.7.jar

#minify first
java -jar $YUIC -o g.raphael-min.js g.raphael.js
java -jar $YUIC -o g.bar-min.js g.bar.js
java -jar $YUIC -o g.dot-min.js g.dot.js
java -jar $YUIC -o g.line-min.js g.line.js
java -jar $YUIC -o g.pie-min.js g.pie.js
java -jar $YUIC -o g.sunburst-min.js g.sunburst.js
java -jar $YUIC -o g.radar-min.js g.radar.js

cat g.raphael-min.js > $ALL-min.js
cat g.bar-min.js >> $ALL-min.js
cat g.dot-min.js >> $ALL-min.js
cat g.line-min.js >> $ALL-min.js
cat g.pie-min.js >> $ALL-min.js
cat g.sunburst-min.js >> $ALL-min.js
cat g.radar-min.js >> $ALL-min.js

echo "/* g.raphael */" > $ALL.js
cat g.raphael.js >> $ALL.js

echo "/* g.bar */" >> $ALL.js
cat g.bar.js >> $ALL.js

echo "/* g.dot */" >> $ALL.js
cat g.dot.js >> $ALL.js

echo "/* g.line */" >> $ALL.js
cat g.line.js >> $ALL.js

echo "/* g.pie */" >> $ALL.js
cat g.pie.js >> $ALL.js

echo "/* g.sunburst */" >> $ALL.js
cat g.sunburst.js >> $ALL.js

echo "/* g.radar */" >> $ALL.js
cat g.radar.js >> $ALL.js