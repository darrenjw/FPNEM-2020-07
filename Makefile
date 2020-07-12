# Makefile

FORCE:
	sbt mdoc
	cd target/mdoc ; make

view:
	cd target/mdoc ; make
	xdg-open target/mdoc/Session.pdf &

edit:
	emacs Makefile *.md *.sbt docs/*.md &



# eof

