package in.abhi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.abhi.entity.PlanCategory;

public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer> {

}
