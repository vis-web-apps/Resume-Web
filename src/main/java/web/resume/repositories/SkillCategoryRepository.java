package web.resume.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import web.resume.entities.SkillCategory;

import java.util.List;

@Repository
public interface SkillCategoryRepository extends PagingAndSortingRepository<SkillCategory, Long> {
    List<SkillCategory> findAll();
}
