
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.model.student;
import com.student.repository.studentRepository;

@RestController
@RequestMapping("/api")
public class studentController {
	
	@Autowired
	studentRepository studentRepository;
	
	@PostMapping("/student")
	public String createNewStudent(student student) {
		studentRepository.save(student);
		return "Student created in database";
	}
	
	@GetMapping("/student")
	public ResponseEntity<List<student>> getAllstudent(){
		List<student> stuList = new ArrayList<>();
		studentRepository.findAll().forEach(stuList::add);
		return new ResponseEntity<List<student>>(stuList,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/stuydent/{sid}")
	public ResponseEntity<student> getstusentid(@PathVariable long sid){
		Optional<student> stu = studentRepository.findById(sid);
		if (stu.isPresent()) {
			return new ResponseEntity<student>(stu.get(),HttpStatus.FOUND);
		} else {
			return new ResponseEntity<student>(HttpStatus.NOT_FOUND);
		}
		
		
	@PutMapping("/student/{sid}")
	public String updateStudentId(@PathVariable Long sid, @RequestBody student student) {
		Optional<student> stu = studentRepository.findById(sid);
		if (stu.isPresent()) {
			student exitstu=stu.get();
			exitstu.setSname(student.getSname());
			exitstu.setSaddress(student.getSaddress());
			exitstu.setSfield(student.getSfield());
			studentRepository.save(exitstu);
			return "student Details against Id " +sid+ "updated";
		} else {
			return "Student details not exits for stuid" +sid;
		}
	}
	
	@DeleteMapping("/student/{sid}")
	public String deleteStudentBysid(@PathVariable Long sid) {
		studentRepository.deleteById(sid);
		return "student delete sucessfully";
		}
	
	@DeleteMapping("/student")
	public String deleteAllStudent() {
		studentRepository.deleteAll();
		return "Student Deleted successfully";
	}
	
	
}	
