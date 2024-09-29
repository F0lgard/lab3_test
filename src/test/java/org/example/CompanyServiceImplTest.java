package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyServiceImplTest {
    private final Company main = new Company(null, 3);
    private final Company bookkeeping = new Company(main, 2);
    private final Company development = new Company(main, 4);
    private final Company design = new Company(development, 3);
    private final Company frontEnd = new Company(development, 10);
    private final Company backEnd = new Company(development, 11);

    private final Company businessLogic = new Company(backEnd, 5);
    private final Company bd = new Company(backEnd, 3);

    private final ICompanyService underTest = new CompanyServiceImpl();

    List<Company> companies = Arrays.asList(main, bookkeeping, development, design, frontEnd, backEnd, businessLogic, bd);

    private final Company mainSecond = new Company(null, 5);

    private final Company selling = new Company(mainSecond, 4);
    private final Company marketing = new Company(mainSecond, 8);
    private final Company sales = new Company(selling, 6);
    private final Company digitalMarketing = new Company(marketing, 3);
    private final Company contentCreation = new Company(marketing, 2);
    private final Company leadGeneration = new Company(sales, 7);
    private final Company customerSupport = new Company(selling, 5);

    @Test
    public void testGetTopLevelParent_bookkeeping() {
        assertEquals(main, underTest.getTopLevelParent(bookkeeping));
    }

    @Test
    public void testGetTopLevelParent_main() {
        assertEquals(main, underTest.getTopLevelParent(main));
    }

    @Test
    public void testGetTopLevelParent_nullCompany() {
        assertNull(underTest.getTopLevelParent(null));
    }

    @Test
    public void testGetTopLevelParent_development() {
        assertEquals(main, underTest.getTopLevelParent(development));
    }

    @Test
    public void testGetTopLevelParent_newCompanySelf() {
        Company newCompany = new Company(null, 3);
        assertEquals(newCompany, underTest.getTopLevelParent(newCompany));
    }

    @Test
    public void testGetTopLevelParent_design() {
        assertEquals(main, underTest.getTopLevelParent(design));
    }

    @Test
    public void testGetTopLevelParent_frontEnd() {
        assertEquals(main, underTest.getTopLevelParent(frontEnd));
    }

    @Test
    public void testGetTopLevelParent_leadGeneration() {
        assertEquals(mainSecond, underTest.getTopLevelParent(leadGeneration));
    }

    @Test
    public void testGetTopLevelParent_selling() {
        assertEquals(mainSecond, underTest.getTopLevelParent(selling));
    }

    @Test
    public void testGetTopLevelParent_contentCreation() {
        assertEquals(mainSecond, underTest.getTopLevelParent(contentCreation));
    }

    //

    @Test
    public void testGetEmployeeCountForCompanyAndChildren_development() {
        long totalEmployees = underTest.getEmployeeCountForCompanyAndChildren(development, companies);
        assertEquals(36, totalEmployees); // development(4) + design(3) + frontEnd(10) + backEnd tree (16) + main(3)
    }

    @Test
    public void testGetEmployeeCountForCompanyAndChildren_backEnd() {
        long totalEmployees = underTest.getEmployeeCountForCompanyAndChildren(backEnd, companies);
        assertEquals(19, totalEmployees); // backEnd(11) + businessLogic(5) + db(3) + main(3)
    }

    @Test
    public void testGetEmployeeCountForCompanyAndChildren_bookkeeping() {
        long totalEmployees = underTest.getEmployeeCountForCompanyAndChildren(bookkeeping, companies);
        assertEquals(2, totalEmployees); // bookkeeping(2) has no children
    }

    @Test
    public void testGetEmployeeCountForCompanyAndChildren_design() {
        long totalEmployees = underTest.getEmployeeCountForCompanyAndChildren(design, companies);
        assertEquals(3, totalEmployees); // design(3) has no children
    }

    @Test
    public void testGetEmployeeCountForCompanyAndChildren_frontEnd() {
        long totalEmployees = underTest.getEmployeeCountForCompanyAndChildren(frontEnd, companies);
        assertEquals(10, totalEmployees); // frontEnd(10) has no children
    }

    @Test
    public void testGetEmployeeCountForCompanyAndChildren_businessLogic() {
        long totalEmployees = underTest.getEmployeeCountForCompanyAndChildren(businessLogic, companies);
        assertEquals(5, totalEmployees); // businessLogic(5) has no children
    }

    @Test
    public void testGetEmployeeCountForCompanyAndChildren_db() {
        long totalEmployees = underTest.getEmployeeCountForCompanyAndChildren(bd, companies);
        assertEquals(3, totalEmployees); // db(3) has no children
    }

    @Test
    public void testGetEmployeeCountForCompanyAndChildren_main() {
        long totalEmployees = underTest.getEmployeeCountForCompanyAndChildren(main, companies);
        assertEquals(41, totalEmployees);
    }

    @Test
    public void testGetEmployeeCountForCompanyAndChildren_CompanyNull() {
        long totalEmployees = underTest.getEmployeeCountForCompanyAndChildren(backEnd, null);
        assertEquals(0, totalEmployees);
    }

    @Test
    public void testGetEmployeeCountForCompanyAndChildren_CompaniesNull() {
        long totalEmployees = underTest.getEmployeeCountForCompanyAndChildren(null, companies);
        assertEquals(0, totalEmployees);
    }
}