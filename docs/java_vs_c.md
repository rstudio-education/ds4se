# Code Quality and Lines of Code



### Redundancy?

- Notes for me:
- Landman suggests not redundant enough to chuck the CC metric
- The correlation between aggregated CC for all subroutines and the total SLOC of a file is higher than the correlation between CC and SLOC of individual subroutines


## How Does Complexity Relate to Quality?

FIXME

## Cycolmatic Complexity

## "Big Data"! Lines of Code vs. Function Declarations

We saw in our last exercise that we could use ASTs to explore the components of a program. That was a helpful exercise for learning about how we employ tools to collect data from programs on GitHub. However, we only collected a few hundred programs; and if you recall, they were all implementations of data structures and programming concepts in isolation. We know that from our *sample*, our results may not generalize to production code. This means we are lacking something called **external validity**: results of a study can realiably generalize to other contexts/samples (within reason). 

Now, you've probably heard this before when discussing concepts like Big O: it only really starts to matter for performance when n is big enough. I know I've certainly questioned why *O(log(n))* vs. *O(n)* even matters when my little sorting snippet runs just fine whatever the algorithm. But here is a big concept: **Complexity matters for big data, and the important findings may be undetectable on toy problems**.

Landman et al. also investigated this problem, using *millions* of Java and C programs. 


FIXME: They're actually different problems. I have to figure out how to tie them together: our problem looks at functions and lines, theirs looks at number of lines per function, and also looks at CC.


```r
# lang,cc,sloc

source("data/ESEUR_config.r") # FIXME

library("plyr")
cc_loc=read.csv(paste0(ESEUR_dir, "data/Landman_m_ccsloc.csv.xz"), as.is=TRUE)
cc_loc=subset(cc_loc, cc != 0)
cc_loc$logloc=log(cc_loc$sloc)

C_loc=subset(cc_loc, lang == "C")
Java_loc=subset(cc_loc, lang == "Java")
```


[analyzing-software-data]: https://www.worldcat.org/title/art-and-science-of-analyzing-software-data-analysis-patterns/oclc/1062303882
[analyze-this-paper]: https://www.microsoft.com/en-us/research/publication/analyze-this-145-questions-for-data-scientists-in-software-engineering/
[coop-software-dev]: http://faculty.washington.edu/ajko/books/cooperative-software-development/index.html
[eseur]: http://www.knosof.co.uk/ESEUR/
[making-software]: https://www.worldcat.org/title/making-software-what-really-works-and-why-we-believe-it/oclc/700636023
[perspectives-ds-se]: https://www.worldcat.org/title/perspectives-on-data-science-for-software-engineering/oclc/1023264016
[sharing-data-models]: https://www.worldcat.org/title/sharing-data-and-models-in-software-engineering/oclc/906700665
