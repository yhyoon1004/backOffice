package yh_project.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yh_project.backoffice.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String username);
}
