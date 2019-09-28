.PHONY : all check clean commands settings

STEM=ds4se
CONFIG=_bookdown.yml _output.yml
FIXED=CONDUCT.md CONTRIBUTING.md LICENSE.md README.md
TEMP=$(patsubst %.Rmd,%.md,$(wildcard *.Rmd))
SRC=${CONFIG} ${FIXED} $(wildcard *.Rmd)
OUT=docs
HTML=${OUT}/index.html
PDF=${OUT}/${STEM}.pdf

all : commands

#-------------------------------------------------------------------------------

## commands     : show all commands.
commands :
	@grep -h -E '^##' ${MAKEFILE_LIST} | sed -e 's/## //g'

## everything   : rebuild all versions.
everything : ${HTML} ${PDF}

## html         : build HTML version.
html : ${HTML}

## pdf          : build PDF version.
pdf : ${PDF}

#-------------------------------------------------------------------------------

${HTML} : ${SRC}
	Rscript -e "bookdown::render_book('index.Rmd', 'bookdown::gitbook'); warnings()"

${PDF} : ${SRC}
	Rscript -e "bookdown::render_book('index.Rmd', 'bookdown::pdf_book'); warnings()"

#-------------------------------------------------------------------------------

## clean        : clean up generated files.
clean :
	@rm -rf ${OUT} ${STEM}.Rmd ${TEMP} *.utf8.md *.knit.md
	@find . -name '*~' -exec rm {} \;

## settings     : echo all variable values.
settings :
	@echo STEM ${STEM}
	@echo CONFIG ${CONFIG}
	@echo FIXED ${FIXED}
	@echo TEMP ${TEMP}
	@echo SRC ${SRC}
	@echo OUT ${OUT}
	@echo HTML ${HTML}
	@echo PDF ${PDF}
