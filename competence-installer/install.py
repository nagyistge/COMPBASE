from jenkinsapi.jenkins import Jenkins
import urllib2, os, datetime, pytz, subprocess

def get_server_instance():
    server = Jenkins('http://fleckenroller.cs.uni-potsdam.de/')
    return server

def download_file(file_name):
    if os.path.isfile(file_name):
        print "Remove old " + file_name
        os.remove(file_name)
    print "Start to download " + file_name
    u = urllib2.urlopen(files[file_name]["url"])
    f = open(files[file_name]["path"], 'wb')
    meta = u.info()
    file_size = int(meta.getheaders("Content-Length")[0])
    print "Downloading: %s Bytes: %s" % (file_name, file_size)

    file_size_dl = 0
    block_sz = 8192
    while True:
        buffer = u.read(block_sz)
        if not buffer:
            break

        file_size_dl += len(buffer)
        f.write(buffer)
        status = r"%10d  [%3.2f%%]" % (file_size_dl, file_size_dl * 100. / file_size)
        status = status + chr(8)*(len(status)+1)
        print status,

    f.close()
    files[file_name]["downloaded"]=1
    print "Download complete."
def modification_date(filename):
    t = os.path.getctime(filename)
    return datetime.datetime.fromtimestamp(t)

def download_process(job_instance, file_name, file_path):
    utc=pytz.UTC
    lbd =  job_instance.get_last_good_build()
    print "Timestamp of last valid build: " + lbd.get_timestamp().strftime('%Y-%m-%d %H:%M:%S')
    if os.path.isfile(file_name):
        mod_date =  utc.localize(modification_date(file_name))
        print "Timestamp of " + file_name + ": " + mod_date.strftime('%Y-%m-%d %H:%M:%S')
        #TODO Insert comment again
        if lbd.get_timestamp() > mod_date :
            while 1:
                answer = raw_input(file_name + " is out dated. Download new one?(yes/no)")
                if answer == "yes" :
                    download_file(file_name);
                    break
                elif answer == "no" :
                    break
                print "No valid answer. Please just use 'yes' or 'no'";
        else:
            print "Your " + file_name + " is up to date."
    else :
        download_file(file_name)
def define_my_path(writeFile,first, end):
    if first == "writeDebugRDF" :
        answer = raw_input(first + "(Default false)")
    else :
        answer = raw_input(first + "(Default is this directory)")
    if answer == "" :
        answer =  end
    writeFile.write(first + "=" + answer  + "\n")

files = {
    "CompetenceServer.jar": {
        "url": "http://fleckenroller.cs.uni-potsdam.de/job/competency%20database/ws/competence-database/target/CompetenceServer.jar",
        "downloaded": 0,
        "path": "CompetenceServer.jar",
    },
    "log4j.xml": {
        "url": "http://fleckenroller.cs.uni-potsdam.de/job/competency%20database/ws/competence-database/log4j.xml",
        "downloaded":0,
        "path": "log4j.xml",
    },
    "evidenceserver.properties": {
        "url": "http://fleckenroller.cs.uni-potsdam.de/job/competency%20database/ws/competence-database/evidenceserver.properties",
        "downloaded":0,
        "path": "evidenceserver.properties",
    },
    "kompetenzen_moodle_utf8.csv": {
        "url": "http://fleckenroller.cs.uni-potsdam.de/job/competency%20database/ws/competence-database/src/main/scala/resources/kompetenzen_moodle_utf8.csv",
        "downloaded":0,
        "path": "kompetenzen_moodle_utf8.csv",
    },
    "epos.xml": {
        "url": "http://fleckenroller.cs.uni-potsdam.de/job/competency%20database/ws/competence-database/src/main/scala/resources/epos.xml",
        "downloaded":0,
        "path": "epos.xml",
    },
    "CompetenceImporter.jar": {
        "url": "http://fleckenroller.cs.uni-potsdam.de/job/competency%20database/ws/competence-database/target/CompetenceImporter.jar",
        "downloaded":0,
        "path": "CompetenceImporter.jar",
    }

}
server = Jenkins('http://fleckenroller.cs.uni-potsdam.de/')
properties = {
    'tdblocation': "tdb2/",
    'log4jlocation': files["log4j.xml"]["path"],
    'rootPath': os.path.abspath(".") + "/",
    'writeDebugRDF': "false",
    'csvFile': files['kompetenzen_moodle_utf8.csv']["path"],
    'eposfile': files['epos.xml']["path"],
}
print "Established Connection to Server. Jenkins Version: " + server.version
for job in server.get_jobs():
    if job[0] == 'competency database':
        for key in files.keys():
            download_process(server.get_job(job[0]), key, files[key]["url"])
        if files["evidenceserver.properties"]["downloaded"] :
            with open('evidenceserver.properties') as stream:
                writeFile = open('evidenceserver.buffer', 'w')
                writeFile.truncate()
                debugRdfProp = 1;
                print "Please choose the path for the attributes in your server config"
                for line in stream:
                    first = line.split("=")[0]
                    if first in properties:
                        define_my_path(writeFile, first, properties[first])
                        if first == 'writeDebugRDF' :
                            debugRdfProp = 0

                    else :
                        writeFile.write(line)

                if debugRdfProp :
                    define_my_path(writeFile, "writeDebugRDF", properties["writeDebugRDF"])
                writeFile.close()
                os.remove("evidenceserver.properties")
                os.renames("evidenceserver.buffer", "evidenceserver.properties")
                print "Later You can change the properties in evidenceserver.properties"
if os.path.isfile("evidenceserver.properties"):
    if not os.path.isfile("mymodelrdf.owlat") :
        print "Integrate Data"
        subprocess.call(['java', '-jar', 'CompetenceImporter.jar'])
    print "Starting program"
    subprocess.call(['java', '-jar', 'CompetenceServer.jar'])
else :
    print "No evidenceserver.properties"
#os.system("java -jar CompetenceServer.jar")
