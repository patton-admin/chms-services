package com.patton.pkg.chms.entity.resultset;

public interface LeadInf {

	Long getLeadid();

	String getLeademail();

	Integer getLeadphone();

	String getLeadfirstname();

	String getLeadprimaryemail();

	String getLeadlastname();

	String getLeadstate();

	String getLeadempname();

	String getLeadjobtitle();

	Integer getLeadsalary();

	String getLeadvisatype();

//	 Integer leadExpectedSalary;
//	 String leadPracticeArea;
//	 String leadStatus;
//	 String leadResume;
//	 Integer leadJobOrderid;
	Bucket getbucket();

	interface Bucket {
		long getId();
	}
	
	default Long getbucketId() {
		Bucket id = getbucket();
		return id.getId();
		
	}
}
