# 12306-Railway
<<<<<<< Updated upstream
>微服务铁路在线系统
=======
>本项目为微服务铁路在线系统
## 项目特点
1. 数据分表，冷热数据分离，减少锁表次数。
2. Redis支持旁路缓存，实现与数据库最终一致性。Redis + lua 防止车票超卖
3. Redis + Token 作幂等，请求前需要获取令牌。
4. Spring Cloud Gateway 作网关，实现请求转发和请求过滤，维护IP黑名单，屏蔽违法请求
5. RocketMq 进行流量削峰
>>>>>>> Stashed changes
