NAME = db-competence-installer-0.1
USERDIR = $(NAME)/usr/bin/db-competence-installer/
SOURDIR = ../..
OBJS = evidenceserver.properties install.py run.py log4j.xml
USEROBJS = $(addprefix $(USERDIR)/, $(OBJS))
SOUROBJS = $(addprefix $(SOURDIR)/, $(OBJS))

all: copy compile

clean:
	rm -r $(NAME)/usr/

$(USEROBJS): | $(USERDIR)

$(USERDIR):
	mkdir -p $(USERDIR)

copy:$(USERDIR) $(SOUROBJS)
	cp -f $(SOUROBJS) $(USERDIR)

compile: $(USEROBJS) $(NAME).deb
	dpkg -b $(NAME)

help:
	@echo This is a makefile to build a deb package for db-competence
	@echo ----------------------------------------------------------
	@echo Is this another version than $(NAME)?
	@echo Do you need any other object than $(OBJS)?
	@echo Is this the source directory $(SOURDIR) and this the wanted user dir $(USERDIR)?
	@echo If it is not, change it in the makefile!
	@echo Just use 'make' to copy the up-to-date files in the right direcotry and to compile it to a deb package afterwards.
	@echo If you want to change something use 'make clean' before you commit it.
