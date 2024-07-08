package com.spring.microservice.service;

import com.spring.microservice.config.AccountEnum;
import com.spring.microservice.dto.AccountsDto;
import com.spring.microservice.dto.CustomersDto;
import com.spring.microservice.dto.OrganizationDto;
import com.spring.microservice.entity.Accounts;
import com.spring.microservice.entity.Customers;
import com.spring.microservice.entity.Organization;
import com.spring.microservice.exception.AccountNotFoundException;
import com.spring.microservice.mapper.AccountsMapper;
import com.spring.microservice.mapper.CustomerMapper;
import com.spring.microservice.mapper.OrganizationMapper;
import com.spring.microservice.repository.AccountsRepository;
import com.spring.microservice.repository.CustomersRepository;
import com.spring.microservice.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private AccountsRepository accountsRepository;
    private OrganizationRepository organizationRepository;
    private CustomersRepository customersRepository;

    private AccountsMapper accountsMapper;
    private CustomerMapper customerMapper;
    private OrganizationMapper organizationMapper;

    public void addAccount(AccountsDto accountsDto) {
        Accounts accounts = accountsMapper.mapToAccounts(accountsDto, new Accounts());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        accounts.setAccountNumber(randomAccNumber);
        Date date = new Date();
        accountsDto.setCreatedDate(date);
        accountsDto.setUpdatedDate(date);

        accountsRepository.save(accounts);
    }

    @Override
    public void addAccounts(List<AccountsDto> accountsDtos) {
        List<Accounts> accountsList = new ArrayList<>();

        for (AccountsDto accountsDto : accountsDtos) {
            Accounts accounts = accountsMapper.mapToAccounts(accountsDto, new Accounts());
            Long randomAccountNumber = 1000000000L + new Random().nextLong(900000000);
            accounts.setAccountNumber(randomAccountNumber);
            Date date = new Date();
            accounts.setCreatedDate(date);
            accounts.setUpdatedDate(date);
            accountsList.add(accounts);
        }
        accountsRepository.saveAll(accountsList);
    }

    @Override
    public boolean updateAccounts(AccountsDto accountsDto) {
        Accounts accounts = accountsRepository.findByAccountNumber(accountsDto.getAccountNumber()).orElseThrow(()
                -> new AccountNotFoundException("Account not found" + accountsDto.getAccountNumber()));

        accountsMapper.mapToAccounts(accountsDto, accounts);
        Date date = new Date();
        accounts.setUpdatedDate(date);
        accounts.setCreatedDate(date);
        accountsRepository.save(accounts);
        return true;
    }


    @Override
    public void addOrganization(OrganizationDto organizationDto) {
        Organization organization = organizationMapper.mapToOrganization(organizationDto, new Organization());
        Date date = new Date();
        organization.setCreatedDate(date);
        organization.setUpdatedDate(date);
        organizationRepository.save(organization);
    }


    public Map<String, Map<AccountEnum, Map<String, Object>>> generateReport(Date fromDate, Date toDate, String type) {
        Map<String, Map<AccountEnum, Map<String, Object>>> reportData = new LinkedHashMap<>();

        // For Monthly or Y type, fromDate and toDate are already set
        if ("Monthly".equalsIgnoreCase(type) || "Y".equalsIgnoreCase(type)) {
        } else {
            // For specific month name (e.g., "January", "February", etc.), calculate weekly data
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fromDate);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            calendar.set(year, month, 1, 0, 0, 0);
            fromDate = calendar.getTime();

            calendar.set(year, month, calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
            toDate = calendar.getTime();
        }

        // Fetch data from repositories
        List<Accounts> registeredUsers = accountsRepository.findByTaxCodeInAndCreatedDateBetween(Arrays.asList("20", "30", "40", "51", "52"), fromDate, toDate);
        List<Customers> activatedCompanies = customersRepository.countActivatedCompanies(fromDate, toDate);

        // Prepare data maps for different customer types
        Map<AccountEnum, Map<String, Object>> dataMap = new LinkedHashMap<>();
        dataMap.put(AccountEnum.SME, createBusinessData(registeredUsers, activatedCompanies));
        dataMap.put(AccountEnum.GOVERNMENT, createBusinessData(registeredUsers, activatedCompanies));
        dataMap.put(AccountEnum.ENTERPRISE, createBusinessData(registeredUsers, activatedCompanies));

        // Calculate grand total
        long grandTotalRegisteredUsers = dataMap.values().stream()
                .mapToLong(map -> (Long) map.get("registeredUser"))
                .sum();
        long grandTotalActivatedCompanies = dataMap.values().stream()
                .mapToLong(map -> (Long) map.get("activatedCompanies"))
                .sum();

        Map<String, Object> grandTotalMap = new HashMap<>();
        grandTotalMap.put("registeredUser", grandTotalRegisteredUsers);
        grandTotalMap.put("activatedCompanies", grandTotalActivatedCompanies);

        dataMap.put(AccountEnum.GRANDTOTAL, grandTotalMap);

        // Add to reportData with the appropriate key (month name or week number)
        reportData.put(getReportKey(fromDate), dataMap);

        return reportData;
    }

    // Helper method to create business data map
    private Map<String, Object> createBusinessData(List<Accounts> registeredUsers, List<Customers> activatedCompanies) {
        Map<String, Object> data = new HashMap<>();
        data.put("registeredUser", registeredUsers);
        data.put("activatedCompanies", activatedCompanies);
        return data;
    }

    // Helper method to get report key (month name or week number)
    private String getReportKey(Date fromDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        int month = calendar.get(Calendar.MONTH);
        return getMonthName(month);
    }

    // Helper method to get month name based on month number
    private String getMonthName(int month) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }

    @Override
    public void addCustomers(CustomersDto customersDto) {
        Customers customers = customerMapper.mapToCustomer(customersDto, new Customers());
        Date date = new Date();
        customers.setCreatedDate(date);
        customers.setUpdatedDate(date);
        customersRepository.save(customers);
    }

    /*public List<Map<String, Map<AccountEnum, Integer>>> fetchData(String monthly, String weekly) {
        List<Map<String, Map<AccountEnum, Integer>>> response = new ArrayList<>();

        if ("Y".equalsIgnoreCase(monthly)) {
            // Fetch data for current month - last 3 months
            LocalDate currentDate = LocalDate.now();
            LocalDate fromDate = DateUtil.getFirstDayOfMonth(currentDate).minusMonths(3);
            LocalDate toDate = DateUtil.getLastDayOfMonth(currentDate);

            Map<String, Map<AccountEnum, Integer>> monthlyData = fetchDataForMonthly(fromDate, toDate);
            response.add(monthlyData);

        } else {
            // Fetch data for a specific month (e.g., January) and weekly data
            // Implement logic to handle weekly data for the selected month
            LocalDate fromDate = LocalDate.of(2024, 1, 1); // Example date for January
            LocalDate toDate = fromDate.with(TemporalAdjusters.lastDayOfMonth());

            Map<String, Map<AccountEnum, Integer>> weeklyData = fetchDataForWeekly(fromDate, toDate);
            response.add(weeklyData);
        }

        return response;
    }

    private Map<String, Map<AccountEnum, Integer>> fetchDataForMonthly(LocalDate fromDate, LocalDate toDate) {
        Map<String, Map<AccountEnum, Integer>> monthlyData = new HashMap<>();

        // Example implementation, replace with actual logic
        // Fetch data from repository based on fromDate and toDate
        List<Accounts> accountsList = accountsRepository.findByTaxCodeInAndCreatedDateBetween(
                List.of("10", "20", "30"), fromDate, toDate);

        // Process fetched data into the response structure
        // Example:
        Map<AccountEnum, Integer> smeData = new HashMap<>();
        smeData.put(AccountEnum.SME, 10); // Example count, replace with actual logic

        Map<AccountEnum, Integer> governmentData = new HashMap<>();
        governmentData.put(AccountEnum.GOVERNMENT, 10); // Example count, replace with actual logic

        Map<AccountEnum, Integer> enterpriseData = new HashMap<>();
        enterpriseData.put(AccountEnum.ENTERPRISE, 10); // Example count, replace with actual logic

        Map<AccountEnum, Integer> grandTotalData = new HashMap<>();
        grandTotalData.put(AccountEnum.GRANDTOTAL, 30); // Example count, replace with actual logic

        monthlyData.put("January", smeData);
        monthlyData.put("January", governmentData);
        monthlyData.put("January", enterpriseData);
        monthlyData.put("January", grandTotalData);

        // Repeat the above structure for each month in the range

        return monthlyData;
    }

    private Map<String, Map<AccountEnum, Integer>> fetchDataForWeekly(LocalDate fromDate, LocalDate toDate) {
        Map<String, Map<AccountEnum, Integer>> weeklyData = new HashMap<>();

        // Example implementation, replace with actual logic
        // Fetch data from repository based on fromDate and toDate for weekly data
        // Example:
        Map<AccountEnum, Integer> week1Data = new HashMap<>();
        week1Data.put(AccountEnum.SME, 10); // Example count, replace with actual logic
        week1Data.put(AccountEnum.GOVERNMENT, 10); // Example count, replace with actual logic
        week1Data.put(AccountEnum.ENTERPRISE, 10); // Example count, replace with actual logic
        week1Data.put(AccountEnum.GRANDTOTAL, 30); // Example count, replace with actual logic

        weeklyData.put("Week1", week1Data);

        // Repeat the above structure for each week in the range

        return weeklyData;
    }*/
    public List<Map<String, Object>> getAccountsData(String monthly, String weekly) {
        List<Map<String, Object>> response = new ArrayList<>();

        if ("Y".equalsIgnoreCase(monthly)) {
            // Fetch data for current month - last 3 months
            response.addAll(fetchMonthlyData());
        } else {
            // Fetch weekly data for the specified month
            response.addAll(fetchWeeklyData(weekly));
        }

        return response;
    }

    private List<Map<String, Object>> fetchMonthlyData() {
        List<Map<String, Object>> monthlyData = new ArrayList<>();

        // Logic to fetch data for current month - last 3 months
        // Example: Replace with your actual logic to query and aggregate data
        Map<String, Object> januaryData = generateMonthlyData("January");
        Map<String, Object> februaryData = generateMonthlyData("February");

        monthlyData.add(januaryData);
        monthlyData.add(februaryData);

        return monthlyData;
    }

    private Map<String, Object> generateMonthlyData(String month) {
        Map<String, Object> monthlyMap = new LinkedHashMap<>();
        Map<String, Integer> smeData = new HashMap<>();
        Map<String, Integer> governmentData = new HashMap<>();
        Map<String, Integer> enterpriseData = new HashMap<>();
        Map<String, Integer> grandTotalData = new HashMap<>();

        // Example: Replace with actual logic to fetch and calculate data
        // For demonstration, assume some sample data
        smeData.put("accountName", 10);
        governmentData.put("accountName", 10);
        enterpriseData.put("accountName", 10);
        grandTotalData.put("accountName", 30);

        monthlyMap.put("sme", smeData);
        monthlyMap.put("government", governmentData);
        monthlyMap.put("enterprise", enterpriseData);
        monthlyMap.put("grandtotal", grandTotalData);

        return Collections.singletonMap(month, monthlyMap);
    }

    private List<Map<String, Object>> fetchWeeklyData(String month) {
        List<Map<String, Object>> weeklyData = new ArrayList<>();

        // Logic to fetch weekly data for the specified month
        // Example: Replace with your actual logic to query and aggregate weekly data
        Map<String, Object> week1Data = generateWeeklyData("Week1");
        Map<String, Object> week3Data = generateWeeklyData("Week3");
        Map<String, Object> week4Data = generateWeeklyData("Week4");

        weeklyData.add(week1Data);
        weeklyData.add(week3Data);
        weeklyData.add(week4Data);

        return weeklyData;
    }

    private Map<String, Object> generateWeeklyData(String week) {
        Map<String, Object> weeklyMap = new LinkedHashMap<>();
        Map<String, Integer> smeData = new HashMap<>();
        Map<String, Integer> governmentData = new HashMap<>();
        Map<String, Integer> enterpriseData = new HashMap<>();
        Map<String, Integer> grandTotalData = new HashMap<>();

        // Example: Replace with actual logic to fetch and calculate weekly data
        // For demonstration, assume some sample data
        smeData.put("accountName", 10);
        governmentData.put("accountName", 10);
        enterpriseData.put("accountName", 10);
        grandTotalData.put("accountName", 30);

        weeklyMap.put("sme", smeData);
        weeklyMap.put("government", governmentData);
        weeklyMap.put("enterprise", enterpriseData);
        weeklyMap.put("grandtotal", grandTotalData);

        return Collections.singletonMap(week, weeklyMap);
    }
}