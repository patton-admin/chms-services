package com.patton.pkg.chms.entity.resultset;

public interface JobOrderProjectionData {

	Long getJoborder_id();

	String getJoborder_title();

	Long getClient_tbl_client_id();

	String getJoborder_priority();

	String getJoborder_city();

	String getJoborder_state();

	String getCreation_date();

	String getClient_name();

	Long getBucket_tbl_bucket_id();

}
