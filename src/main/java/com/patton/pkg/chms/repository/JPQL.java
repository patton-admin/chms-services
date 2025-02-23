//package com.patton.pkg.chms.repository;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.patton.pkg.chms.entity.Bucket;
//
//public class JPQL {
//
//	@Autowired
//	EntityManager em;
//
//	public List<Bucket> findByBucketOwner(String bucketOwner) {
//		Query q = em.createNamedQuery("Bucket.findByBucketOwner");
//		q.setParameter(1, bucketOwner);
//		List<Bucket> bkt= q.getResultList();
//		return bkt;
//	}
//}
