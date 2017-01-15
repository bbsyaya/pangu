package org.turing.pangu.utils;

public class RightsCode {
	
	/**管理员模块*/
	public static String  admin_module="001";
	
	public static String  admin_page_list="001001";
	public static String  admin_function_add="001001001";
	public static String  admin_function_add_href="001001001001";
	public static String  admin_function_add_select_role="001001001002";
	public static String  admin_function_modify="001001002";
	public static String  admin_function_modify_name_exist="001001002001";
	public static String  admin_function_modify_select_role="001001002002";
	public static String  admin_function_lock="001001003";
	public static String  admin_function_unlock="001001004";
	public static String  admin_function_del="001001005";
	public static String  admin_function_view="001001006";
	
	
	/**会员管理模块*/
	public static String  member_module="002";
	public static String  member_page_investPerson_list="002001";
	public static String  member_function_investPerson_modify="002001001";
	public static String  member_function_investPerson_modify_mobile_exist="002001001001";
	public static String  member_function_investPerson_lock="002001002";
	public static String  member_function_investPerson_activate="002001003";
	public static String  member_function_investPerson_blacklist="002001004";
	public static String  member_function_investPerson_view="002001005";
	
	public static String  member_page_investCompany_list="002002";
	public static String  member_function_invest_company_modify="002002001";
	public static String  member_function_invest_company_modify_mobile_exist="002002001001";
	public static String  member_function_invest_company_lock="002002002";
	public static String  member_function_invest_company_activate="002002003";
	public static String  member_function_invest_company_blacklist="002002004";
	public static String  member_function_invest_company_view="002002005";
	public static String  member_function_invest_company_add="002002006";
	public static String  member_function_invest_company_add_mobile_exist="002002006001";
	
	public static String  member_page_guarantee_list="002003";
	public static String  member_function_guarantee_company_add="002003001";
	public static String  member_function_guarantee_company_add_mobile_exist="002003001001";
	public static String  member_function_guarantee_company_modify="002003002";
	public static String  member_function_guarantee_company_modify_mobile_exist="002003002001";
	public static String  member_function_guarantee_company_lock="002003003";
	public static String  member_function_guarantee_company_activate="002003004";
	public static String  member_function_guarantee_company_blacklist="002003005";
	public static String  member_function_guarantee_company_view="002003006";
	public static String  member_function_guarantee_company_freeze="002003007";
	
	
	public static String  member_page_finance_list="002004";
	public static String  member_function_finance_company_add="002004001";
	public static String  member_function_finance_company_add_mobile_exist="002004001001";
	public static String  member_function_finance_company_modify="002004002";
	public static String  member_function_finance_company_modify_mobile_exist="002004002001";
	public static String  member_function_finance_company_lock="002004003";
	public static String  member_function_finance_company_activate="002004004";
	public static String  member_function_finance_company_blacklist="002004005";
	public static String  member_function_finance_company_view="002004006";
	
	
	
	/**融资管理模块*/
	public static String  finance_module="003";
	
	public static String  finance_page_project_add="003001";
	public static String  finance_page_project_add_item_show="003001001";
	public static String  finance_page_project_add_select_guaranteeAndfinance="003001002";
	public static String  finance_page_project_add_image_upload="003001003";
	
	public static String  finance_page_project_list="003002";
	
	/**正式提交*/
	public static String  finance_function_project_submit="003002001";
	/**风控审核通过*/
	public static String  finance_function_project_auditYes="003002002";
	/**风控审核拒绝*/
	public static String  finance_function_project_auditNo="003002003";
	/**修改*/
	public static String  finance_function_project_modify="003002004";
	public static String  finance_function_project_modify_item_show="003002004001";
	public static String  finance_function_project_modify_select_guaranteeAndfinance="003002004002";
	public static String  finance_function_project_modify_param_show="003002004003";
	public static String  finance_function_project_platformYes="003002005";
	public static String  finance_function_project_platformYes_info="003002005001";
	public static String  finance_function_project_platformYes_save="003002005002";
	public static String  finance_function_project_platformNo="003002006";
	public static String  finance_function_project_fabiao="003002007";
	public static String  finance_function_project_details="003002008";
	public static String  finance_function_project_details_param="003002008001";
	public static String  finance_function_project_view="003002009";
	public static String  finance_function_project_financialYes="003002010";
	public static String  finance_function_project_financialNo="003002011";
	public static String  finance_function_project_bossYes="003002012";
	public static String  finance_function_project_bossNo="003002013";
	public static String  finance_function_project_end="003002014";
	
	/**投资管理模块*/
	public static String  invest_module="004";
	
	public static String  invest_page_list="004001";
	public static String  invest_page_list_view="004001001";
	
	
	/**财务管理模块*/
	public static String  financial_module="005";
	public static String  financial_page_loaning="005001";
	public static String  financial_function_loaning_loanYes="005001001";
	public static String  financial_function_loaning_loanNo="005001002";
	public static String  financial_function_loaning_auditYes="005001003";
	public static String  financial_function_loaning_auditNo="005001004";
	public static String  financial_function_loaning_view="005001005";

	public static String  financial_page_overdue="005002";
	public static String  financial_function_overdue_view="005002001";
	public static String  financial_function_overdue_bad="005002002";
	
	
	public static String  financial_page_moneyRecord="005003";
	public static String  financial_function_moneyRecord_view="005003001";
	
	
	public static String  financial_page_accountMoney="005004";
	public static String  financial_function_accountMoney_view="005004001";
	public static String  financial_function_accountMoney_Offline_recharge="005004002";
	public static String  financial_function_accountMoney_Offline_withdraw="005004003";
	
	
	public static String  financial_page_accountCheck="005005";
	public static String  financial_function_accountCheck_quickcheck="005005001";
	public static String  financial_function_accountCheck_fullcheck="005005002";
	public static String  financial_function_accountCheck_view="005005003";
	public static String  financial_page_accountCheck_info="005005004";
	public static String  financial_page_accountCheck_info_ledgerList="005005004001";
	public static String  financial_page_accountCheck_info_ledgerList_Execl="005005004001001";
	public static String  financial_page_accountCheck_info_userList="005005004002";
	public static String  financial_page_accountCheck_info_userList_Execl="005005004002001";
	public static String  financial_page_accountCheck_info_userAndledgerList="005005004003";
	public static String  financial_page_accountCheck_info_userAndledgerList_Execl="005005004003001";
	public static String  financial_page_accountCheck_info_recordList="005005004004";
	public static String  financial_page_accountCheck_info_recordList_Execl="005005004004001";
	

	public static String  financial_page_payRequest="005006";
	
	public static String  financial_page_fundPlan="005007";
	
	
	/**公告模块*/
	public static String  note_module="006";
	public static String  note_page_note="006001";
	public static String  note_function_add="006001001";
	public static String  note_function_modify="006001002";
	public static String  note_function_del="006001003";
	public static String  note_function_view="006001004";
	
	public static String  note_page_noteAdd="006002";
	public static String  note_function_noteAdd="006002001";
	
	
	/**系统管理*/
	public static String  system_module="007";
	public static String  system_page_group="007001";
	public static String  system_function_group_add="007001001";
	public static String  system_function_group_modify="007001002";
	public static String  system_function_group_del="007001003";
	public static String  system_function_group_view="007001004";
	public static String  system_page_item="007002";
	public static String  system_page_item_add="007002001";
	public static String  system_page_item_add_select_group="007002001001";
	public static String  system_page_item_modify="007002002";
	public static String  system_page_item_modify_select_group="007002002001";
	public static String  system_page_item_del="007002003";
	public static String  system_page_item_view="007002004";
	public static String  system_page_item_allocation="007002005";
	public static String  system_page_item_allocation_view="007002005001";
	public static String  system_page_role="007003";
	public static String  system_page_role_add="007003001";
	public static String  system_page_role_add_name_exist="007003001001";
	public static String  system_page_role_modify="007003002";
	public static String  system_page_role_modify_name_exist="007003002001";
	public static String  system_page_role_del="007003003";
	public static String  system_page_role_allot="007003004";
	public static String  system_page_role_allot_list="007003004001";
	public static String  system_page_role_view="007003005";
	
	
	public static String  system_page_rights="007004";
	public static String  system_page_rights_add="007004001";
	public static String  system_page_rights_add_name_exist="007004001001";
	public static String  system_page_rights_add_select_parent_name="007004001002";
	public static String  system_page_rights_modify="007004002";
	public static String  system_page_rights_del="007004003";
	public static String  system_page_rights_view="007004004";
	
	/**活动*/
	
	public static String  act_module="008";
	public static String  act_page_register="008001";
	public static String  act_page_register_image_upload="008001001";
	
}
