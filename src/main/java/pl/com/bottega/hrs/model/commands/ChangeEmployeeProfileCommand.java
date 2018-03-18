
package pl.com.bottega.hrs.model.commands;

import pl.com.bottega.hrs.model.Address;
import pl.com.bottega.hrs.model.Gender;

import java.time.LocalDate;

public class ChangeEmployeeProfileCommand implements Command {

    private String firstName, lastName;

    private LocalDate birthDate;

    private Address address;

    private Gender gender;

    private Integer empNo;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public void validate(ValidationErrors errors){
        validatePresence(errors,"firstName", firstName);
        validatePresence(errors,"lastName", lastName);
        validatePresence(errors,"birthDate", birthDate);
        validatePresence(errors,"address.street",address.getStreet());
        validatePresence(errors,"address.city", address.getCity());
        validatePresence(errors,"gender",gender);
        validatePresence(errors,"empNo",empNo);

        if(birthDate != null && birthDate.isAfter(LocalDate.now())){
            errors.add("birthDate", "mast be in the past");
        }
    }

}
