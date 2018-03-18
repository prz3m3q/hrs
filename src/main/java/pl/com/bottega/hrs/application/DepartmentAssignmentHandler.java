package pl.com.bottega.hrs.application;

import com.sun.corba.se.impl.activation.CommandHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.hrs.model.Employee;
import pl.com.bottega.hrs.model.commands.AssignDepartmentToEmployeeCommand;
import pl.com.bottega.hrs.model.commands.Command;
import pl.com.bottega.hrs.model.repositories.DepartmentRepository;
import pl.com.bottega.hrs.model.repositories.EmployeeRepository;

@Component
public class DepartmentAssignmentHandler implements Handler<AssignDepartmentToEmployeeCommand> {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    public DepartmentAssignmentHandler(
            DepartmentRepository departmentRepository,
            EmployeeRepository employeeRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public void handle(AssignDepartmentToEmployeeCommand cmd) {
        Employee employee = employeeRepository.get(cmd.getEmpNo());
        employee.assignDepartment(departmentRepository.get(cmd.getDeptNo()));
        employeeRepository.save(employee);
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return AssignDepartmentToEmployeeCommand.class;
    }

}

