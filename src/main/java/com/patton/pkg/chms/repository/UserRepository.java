package com.patton.pkg.chms.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.patton.pkg.chms.entity.UserTbl;
import com.patton.pkg.chms.entity.resultset.UserInf;
import com.patton.pkg.chms.entity.resultset.UserProjectionData;

@Repository
public interface UserRepository extends JpaRepository<UserTbl, Long> {

	public static final String FIND_PASSWORD = "SELECT user_id,user_password FROM user_tbl  WHERE user_id = :id";

	/*** Delete User ****/
	@Query(value = "SELECT bucket_id e FROM bucket_tbl e WHERE e.bucket_owner_id IN (?1)", nativeQuery = true)
	@Modifying
	List<Long> getAllBucketsByUserId(List<Long> id);

	@Query("DELETE UserTbl e WHERE e.id IN (?1)")
	@Modifying
	void deleteUserByIdHard(List<Long> id);

	// jpa query example...
	@Query("SELECT u.userPassword as password, u.userId as userid FROM UserTbl u WHERE u.userId = ?1")
	Collection<UserInf> findUsersPassword(Long id);

	/**
	 * to find the user based on the userName... Dont remove this
	 * 
	 */
	UserTbl findByUserEmail(String email);

	UserTbl findByUserFirstName(String firstName);

	// softDelete the Operation in jpa...
	@Query("UPDATE UserTbl e SET e.userStatus='InActive' WHERE e.id =?1")
	@Modifying
	public int deleteUserById(Long id);

	// hardDelete the Operation in jpa...
	@Query("DELETE UserTbl e WHERE e.id =?1")
	@Modifying
	public int deleteUser(Long id);

	// to get data with Join user_bucket_rel...
	@Query(value = "select a.user_id, a.user_loginid, a.user_password,a.user_phone_primary,a.user_phone_secondary, "
			+ "a.user_firstname, a.user_lastname, a.user_resetquestion1, a.user_resetquestion2, a.user_resetans1, a.user_resetans2,\r\n"
			+ "a.user_role, a.user_email,a.user_location,b.bucket_tbl_bucket_id as bucketId"
			+ " from user_tbl a left join user_bucket_rel b on a.user_id = b.user_tbl_user_id or a.user_status != 'InActive';", nativeQuery = true)
	Collection<UserProjectionData> findByNativeQuery();

}
