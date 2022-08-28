package study.querydsl.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {
    
    @PersistenceContext
    EntityManager em;
    
    @Test
    void testEntity() {
        
        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");
        
        em.persist(teamA);
        em.persist(teamB);
        
        Member member1 = new Member("Member1", 10, teamA);
        Member member2 = new Member("Member2", 20, teamA);
        Member member3 = new Member("Member3", 30, teamB);
        Member member4 = new Member("Member4", 40, teamB);
        
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        
        em.flush();
        em.clear();

        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        for (Member member : result) {
            System.out.println("member = " + member);
            System.out.println("member.getTeam() = " + member.getTeam());
        }
    }



}