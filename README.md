  # ĐỒ ÁN QUẢN LÝ THƯ VIỆN CONSOLE
  
## I. Giới thiệu
  Trong xã hội hiện đại, để đáp ứng nhu cầu phát triển các hoạt động giáo dục,khoa học, văn hóa, kinh tế, v.v…, việc đọc sách báo (và các tài liệu khác) với mục đích khai thác, sử dụng thông tin, tri thức, càng ngày càng trở nên cấp thiết. Cùng với đó, số lượt mượn sách hàng ngày trong thư viện đạt đến con số hàng nghìn lượt.Việc quản lý sách được thực hiện thủ công gặp nhiều khó khăn. Vì vậy việc xây dựng chương tình quản lý thông tin liên quan đến việc mượn, trả, tìm kiếm thông tin về sách là một việc cần thiết. Từ đó giúp cho việc quản lý thư viện trở nên thuận tiện và ưu việt hơn.
  
  Đồ án này nhằm mục đích thực hành và hiểu rõ về lập trình Java cơ bản. Được viết ra để giải quyết những vấn đề xung quanh việc quản lý thư viện ở một nhà sách, hoặc một trường học.

## II. Setup
   **Bước 1:**  Cài đặt Itellij IDEA và JDK 17 - https://www.jetbrains.com/idea/download/?section=windows
     
   **Bước 2:** Tải xuống hoặc sao chép code từ GitHub sang máy.
     
   **Bước 3:** Chạy chương trình từ hàm main của class "LibraryManagementSystem".
     
   **Bước 4:** Admin ID: admin & Password: password

## III. Biểu đồ UML

### 1. Biểu đồ User Case

   ![image](https://github.com/trducloc/LibraryManagementSystem/blob/master/ClassDiagram/UserCase.drawio.png)

### 2. Biểu đồ Sequence Diagram
 
   
   ![image](https://github.com/trducloc/LybraryManagementSystem/blob/master/UmlSequenceDiagram.drawio.png)
   
- Trong biểu đồ này:
  - Actor "User" tương tác với hệ thống và gọi các phương thức từ lớp Menu để quản lý thư viện.
  - Các lớp Menu, BookManagement, BorrowerManagement, và LoanSlipManagement đại diện cho các chức năng chính của hệ thống và tương tác với các lớp logic và dữ liệu tương ứng.
  - Lớp BookLogic, BorrowerLogic, và LoanSlipLogic thực hiện các nhiệm vụ logic và tương tác với dữ liệu thông qua các lớp dữ liệu tương ứng (BookDat, BorrowerDat, và LoanSlipDat).

   ### 3. Biểu đồ Class Diagram
   **a) Biểu đồ tổng quát**
   
  ![image](https://github.com/trducloc/LibraryManagementSystem/blob/master/ClassDiagram/ClassDiagramOverview.drawio.png)

  Trong biểu đồ này:
- Package data: Đây là gói chứa các lớp liên quan đến dữ liệu và lưu trữ dữ liệu.
  + BookDat: Đại diện cho lớp quản lý dữ liệu về sách.
  + BorrowerDat: Đại diện cho lớp quản lý dữ liệu về người mượn sách.
  + LoanSlipDat: Đại diện cho lớp quản lý dữ liệu về phiếu mượn sách.
- Package entity: Chứa các lớp đại diện cho các thực thể trong hệ thống.
  + Account: Đại diện cho lớp quản lý tài khoản người dùng.
  + Book: Đại diện cho lớp sách.
  + Borrower: Đại diện cho lớp người mượn sách.
  + LoanSlip: Đại diện cho lớp phiếu mượn sách.
- Package list: Chứa các lớp liên quan đến danh sách và quản lý danh sách.
  + ListBook và NodeBook: Đại diện cho lớp danh sách sách và nút trong danh sách sách.
  + ListBorrower và NodeBorrower: Đại diện cho lớp danh sách người mượn và nút trong danh sách người mượn.
  + ListLoanSlip và NodeLoanSlip: Đại diện cho lớp danh sách phiếu mượn và nút trong danh sách phiếu mượn.
- Package service: Chứa các lớp liên quan đến việc quản lý dịch vụ của hệ thống.
  + BookManagement: Đại diện cho quản lý sách.
  + BorrowerManagement: Đại diện cho quản lý người mượn sách.
  + LoanSlipManagement: Đại diện cho quản lý phiếu mượn sách.
  + Menu: Đại diện cho quản lý menu hệ thống.
- Package logic: Chứa các lớp chứa logic xử lý dữ liệu và quản lý.
  + BookLogic: Đại diện cho logic liên quan đến sách.
  + BorrowerLogic: Đại diện cho logic liên quan đến người mượn sách.
  + LoanSlipLogic: Đại diện cho logic liên quan đến phiếu mượn sách.
- Class LibraryManagementSystem: Đây là lớp gốc của hệ thống, chứa phương thức main để khởi chạy ứng dụng.



   **b) Biểu đồ chi tiết**
     
   ![image](https://github.com/trducloc/LibraryManagementSystem/blob/master/ClassDiagram/ClassDiagramDetail.drawio.png)
   
   Trong biểu đồ này: 
   
- Menu: Lớp này quản lý các chức năng của hệ thống và gửi yêu cầu từ người dùng đến các lớp xử lý tương ứng (ví dụ: BookManagement, BorrowerManagement, LoanSlipManagement).

- BookManagement: Quản lý các chức năng liên quan đến sách trong hệ thống thư viện, bao gồm thêm, sửa, xóa, tìm kiếm, sắp xếp sách.

- BorrowerManagement: Quản lý các chức năng liên quan đến người mượn sách trong hệ thống, bao gồm thêm, sửa, xóa, tìm kiếm, sắp xếp và cho mượn sách.

- LoanSlipManagement: Quản lý các chức năng liên quan đến phiếu mượn sách, bao gồm thêm, sửa, xóa, tìm kiếm, sắp xếp.

- BookLogic, BorrowerLogic, LoanSlipLogic: Các lớp xử lý logic liên quan đến sách, người mượn và phiếu mượn sách. Chúng thực hiện các thao tác cơ sở dữ liệu và logic kinh doanh như thêm, sửa, xóa, tìm kiếm     và kiểm tra.

- BookDat, BorrowerDat, LoanSlipDat: Các lớp tương tác với dữ liệu và lưu trữ thông tin về sách, người mượn và phiếu mượn sách trong tệp hoặc cơ sở dữ liệu.

- Account: Đại diện cho tài khoản và xử lý đăng ký và đăng nhập của người dùng hệ thống. Nó cũng quản lý danh sách tài khoản.

- Book, Borrower, LoanSlip: Các lớp này đại diện cho sách, người mượn và phiếu mượn sách. Chúng chứa thông tin chi tiết và các phương thức để thao tác với dữ liệu.

- ListBook, ListBorrower, ListLoanSlip: Các lớp này đại diện cho danh sách các đối tượng tương ứng (sách, người mượn, phiếu mượn sách). Chúng chứa thông tin về danh sách và cung cấp các phương thức để 
     thêm, sửa đổi và tìm kiếm trong danh sách.

- NodeBook, NodeBorrower, NodeLoanSlip: Các lớp này đại diện cho các nút trong danh sách liên kết của sách, người mượn và phiếu mượn sách. Chúng liên kết các đối tượng với nhau và quản lý các phần tử trong 
    danh sách.

- Displayable: Đây là một interface chứa phương thức "display()" để hiển thị thông tin của đối tượng, được triển khai bởi các lớp Book, Borrower và LoanSlip.

- Các mũi tên trên biểu đồ thể hiện các quan hệ giữa các lớp và chức năng. Ví dụ: một User tương tác với Menu, Menu quản lý các chức năng của hệ thống, Menu gửi yêu cầu tới các lớp quản lý sách 
    (BookManagement), người mượn (BorrowerManagement) và phiếu mượn sách (LoanSlipManagement) để thực hiện các chức năng cụ thể. Các lớp xử lý logic (BookLogic, BorrowerLogic, LoanSlipLogic) gửi yêu cầu tới 
    các lớp dữ liệu (BookDat, BorrowerDat, LoanSlipDat) để thực hiện thao tác trên dữ liệu.

 ### 2. Biểu đồ Sequence Diagram
 
   
   ![image](https://github.com/trducloc/LybraryManagementSystem/blob/master/UmlSequenceDiagram.drawio.png)
- Trong biểu đồ này:
  - Actor "User" tương tác với hệ thống và gọi các phương thức từ lớp Menu để quản lý thư viện.
  - Các lớp Menu, BookManagement, BorrowerManagement, và LoanSlipManagement đại diện cho các chức năng chính của hệ thống và tương tác với các lớp logic và dữ liệu tương ứng.
  - Lớp BookLogic, BorrowerLogic, và LoanSlipLogic thực hiện các nhiệm vụ logic và tương tác với dữ liệu thông qua các lớp dữ liệu tương ứng (BookDat, BorrowerDat, và LoanSlipDat).

## IV. Các chức năng cơ bản
   - Login
   - Quản lý sách.
   - Quản lý phiếu mượn.
   - Quản lý người mượn.
   - Tìm kiếm.
   - Thống kê.

## V. Chi tiết hoạt động của các chức năng
    
   ### 1. Đăng ký
   Đăng ký tài khoản mới để sử dụng các chức năng của thành viên trong thư viện.
   
   ### 2. Đăng nhập
   Mỗi vai trò trong thư viện sẽ được sử dụng những chức năng khác nhau:
  - Với admin:
      - Quản lý sách: Có quyền thêm, sửa, xóa sách; Xem, sắp xếp danh sách sách.
      - Quản lý phiếu mượn: Có quyền thêm, sửa, xóa phiếu mượn; Xem, sắp xếp danh sách phiếu mượn.
      - Quản lý người mượn: Có quyền thêm sửa, xóa người; Xem, sắp xếp danh sách người mượn.
      - Tìm kiếm: Có thể tìm kiếm thông tin sách, phiếu mượn và người mượn.
      - Thống kê: Có thể xem thống kê số sách còn trong thư viện, số phiếu mượn và số người mượn.
   
  - Với thành viên:
      - Xem danh sách sách: Có thể xem danh sách những đầu sách khả dụng trong thư viện.
      - Tìm kiếm sách: Có thể tìm kiếm thông tin sách.
      - Mượn sách: Sau khi xem danh sách sách, có thể mượn sách dựa trên số sách khả dụng trong thư viện.
      - Trả sách: Sau khi mượn sách, cần trả trong vòng 7 ngày. Khi cả sẽ cập nhật lại danh sách sách.
   
   ### 3. Đăng xuất
   Sau khi đăng xuất sẽ thoát ra khỏi chương trình.

## VI. Kết quả

- Đồ án Quản lý thư viện đã được triển khai thành công và có thể sử dụng để quản lý thông tin về sách và độc giả trong thư viện.

- Sau khi hoàn thành môn JAVA CORE ở TECHMASTER, mình đã:
  + Nắm được kiến thức cơ bản về Java: hiểu về cú pháp, cấu trúc, và các đặc điểm cơ bản của ngôn ngữ lập trình Java.
  + Có khả năng viết các chương trình đơn giản bằng Java để thực hiện các nhiệm vụ cơ bản.
  + Hiểu biết về OOP (Lập trình hướng đối tượng): hiểu cách áp dụng các nguyên tắc OOP trong việc thiết kế và phát triển ứng dụng.
  + Xử lý lỗi: học được cách xử lý lỗi và ngoại lệ trong Java để làm cho ứng dụng ổn định hơn.
  + Khả năng tìm hiểu thêm: có nền tảng để tìm hiểu về các lĩnh vực khác của Java như phát triển web, ứng dụng di động, hoặc phát triển ứng dụng máy tính cá nhân.
  + Sẵn sàng kiến thức để áp dụng cho môn học tiếp theo.

## VII. Nhận xét
   - Dự án đã đạt được các chức năng cơ bản và hoạt động một cách ổn định.
   - Giao diện console được in gọn gàng, dễ nhìn để tăng trải nghiệm người dùng.
   - Cần xem xét thêm một số tính năng. như trả sách, quản lý tồn kho sách và tính phí phạt khi trả sách.

## VIII. Công việc trong tương lai
   
   Dưới đây là danh sách công việc cần thực hiện để phát triển đồ án trong tương lai:
   - Cải thiện giao diện người dùng.
   - Cải thiện độ bảo mật khi đăng ký, đăng nhập
   - Quản lý tồn kho sách một cách chi tiết hơn.
   - Tính phí phạt khi trả sách dựa vào ngày trả muộn và tình trạng sách khi trả.

   





   








   
