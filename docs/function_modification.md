# Function Modification



Modification and Developer Metrics at the Function Level:
Metrics for the Study of the Evolution of a Software Project

https://github.com/Derek-Jones/ESEUR-code-data/blob/master/evolution/functions/num-func-mods.R


```r
#
# num-func-mods.R, 22 Dec 15
#
# Data from:
# Modification and developer metrics at the function level: Metrics for the study of the evolution of a software project
#
# Example from:
# Empirical Software Engineering using R
# Derek M. Jones

source("data/ESEUR_config.r") # FIXME

funcs=read.csv(paste0(ESEUR_dir, "data/ev_funcmod.tsv.xz"), as.is=TRUE, sep="\t")

revdate=read.csv(paste0(ESEUR_dir, "data/ev_rev_date.csv.xz"), as.is=TRUE)
#revdate=rbind(revdate, c(NA, NA))

revdate$date_time=as.POSIXct(revdate$date_time, format="%Y-%m-%d %H:%M:%S")

# Assign date-time for a given revid
funcs$revdate=revdate$date_time[funcs$revid]
funcs$prevdate=revdate$date_time[funcs$revprev]

time_between=funcs$revdate-funcs$prevdate

q=density(as.numeric(time_between)/(60*60), adjust=0.5, na.rm=TRUE)
plot(q, log="y", col=point_col,
	main="",
	xlab="Hours", ylab="Modification density\n",
	xlim=c(2, 20000), ylim=c(1e-8,1e-2))
```

```
## Warning in xy.coords(x, y, xlabel, ylabel, log): 69 y values <= 0 omitted
## from logarithmic plot
```

![](function_modification_files/figure-latex/unnamed-chunk-1-1.pdf)<!-- --> 

```r
#revdate=read.csv(paste0(ESEUR_dir, "evolution/functions/ev_rev_date.csv.xz"), as.is=TRUE)

# Assign date-time for a given revid
#funcs$date=revdate$date_time[funcs$revid %in% revdate$revid]


count_mods=function(df)
{
# Only count additions and modifications
df=subset(df, typemod != "D")
num_mods=nrow(df)
num_authors=length(unique(df$author))

return(cbind(num_mods, num_authors))
}


mod_count=ddply(funcs, .(filename, func_name), count_mods)
total_mods=ddply(mod_count, .(num_mods, num_authors), nrow)


plot_mods=function(X)
{
t=subset(total_mods, num_authors == X)
lines(t$num_mods, t$V1, col=pal_col[X])
}


xbounds=c(1, 15)
ybounds=c(1, max(total_mods$V1))

plot(1, 1, type="n", log="y",
	xlab="Modifications", ylab="Functions\n",
	xlim=xbounds, ylim=ybounds)
pal_col=rainbow(5)
dummy=sapply(1:5, plot_mods)

legend(x="topright",legend=c("1 author", "2 authors", "3 authors", "4 authors", "5 authors"),
				bty="n", fill=pal_col, cex=1.3)


ma_mod=glm(V1 ~ I(log(num_authors)^0.4)*num_mods,
		data=total_mods, family=poisson)

pred1=predict(ma_mod, data.frame(num_authors=1, num_mods=1:15),
		type="response")
points(pred1, col=pal_col[1])
pred3=predict(ma_mod, data.frame(num_authors=3, num_mods=1:15),
		type="response")
points(pred3, col=pal_col[3])
pred5=predict(ma_mod, data.frame(num_authors=5, num_mods=1:15),
		type="response")
points(pred5, col=pal_col[5])
```

![](function_modification_files/figure-latex/unnamed-chunk-1-2.pdf)<!-- --> 

```r
# a1=subset(total_mods, num_authors == 3)
# ma_mod=nls(V1 ~ a*exp(c*num_mods),
# 		data=a1, trace=TRUE,
# 		start=list(a=3000, c=-0.3))
```



```r
#
# author-mod-func.R, 25 Mar 18
#
# Data from:
# Modification and developer metrics at the function level: Metrics for the study of the evolution of a software project
# Gregorio Robles and Israel Herraiz and Daniel M. Germ{\'a}n and Daniel Izquierdo-Cort{\'a}zar
#
# Example from:
# Empirical Software Engineering using R
# Derek M. Jones



# The igraph package (which might be loaded when building the book)
# contains functions found in gnm.  The treemap package might also have
# been loaded, and its 'load' of igraph cannot be undone without first
# unloading treemap!
unloadNamespace("treemap")
unloadNamespace("igraph")

library("gnm")
library("plyr")

pal_col=rainbow(3)
plot_layout(2, 1)




# Investigate how many times files might be moved
merge_move=function(df)
{
deleted=subset(df, typemod == "D")
added=subset(df, typemod == "A")

return (cbind(nrow(deleted), nrow(added)))
}
# all_moves=ddply(funcs, .(func_name), merge_move)
# table(all_moves[, 2:3])
# Number of functions with the same name that are
# deleted (row) and added (column)
#       2
# 1        0    1    2    3    4    5    6    7
#   0      0 9152  286  111   31   12    1    2
#   1    107 9659 1075   74   17    9    5    1
#   2      3  474 1714  140   20   18    9    3
#   3      0    9   88  322   67   15    9    1
#   4      0    0   10   10   80   18    8    2
#   5      0    0    0   26    6   33    7    1
#
# 1 delete + 2 add could be a move: 1075 of them...


# Count number of changes and authors for a given function
count_mods=function(df)
{
# Only count additions and modifications
df=subset(df, typemod != "D")
num_mods=nrow(df)
num_authors=length(unique(df$author))

return(cbind(num_mods, num_authors))
}


# Two files may have the same name
mod_count=ddply(funcs, .(filename, func_name), count_mods)
total_mods=ddply(mod_count, .(num_mods, num_authors), nrow)


all_mods=ddply(total_mods, .(num_mods), function(df) sum(df$V1))

plot(all_mods$num_mods, all_mods$V1, log="y", col=point_col,
	xlim=c(0, 50),
	xlab="Modifications", ylab="Functions\n")

# a_mod=glm(V1 ~ num_mods+I(num_mods^2)+I(num_mods^3), data=all_mods[-1, ], family=poisson)
# a_mod=glm(V1 ~ num_mods, data=all_mods[-1, ], family=poisson)
# lines(1:50, exp(predict(a_mod, newdata=data.frame(num_mods=1:50), type="link")), col=pal_col[3])

a2_mod=gnm(V1 ~ instances(Mult(1, Exp(num_mods)), 2)-1,
                data=all_mods[-1,], verbose=FALSE,
                start=c(20000.0, -0.6, 300.0, -0.1),
                family=poisson(link="identity"))
exp_coef=as.numeric(coef(a2_mod))

lines(exp_coef[1]*exp(exp_coef[2]*all_mods$num_mods), col=pal_col[2])
lines(exp_coef[3]*exp(exp_coef[4]*all_mods$num_mods), col=pal_col[3])
t=predict(a2_mod)
lines(t, col=pal_col[1])

s1=exp(a2_mod$coef[2])
D_I_1=(1-s1)^2/s1

s2=exp(a2_mod$coef[4])
D_I_2=(1-s2)^2/s2

# c(s1, D_I_1, s2, D_I_2)


author_mods=ddply(total_mods, .(num_authors), function(df) sum(df$V1))

plot(author_mods$num_authors, author_mods$V1, log="y", col=point_col,
	xlab="Authors", ylab="Functions\n")

# a1_authors=glm(V1 ~ num_authors, data=author_mods[-1, ], family=poisson)
# lines(1:15, exp(predict(a1_authors, newdata=data.frame(num_authors=1:15), type="link")), col=pal_col[1])
# a2_authors=glm(V1 ~ num_authors+I(num_authors^2), data=author_mods[-1, ], family=poisson)
# lines(1:20, exp(predict(a2_authors, newdata=data.frame(num_authors=1:20), type="link")), col=pal_col[2])

a2_mod=gnm(V1 ~ instances(Mult(1, Exp(num_authors)), 2)-1,
                data=author_mods[-1,], verbose=FALSE,
                start=c(20000.0, -0.6, 300.0, -0.1),
                family=poisson(link="identity"))
exp_coef=as.numeric(coef(a2_mod))

lines(exp_coef[1]*exp(exp_coef[2]*author_mods$num_authors), col=pal_col[2])
lines(exp_coef[3]*exp(exp_coef[4]*author_mods$num_authors), col=pal_col[3])
t=predict(a2_mod)
lines(t, col=pal_col[1])
```

![](function_modification_files/figure-latex/unnamed-chunk-2-1.pdf)<!-- --> 

```r
s1=exp(a2_mod$coef[2])
D_I_1=(1-s1)^2/s1

s2=exp(a2_mod$coef[4])
D_I_2=(1-s2)^2/s2

# c(s1, D_I_1, s2, D_I_2)
```

## Figure 4.50: Number of functions 
(in Evolution; the point at zero are incorrect counts) modified a given number of
times (upper) or modified by a given number of different
people (lower); red line is a bi-exponential fit, green/blue
lines are the individual exponentials


[analyzing-software-data]: https://www.worldcat.org/title/art-and-science-of-analyzing-software-data-analysis-patterns/oclc/1062303882
[analyze-this-paper]: https://www.microsoft.com/en-us/research/publication/analyze-this-145-questions-for-data-scientists-in-software-engineering/
[coop-software-dev]: http://faculty.washington.edu/ajko/books/cooperative-software-development/index.html
[eseur]: http://www.knosof.co.uk/ESEUR/
[making-software]: https://www.worldcat.org/title/making-software-what-really-works-and-why-we-believe-it/oclc/700636023
[perspectives-ds-se]: https://www.worldcat.org/title/perspectives-on-data-science-for-software-engineering/oclc/1023264016
[sharing-data-models]: https://www.worldcat.org/title/sharing-data-and-models-in-software-engineering/oclc/906700665
