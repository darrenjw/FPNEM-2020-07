# Makefile

TARGETS=Session.html

FORCE:
	make $(TARGETS)


%.html: %.md
	pandoc -t html5 $< -o $@

clean:
	rm -f *~ *.png $(TARGETS)



# eof


