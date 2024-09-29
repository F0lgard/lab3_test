package org.example;

import java.util.List;

public class CompanyServiceImpl implements ICompanyService {

    @Override
    public Company getTopLevelParent(Company child) {
        if (child == null) {
            return null;
        }

        Company current = child;
        while (current.getParent() != null) {
            current = current.getParent();
        }

        return current;
    }

    @Override
    public long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies) {
        if (company == null || companies == null || companies.isEmpty()) {
            return 0;
        }

        long totalEmployees = company.getEmployeeCount();

        for (Company child : companies) {
            if (child.getParent() != null && child.getParent().equals(company)) {
                totalEmployees += getEmployeeCountForCompanyAndChildren(child, companies);
            }
        }

        return totalEmployees;
    }
}
