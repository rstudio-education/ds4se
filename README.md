# Data Science for Software Engineering

This repository holds work done by [Yim Register][register-yim] for their [RStudio internship][internship] in 2019.
The goal is to develop course materials to teach basic data analysis to programmers using software engineering problems and data sets.
Some of the questions it will show people how to answer include:

-   How many repositories are there on GitHub?
    -   This can be answered with [some API calls][github-api] or via [GHTorrent][ghtorrent]
-   How many people contribute to the average project?
    -   It isn't feasible to examine every project, and they're constantly changing, so we need to talk about sampling and estimation.
-   How long does the average project last?
    -   And is there a correlation between how long a project has been going on and how many people have contributed to it?
        To answer that, we'll have to talk about what correlation means and how to measure it.
    - Beyond that, could you learn to predict features that might help productivity? What would it mean to make a model of that?
-   Are there differences between projects that use different languages?
    -   What do we mean by "use different languages"? Do we rely on self-identification through repository tagging? Do we look at the files in the project? Do those classifications correlate?
    -   And once we have a way to classify projects, what kinds of statistical tests will tell us if two populations are the same or different?
    -   Beyond *what* tests, how do we interpet the results correctly, especially without making errors about what the results are actually saying?
-   In what order do people use functions in tidyverse pipelines?
    -   Are there common patterns to the order?
    
### Statistical Concepts Covered:

- mean, variance, standard deviation, normalization, z-scores
- experimental design and setup
- normality, parametric and nonparametric tests
- curve fitting
- introduction to modeling
- train/test split for prediction and evaluation

    
### Statistical Learning Objectives:

Students will be able to:
- set up their data for comparisons and/or modeling
- critically select features to investigate
- critically select appropriate models for their data
- collect data from the web for their own questions
- relate software engineering to (answerable!) statistical questions

[ghtorrent]: http://ghtorrent.org/
[github-api]: https://developer.github.com/v3/
[internship]: https://blog.rstudio.com/2019/03/25/summer-interns-2019/
[register-yim]: http://students.washington.edu/yreg/


### Things Covered In Current Lessons (8/20/19)

- `tidyverse` and data wrangling
- `ggplot2`

*Analyze This!*

- boxplots and outliers (IQR, standard deviation)
- distributions (normal and non-normal)
- means, confidence intervals, comparing means

*Developer Performance*

- correlation and r^2
- glm
- weighted features
- Anova
- boxplots and outliers
- experimental design
- order effects
- lm
- interaction plots

*Curiosity and Play*

- queries to github/bigquery
- SQL
- periodicity/time series
- boxplots and outliers
- logistic regression
- sigmoid curve
- predictive modeling

*How Many Repos*

- queries to github/bigquery
- SQL
- test and training data
- overfitting and underfitting
- modeling, number of parameters
- predictive modeling
- curve fitting
- residuals
- model evaluation

*Lines and Functions*

- boxplots and outliers
- effectively comparing means
- normalization
- correlation
- sample bias
- histograms
- normality
- shapiro test
- p value
- nonparametric tests

*When Will I Be Done?*

- reliability and validity
- data wrangling, `melt`,`cast`,`reshape`, long form, wide form
- log plot
- shapiro test
- correlation
- data viz
- histogram
- boxplots and outliers
- geom_smooth
- why is this lesson double, Greg?


*Sleep Deprivation*

- quasi-experiment
- distributions
- boxplots and outliers
- operationalizing a measurement
- normality and shaprio test
- violin plots
- confidence intervals
- effect size: cliff's delta
- parametric vs nonparametric
- statistical power
- Wilcoxon/Mann-Whitney U Test

*TDD*

- design thinking
- modeling recipe
- features
- descriptive vs inferential statistics
- modeling
- regression vs hypothesis testing
- AIC evaluation of modeling
- interactions
- component and residual plots
- regression output

*Stack Overflow*

- SQL query to Stack Exchange
- curve comparison
- kolmogorov-smirnov test
- time series
- cumulative sums
- let's try to put in a t-test or something simple, and discuss standard deviation and whatnot

```{r}
library(renv)
renv::init()
```

### Making Status Changes

Within the `yaml` there is a 'status:' string where the status of the index.RmD file is recorded. If you make any changes, right now you must manually run:

`python bin/py2json.py`

in order to update the status badges on the main webpage.

### Acknowledgments

*Thanks kindly to Greg Wilson, Derek Jones, Davide Fucci, Julia Silge, Rayce Rossum, Tom Zimmerman, Andy Begel, Andy Stefik, Ahmed Hassan, Jake Wobbrock, Andy Ko, Alison Hill, Carl Howe, and Dan Chen for academic and statistics inspiration, datasets, and guidance.*
TODO: ahh who else?

*Thanks kindly to Lisa Elkin, Maya Gans, and Lenny Brown for reminding me that it's totally okay to have to look everything up, that you don't need an airtight legal case to take a break, and for helping me through this summer.*
