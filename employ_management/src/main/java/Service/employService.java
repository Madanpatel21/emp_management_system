package Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import Dto.EmployDto;
import Entity.Employ;
import Exception.EmployNotFoundException;
import Repository.employRepository;

@Service
public class employService {
 @Autowired
 private employRepository repository;
 @Transactional
 public EmployDto createEmployee(String name, String email, String empId, MultipartFile photo) throws IOException {
	 if (photo == null || photo.isEmpty()) {
	        throw new IllegalArgumentException("Photo file is required and cannot be empty");
	    }
	 
	 long maxSize = 5 * 1024 * 1024; // 5MB
     if (photo.getSize() > maxSize) {
         throw new IllegalArgumentException("Photo file size exceeds 5MB limit");
     }
     if (repository.existsByEmpId(empId)) {
         throw new IllegalArgumentException("Employee ID '" + empId + "' already exists!");
     }
     if(repository.existsByEmail(email)) {
         throw new IllegalArgumentException("Employee email '" + email + "' already exists!");
     }
	 
     Employ employee = new Employ();
     employee.setName(name);
     employee.setEmail(email);
     employee.setEmpId(empId);
     employee.setPhoto(photo.getBytes());
     Employ saved = repository.save(employee);

     return mapToDTO(saved);
 }
 public List<EmployDto> getAllEmployees() {
     return repository.findAll()
             .stream()
             .map(this::mapToDTO)
             .collect(Collectors.toList());
 }
 public byte[] getEmployeePhoto(Long id) {
     Employ employee = repository.findById(id)
             .orElseThrow(() -> new EmployNotFoundException(id));
     return employee.getPhoto();
 }
 private EmployDto mapToDTO(Employ emp) {
     EmployDto dto = new EmployDto();
     dto.setId(emp.getId());
     dto.setName(emp.getName());
     dto.setEmail(emp.getEmail());
     dto.setEmpId(emp.getEmpId());

     if (emp.getPhoto() != null && emp.getPhoto().length > 0) {
         String base64 = Base64.getEncoder().encodeToString(emp.getPhoto());
         dto.setPhotobase64("data:image/jpeg;base64," + base64);
     }
     return dto;
 }
 public void deleteEmployee(Long id) {
	    if (!repository.existsById(id)) {
	        throw new EmployNotFoundException(id);
	    }
	    repository.deleteById(id);
	}
}
