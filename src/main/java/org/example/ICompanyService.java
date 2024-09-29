package org.example;

import java.util.List;

public interface ICompanyService {

    /**
     * @param child - company for whom we are searching the top-level parent (parent of parent of...)
     * @return top-level parent company
     */
    Company getTopLevelParent(Company child);

    /**
     * @param company  - company for whom we are searching count of employees
     * @param companies - list of all companies
     * @return count of employees for the company and all its child companies
     */
    long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies);
}