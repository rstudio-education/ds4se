.PHONY : all clean commands settings

SRC=$(wildcard */index.Rmd)
OUT=$(patsubst %/index.Rmd,%/index.html,$(SRC))
TIMEOUT_SEC=15

#-------------------------------------------------------------------------------

## commands     : show all commands (default).
commands :
	@grep -h -E '^##' ${MAKEFILE_LIST} | sed -e 's/## //g'

## all          : rebuild all files.
all : $(OUT)

%/index.html : %/index.Rmd
	-cd $$(dirname $<) && \
	../bin/timeout.sh ${TIMEOUT_SEC} 'Rscript -e "rmarkdown::render(\"index.Rmd\")"'

## clean        : clean up generated files.
clean :
	@find . -name '*~' -exec rm {} \;

## settings     : echo all variable values.
settings :
	@echo SRC ${SRC}
	@echo OUT ${OUT}
