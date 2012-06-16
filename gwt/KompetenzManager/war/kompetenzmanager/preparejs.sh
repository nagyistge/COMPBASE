ALL=raphael4gwt-all

YUIC=../../../../../yuicompressor-2.4.7.jar

#minify first
java -jar $YUIC -o raphael-min.js raphael.js
java -jar $YUIC -o raphael-ext-min.js raphael-ext.js
java -jar $YUIC -o raphael.free_transform-min.js raphael.free_transform.js
cp raphael4gwt.js raphael4gwt-min.js #do not compress cause it hass a debugger;
#java -jar $YUIC -o raphael4gwt-min.js raphael4gwt.js

cat raphael-ext-min.js > $ALL-min.js
cat raphael.free_transform-min.js >> $ALL-min.js
cat raphael4gwt-min.js >> $ALL-min.js


echo "/* raphael-ext */" > $ALL.js
cat raphael-ext.js >> $ALL.js

echo "/* raphael.free_transform */" >> $ALL.js
cat raphael.free_transform.js >> $ALL.js

echo "/* raphael4gwt */" >> $ALL.js
cat raphael4gwt.js >> $ALL.js

cd graphael
sh preparejs.sh
cd ..