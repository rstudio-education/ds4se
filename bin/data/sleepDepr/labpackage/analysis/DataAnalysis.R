library("xlsx")
library("lawstat")
library("metafor")


descriptive <- function(distrName, m1){
a <- summary(m1)
std <- round (sd (m1),3)
cat("\n*********", distrName, "*********\n")
cat("MIN: ", a[1], "\n")
cat("MAX:", a[6], "\n")
cat("MEDIAN: ", a[3], "\n")
cat("MEAN:", a[4], "\n")
cat("STD:", std, "\n")
cat("*********************************\n")

# cat("Min & Max & Median & Mean & StD\n")
# cat(round(a[1], 3), "&", round(a[6],3), "&", round(a[3],3), "&", round(a[4],3), "&", std, "\\\\ \n")
# cat("&  Mean & StD\n")
# cat( distrName, "&", round(a[4], 3), "&", round(std,3), "\\\\ \n")

cat(distrName, "\n")
cat("& Min & Max & Mean & Q1 & Median & Q3 & St. Dev. & \n")
cat("&", round(a[1],3), "&", round(a[6],3), "&", round(a[4],3), "&", round(a[2],3), "&",
    round(a[3],3), "&", round(a[5],3), "&", std, "&", "\\\\ \n")

}

descriptiveTable <- function (metricName, distr1, distr2){
  d1 <- summary(distr1)
  d2 <- summary(distr2)
  std_d1 <- round(sd (distr1),3)
  std_d2 <- round(sd (distr2),3)
  cat(metricName, "&", round(d1[4],3), "&", std_d1, "&",  round(d1[1],3), "&",  round(d1[3],3), "&",  round(d1[1],3), "&",  round(d1[6],3),
        "&", round(d2[4],3), "&", std_d2, "&",  round(d2[1],3), "&",  round(d2[3],3), "&",  round(d2[1],3), "&",  round(d2[6],3))

}


DescriptiveStat <- function (varName, distr1, distr2){
  aDistr1 <- summary(distr1)
  stdDistr1 <- round (sd (distr1),3)
  aDistr2 <- summary(distr2)
  stdDistr2 <- round (sd (distr2),3)
  cat("&", varName, "&", round(aDistr1[4],3), "&", stdDistr1, "&&",  round(aDistr2[4],3), "&",  stdDistr2,"\\\\ \n")
}

ConfidInt <- function(x){
  s2 <- var(x)
  mx <- mean(x)
  n <- length(x)
  a <- qt(0.975, df = n - 1) * sqrt(s2 / n)
  l.inf <- mx - a
  l.sup <- mx + a
  cat("(",l.inf," - ",l.sup,")\n")
}

#  Cohen's d - effect size
# negligible for |d|<0.2,small for 0.2???|d|<0.5, medium for 0.5???|d|< 0.8, and large for |d| ??? 0.8

dCohen <- function(m1, m2, samp="independent")
{
M1 <- mean(m1)
M2 <- mean(m2)
if (samp == "independent"){
  ### for independent samples (unpaired analyses)
  pstd <- sqrt(((sd(m1)*sd(m1))+(sd(m2)*sd(m2)))/2)
  pstd
}else{
  ### for dependent samples (paired analyses)
  pstd <- sd(m1-m2)
  pstd
#  cat("**paired analyses**")
}
d <- round((M1-M2)/pstd,3)

size = "large"
if (0 < abs(d) & abs(d) < 0.2) size = "negligible"
else
	if (0.2 < abs(d) & abs(d) < 0.5) size = "small"
	else
		if (0.5 < abs(d) & abs(d) < 0.8) size = "medium"

#return (cat(" Cohen's d", size, " (", d, ")\n"))
return(c(cat("Cohen's d ", size, " (", d, ")\n"), size, d))

}



######################
# Cliff Delta - We use non-parametric effect size measure Cliff???s d, which indicates the magnitude of the effect
# size of the treatment on the dependent variable. The effect size is small for 0.147 ??? d < 0.33, medium for 0.33 ??? d <  0.474, and large for d ??? 0.474.
#####################



cliffs.d  <-  function(x,y) {

	r = mean(rowMeans(sign(outer(x, y, FUN="-"))))
	r = round(r,3)
	#cat("Cliff-Deelta val = ", r)

	size = "large"
	if ( 0.147 < abs(r) & abs(r) < 0.33){
		size= "small"
	}else{
		if ( 0.33 <= abs(r) & abs(r) < 0.474){
			size = "medium"
		}
	}

	if (abs(r) < 0.147)
		size = "negligible"
	return(c(cat("\n Cliff Delta ", size, " (", r, ")\n"), size, r))


}



# Statistical power

StatisticalPowerNonParam <- function(distribution1, nameDistribution1, distribution2, nameDistribution2){

#  non-parametric analysis
 M1 <- mean(distribution1)
 M2 <- mean(distribution2)
 sd1 <- sd(distribution1)
 sd2 <- sd(distribution2)
 n1 <- length(distribution1) ### sample size
 n2 <- length(distribution2) ### sample size
 n <- n1 + n2
 pval <- replicate(1000, wilcox.test(rnorm(n1,M1,sd1), rnorm(n2,M2,sd2), paired=FALSE)$p.value)
 power = round(sum(pval < 0.05)/1000,3)
 cat(" Statistical Power", power, "\n")
 cat(" beta-val", 1- power)
 return(power)
}



StatisticalPowerParam <- function(distribution1, nameDistribution1, distribution2, nameDistribution2, direction = "two.sided"){

#
#  parametric analysis
#

sdd <- sd(c(distribution1,distribution2))
delta <- abs(mean(distribution1) - mean(distribution2))
n = max(length(distribution1), length(distribution2))
pow <- power.t.test(n, delta, sdd, sig.level=0.05, power=NULL, type="two.sample", alternative="two.sided")
power = round(pow$power,3)
cat(" Statistical Power", power)
cat(" beta-val", 1- power, "\n")

return(power)
}


TableII <- function(){
  loadAllData(fileName = "piglatin.xlsx")
  SlD <<- SD_Cleaned
  RS <<- NOSD_Cleaned
  SD_Uncleaned <<- SlD
  RS_Uncleaned <<- NOSD_Cleaned

  descriptive("RS-PAAP", RS$PROD)
  descriptive("SD-PAAP", SlD$PROD)
  descriptive("SD-PAAP", SD_Uncleaned$PROD)

  descriptive("RS-episodes", RS$X.Episodes)
  descriptive("SD-episodes", SlD$X.Episodes)
  descriptive("SD-episodes", SD_Uncleaned$X.Episodes)

  descriptive("RS-conformance", RS$X.CONF.)
  descriptive("SD-conformance", SlD$X.CONF.)
  descriptive("SD-conformance", SD_Uncleaned$X.CONF.)
}



ICSE_Analysis <- function(){

  loadAllData(fileName = "piglatin.xlsx")

  #NOT = SD --- Sleep Deprived
  #YES = NS --- Regular Sleep

  NOT <<- SD_Cleaned
  YES <<- NOSD_Cleaned
  NOT_Uncleaned <<- SlD
  YES_Uncleaned <<- NOSD_Cleaned

  #################################################
  # Conformance - Descriptive
  #################################################

  DescriptiveStat("\\%conformance", YES$X.CONF., NOT$X.CONF.)
  DescriptiveStat("\\%conformance - Uncl", YES_Uncleaned$X.CONF., NOT_Uncleaned$X.CONF.)

  #################################################
  # Productivity - Descriptive
  #################################################

  DescriptiveStat("\\#tests ", YES$X.TEST, NOT$X.TEST)
  DescriptiveStat("\\#episodes ", YES$X.Episodes, NOT$X.Episodes)
  DescriptiveStat("PAAP ", YES$PROD, NOT$PROD)


  DescriptiveStat("\\#tests - Uncl", YES_Uncleaned$X.TEST, NOT_Uncleaned$X.TEST)
  DescriptiveStat("\\#episodes - Uncl", YES_Uncleaned$X.Episodes, NOT_Uncleaned$X.Episodes)
  DescriptiveStat("PAAP - Uncl", YES_Uncleaned$PROD, NOT_Uncleaned$PROD)

  #################################################
  # Boxplot - Conformance
  #################################################

  boxPlotsTreatvsContr("NS", YES$X.CONF., "SD", NOT$X.CONF., "%conformance")

  #################################################
  # Boxplot - Productivity
  #################################################

  boxPlotsTreatvsContr("NS", YES$X.TEST, "SD", NOT$X.TEST, "#tests")
  boxPlotsTreatvsContr("NS", YES$X.Episodes, "SD", NOT$X.Episodes, "#episodes")
  boxPlotsTreatvsContr("NS", YES$PROD, "SD", NOT$PROD, "PAAP")


  #################################################
  # YES vs. NOT - Conformance
  #################################################

  Control_vs_Treatment("\n \\%conformance - NOT vs.", NOT$X.CONF., "YES", YES$X.CONF., "less")
  Control_vs_Treatment("\n Uncl \\%conformance - NOT vs.", NOT_Uncleaned$X.CONF., "YES", YES_Uncleaned$X.CONF., "less")

  #################################################
  # YES vs. NOT - Descriptive
  #################################################

  Control_vs_Treatment("\n \\#tests - NOT vs.", NOT$X.TEST, "YES", YES$X.TEST, "less")
  Control_vs_Treatment("\n \\#episodes - NOT vs.", NOT$X.Episodes, "YES", YES$X.Episodes, "less")
  Control_vs_Treatment("\n PAAP - NOT vs.", NOT$PROD, "YES", YES$PROD, "less")



  Control_vs_Treatment("\n Uncl - \\#tests - NOT vs.", NOT_Uncleaned$X.TEST, "YES", YES_Uncleaned$X.TEST, "less")
  Control_vs_Treatment("\n Uncl - \\#episodes - NOT vs.", NOT_Uncleaned$X.Episodes, "YES", YES_Uncleaned$X.Episodes, "less")
  Control_vs_Treatment("\n Uncl - PAAP - NOT vs.", NOT_Uncleaned$PROD, "YES", YES_Uncleaned$PROD, "two.sided")


  #################################################
  # YES vs. NOT - Boxplot - dataset uncleaned
  #################################################

  boxPlotsTreatvsContr("NS", YES$PROD, "SD", NOT$PROD, "PAAP")
  boxPlotsTreatvsContr("NS", YES$X.TEST, "SD", NOT$X.TEST, "#tests")
  boxPlotsTreatvsContr("NS", YES$X.Episodes, "SD", NOT$X.Episodes, "#episodes")
  boxPlotsTreatvsContr("NS", YES$X.CONF., "SD", NOT$X.CONF., "%conformance")

  #################################################
  # YES vs. NOT - Boxplot - dataset uncleaned
  #################################################

  boxPlotsTreatvsContr("NS", YES_Uncleaned$PROD, "SD", NOT_Uncleaned$PROD, "PAAP")
  boxPlotsTreatvsContr("NS", YES_Uncleaned$X.TEST, "SD", NOT_Uncleaned$X.TEST, "#tests")
  boxPlotsTreatvsContr("NS", YES_Uncleaned$X.Episodes, "SD", NOT_Uncleaned$X.Episodes, "#episodes")
  boxPlotsTreatvsContr("NS", YES_Uncleaned$X.CONF., "SD", NOT_Uncleaned$X.CONF., "%conformance")

  #################################################
  # NS vs. SD (cleaned, uncleaned)
  #################################################

  boxPlotsTreatvsDoubleContr("NS", YES$PROD, "SD-Uncleaned", NOT_Uncleaned$PROD,"SD-Cleaned", NOT$PROD, "PAAP")
  boxPlotsTreatvsDoubleContr("NS", YES$X.TEST, "SD-Uncleaned", NOT_Uncleaned$X.TEST,"SD-Cleaned", NOT$X.TEST, "#tests")
  boxPlotsTreatvsDoubleContr("NS", YES$X.Episodes, "SD-Uncleaned", NOT_Uncleaned$X.Episodes,"SD-Cleaned", NOT$X.Episodes, "#episodes")
  boxPlotsTreatvsDoubleContr("NS", YES$X.CONF., "SD-Uncleaned", NOT_Uncleaned$X.CONF.,"SD-Cleaned", NOT$X.CONF., "%conformance")

}



#################################################
# Descriptive Statistics
#################################################

PreliminaryAnalysis <- function(){

  descriptive("SD_Cleaned$X.TUS", SD_Cleaned$X.TUS)
  ConfidInt(SD_Cleaned$X.TUS)
  descriptive("NOSD_Cleaned$X.TUS", NOSD_Cleaned$X.TUS)
  ConfidInt(NOSD_Cleaned$X.TUS)
  boxPlotsTreatvsContr("SD", SD_Cleaned$X.TUS, "NOSD", NOSD_Cleaned$X.TUS, varName =  "#TUS")
  Control_vs_Treatment("\n \\#TUS - SD vs", SD_Cleaned$X.TUS, "NOSD", NOSD_Cleaned$X.TUS, "two.sided")

  descriptive("SD_Cleaned$X.TEST", SD_Cleaned$X.TEST)
  ConfidInt(SD_Cleaned$X.TEST)
  descriptive("NOSD_Cleaned$X.TEST", NOSD_Cleaned$X.TEST)
  ConfidInt(NOSD_Cleaned$X.TEST)
  boxPlotsTreatvsContr("SD", SD_Cleaned$X.TEST, "NOSD", NOSD_Cleaned$X.TEST, varName =  "#TEST")
  Control_vs_Treatment("\n \\#TEST - SD vs", SD_Cleaned$X.TEST, "NOSD", NOSD_Cleaned$X.TEST, "two.sided")


  descriptive("SD_Cleaned$QLTY", SD_Cleaned$QLTY)
  ConfidInt(SD_Cleaned$QLTY)
  descriptive("NOSD_Cleaned$QLTY", NOSD_Cleaned$QLTY)
  ConfidInt(NOSD_Cleaned$QLTY)
  boxPlotsTreatvsContr("SD", SD_Cleaned$QLTY, "NOSD", NOSD_Cleaned$QLTY, varName =  "QLTY")
  Control_vs_Treatment("\n  QLTY - SD vs.", SD_Cleaned$QLTY, "NOSD", NOSD_Cleaned$QLTY, "two.sided")


  descriptive("SD_Cleaned$PROD", SD_Cleaned$PROD)
  ConfidInt(SD_Cleaned$PROD)
  descriptive("NOSD_Cleaned$PROD", NOSD_Cleaned$PROD)
  ConfidInt(NOSD_Cleaned$PROD)
  boxPlotsTreatvsContr("SD", SD_Cleaned$PROD, "NOSD", NOSD_Cleaned$PROD, varName =  "PROD")
  Control_vs_Treatment("\n  PROD - SD vs.", SD_Cleaned$PROD, "NOSD", NOSD_Cleaned$PROD, "two.sided")

  descriptive("SD_Cleaned$X.Episodes", SD_Cleaned$X.Episodes)
  ConfidInt(SD_Cleaned$X.Episodes)
  descriptive("NOSD_Cleaned$X.Episodes", NOSD_Cleaned$X.Episodes)
  ConfidInt(NOSD_Cleaned$X.Episodes)
  boxPlotsTreatvsContr("SD", SD_Cleaned$X.Episodes, "NOSD", NOSD_Cleaned$X.Episodes, varName =  "X.Episodes")
  Control_vs_Treatment("\n  X.Episodes - SD vs.", SD_Cleaned$X.Episodes, "NOSD", NOSD_Cleaned$X.Episodes, "two.sided")

  descriptive("SD_Cleaned$X.CONF.", SD_Cleaned$X.CONF.)
  ConfidInt(SD_Cleaned$X.CONF.)
  descriptive("NOSD_Cleaned$X.CONF.", NOSD_Cleaned$X.CONF.)
  ConfidInt(NOSD_Cleaned$X.CONF.)
  boxPlotsTreatvsContr("SD", SD_Cleaned$X.CONF., "NOSD", NOSD_Cleaned$X.CONF., varName =  "X.CONF.")
  Control_vs_Treatment("\n  X.CONF. - SD vs.", SD_Cleaned$X.CONF., "NOSD", NOSD_Cleaned$X.CONF., "two.sided")

  descriptive("SD_Cleaned$X.REFACTORING", SD_Cleaned$X.REFACTORING)
  ConfidInt(SD_Cleaned$X.REFACTORING)
  descriptive("NOSD_Cleaned$X.REFACTORING", NOSD_Cleaned$X.REFACTORING)
  ConfidInt(NOSD_Cleaned$X.REFACTORING)
  boxPlotsTreatvsContr("SD", SD_Cleaned$X.REFACTORING, "NOSD", NOSD_Cleaned$X.REFACTORING, varName =  "X.REFACTORING")
  Control_vs_Treatment("\n  X.REFACTORING - SD vs.", SD_Cleaned$X.REFACTORING, "NOSD", NOSD_Cleaned$X.REFACTORING, "two.sided")


  descriptive("SD_Cleaned$X.Refactoring", SD_Cleaned$X.Refactoring)
  ConfidInt(SD_Cleaned$X.Refactoring)
  descriptive("NOSD_Cleaned$X.Refactoring", NOSD_Cleaned$X.Refactoring)
  ConfidInt(NOSD_Cleaned$X.Refactoring)
  boxPlotsTreatvsContr("SD", SD_Cleaned$X.Refactoring, "NOSD", NOSD_Cleaned$X.Refactoring, varName =  "X.Refactoring")
  Control_vs_Treatment("\n  X.Refactoring - SD vs.", SD_Cleaned$X.Refactoring, "NOSD", NOSD_Cleaned$X.Refactoring, "two.sided")

  # Su duration c'?? qualcosa di strano - basta guardare le distribusioni. Ci sono degli 0 e per SD i valori sono pi?? bassi in media

  descriptive("SD_Cleaned$duration.avg ", SD_Cleaned$duration.avg)
  ConfidInt(SD_Cleaned$duration.avg)
  descriptive("NOSD_Cleaned$duration.avg", NOSD_Cleaned$duration.avg)
  ConfidInt(NOSD_Cleaned$duration.avg)
  boxPlotsTreatvsContr("SD", SD_Cleaned$duration.avg, "NOSD", NOSD_Cleaned$duration.avg, varName =  "duration.avg")
  Control_vs_Treatment("\n  duration.avg - SD vs.", SD_Cleaned$duration.avg, "NOSD", NOSD_Cleaned$duration.avg, "two.sided")


  descriptive("SD_Cleaned$duration.sd ", SD_Cleaned$duration.sd)
  ConfidInt(SD_Cleaned$duration.sd)
  descriptive("NOSD_Cleaned$duration.sd", NOSD_Cleaned$duration.sd)
  ConfidInt(NOSD_Cleaned$duration.sd)
  boxPlotsTreatvsContr("SD", SD_Cleaned$duration.sd, "NOSD", NOSD_Cleaned$duration.sd, varName =  "duration.sd")
  Control_vs_Treatment("\n  duration.sd - SD vs.", SD_Cleaned$duration.sd, "NOSD", NOSD_Cleaned$duration.sd, "two.sided")

  descriptive("SD_Cleaned$duration.tot ", SD_Cleaned$duration.tot)
  ConfidInt(SD_Cleaned$duration.tot)
  descriptive("NOSD_Cleaned$duration.tot", NOSD_Cleaned$duration.tot)
  ConfidInt(NOSD_Cleaned$duration.tot)
  boxPlotsTreatvsContr("SD", SD_Cleaned$duration.tot, "NOSD", NOSD_Cleaned$duration.tot, varName =  "duration.tot")
  Control_vs_Treatment("\n  duration.tot - SD vs.", SD_Cleaned$duration.tot, "NOSD", NOSD_Cleaned$duration.tot, "two.sided")



}


subTable <- function(data, nameCol, val){
  return (data[which(data[nameCol] == val),])

}

# loadAllData
loadAllData <- function(fileName = "../data/piglatin.xlsx"){

  Exp <<-   read_xlsx(fileName)
  Exp_Cleaned <<- subTable(Exp, "PVT-remove", "NO")
  SlD <<- subTable(Exp, "METHOD", "SD")
  NOSD <<- subTable(Exp, "METHOD", "RS")
  SD_Cleaned <<- subTable(SlD, "PVT-remove", "NO")
  NOSD_Cleaned <<- subTable(NOSD, "PVT-remove", "NO")
}


Control_vs_Treatment <- function(nameTreatment, treatment, nameControl, control, direction = "two.sided") {

  sTreatment <- shapiro.test(treatment)
  sControl <- shapiro.test(control)

  if (sTreatment$p.value > 0.05 && sControl$p.value > 0.05) {
    cat(" Parametric analyses allowed \n", nameTreatment, "is normal ", round(sTreatment$p.value,4), " \n",
        nameControl, " is normal ", round(sControl$p.value,4), "\n")

    # 	  #### The distributions are both normal parametric analyses can be computed.
    # 		# t-test
    a <- t.test(treatment, control, alternative = direction, paired = FALSE, exact = FALSE, correct = TRUE)
    a = round(a$p.value,3)
    # 		# ANOVA
    # 	  a <-anova(lm(dataExp$X.TUS ~ dataExp$Method))
    # 	  a = round(as.numeric(a$`Pr(>F)`[1]),3)
    effectSize <- dCohen(treatment, control, "independent")
    s <- StatisticalPowerParam(treatment, nameTreatment, control, nameControl, direction)

  }else {
    cat(" Non-parametric analyses \n", nameTreatment, ": ", round(sTreatment$p.value,4), " \n", nameControl,
        ": ", round(sControl$p.value,4))

    a <- wilcox.test(treatment, control, alternative = direction, paired = FALSE, exact = FALSE,
                     correct = TRUE)
    #a = a$p.value
    a = round(as.numeric(a[3]),3)
    effectSize <- cliffs.d(treatment,control)
    s = StatisticalPowerNonParam(treatment, nameTreatment, control, nameControl)
  }

  #descriptive stats for treatment
  t = summary(treatment)

  #descriptive stats for control
  c = summary(control)
  impr = ((t[4]-c[4])/c[4])*100
  impr = round (impr,3)
  cat("\n Mean improvement", impr,"\\%")

  cat("\n Statistical test p-value (i.e., Hnx) ", a, "\n")
  if (a < 0.05){
    cat("***  STATISTICALLY SIGNIFICANT DIFFERENCE ***\n")
  }
  cat("&", a, "&", effectSize, "&", impr, "&", s )

}


#################################################
# Normaly plots
#################################################

NormalProbPlotsFMeasure <- function(){


  qqnorm(NOUM$FMeasure, main = "Fmeasure- NOUM", xlab = "", ylab = "")
  qqline(NOUM$FMeasure)

  qqnorm(UM$FMeasure, main = "Fmeasure- NOUM", xlab = "", ylab = "")
  qqline(UM$FMeasure)

  close.screen( all = TRUE )

}



boxPlotsTreatvsContr <-function (nameTreatment = "SD", treatment, nameControl="NOSD", control, varName="#TUS"){
  lab <- c(varName, "- ", nameTreatment, " vs. ", nameControl)
  lab <- paste(lab, collapse=" ")
  l = t(c(1,1));
  layout(l)
  left.margin = 1;
  right.margin = 1;
  yx = "s"
  boxplot(treatment, control,
          names=c(nameTreatment, nameControl),
          horizontal=FALSE,
          ylab=lab,
          ylim = c(0, max(treatment, control)),
          outline = TRUE, col=c("white", "red"), yaxt=yx,cex.axis=1.2,cex.lab=1.2,plot=TRUE)


}

boxPlotsTreatvsDoubleContr <-function (nameControl = "NS", control, nameTreatment1="NS-Uncleaned", treatment1, nameTreatment2="NS-Cleaned", treatment2, varName="PAAP"){
  lab <- c(varName)
  lab <- paste(lab, collapse=" ")
  l = t(c(1,1,1));
  layout(l)
  left.margin = 1;
  right.margin = 1;
  yx = "s"
  boxplot(control, treatment1, treatment2,
          names=c(nameControl,nameTreatment1, nameTreatment2),
          horizontal=FALSE,
          ylab=lab,
          ylim = c(0, max(control, treatment1, treatment2)),
          outline = TRUE, col=c("white", "red", "green"), yaxt=yx,cex.axis=1.2,cex.lab=1.2,plot=TRUE)


}



######################################
# Post experiment survey analyses
######################################

BoxplotPostQ <- function(){

  d <<- read.xlsx("QuestionarioPostCompr.xlsx","Responses")
  GroupA <- subTable(d, "GroupCleaned", "A")
  GroupB <- subTable(d, "GroupCleaned", "B")
  GroupAB <- (d[which(d["GroupCleaned"] != "R"),])
  postscript(file="BoxPostQ.eps", width=15,height=5,horizontal=FALSE)


  left.margin = 0;
  right.margin = 0;
  yx = "s"

  box <- boxplot(GroupAB$Q1, GroupAB$Q2, GroupAB$Q3, GroupAB$Q4, GroupAB$Q5, GroupAB$Q6, GroupAB$Q7, GroupAB$Q8,GroupAB$Q9,GroupAB$Q10,GroupAB$Q11,GroupAB$Q12,
        names=c("Q1", "Q2", "Q3", "Q4", "Q5", "Q6", "Q7", "Q8", "Q9", "Q10", "Q11", "Q12"),
        add=FALSE,
        horizontal=FALSE,
        ylim = c(1, 5),
        outline = TRUE, xlab="Questions", yaxt=yx,cex.axis=1,cex.lab=1)

  dev.off()
}
