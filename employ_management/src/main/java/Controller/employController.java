package Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import Dto.EmployDto;
import Exception.EmployNotFoundException;
import Repository.employRepository; // Import the repository
import Service.employService;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class employController {

    @Autowired
    private employService employeeService;

    @Autowired
    private employRepository repository; 

    // Create new employee
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public EmployDto addEmployee(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("empId") String empId,
            @RequestParam("photo") MultipartFile photo) throws IOException {
       
        return employeeService.createEmployee(name, email, empId, photo);
    }

    @GetMapping
    public List<EmployDto> getAllEmployees() throws Exception {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) {
        byte[] photo = employeeService.getEmployeePhoto(id);
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"photo.jpg\"")
                .contentType(MediaType.IMAGE_JPEG)
                .body(photo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        // Now calling the service instead of the repository directly
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build(); 
    }
}