package it.gdgpt.scheduled;

import io.quarkus.arc.Arc;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.quartz.*;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

//@ApplicationScoped
//public class JobBean {
//
//    @Inject
//    Scheduler quartz;
//
//    void onStart(@Observes StartupEvent event) throws SchedulerException {
//
//        quartz.setJobFactory((bundle, scheduler) -> {
//            var jobClazz = bundle.getJobDetail().getJobClass();
//            return Arc.container().instance(jobClazz).get();
//        });
//
//        final JobDetail reportJob = JobBuilder.newJob(MyJob.class)
//                .withIdentity(JobsConstants.MY_JOB)
//                .build();
//
//        final Trigger reportJobTrigger = TriggerBuilder.newTrigger()
//                .withIdentity(JobsConstants.MY_JOB) // IMPORTANT: use identifier to avoid duplication of triggers every startup
//                .startAt(Date.from(Instant.now()))
//                .withSchedule(
//                        SimpleScheduleBuilder.simpleSchedule()
//                                .withIntervalInMilliseconds(1 * 1000)
//                                .repeatForever())
//                .build();
//
//        //scheduleJobOrFailSilently(reportJob, reportJobTrigger, "MyJob Scheduling ERROR");
//    }
//
//    private void scheduleJobOrFailSilently(JobDetail job, Trigger trigger, String message) {
//        try {
//            quartz.scheduleJob(job, Set.of(trigger), true);
//        } catch (SchedulerException e) {
//            //log.error(message, e);
//        }
//    }
//}
