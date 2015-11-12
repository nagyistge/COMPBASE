xcopy ..\evidenceserver.properties .\
xcopy ..\install.py .\
xcopy ..\log4j.xml .\
xcopy ..\run.py .\
xcopy ..\tray.py .\
xcopy ..\freeze.py .\
mkdir img
xcopy ..\img\* .\img\

python freeze.py build
xcopy /Y build\exe.win32-2.7\* .\
RD /S /Q build

del .\*.py
