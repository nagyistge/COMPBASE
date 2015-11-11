xcopy ..\evidenceserver.properties .\
xcopy ..\install.py .\
xcopy ..\log4j.xml .\
xcopy ..\run.py .\
xcopy ..\setup.py .\

python setup.py py2exe

RD /S /Q build
xcopy /Y dist\* .\
RD /S /Q dist
del .\*.py