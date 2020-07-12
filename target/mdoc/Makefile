# Makefile

TARGETS=Session.html Session.scala Session.pdf

FORCE:
	make $(TARGETS)

%.scala: %.md
	cat $< | sed -n '/^```scala/,/^```/ p' | sed '/^\/\/ /d' | sed 's|^```scala||g' | sed 's|^```||g'  > $@


%.pdf: %.md
	pandoc $< -t beamer -o $@

%.html: %.md
	pandoc -t html5 $< -o $@

clean:
	rm -f *~ *.png $(TARGETS)



# eof


