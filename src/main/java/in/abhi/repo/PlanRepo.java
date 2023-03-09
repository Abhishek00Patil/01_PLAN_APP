package in.abhi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.abhi.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
