from distutils.core import setup
import py2exe, sys

dfiles = [(".", ["evidenceserver.properties", "log4j.xml"])]
try :
    setup(options = {"py2exe": {
        "packages": ["jenkinsapi", "pkg_resources"]
                    }},
          console=["install.py", "run.py"],
          data_files=dfiles)
    print "done"
except SystemExit as e:
    print "System Exit ",  e.message
    
except:
    print "Error"
    print "Unexpected error:", sys.exc_info()[0]
    raise
    
