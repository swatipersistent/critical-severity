package de.ba.ascc.CheckmarxTest;

import java.util.ArrayList;
import java.util.List;

@WebService
public class FindSpringQueryDBIn {

	public List<?> iResultList;
	public List<?> iGetSingleResult;
    public FindSpringQueryDBIn(List<?> iResultList, List<?> iGetSingleResult) {
		super();
		this.iResultList = iResultList;
		this.iGetSingleResult = iGetSingleResult;
	}
	public List<?> getResultList()
    {
        return iResultList;
    }
    public List<?> getSingleResult(){
    	return iGetSingleResult;
    }
    @WebMethod
    public void noDBQuery(String firstArg, String secondArg) {

    	ArrayList<String> myResultList = new ArrayList<String>();
    	myResultList.add(firstArg);
    	
    	ArrayList<String> mySingleResult = new ArrayList<String>();
    	mySingleResult.add(secondArg);
    	
    	FindSpringQueryDBIn noDBQuery = new FindSpringQueryDBIn(myResultList, mySingleResult);
    	
    	noDBQuery.getResultList();
    	noDBQuery.getSingleResult();
    }
public FilterWorkflow getNextStatusesForAction(final Long currentStatus, final String actionRequested) {
    Query query = this.getEntityManager().createQuery(GET_NEXT_STATUSES_FOR_ACTION);
    query.setParameter("currentStatus_Id", currentStatus);
    query.setParameter("actionRequested", actionRequested);
    return (FilterWorkflow) query.getSingleResult();
}

@Override
@Transactional(value="txManager", rollbackFor = Exception.class)
public SomeFilter executeAction(SomeFilter bf, final String action requested) throws Exception {
    Long currentStatusID = bf.getFilteStatus().getTableId();
    FilterWorkflow fw = this.someDAO.getNextStatusesForAction(currentStatusID, actionRequested);

    return this.updateFilterStatus(fw, bf, actionRequested);
}

public String execute(SomeFilter bf, final String command) {
    try {
        bf = this.someService.executeAction(bf, command);
    } catch (Exception e) {
        LOGGER.info(e.getMessage());
        FacesUtil.addErrorMessage(this.msgApp.getMessage("error_message"));
        return null;
    }
    return null;
}

public int getUserId(HttpServletRequest request) 
		throws ServletException, IOException {
	int userId = 0;
	
	String userName = request.getParameter("UserName");
	String sql = "SELECT [UserID] FROM [AppUsers] WHERE [UserName] = '" + userName + "' " ;

	try {
		Connection conn = getConnection(); 
		Statement stmt = conn.createStatement(); 
		ResultSet data = stmt.executeQuery(sql);
		
		userId = data.getInt(1);		
	} catch (SQLException ex) {
		handleExceptions(ex);
	}
	finally {
		closeQuietly(data);
		closeQuietly(stmt);
		closeQuietly(conn);
	}
	
	return userId;
}
}
