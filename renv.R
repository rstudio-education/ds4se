if (!requireNamespace("remotes"))
  install.packages("remotes")

remotes::install_github("rstudio/renv")
library(renv)
renv::init()
