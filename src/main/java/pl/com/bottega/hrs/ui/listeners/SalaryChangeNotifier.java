package pl.com.bottega.hrs.ui.listeners;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import pl.com.bottega.hrs.model.Employee;
import pl.com.bottega.hrs.model.events.SalaryChangedEvent;
import pl.com.bottega.hrs.model.repositories.EmployeeRepository;
import org.apache.log4j.Logger;

@Component
@ConditionalOnProperty(name = "hrs.notoficationEnabled", havingValue = "true")
public class SalaryChangeNotifier {

    private EmployeeRepository employeeRepository;

    public SalaryChangeNotifier(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @TransactionalEventListener
    @Async
    public void salaryChanged(SalaryChangedEvent event) {
        Employee employee = employeeRepository.get(event.getEmpNo());
        Logger.getLogger(SalaryChangeNotifier.class).info("Sending emaili to " + employee.getLastName());
    }

//    @Scheduled(cron="*/5 * * * * *")
//    public void sampleSchedule() {
//        Logger.getLogger(SalaryChangeNotifier.class).info("Sample schedule");
//    }
}
