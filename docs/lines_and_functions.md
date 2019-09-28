# Number of Lines versus Number of Functions in Different Languages



## "Ugh, why do we have to write this in *that* language?"

Whether you're groaning over having to translate your code into some other [**language**](glossary.html#programminglanguage), or arguing profusely over the benefits of some obscure [**package**](glossary.html#library), it's likely that you've considered the differences between programming languages before.  Perhaps you're such a language switcher that your semicolons get mixed up with your whitespace and you search for way too long for the `switch` statement that doesn't exist in Python. Perhaps you're committed to your language and never touching anything else, because yours is *clearly* superior. But whatever the case, we are going to explore some differences between languages today. 

![](static/languages.png)

## What does it mean to be "different"?

One of the most common statistical tools is to compare [**samples**](glossary.html#sample) in order to detect a [**significant difference**](glossary.html#significantdifference). This might mean detecting a difference in their averages, or more formally, detecting that they are drawn from truly different generating processes in the world. More on that: whenever we draw a sample, we are trying to capture a sample of some phenomenon happening; there is some process (that we can't quite know) that dictates how that data turns out. In this example. it could be that there is something inherent about Python vs. JavaScript interpreters that make them actually different. Or perhaps it could be that Python *users* are different from JavaScript *users*, with one tending to be more verbose than the other. We are trying to draw samples in order to capture patterns in the world, and those patterns come from some [**causal**](glossary.html#causality) relationship. With comparisons, we really *cannot* determine causality, but this is to get us thinking about the "why" when we do see a difference afterall. If we see a difference in average number of lines for Python vs. JavaScript, is there something inherent about writing Python code that lends nicely to one liners? Or is it the population of Python users? Or is it the specific Python code we grabbed? These are all questions we need to think about when we hear someone say "Oh please, use this language instead! It's way better at... blah blah blah." So let's get to the bottom of those "blah blah blah" claims, shall we?



## How does the number of lines relate to the number of function definitions?

You're faced with our question: How does the number of lines of code compare with the number of [**function definitions**](glossary.html#function) between languages? Immediately, I think about the many times I've seen code that copy-and-pastes the same block of code over and over, without declaring it as a function. (*Remember your coding etiquette, people! If you copy and paste 3 times, it's time for a function.*) But I'm also reminded of the massive code libaries, containing a function for *everything*, with hundreds of lines of code for all of those functions. Try to reflect on your own intuition about if the number of lines has a relationship to the number of function declarations in a program. Maybe take a look at a program you've written: how many lines? how many function declarations? And did it change over the duration of your first programming course? (I personally went from copy-and-pasting a hard-coded mess into neat little helper functions as I had to face my own monsterous and unmaintainable code.) But wouldn't that *shorten* my program length? You can imagine that the larger the program, the more function definitions there are. But you can also imagine just the opposite.

## How do I parse a programming language? Abstract Syntax Trees

We need  to parse a program in order to count up the number of function definitions. The most straightforward way of doing this might be to look for the "def" keyword in a language like Python. Imagine you were writing this yourself; trying to count the number of function definitions in a piece of code. You might rely on good old fashioned string matching: `for line in program: if "def" in line: function_count +=1` That's a fine start but it gets very messy, *fast*. Luckily, we can rely on something called an [**Abstract Syntax Tree**](glossary.html#ast). In this example, we use the `ast` package for Python and `acorn.js` for JavaScript. *TODO: lobstr for R* These parsers give us easy access to the different components of a program, and help us to count them up easier. The example scripts are located [here](https://github.com/rstudio-education/ds4se/blob/master/data/PythonFiles/ast_example.py) (Python) and [here](https://github.com/rstudio-education/ds4se/blob/master/data/JSFiles/acorn_example.js) (JavaScript).

## Load Your Libraries


```r
library(ggplot2)
library(dplyr)
```

## Read in Your Data


```r
py = read.csv("data/PythonFiles/Stats/python_stats.csv")
colnames(py) <- c("Lines","FunctionDefs")
length(py$Lines)
```

```
## [1] 238
```

```r
#240 python programs
py$Language = "Python"

# JS programs
js = read.csv("data/JSFiles/Stats/js_stats.csv")
colnames(js) <- c("Lines","FunctionDefs")
js$Language= "JavaScript"

data = rbind(py,js)
# some programs must not have been finished yet from the repository of programs?
#data = data[data$Lines>0,]
#data = data[data$FunctionDefs>0,]

# we have a few outliers in our data that skew things
out_js.lines <- boxplot(data$Lines[data$Language=="JavaScript"], plot=FALSE)$out
out_js.funcs <- boxplot(data$FunctionDefs[data$Language=="JavaScript"], plot=FALSE)$out
out_py.lines <- boxplot(data$Lines[data$Language=="Python"], plot=FALSE)$out
out_py.funcs <- boxplot(data$FunctionDefs[data$Language=="Python"], plot=FALSE)$out


data <- data[-which(data$Lines[data$Language=="JavaScript"] %in% out_js.lines),]
data <- data[-which(data$FunctionDefs[data$Language=="JavaScript"] %in% out_js.funcs),]
data <- data[-which(data$Lines[data$Language=="Python"] %in% out_py.lines),]
data <- data[-which(data$FunctionDefs[data$Language=="Python"] %in% out_py.funcs),]
```


## Don't Compare Apples to Oranges
You may notice when we try to compare the languages, that it's very hard to see what's going on with the Python programs as opposed to the JavaScript programs. That's because we have entirely different scales; The JavaScript AST traces all of the callback functions, resulting in a mean of 55 functions per program, while Python has 3 functions per program. It could also be that our sample is simply not as comparable as we thought. The JavaScript files are *significantly* longer than the Python programs (*JS mean lines = 2646, Python mean lines = 69*). Not only are they difficult to visualize side-by-side, it is an actual statistical issue if we were to compare them! Our samples are just too different. But we are not entirely out of luck. We get to use [**normalization**](glossary.html#normalize) to make our apples (JS) and oranges (Python) more comparable. 


```r
plt = ggplot(data,aes(Lines,FunctionDefs))+
  facet_wrap(~Language)+
  geom_point(aes(colour=Language),size=1, show.legend=FALSE)+
  #geom_vline(xintercept=mean(data$Lines),colour="red",linetype="dashed")+
  #geom_hline(yintercept=mean(data$FunctionDefs),colour="red",linetype="dashed")+
  ggtitle("Lines v. Function Definitions")+
  xlab("Number of Lines (raw)")+
  ylab("Number of Function Definitions (raw)")+
  
  theme_bw()
  
plt
```



\begin{center}\includegraphics{lines_and_functions_files/figure-latex/unnamed-chunk-3-1} \end{center}

```r
cor(data$Lines[data$Language=="JavaScript"],data$FunctionDefs[data$Language=="JavaScript"])
```

```
## [1] 0.0218202
```

```r
cor(data$Lines[data$Language=="Python"],data$FunctionDefs[data$Language=="Python"])
```

```
## [1] 0.5789447
```

```r
#take a look at the scales between the two languages
data %>%
  group_by(Language) %>%
  summarise(mean(Lines),mean(FunctionDefs), sd(Lines),sd(FunctionDefs))
```

```
## # A tibble: 2 x 5
##   Language   `mean(Lines)` `mean(FunctionDef~ `sd(Lines)` `sd(FunctionDefs~
##   <chr>              <dbl>              <dbl>       <dbl>             <dbl>
## 1 JavaScript          62.1              0.476        63.7             0.732
## 2 Python              50.7              2.44         29.6             1.83
```




```r
#we need to normalize in order to more fairly compare
data = data %>% 
  group_by(Language) %>%
  mutate(Lines.normed = scale(Lines)) %>%
  mutate(FunctionDefs.normed = scale(FunctionDefs))
```


```r
plt = ggplot(data,aes(Lines.normed,FunctionDefs.normed))+
  facet_wrap(~Language)+
  geom_point(aes(colour=Language),size=1, show.legend=FALSE)+
  #geom_vline(xintercept=mean(data$Lines),colour="red",linetype="dashed")+
  #geom_hline(yintercept=mean(data$FunctionDefs),colour="red",linetype="dashed")+
  ggtitle("Scaled Lines v. Function Definitions")+
  xlab("Number of Lines (z score)")+
  ylab("Number of Function Definitions (z score)")+
  
  theme_bw()
  
plt
```



\begin{center}\includegraphics{lines_and_functions_files/figure-latex/unnamed-chunk-5-1} \end{center}

## Sample Bias

You may have some questions about the sample we used. With any problem, we need to think about where our data came from (not just questions that we can ask *about* the data). Problems start in our sample, not in our analysis. This is an important concept to keep in mind when presented with *any* statistic. Is the sample representative? Where did the data come from? Is publicly available data different from what you could obtain from a private source? Is the sample from one group comparable with the sample from the other? In this case, I've tried to collect programs that are roughly the same content; introductory Computer Science programs and data structures. Whatever I find may not generalize to the languages as a whole; perhaps in writing out data structures the code is for educational purposes, and looks different from code in practice. Whatever we conclude on this sample might not be representative on a different sample. Not to get too philosophical, but any statistics that we perform are really an exercise in: 

> [*"How do we know what we know?"*](glossary.html#epistemology)


```r
cor(data$FunctionDefs[data$Language=="JavaScript"],data$Lines[data$Language=="JavaScript"])
```

```
## [1] 0.0218202
```

## Normality Matters
We are all about finding differences, but comparing those differences is a delicate business. We learned that we can't compare Apples to Oranges, so we **normalized** our data. Sometimes, that's enough to be able to draw direct comparisons. Once we are on the same scale, we can go ahead and use statistical tests to compare groups. But there's something else that matters, and that's whether our data can be represented [**parametrically**](glossary.html#parametrictest) or not. Don't worry, it's not as scary as it sounds. **Parametric** means that the data distribution can be represented using [*parameters*](glossary.html#parameter), like mean and standard deviation. We learned that the normal distribution can be expressed in terms of mean and standard deviation, and any data that falls [**normally**](glossary.html#normaldistribution) is parametric. But lots of data will *not* fall under a normal distribution. Never fear, the weirdos can be compared too. We use something called [**nonparametric testing**](glossary.html#nonparametrictest). Yep, you guessed it. These tests don't represent the data with parameters. They tend to use [**ranks**](glossary.html#rank) instead. For now, just know that in order to compare two samples, we need to make sure we know which tests to use given the normality of those samples.




```r
# here we can take a look at the distributions of our normalized (z-scores) data
plt = ggplot(data, aes(FunctionDefs.normed,fill=Language))+
  geom_histogram(show.legend=FALSE)+
  #geom_density()+
  facet_wrap(~Language)+
  theme_bw()
plt
```



\begin{center}\includegraphics{lines_and_functions_files/figure-latex/unnamed-chunk-7-1} \end{center}

```r
plt = ggplot(data, aes(Lines.normed,fill=Language))+
  geom_histogram(show.legend=FALSE)+
  #geom_density()+
  facet_wrap(~Language)+
  theme_bw()
plt
```



\begin{center}\includegraphics{lines_and_functions_files/figure-latex/unnamed-chunk-7-2} \end{center}

it doesn't look normal, and that's pretty obvious. But for less obvious deviation from normality, we use a [**Shapiro-Wilk Test**](glossary.html#shapirowilktest). The test is beginning with the hypothesis that our distribution and a normal distribution **are different**. The test will give back a *W* score and a [**p-value**](glossary.html#pvalue). When *W* is small, it is likely that our distribution is *different from normal*, with probability *p*. If the *p-value* is less than .05, we tend to conclude that our distribution is **not** normal.

<center>![](static/pval.jpeg)</center><br>


```r
# Python number of function definitions
shapiro.test(data$FunctionDefs[data$Language=="Python"])
```

```
## 
## 	Shapiro-Wilk normality test
## 
## data:  data$FunctionDefs[data$Language == "Python"]
## W = 0.87633, p-value = 2.871e-11
```

```r
# JavaScript number of function definitions
shapiro.test(data$FunctionDefs[data$Language=="JavaScript"])
```

```
## 
## 	Shapiro-Wilk normality test
## 
## data:  data$FunctionDefs[data$Language == "JavaScript"]
## W = 0.64367, p-value < 2.2e-16
```

```r
# Python number of lines
shapiro.test(data$Lines[data$Language=="Python"])
```

```
## 
## 	Shapiro-Wilk normality test
## 
## data:  data$Lines[data$Language == "Python"]
## W = 0.92613, p-value = 3.962e-08
```

```r
# JavaScript number of lines
shapiro.test(data$Lines[data$Language=="JavaScript"])
```

```
## 
## 	Shapiro-Wilk normality test
## 
## data:  data$Lines[data$Language == "JavaScript"]
## W = 0.75725, p-value < 2.2e-16
```

## Does Comparing Averages Make Sense?
### NonParametric Tests

## How to Compare Spreads
### Clustering

## What Have We Learned?
## Next Steps and Other Questions


[analyzing-software-data]: https://www.worldcat.org/title/art-and-science-of-analyzing-software-data-analysis-patterns/oclc/1062303882
[analyze-this-paper]: https://www.microsoft.com/en-us/research/publication/analyze-this-145-questions-for-data-scientists-in-software-engineering/
[coop-software-dev]: http://faculty.washington.edu/ajko/books/cooperative-software-development/index.html
[eseur]: http://www.knosof.co.uk/ESEUR/
[making-software]: https://www.worldcat.org/title/making-software-what-really-works-and-why-we-believe-it/oclc/700636023
[perspectives-ds-se]: https://www.worldcat.org/title/perspectives-on-data-science-for-software-engineering/oclc/1023264016
[sharing-data-models]: https://www.worldcat.org/title/sharing-data-and-models-in-software-engineering/oclc/906700665
