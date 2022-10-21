package com.example.demo.web;

import java.util.List;

import com.example.demo.dao.TaskRepository;
import com.example.demo.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
	@Autowired
	TaskRepository taskrepo;

	@GetMapping("/tasks")
	@PreAuthorize("hasAuthority('ALL_TASK')")
	public ResponseEntity<List<Task>> alltask() {

		return new ResponseEntity<List<Task>>(taskrepo.findAll(), HttpStatus.FOUND);
	}

	@GetMapping("/tasks/{id}")
	public ResponseEntity<Task> gettask(@PathVariable(name = "id")long id){
		Task t = taskrepo.findById(id).orElseThrow(()-> new RuntimeException("Task "+id+" Not found "));
		return new ResponseEntity<Task>(t, HttpStatus.FOUND);
	}

	@PostMapping("/tasks")
	@PreAuthorize("hasAuthority('CREATE_TASK')")
	public ResponseEntity<Task> savetask(@RequestBody Task t) {

		return new ResponseEntity<Task>(taskrepo.save(t), HttpStatus.CREATED);
	}

	@DeleteMapping("/tasks/{id}")
	@PreAuthorize("hasAuthority('DELETE_TASK')")
	public ResponseEntity<Void> deletask(@PathVariable(name = "id")long id){
		Task t = taskrepo.findById(id).orElseThrow(()-> new RuntimeException("Task "+id+" Not found "));
		taskrepo.delete(t);
		return new ResponseEntity<Void>(HttpStatus.GONE);
	}

}
