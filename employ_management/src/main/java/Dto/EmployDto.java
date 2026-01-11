package Dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployDto implements Serializable{
 private Long id;
 private String name;
 private String email;
 private String empId;
 private String photobase64;
 
}
