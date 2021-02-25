package com.nowcoder.community.config;

import com.nowcoder.community.quartz.AlphaJob;
import com.nowcoder.community.quartz.PostScoreRefreshJob;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;


// 只要启动服务，配置文件默认就会被加载，Quart就会根据这两个配置往数据库里插入数据，Quart的底层的调度器Scheduler会根据数据库中的数据去进行调度
// 配置 -> 数据库 -> 调用

/** quartz 数据库表各个表名的信息
 1.qrtz_blob_triggers :       以Blob 类型存储的触发器。
 2.qrtz_calendars：           存放日历信息， quartz可配置一个日历来指定一个时间范围。
 3.qrtz_cron_triggers：       存放cron类型的触发器。
 4.qrtz_fired_triggers：      存放已触发的触发器。
 5.qrtz_job_details：         存放一个jobDetail信息。
 6.qrtz_job_listeners：       job**监听器**。
 7.qrtz_locks：               存储程序的悲观锁的信息(假如使用了悲观锁)。
 8.qrtz_paused_trigger_graps：存放暂停掉的触发器。
 9.qrtz_scheduler_state：     调度器状态。
 10.qrtz_simple_triggers：    简单触发器的信息。
 11.qrtz_triggers：           触发器的基本信息。
 */
@Configuration
public class QuartzConfig {


    // FactoryBean可简化Bean的实例化过程:
    // 1.通过FactoryBean封装Bean的实例化过程.
    // 2.将FactoryBean装配到Spring容器里.
    // 3.将FactoryBean注入给其他的Bean.
    // 4.该Bean得到的是FactoryBean所管理的对象实例.

    // 配置JobDetail
//     @Bean
    public JobDetailFactoryBean alphaJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(AlphaJob.class);
        factoryBean.setName("alphaJob");
        factoryBean.setGroup("alphaJobGroup");
        factoryBean.setDurability(true);   // 是否长期的执行
        factoryBean.setRequestsRecovery(true);
        return factoryBean;
    }

    // 配置Trigger(SimpleTriggerFactoryBean  简单的模式--每10分钟执行一次, CronTriggerFactoryBean  复杂的模式--每周的最后十分钟执行一次)
    //@Bean
    public SimpleTriggerFactoryBean alphaTrigger(JobDetail alphaJobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(alphaJobDetail);
        factoryBean.setName("alphaTrigger");
        factoryBean.setGroup("alphaTriggerGroup");
        factoryBean.setRepeatInterval(3000);
        factoryBean.setJobDataMap(new JobDataMap());
        return factoryBean;
    }

    // 刷新帖子分数任务
    @Bean
    public JobDetailFactoryBean postScoreRefreshJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(PostScoreRefreshJob.class);
        factoryBean.setName("postScoreRefreshJob");
        factoryBean.setGroup("communityJobGroup");
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(true);
        return factoryBean;
    }

    @Bean
    public SimpleTriggerFactoryBean postScoreRefreshTrigger(JobDetail postScoreRefreshJobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(postScoreRefreshJobDetail);
        factoryBean.setName("postScoreRefreshTrigger");
        factoryBean.setGroup("communityTriggerGroup");
        factoryBean.setRepeatInterval(1000 * 60 * 5);  //5分钟执行一次定时任务
        factoryBean.setJobDataMap(new JobDataMap());
        return factoryBean;
    }

}
