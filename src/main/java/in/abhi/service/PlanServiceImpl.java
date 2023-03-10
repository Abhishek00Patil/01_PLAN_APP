package in.abhi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.abhi.entity.Plan;
import in.abhi.entity.PlanCategory;
import in.abhi.repo.PlanCategoryRepo;
import in.abhi.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private PlanCategoryRepo categoryRepo;
	
	@Override
	public Map<Integer, String> getPlanCategories() {
		
		List<PlanCategory> catregories = categoryRepo.findAll();
		
		Map<Integer, String> categoryMap = new HashMap<>();
		
		catregories.forEach(category -> {
			
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		
		return categoryMap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		
		Plan saved = planRepo.save(plan);
		
		return saved.getPlanId()!=null;
	}

	@Override
	public List<Plan> getAllPlan() {
		List<Plan> plans = planRepo.findAll();
		return plans;
	}

	@Override
	public Plan getPlanById(Integer planId) {
	
	  Optional<Plan> findById = planRepo.findById(planId);
	  
	  if(findById.isPresent()) {
		  return findById.get();
	  }
		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		
		planRepo.save(plan);
		
		return plan.getPlanId()!=null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			status=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		
		Optional<Plan> findById = planRepo.findById(planId);
		
		if(findById.isPresent()) {
			
			Plan plan = findById.get();
			plan.setActiveSw(status);
			planRepo.save(plan);
			return true;
		}
		
		return false;
	}

}
