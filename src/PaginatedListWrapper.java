

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The goal of this class was to use generics so the wrapper could enclose a list of any object type.
 * Original version with generics compiled and deployed fine but JAXB through the following marshalling exception
 * 
 * 06-Oct-2016 09:08:30.795 SEVERE [http-nio-8080-exec-3] org.jboss.resteasy.core.ExceptionHandler. Failed executing GET /jsonl
 * org.jboss.resteasy.plugins.providers.jaxb.JAXBMarshalException: javax.xml.bind.MarshalException
 * - with linked exception:
 * [com.sun.istack.SAXException2: class com.msp.model.Person nor any of its super class is known to this context.
 * 
 * This was resolved once I hard coded the parameter type to Person.
 * TODO - identify how to get JAXB working with generics
 * 
 * @author markpower
 *
 */

@XmlRootElement
public class PaginatedListWrapper {

    private List<Person> list;
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalResults;
	
	private String sortFields;
	private String sortDirections;
	
	
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}
	public String getSortFields() {
		return sortFields;
	}
	public void setSortFields(String sortFields) {
		this.sortFields = sortFields;
	}
	public String getSortDirections() {
		return sortDirections;
	}
	public void setSortDirections(String sortDirections) {
		this.sortDirections = sortDirections;
	}
	
	public List<Person> getList() {
		return list;
	}
	public void setList(List<Person> list) {
		this.list = list;
	}
	
	
}
