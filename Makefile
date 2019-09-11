.PHONY : all clean commands settings

SRC=$(wildcard */index.Rmd)
OUT=$(patsubst %/index.Rmd,%/index.html,$(SRC))

#-------------------------------------------------------------------------------

## commands     : show all commands (default).
commands :
	@grep -h -E '^##' ${MAKEFILE_LIST} | sed -e 's/## //g'

## all          : rebuild all files.
all : $(OUT)

%/index.html : %/index.Rmd
	-cd $$(dirname $<) && Rscript -e "rmarkdown::render(\"index.Rmd\")"

## clean        : clean up generated files.
clean :
	@find . -name '*~' -exec rm {} \;

## force-all    : force everything to rebuild.
force-all :
	touch ${SRC}
	make all

## settings     : echo all variable values.
settings :
	@echo SRC ${SRC}
	@echo OUT ${OUT}
