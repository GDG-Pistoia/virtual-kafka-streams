package it.gdgpt.scheduled;

import io.quarkus.narayana.jta.runtime.TransactionConfiguration;
import io.vavr.control.Try;
import it.gdgpt.control.MyCheckpointControl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.time.LocalDateTime;


//@ApplicationScoped
//@DisallowConcurrentExecution
//public class MyJob implements Job {
//
//    @Inject
//    MyCheckpointControl checkpointControl;
//
//    @Transactional
//    @TransactionConfiguration(timeout = 3600)
//    @Override
//    public void execute(JobExecutionContext context) {
//        //log.info("operationName=myJobExecution");
//        if (context.isRecovering()) {
//           // log.debug("Skipping recovery");
//            return;
//        }
//        run();
//    }
//
//    private void run() {
//        Try.of(() -> {
//            checkpointControl.pickOldestCheckpoint()
//                    .ifPresent(checkpoint -> {
//                        //TODO: implement logic
//                        checkpoint.setUpdated(LocalDateTime.now());
//                        checkpointControl.saveOrUpdate(checkpoint);
//                    });
//            return null;
//        }).onFailure(throwable -> {
//            return;
//        });
//    }
//}
