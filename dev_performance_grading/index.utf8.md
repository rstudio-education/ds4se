---
title: "Developer Performance and Designing a New Grading System for Teams"
author: "Yim Register"
date: "7/3/2019"
output: html_document
---


<center>
![](../bin/images/teamwork.svg)
</center><br>


Someone wise once asked me:

> *"How do you choose a heart surgeon?"*
>
> "I don't know, how?"
>
>*"You look for the surgeon with the highest failure rate who is still accepting patients"*
>
> "And why on earth would you want that?"
>
>"*Because it means that they're getting* **all the hardest cases** *and people still trust them to do the surgery*."

This little exchange is surprisingly relevant to how we measure developer performance. Bear with me. If I were to tell you that the best developer at my company fixed the least number of bugs, what would you think? *Perhaps they are fixing the really tough ones*. It could also be that they simply don't make mistakes but that is just untrue. We all make mistakes in our code and spend a lot of time debugging (novices more than experts, but also the problems get harder so there's a lot at play!). Imagine that at the end of the workweek, everyone has to report how many bugs they fixed. One person reports fixing 78 and another person reports fixing 2. Who is the better developer?

Maybe you have some theories, or some immediate reactions. Perhaps you have some explanations of why that's not enough information. This is great practice for figuring out how to measure a concept like performance. 

One way to measure performance is to look at "number of faults found" in software testing. Finding and fixing faults may be a reasonable way to determine if developers can identify a problem and implement solutions to help software behave as expected. Reasonable enough, right? Hopefully you're learning that when we measure *anything*, we have to operationalize it in a reasonable way, taking into account prior research and interpreting results with our nuanced operationaliztion in mind. "Performance" is not necessarily captured entirely by "number of faults found" but it is totally reasonable to start somewhere. We take a look at data provided from Iivonen's [*Identifying and Characterizing Highly Performing Testers– A Case Study in Three Software Product Companies*](http://www.soberit.hut.fi/espa/publications/MSc-Thesis-Iivonen-2009-10-30.pdf)


http://www.knosof.co.uk/ESEUR/ESEUR-draft.pdf pg 52


## How do developers differ in their measurable output performance?






```r
library(knitr)
data <- read.csv("performance_dev.csv")

#just adding in a check on the percentages, we see some off-by-ones but that's probably due to rounding
data$Totals <- data$Fixed + data$No.Fix +data$Duplicate + data$Cannot.reproduce

kable(data)%>%
  kable_styling(bootstrap_options = c("striped", "hover"))
```

<table class="table table-striped table-hover" style="margin-left: auto; margin-right: auto;">
 <thead>
  <tr>
   <th style="text-align:left;"> Tester </th>
   <th style="text-align:right;"> Defects </th>
   <th style="text-align:right;"> Extra.Hot </th>
   <th style="text-align:right;"> Hot </th>
   <th style="text-align:right;"> Normal </th>
   <th style="text-align:right;"> Status.Open </th>
   <th style="text-align:right;"> Fixed </th>
   <th style="text-align:right;"> No.Fix </th>
   <th style="text-align:right;"> Duplicate </th>
   <th style="text-align:right;"> Cannot.reproduce </th>
   <th style="text-align:right;"> Totals </th>
  </tr>
 </thead>
<tbody>
  <tr>
   <td style="text-align:left;"> A </td>
   <td style="text-align:right;"> 74 </td>
   <td style="text-align:right;"> 4 </td>
   <td style="text-align:right;"> 1 </td>
   <td style="text-align:right;"> 95 </td>
   <td style="text-align:right;"> 12 </td>
   <td style="text-align:right;"> 62 </td>
   <td style="text-align:right;"> 26 </td>
   <td style="text-align:right;"> 12 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 100 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> B </td>
   <td style="text-align:right;"> 73 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 56 </td>
   <td style="text-align:right;"> 44 </td>
   <td style="text-align:right;"> 15 </td>
   <td style="text-align:right;"> 87 </td>
   <td style="text-align:right;"> 6 </td>
   <td style="text-align:right;"> 2 </td>
   <td style="text-align:right;"> 5 </td>
   <td style="text-align:right;"> 100 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> C </td>
   <td style="text-align:right;"> 70 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 29 </td>
   <td style="text-align:right;"> 71 </td>
   <td style="text-align:right;"> 36 </td>
   <td style="text-align:right;"> 71 </td>
   <td style="text-align:right;"> 24 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 4 </td>
   <td style="text-align:right;"> 99 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> D </td>
   <td style="text-align:right;"> 51 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 27 </td>
   <td style="text-align:right;"> 73 </td>
   <td style="text-align:right;"> 33 </td>
   <td style="text-align:right;"> 85 </td>
   <td style="text-align:right;"> 6 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 9 </td>
   <td style="text-align:right;"> 100 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> E </td>
   <td style="text-align:right;"> 50 </td>
   <td style="text-align:right;"> 2 </td>
   <td style="text-align:right;"> 16 </td>
   <td style="text-align:right;"> 82 </td>
   <td style="text-align:right;"> 30 </td>
   <td style="text-align:right;"> 89 </td>
   <td style="text-align:right;"> 9 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 3 </td>
   <td style="text-align:right;"> 101 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> F </td>
   <td style="text-align:right;"> 18 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 22 </td>
   <td style="text-align:right;"> 78 </td>
   <td style="text-align:right;"> 22 </td>
   <td style="text-align:right;"> 64 </td>
   <td style="text-align:right;"> 14 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 21 </td>
   <td style="text-align:right;"> 99 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> G </td>
   <td style="text-align:right;"> 17 </td>
   <td style="text-align:right;"> 18 </td>
   <td style="text-align:right;"> 18 </td>
   <td style="text-align:right;"> 65 </td>
   <td style="text-align:right;"> 18 </td>
   <td style="text-align:right;"> 71 </td>
   <td style="text-align:right;"> 14 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 14 </td>
   <td style="text-align:right;"> 99 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> H </td>
   <td style="text-align:right;"> 17 </td>
   <td style="text-align:right;"> 53 </td>
   <td style="text-align:right;"> 18 </td>
   <td style="text-align:right;"> 29 </td>
   <td style="text-align:right;"> 6 </td>
   <td style="text-align:right;"> 94 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 6 </td>
   <td style="text-align:right;"> 100 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> I </td>
   <td style="text-align:right;"> 12 </td>
   <td style="text-align:right;"> 8 </td>
   <td style="text-align:right;"> 17 </td>
   <td style="text-align:right;"> 75 </td>
   <td style="text-align:right;"> 42 </td>
   <td style="text-align:right;"> 100 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 100 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> J </td>
   <td style="text-align:right;"> 2 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 100 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 50 </td>
   <td style="text-align:right;"> 50 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 100 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> K </td>
   <td style="text-align:right;"> 80 </td>
   <td style="text-align:right;"> 21 </td>
   <td style="text-align:right;"> 59 </td>
   <td style="text-align:right;"> 20 </td>
   <td style="text-align:right;"> 13 </td>
   <td style="text-align:right;"> 90 </td>
   <td style="text-align:right;"> 7 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 3 </td>
   <td style="text-align:right;"> 100 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> L </td>
   <td style="text-align:right;"> 55 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 29 </td>
   <td style="text-align:right;"> 71 </td>
   <td style="text-align:right;"> 27 </td>
   <td style="text-align:right;"> 80 </td>
   <td style="text-align:right;"> 15 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 5 </td>
   <td style="text-align:right;"> 100 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> M </td>
   <td style="text-align:right;"> 48 </td>
   <td style="text-align:right;"> 13 </td>
   <td style="text-align:right;"> 21 </td>
   <td style="text-align:right;"> 67 </td>
   <td style="text-align:right;"> 19 </td>
   <td style="text-align:right;"> 97 </td>
   <td style="text-align:right;"> 3 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 100 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> N </td>
   <td style="text-align:right;"> 48 </td>
   <td style="text-align:right;"> 17 </td>
   <td style="text-align:right;"> 38 </td>
   <td style="text-align:right;"> 42 </td>
   <td style="text-align:right;"> 8 </td>
   <td style="text-align:right;"> 89 </td>
   <td style="text-align:right;"> 7 </td>
   <td style="text-align:right;"> 0 </td>
   <td style="text-align:right;"> 5 </td>
   <td style="text-align:right;"> 101 </td>
  </tr>
</tbody>
</table>
**Note that everything except the first column is in percentages**


### The Measure Matters
Here we have the developer with the *least* defects and the developer with the *most* defects. Let's play with personas a little bit: 

**Jesse Jordan (Tester J)** found 2 defects, each self-reported as "Normal" faults. They fixed one of them, but couldn't fix the other. The paper points out that Jesse actually resigned during the data collection period, and this is only a partial time series. We don't know what actually happened to Jesse, or why they resigned.



```r
data[data$Defects==min(data$Defects),]
```

```
##    Tester Defects Extra.Hot Hot Normal Status.Open Fixed No.Fix Duplicate
## 10      J       2         0   0    100           0    50     50         0
##    Cannot.reproduce Totals
## 10                0    100
```

**Kendall Kennedy (Tester K)** found 80 defects. They're still working on 13% of the faults, but for the finished ones they fixed 90%. They weren't able to fix 7% of those, though. They labeled the faults as primarily "Hot", with a few "Normal" and a few "Extra Hot". 3% were just not reproducible at all.


```r
data[data$Defects==max(data$Defects),]
```

```
##    Tester Defects Extra.Hot Hot Normal Status.Open Fixed No.Fix Duplicate
## 11      K      80        21  59     20          13    90      7         0
##    Cannot.reproduce Totals
## 11                3    100
```
### Do you think we can tell who is performing better from that measure? Surely there is more to the story than just number of defects found. 

These lessons are here not only to provide some statistical skills but also to get you thinking about *how to measure things*. Towards the end of this lesson, we will also try to devise a system for **grading group work**. Keep that in mind as we operationalize what it means to measure performance.

## Weighted Features
Each of the columns represents a *feature of interest*. If you've heard the term "feature engineering" when reading about Big Data or Machine Learning, it's talking about choosing which factors to pay attention to. Sometimes we get so nervous about the buzzwords like "Machine Learning" and don't ever get the chance to break down what it all really means. Well, this problem is an introduction to what it might mean to select features and combine them to predict an outcome. Whether it's determining what series you will watch on Netflix, or how well you are performing at your job, this kind of problem persists across all disciplines. It might occur to you that *it's simply not fair* to use such a measure to determine someone's productivity. Most of the time, you'd probably be correct. Our data sources, the people designing the measures, and the people interpreting the results are ill-equipped to account for the tons of diversity in the world. Hopefully now that you're armed with more statistical know-how, you'd be able to better advocate for yourself against harmful models.

While it may not be ideal to use a blanket measure for someone's productivity, it might still be useful to implement different support systems for workers in a company; if someone is struggling to feel productive and accomplished, perhaps we could catch it with some metrics and intervene to help them be the most fulfilled version of themselves!

We discussed that "number of faults found" isn't the best measure of performance in isolation, and we talked about how you need to include other features in combination. But should every feature be weighted equally? Or do some things matter more than others? In the performance data we have, we even have the developer ratings of how *serious* the faults were that they found. Let's look at how Jesse and Kendall rated everything.


```r
min_and_max <- data[data$Defects==max(data$Defects) | data$Defects==min(data$Defects) ,] #an overly verbose way to do this

ggplot(data,aes(Defects,Fixed,color=Tester))+
  geom_point()+
  xlab("Number of Defects Found")+
  ylab("Percentage Fixed")+
  ggtitle("Defects Found by Percentage Fixed")+
  theme_bw()
```

<img src="index_files/figure-html/unnamed-chunk-4-1.png" width="864" />

```r
cor(data$Defects,data$Fixed) #nada
```

```
## [1] 0.1754979
```

```r
#I want the distribution of Extra Hot, Hot, Normal for each tester
library(tidyr)
hot <- gather(data, Heat, Value, c("Extra.Hot","Hot","Normal"), factor_key=TRUE)

ggplot(hot,aes(Value,fill=Heat))+
  facet_wrap(~Heat)+
  geom_histogram(aes(y = ..density..), color="black")+
  geom_density(aes(y = ..density..),color="black",fill="black", alpha=.2,stat = 'density')+
  theme_bw()
```

```
## `stat_bin()` using `bins = 30`. Pick better value with `binwidth`.
```

<img src="index_files/figure-html/unnamed-chunk-4-2.png" width="864" />
We can see that "Extra Hot" faults occur with a lower frequency (the majority being close to 0), and "Normal" faults are...well, normal. We don't actually have access to how many of the **Fixed** faults were which Heat Level, but you might imagine that fixing an "Extra Hot" should count for more than fixing a "Normal" fault. Keep in mind that these are self-reported Heat levels, as well. One person's "Normal" might be someone else's "Extra Hot". But is there a way to give someone more credit for fixing an "Extra Hot" fault than for fixing a "Normal" one? Or would that simply incentivize developers to label all of their work as "Extra Hot"? Maybe other developers could rate how difficult the faults were, so that people wouldn't game the system. But that would also be really unfair, as someone might have worked *really* hard on something that would take someone else less time. Or another developer may not have the expertise to actually rate the severity of a fault for another developer. 

**Weighted Features** are a foundation in machine learning models. Algorithms can actually systematically infer the proper combination of weights on each feature that most accurately predicts an outcome variable.


Defects differ in their commercial importance, and a relative
weight for each classification has to be decided; should the weight of “No fix” be larger than
that given to “Cannot reproduce” or “Duplicate”

## Generalizability
To what extent would a tester’s performance, based on measurements involving one software
system in one company, be transferable to another system in the same company or another
company?

## It's All Made Up and the Points Don't Matter
Some of the theme of these lessons is to critically scrutinize statistics and t


## Investigating the Grant-Sackman Legend
### How long do different programmers take to solve the same task? It's not a 28:1 difference!
>There are three problems with this number:
>
>1. It is wrong.
>
>2. Comparing the best with the worst is inappropriate.
>
>3. One should use more data to make such a claim.

a bunch of developers did 2 tasks.
1. find the proper way through a maze (maze)
2. do some algebra operations (algebra)

they both CODED it (C) and DEBUGGED it (D), and were timed for everything they did

some of the devs did it online interactively with others and some did it offline where they got help, like, later?

some dingbats back in the 70s tried to say that for any given project, theres a 28:1 ratio of time-taken/ability between the worst developer and the best developer, meaning theres tons of variation for any developer and its a crapshoot.

but they didn't do their comparisons taking into accounts the different groups.... because, dingbats. and its just a dumb measure.

my lesson walks through why its a dumb measure

https://pdfs.semanticscholar.org/7fa6/bb45efda15fd9aef3c225b2dd99a7dc018d6.pdf


```r
#
# GS-perm-diff.R, 29 Aug 16
# Data from:
# The 28:1 {Grant}/{Sackman} legend is misleading, or: {How} large is interpersonal variation really?
# Lutz Prechelt
#
# Example from:
# Evidence-based Software Engineering: based on the publicly available data
# Derek M. Jones
#
# TAG experiment developer performance


source("../bin/data/ESEUR_config.r")



gs=read.csv(paste0(ESEUR_dir, "../bin/data/grant-sackman.csv.xz"), as.is=TRUE)
gs2 <-gs
gs2$task[gs$group=="online"] <- paste(gs$task[gs$group=="online"],"_online")
gs2$task[gs$group=="offline"] <- paste(gs$task[gs$group=="offline"],"_offline")

plt = ggplot(gs2,aes(task,time,fill=type,linetype=group))+
  geom_boxplot()+
  coord_flip()+
  theme_bw()
plt
```

<img src="index_files/figure-html/unnamed-chunk-5-1.png" width="672" />

```r
#ggsave("boxplots.png",plt)
```

## Order Effects


```r
#is there an order effect?
library(car)
```

```
## Warning: package 'car' was built under R version 3.6.1
```

```
## Loading required package: carData
```

```
## 
## Attaching package: 'car'
```

```
## The following object is masked from 'package:dplyr':
## 
##     recode
```

```r
model = lm(time ~seq,
           data = gs)


Anova(model,
      type = "II")
```

```
## Anova Table (Type II tests)
## 
## Response: time
##              Sum Sq Df F value    Pr(>F)    
## seq        44686140  1  14.198 0.0004669 ***
## Residuals 144774360 46                      
## ---
## Signif. codes:  0 '***' 0.001 '**' 0.01 '*' 0.05 '.' 0.1 ' ' 1
```

## Interaction Plots

```r
interaction.plot(gs$group,gs$task,gs$time)
```

<img src="index_files/figure-html/unnamed-chunk-7-1.png" width="672" />

```r
interaction.plot(gs$task,gs$group,gs$time)
```

<img src="index_files/figure-html/unnamed-chunk-7-2.png" width="672" />



```r
summary <- gs %>%
  group_by(task,group) %>%
  summarise(mean=mean(time),min=min(time),max=max(time),ratio=max(time)/min(time))
kable(summary)%>%
  kable_styling(bootstrap_options = c("striped", "hover"))
```

<table class="table table-striped table-hover" style="margin-left: auto; margin-right: auto;">
 <thead>
  <tr>
   <th style="text-align:left;"> task </th>
   <th style="text-align:left;"> group </th>
   <th style="text-align:right;"> mean </th>
   <th style="text-align:right;"> min </th>
   <th style="text-align:right;"> max </th>
   <th style="text-align:right;"> ratio </th>
  </tr>
 </thead>
<tbody>
  <tr>
   <td style="text-align:left;"> C_algebra </td>
   <td style="text-align:left;"> offline </td>
   <td style="text-align:right;"> 2100 </td>
   <td style="text-align:right;"> 420 </td>
   <td style="text-align:right;"> 5280 </td>
   <td style="text-align:right;"> 12.571429 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> C_algebra </td>
   <td style="text-align:left;"> online </td>
   <td style="text-align:right;"> 3460 </td>
   <td style="text-align:right;"> 660 </td>
   <td style="text-align:right;"> 6660 </td>
   <td style="text-align:right;"> 10.090909 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> C_maze </td>
   <td style="text-align:left;"> offline </td>
   <td style="text-align:right;"> 1080 </td>
   <td style="text-align:right;"> 240 </td>
   <td style="text-align:right;"> 3000 </td>
   <td style="text-align:right;"> 12.500000 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> C_maze </td>
   <td style="text-align:left;"> online </td>
   <td style="text-align:right;"> 480 </td>
   <td style="text-align:right;"> 120 </td>
   <td style="text-align:right;"> 960 </td>
   <td style="text-align:right;"> 8.000000 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> D_algebra </td>
   <td style="text-align:left;"> offline </td>
   <td style="text-align:right;"> 3010 </td>
   <td style="text-align:right;"> 1200 </td>
   <td style="text-align:right;"> 10200 </td>
   <td style="text-align:right;"> 8.500000 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> D_algebra </td>
   <td style="text-align:left;"> online </td>
   <td style="text-align:right;"> 2070 </td>
   <td style="text-align:right;"> 360 </td>
   <td style="text-align:right;"> 5100 </td>
   <td style="text-align:right;"> 14.166667 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> D_maze </td>
   <td style="text-align:left;"> offline </td>
   <td style="text-align:right;"> 740 </td>
   <td style="text-align:right;"> 270 </td>
   <td style="text-align:right;"> 1560 </td>
   <td style="text-align:right;"> 5.777778 </td>
  </tr>
  <tr>
   <td style="text-align:left;"> D_maze </td>
   <td style="text-align:left;"> online </td>
   <td style="text-align:right;"> 240 </td>
   <td style="text-align:right;"> 60 </td>
   <td style="text-align:right;"> 750 </td>
   <td style="text-align:right;"> 12.500000 </td>
  </tr>
</tbody>
</table>

```r
algebra=subset(gs, task=="C_algebra")

online=subset(subset(algebra, group == "online"))
offline=subset(subset(algebra, group != "online"))
subj_online=nrow(online)
total_subj=nrow(algebra)
subj_time=c(online$time, offline$time)

subj_mean_diff=mean(online$time)-mean(offline$time)


# Exact permutation test
subj_nums =seq(1:total_subj)
# Generate all possible subject combinations
subj_perms=combn(subj_nums, subj_online)

mean_diff = function(x)
{
# Difference in mean of one subject combination and all other subjects
mean(subj_time[x]) - mean(subj_time[!(subj_nums %in% x)])
}

# Indexing by column iterates through every permutation
perm_res=apply(subj_perms, 2, mean_diff)

# p-value of two-sided test
sum(abs(perm_res) >= abs(subj_mean_diff)) / length(perm_res)
```

```
## [1] 0.2943723
```

This shiz is nuanced! 


```r
plot(density(perm_res),
	main="", ylab="Density\n")
```

<img src="index_files/figure-html/unnamed-chunk-9-1.png" width="672" />

## Bootstrapping


```r
# Now the bootstrap, which in this case is testing an assumed wider population.

library("boot")
```

```
## 
## Attaching package: 'boot'
```

```
## The following object is masked from 'package:car':
## 
##     logit
```

```r
mean_diff=function(bids, indices)
{
t=bids[indices]
return(mean(t[1:subj_online])-mean(t[(subj_online+1):total_subj]))
}

bid_boot=boot(algebra$time, mean_diff, R = 9999)

plot(density(bid_boot$t),
	main="", ylab="Density\n")
```

<img src="index_files/figure-html/unnamed-chunk-10-1.png" width="672" />

```r
mean(bid_boot$t)
```

```
## [1] -0.2330233
```

```r
sd(bid_boot$t)
```

```
## [1] 1157.734
```

```r
length(bid_boot$t[abs(bid_boot$t) >= subj_mean_diff])
```

```
## [1] 2420
```

## In Your Ideal World...
### How should group projects be graded fairly?
