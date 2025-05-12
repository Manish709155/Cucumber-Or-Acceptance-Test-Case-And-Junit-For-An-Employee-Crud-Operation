Feature: Employee CRUD Operations

  Scenario Outline: Create an employee
    Given I have employee details with name "<name>" and department "<department>"
    When I create the employee
    Then the employee should be created successfully with name "<name>" and department "<department>"

    Examples:
      | name        | department |
      | John Doe    | IT         |
      | Jane Smith  | HR         |
      | Alice Ray   | Finance    |
      | Bob Brown   | Sales      |


  Scenario Outline: Update employee name and department by ID
    Given an employee with ID <id> already exists
    When I update the employee name to "<newName>" and department to "<newDept>"
    Then the employee name should be "<newName>" and department should be "<newDept>"

    Examples:
      | id | newName     | newDept            |
      | 1  | Jane Smith  | Marketing          |
      | 2  | John Doe    | Development        |



  Scenario Outline: Get employee details by ID and verify
    Given an employee with ID <id> exists in the system
    When I retrieve the employee by ID
    Then the employee name should be "<expectedName>" and department should be match "<expectedDept>"

    Examples:
      | id | expectedName | expectedDept      |
      | 1  | Jane Smith  | Marketing          |
      | 2  | John Doe    | Development        |

  Scenario Outline: Delete an employee by ID
    Given an employee exist with this employee_id "<employee_id>"
    When I delete the employee by ID
    Then the employee should not exist anymore

    Examples:
      | employee_id |
      | 4           |


