import sys
from cx_Freeze import setup, Executable

includefiles = ['evidenceserver.properties', 'log4j.xml']
setup(
	name = "Duckbase - Installer",
	version = "1.0",
	description = "Database competence handler",
	options = {'build_exe': {'include_files': includefiles}},
	executables = [Executable("install.py"), Executable("tray.py")])
