# J1.L.P0021
### **Cấu trúc**
- **Main.java**: khởi tạo hệ thống và xử lý exception
- **Student.java**: Entity class, Encapsulation với private fields + public getters/setters
- **StudentManager.java**: Business logic layer, quản lý CRUD operations với ArrayList
- **StudentManagementSystem.java**: Presentation layer, xử lý user interface và input validation

**thao tác**:
```
1. Tạo sinh viên: 
   Main.main() → StudentManagementSystem.run()
   → StudentManagementSystem.createStudent() 
   → getStudentId() + getStudentName() + getSemester() + getCourseName()
   → new Student(id, name, semester, course) [Student Constructor]
   → StudentManager.addStudent() 
   → ArrayList.add()

2. Tìm kiếm: 
   Main.main() → StudentManagementSystem.run()
   → StudentManagementSystem.findAndSortStudents() 
   → StudentManager.findStudentsByName() 
   → Student.getStudentName() [trong Stream.filter()]
   → Stream.filter() + Collections.sort()
   → Student.toString() [hiển thị kết quả]

3. Cập nhật: 
   Main.main() → StudentManagementSystem.run()
   → StudentManagementSystem.updateOrDeleteStudent() 
   → updateStudent() 
   → Student.setStudentName() + Student.setSemester() + Student.setCourseName()
   (Cập nhật trực tiếp vào Student object, không qua StudentManager)

4. Xóa: 
   Main.main() → StudentManagementSystem.run()
   → StudentManagementSystem.updateOrDeleteStudent() 
   → StudentManager.findStudentById() → Student.getId()
   → deleteStudent() 
   → StudentManager.deleteStudent() 
   → ArrayList.remove()

5. Báo cáo: 
   Main.main() → StudentManagementSystem.run()
   → StudentManagementSystem.generateReport() 
   → StudentManager.generateReport() 
   → Student.getStudentName() + Student.getCourseName()
   → HashMap.put() + getOrDefault()
```

**Flow**:
```
Main.java → StudentManagementSystem.java → StudentManager.java → Student.java
     ↓              ↓                        ↓                    ↓
  main()    createStudent()           addStudent()         Constructor
            findAndSortStudents()     findStudentsByName()  getters/setters
            updateOrDeleteStudent()   deleteStudent()       toString()
            generateReport()          generateReport()      equals()
```

### **OOP**
- **Encapsulation**: Student class ẩn dữ liệu, chỉ expose qua methods
- **Abstraction**: StudentManager ẩn chi tiết implementation của ArrayList
- **Polymorphism**: Override toString(), equals(), hashCode() trong Student
- **Single Responsibility**: Mỗi class có 1 nhiệm vụ riêng biệt

### 1. ArrayList
```java
private List<Student> students = new ArrayList<>();
students.add(student);
students.remove(student);
students.get(index);
students.contains(student);
students.isEmpty();
students.clear();
students.size();
new ArrayList<>(students); // Copy constructor
```
->Lưu trữ danh sách động
**Ví dụ**:
```
students.add(new Student("001", "An", 1, "Java"));
students.add(new Student("002", "Binh", 2, "Java"));
System.out.println(students.size()); // Output: 2

// Kiểm tra có rỗng không
System.out.println(students.isEmpty()); // Output: false

// Lấy phần tử theo index
Student first = students.get(0);
System.out.println(first.getStudentName()); // Output: An

// Kiểm tra có chứa phần tử không
Student newStudent = new Student("003", "Cuong", 3, "Python");
System.out.println(students.contains(newStudent)); // Output: false

students.remove(0);
System.out.println(students.size()); // Output: 1

// Xóa tất cả
students.clear();
System.out.println(students.isEmpty()); // Output: true

// Lấy kích thước danh sách
System.out.println(students.size()); // Output: 0

// Tạo bản sao danh sách
List<Student> copyStudents = new ArrayList<>(students);
System.out.println(copyStudents.size()); // Output: 0
```

### 2. Stream API
```java
return students.stream()
    .filter(student -> student.getName().contains(name))
    .collect(Collectors.toList());
```
**Ví dụ**:
```
List<Student> students = Arrays.asList(
    new Student("001", "An", 1, "Java"),
    new Student("002", "Binh", 1, "Java")
);
List<Student> result = students.stream()
    .filter(s -> s.getName().contains("An"))
    .collect(Collectors.toList());
System.out.println(result.size()); // Output: 1
```

### 3. Exception Handling
```java
try {
    int choice = Integer.parseInt(input);
} catch (NumberFormatException e) {
    System.out.println("Vui lòng nhập số hợp lệ!");
}
```
-> Bắt và xử lý lỗi khi parse string thành int
**Ví dụ**:
```
String input1 = "5";
int choice1 = Integer.parseInt(input1);
System.out.println(choice1); // Output: 5

String input2 = "abc";
try {
    int choice2 = Integer.parseInt(input2);
} catch (NumberFormatException e) {
    System.out.println("Vui lòng nhập số hợp lệ!"); // Output: Vui lòng nhập số hợp lệ!
}
```

### 4. @Override Annotation
```java
@Override
public String toString() { return "Student: " + name; }
```
->Đánh dấu method ghi đè từ class cha. Override methods từ Object class
**Ví dụ**:
```
Student student = new Student("001", "An", 1, "Java");
System.out.println(student); // Output: Student: An
```

### 5. String Methods
```java
String input = scanner.nextLine().trim();
if (input.toLowerCase().contains("java")) { }
```
->trim() bỏ khoảng trắng, toLowerCase() chuyển thành chữ thường
**Ví dụ**:
```
String input = "  JAVA Programming  ";
String trimmed = input.trim();
System.out.println(trimmed); // Output: JAVA Programming

String lower = trimmed.toLowerCase();
System.out.println(lower); // Output: java programming

boolean contains = lower.contains("java");
System.out.println(contains); // Output: true
```

### 6. HashMap
```java
Map<String, Map<String, Integer>> report = new HashMap<>();
report.put(studentName, new HashMap<>());
```
->Lưu trữ dữ liệu dạng key-value, truy cập nhanh
**Ví dụ**:
```
Map<String, Integer> studentCount = new HashMap<>();
studentCount.put("Java", 5);
studentCount.put(".Net", 3);

System.out.println(studentCount.get("Java")); // Output: 5
System.out.println(studentCount.containsKey("Python")); // Output: false
```

### 7. Collections.sort()
```java
Collections.sort(sortedStudents, new Comparator<Student>() {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getStudentName().compareToIgnoreCase(s2.getStudentName());
    }
});
```
**Ví dụ**:
```
List<String> names = Arrays.asList("Binh", "An", "Cuong");
Collections.sort(names);
System.out.println(names); // Output: [An, Binh, Cuong]
```
---------------------------------
### 1. Static Final Constants
```java
private static final String[] ALLOWED_COURSES = {"Java", ".Net", "C/C++"};
```
**Ví dụ**:
```
String[] courses = StudentManager.getAllowedCourses();
for (String course : courses) {
    System.out.println(course);
}
// Output: Java
//         .Net
//         C/C++
```

### 2. Array.clone()
```java
return ALLOWED_COURSES.clone();
```
->bản sao shallow của array->bảo vệ dữ liệu gốc khỏi thay đổi
**Ví dụ**:
```
String[] original = {"Java", ".Net", "C/C++"};
String[] copy = original.clone();
copy[0] = "Python";

System.out.println(original[0]); // Output: Java
System.out.println(copy[0]);     // Output: Python
```

### 3. Map.getOrDefault()
```java
studentCourses.put(courseName, studentCourses.getOrDefault(courseName, 0) + 1);
```
->Lấy giá trị từ map, nếu không có thì dùng giá trị mặc định
->Đếm số lần xuất hiện, tránh NullPointerException
**Ví dụ**:
```
Map<String, Integer> studentCourses = new HashMap<>();
String courseName = "Java";

// Lần 1: key chưa tồn tại
studentCourses.put(courseName, studentCourses.getOrDefault(courseName, 0) + 1);
System.out.println(studentCourses.get("Java")); // Output: 1

// Lần 2: key đã tồn tại
studentCourses.put(courseName, studentCourses.getOrDefault(courseName, 0) + 1);
System.out.println(studentCourses.get("Java")); // Output: 2
```

### 4. String.repeat()
```java
System.out.println("=".repeat(50));
```
->Lặp lại chuỗi n lần
**Ví dụ**:
```
String line = "=".repeat(5);
System.out.println(line); // Output: =====

String stars = "*".repeat(3);
System.out.println(stars); // Output: ***
```

### 6. Nested Map Structure
```java
Map<String, Map<String, Integer>> report = new HashMap<>();
```
->Map lồng nhau để lưu trữ dữ liệu phân cấp
**Ví dụ**:
```
Map<String, Map<String, Integer>> report = new HashMap<>();
report.put("An", new HashMap<>());
report.get("An").put("Java", 2);
report.get("An").put("Python", 1);

System.out.println(report.get("An").get("Java")); // Output: 2
System.out.println(report.get("An").get("Python")); // Output: 1
```

