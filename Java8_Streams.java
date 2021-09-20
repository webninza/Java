//1. To group a student list based on their branch
public Map<String, List<Student>> groupByBranchName(List<Student> studentList) {
  return studentList.stream()
     .collect(Collectors.groupingBy(Student::getBranch));
}

//2. To find average salary of Employees
public double calculateAverage(List<Employee> employeeList) {
  return employeeList.stream()
          .mapToInt(employee -> employee.getSalary())
          .average()
          .getAsDouble();
}

//3. Obtain a list of products belonging to category “Books” with price > 100
List<Product> result = productRepo.findAll()
  .stream()
  .filter(p -> p.getCategory().equalsIgnoreCase("Books"))
  .filter(p -> p.getPrice() > 100)
  .collect(Collectors.toList());

//4. Obtain a list of order with products belong to category “Baby”
List<Order> result = orderRepo.findAll()
        .stream()
        .filter(o -> 
          o.getProducts()
          .stream()
          .anyMatch(p -> p.getCategory().equalsIgnoreCase("Baby"))
        )
        .collect(Collectors.toList());  

//5. Obtain a list of product with category “Toys” and then apply 10% discount
List<Product> result = productRepo.findAll()
        .stream()
        .filter(p -> p.getCategory().equalsIgnoreCase("Toys"))
        .map(p -> p.withPrice(p.getPrice() * 0.9))
        .collect(Collectors.toList());  

//6. Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021
List<Product> result = orderRepo.findAll()
  .stream()
  .filter(o -> o.getCustomer().getTier() == 2)
  .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
  .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 4, 1)) <= 0)
  .flatMap(o -> o.getProducts().stream())
  .distinct()
  .collect(Collectors.toList());

//7. Get the cheapest products of “Books” category
Optional<Product> result = productRepo.findAll()
        .stream()
        .filter(p -> p.getCategory().equalsIgnoreCase("Books"))
        .sorted(Comparator.comparing(Product::getPrice))
        .findFirst();
//OR
Optional<Product> result = productRepo.findAll()
        .stream()
        .filter(p -> p.getCategory().equalsIgnoreCase("Books"))
        .min(Comparator.comparing(Product::getPrice));

//8. Get the 3 most recent placed order
List<Order> result = orderRepo.findAll()
        .stream()
        .sorted(Comparator.comparing(Order::getOrderDate).reversed())
        .limit(3)
        .collect(Collectors.toList());

//9. Get a list of orders which were ordered on 15-Mar-2021, log the order records to the console and then return its product list
List<Product> result = orderRepo.findAll()
    .stream()
    .filter(o -> o.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
    .peek(o -> System.out.println(o.toString()))
    .flatMap(o -> o.getProducts().stream())
    .distinct()
    .collect(Collectors.toList());

//10. Calculate total lump sum of all orders placed in Feb 2021
Double result = orderRepo.findAll()
    .stream()
    .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
    .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 3, 1)) < 0)
    .flatMap(o -> o.getProducts().stream())
    .mapToDouble(p -> p.getPrice())
    .sum();	

//11. Calculate order average payment placed on 14-Mar-2021
Double result = orderRepo.findAll()
        .stream()
        .filter(o -> o.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
        .flatMap(o -> o.getProducts().stream())
        .mapToDouble(p -> p.getPrice())
        .average().getAsDouble();

//12. Obtain a data map with order id and order’s product count
Map<Long, Integer>  result = orderRepo.findAll()
        .stream()
        .collect(
            Collectors.toMap(
                order -> order.getId(),
                order -> order.getProducts().size()
                )
            );

//13. Produce a data map with order records grouped by customer
Map<Customer, List<Order>> result = orderRepo.findAll()
        .stream()
        .collect(
            Collectors.groupingBy(Order::getCustomer)
            );

//14. Produce a data map with order record and product total sum
Map<Order, Double> result = orderRepo.findAll()
        .stream()
        .collect(
          Collectors.toMap(
              Function.identity(), 
              order -> order.getProducts().stream()
                    .mapToDouble(p -> p.getPrice()).sum()
            ) 
          );

//15. Obtain a data map with list of product name by category
Map<String, List<String>> result = productRepo.findAll()
        .stream()
        .collect(
            Collectors.groupingBy(
                Product::getCategory,
                Collectors.mapping(product -> product.getName(), Collectors.toList()))
            );

//16. Get the most expensive product by category
Map<String, Optional<Product>> result = productRepo.findAll()
        .stream()
        .collect(
            Collectors.groupingBy(
                Product::getCategory,
                Collectors.maxBy(Comparator.comparing(Product::getPrice)))
  
//17. sum, average, max, min, count using summaryStatistics()
DoubleSummaryStatistics statistics = productRepo.findAll()
    .stream()
    .filter(p -> p.getCategory().equalsIgnoreCase("Books"))
    .mapToDouble(p -> p.getPrice())
    .summaryStatistics();
System.out.println(String.format("count = %1$d, average = %2$f, max = %3$f, min = %4$f, sum = %5$f", 
    statistics.getCount(), statistics.getAverage(), statistics.getMax(), statistics.getMin(), statistics.getSum())));
  
  
