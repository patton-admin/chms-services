package com.patton.pkg.chms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.patton.pkg.chms.entity.UserBucketKey;
import com.patton.pkg.chms.entity.UserBucketRelation;

@Repository
public interface UserBucketRepository extends JpaRepository<UserBucketRelation, UserBucketKey> {

	UserBucketRelation findById(UserBucketRelation x);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM user_bucket_rel u WHERE u.user_tbl_user_id = :name "
			+ "AND u.bucket_tbl_bucket_id = :name1", nativeQuery = true)
	int deleteByIdNative(@Param("name") long name, @Param("name1") long name1);

	@Transactional
	@Modifying
	@Query(value = "UPDATE user_bucket_rel u SET u.status= :status " + "WHERE u.user_tbl_user_id = :name "
			+ "AND u.bucket_tbl_bucket_id = :name1", nativeQuery = true)
	int UpdateUserByIdNative(@Param("status") String stat, @Param("name") long name, @Param("name1") long name1);

	@Transactional
	@Modifying
	@Query(value = "SELECT * FROM user_bucket_rel u WHERE u.bucket_tbl_bucket_id = :name1", nativeQuery = true)
	List<UserBucketRelation> getUsersByBucketId(@Param("name1") long name1);

}
