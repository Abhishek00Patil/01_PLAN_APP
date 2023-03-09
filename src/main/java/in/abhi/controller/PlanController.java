package in.abhi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.abhi.entity.Plan;
import in.abhi.service.PlanService;

@RestController
public class PlanController {

	@Autowired
	private PlanService planService;

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> getCategories() {
		Map<Integer, String> categories = planService.getPlanCategories();

		return new ResponseEntity<Map<Integer, String>>(categories, HttpStatus.OK);

	}

	@PostMapping("/save-plan")
	public ResponseEntity<String> saveplan(@RequestBody Plan plan) {
		boolean saved = planService.savePlan(plan);

		if (saved) {

			return new ResponseEntity<String>("Plan Saved", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Plan Not Saved", HttpStatus.OK);
		}

	}

	@GetMapping("/get-all-plans")
	public ResponseEntity<List<Plan>> getAllPlans() {
		List<Plan> allPlan = planService.getAllPlan();

		return new ResponseEntity<List<Plan>>(allPlan, HttpStatus.OK);

	}

	@GetMapping("/get-plan-by-Id/{planId}")
	public ResponseEntity<Plan> getPlanById(@PathVariable Integer planId) {
		Plan planById = planService.getPlanById(planId);

		if (planById != null) {
			return new ResponseEntity<Plan>(planById, HttpStatus.OK);
		} else {
			return new ResponseEntity<Plan>(planById, HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/update-plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
		boolean isUpdated = planService.updatePlan(plan);

		String msg = "";

		if (isUpdated) {
			msg = "Plan Updated";
		} else {
			msg = "Plan not updated";
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/delete-plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		
		boolean isDeleted = planService.deletePlanById(planId);
		
		String msg = "";

		if (isDeleted) {
			msg = "Plan Updated";
		} else {
			msg = "Plan not updated";
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}
	
	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String>planStatusChange(@PathVariable Integer planId,@PathVariable String status)
	{
		boolean isStatusChange = planService.planStatusChange(planId, status);
		
		String msg="";
		
		if(isStatusChange)
		{
			msg="Status Changed";
		}
		else
		{
			msg="Status not Changed";
		}
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
		
	}
}
