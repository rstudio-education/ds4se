library(bigrquery)
library(ggplot2)
project <- "gitstats"



################################ playing with bigrquery ################
##################### Commits ~ language on Independence Day (USA vs Canada)




data_sql <- "select  u.country_code as country, p.language as language, date(c.created_at) as day, count(c.id) as num_commits
from [ghtorrent-bq.ght.project_commits] pc join
     (SELECT id, author_id, created_at FROM [ghtorrent-bq.ght.commits] WHERE
     date(created_at) between date('2016-05-27')
                          and date('2016-08-08') )c on pc.commit_id = c.id join
     (SELECT id, language
     FROM [ghtorrent-bq.ght.projects] WHERE language = 'Python' or language = 'Java') p on p.id = pc.project_id join
     (SELECT login, location, id, country_code,
     FROM [ghtorrent-bq.ght.users]
     WHERE country_code = 'us' or country_code = 'ca') u on c.author_id = u.id,
group by  language, country, day
order by num_commits desc;"

data  <- query_exec(data_sql, project = project, useLegacySql = FALSE)
data$weekday <-weekdays(as.Date(data$day))
summary <- data %>% 
  group_by(country, day) %>%
  summarise(mean = mean(num_commits))

summary$day <- gsub("2016-","",summary$day)
plt = ggplot(summary, aes(day,mean, color=country))+
  geom_point()+ geom_line(aes(group=country))+
  theme_bw()+
  ggtitle("Mean commits on GitHub")+
  ylab("Number of commits")+
  theme(axis.text.x = element_text(angle = 45, hjust = 1))
plt
ggsave("canada.png",plt)

summary <- data[data$day=="2015-06-27",] %>% 
  group_by(country) %>%
  summarise(avg = mean(num_commits),sd=sd(num_commits))

#### NOTHING IS NORMAL ####
shapiro.test(data$num_commits[data$language=="Python" & data$country=="USA"])
hist(data$num_commits[data$language=="Python" & data$country=="USA"])

shapiro.test(data$num_commits[data$language=="Java" & data$country=="USA"])
hist(data$num_commits[data$language=="Java" & data$country=="USA"])

shapiro.test(data$num_commits[data$language=="Python" & data$country=="Canada"])
hist(data$num_commits[data$language=="Python" & data$country=="Canada"])

shapiro.test(data$num_commits[data$language=="Java" & data$country=="Canada"])
hist(data$num_commits[data$language=="Java" & data$country=="Canada"])

plt = ggplot(data, aes( language,num_commits,fill=language))+
  stat_summary_bin(aes(y = num_commits), fun.y = "mean", geom = "bar")+
  stat_summary(geom = "pointrange", fun.data = mean_se)+
  facet_wrap(~country)+
  theme_bw()
plt

data$Date="Canada Day"
data$Date[data$country=="USA"]="Independence Day"


