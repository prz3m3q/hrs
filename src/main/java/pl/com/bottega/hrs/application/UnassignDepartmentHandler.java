package pl.com.bottega.hrs.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.hrs.model.Department;
import pl.com.bottega.hrs.model.Employee;
import pl.com.bottega.hrs.model.commands.Command;
import pl.com.bottega.hrs.model.commands.UnassignDepartmentCommand;
import pl.com.bottega.hrs.model.repositories.DepartmentRepository;
import pl.com.bottega.hrs.model.repositories.EmployeeRepository;

@Component
public class UnassignDepartmentHandler implements Handler<UnassignDepartmentCommand> {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    public UnassignDepartmentHandler(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public void handle(UnassignDepartmentCommand cmd) {
        Employee employee = employeeRepository.get(cmd.getEmpNo());
        Department department = departmentRepository.get(cmd.getDeptNo());
        employee.unassignDepartment(department);
        employeeRepository.save(employee);
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return UnassignDepartmentCommand.class;
    }
}
