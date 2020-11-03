src = $(wildcard *.svg)
obj = $(src:.svg=.pdf)

main.pdf: $(obj) main.tex
	pdflatex main.tex

%.pdf: %.svg
	inkscape -z --file=$^ --export-area-page --export-pdf=$@
#	inkscape -D -z --file=$^ --export-pdf=$@
	

.PHONY: clean
clean:
	rm -f $(obj) main.aux main.log main.nav main.out main.snm main.toc main.vrb


