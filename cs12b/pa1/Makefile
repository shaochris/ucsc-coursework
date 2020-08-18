# Makefile for pa1 to clean .class files and executable jar file

JAVASRC	= Recursion.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = Recursion
CLASSES = Recursion.class
JARFILE = Recursion
SUBMIT = submit cmps012b-pt.w18 pa1

all: $(JARFILE)
$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)
$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)
clean:
	rm $(CLASSES) $(JARFILE)
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
