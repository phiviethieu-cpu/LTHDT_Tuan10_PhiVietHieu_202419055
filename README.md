# Smart Home Mini – Bài thực hành 10

Sinh viên: Phí Việt Hiếu – MSSV: 202419055

Ứng dụng điều khiển nhà thông minh (SmartLight, SmartFan, SmartDoor) áp dụng ba mẫu thiết kế:
**Factory**, **Singleton**, **Facade**.

## Yêu cầu môi trường

- JDK 17 trở lên
- Maven 3.8 trở lên

## Cách chạy

```bash
mvn test                                  # chạy kiểm thử
mvn package                               # build file jar (có chạy kiểm thử)
java -jar target/smart-home-mini-1.0.0.jar  # chạy chương trình demo
```

## Cấu trúc mã nguồn

```text
src/main/java/smarthome/
  IDevice.java, ILockable.java          interface thiết bị
  SmartLight.java, SmartFan.java,
  SmartDoor.java                        thiết bị cụ thể
  DeviceFactory.java                    Factory
  HomeConfiguration.java                Singleton
  SmartHomeFacade.java                  Facade
  Main.java                             chương trình demo
src/test/java/smarthome/
  DeviceFactoryTest.java                T1, T2
  HomeConfigurationTest.java            T3
  SmartHomeFacadeTest.java              T4, T5
```

## Sơ đồ lớp UML

File nguồn: [SmartHomeMini_ClassDiagram.drawio](SmartHomeMini_ClassDiagram.drawio)
(mở bằng <https://app.diagrams.net> hoặc extension Draw.io Integration trong VS Code).

## Vị trí sử dụng pattern

| Pattern   | Vị trí | Lợi ích đạt được |
|-----------|--------|------------------|
| Factory   | `DeviceFactory.create(type, name)` | Client không gọi `new SmartLight(...)` trực tiếp, chỉ làm việc với `IDevice`. Việc chọn lớp cụ thể, chuẩn hóa chuỗi đầu vào và báo lỗi loại không hợp lệ nằm ở một chỗ. |
| Singleton | `HomeConfiguration.getInstance()` (Holder idiom) | Cả ứng dụng dùng chung một cấu hình (tên nhà, mức sáng ban đêm). Đối tượng chỉ tạo khi cần và an toàn đa luồng mà không cần `synchronized`. |
| Facade    | `SmartHomeFacade.goToWork()`, `goToSleep()` | Client kích hoạt một chế độ bằng một lời gọi, không cần nhớ thiết bị nào làm gì và theo thứ tự nào. Các thiết bị vẫn điều khiển riêng được khi cần. |

### Hạn chế của thiết kế

- `DeviceFactory` dùng `switch` nên mỗi lần thêm thiết bị (ví dụ `SmartCamera`) vẫn phải sửa factory, chưa đúng hoàn toàn nguyên tắc Open/Closed.
- `SmartHomeFacade` tự gọi `HomeConfiguration.getInstance()` bên trong, nên đây là phụ thuộc ẩn và khó thay cấu hình khác khi test. Trong test phải đặt lại cấu hình sau mỗi lần chạy. Có thể sửa bằng cách truyền cấu hình qua constructor của facade.
- Factory trả về `IDevice` nên khi tạo facade phải ép kiểu `(ILockable) door`. Nếu truyền nhầm loại thiết bị thì lỗi chỉ xuất hiện lúc chạy.

## Kết quả kiểm thử

| Mã | Tình huống | Kết quả mong đợi | Kết quả |
|----|------------|------------------|---------|
| T1 | Tạo light, fan, door | Factory trả đúng kiểu đối tượng | Đạt |
| T2 | Tạo loại `camera` khi chưa hỗ trợ | Ném `IllegalArgumentException` | Đạt |
| T3 | Lấy `HomeConfiguration.getInstance()` hai lần | Hai biến cùng tham chiếu một đối tượng | Đạt |
| T4 | Gọi `goToWork()` | Đèn tắt, quạt tắt, cửa khóa | Đạt |
| T5 | Gọi `goToSleep()` | Quạt tắt, cửa khóa, đèn bật, hiện mức sáng | Đạt |

Ngoài 5 tình huống trên còn có các test phụ: không phân biệt hoa thường, nhiều luồng cùng gọi `getInstance()`, constructor `private`, mức sáng ngoài 0–100, truyền `null` vào facade.

```text
Tests run: 4, Failures: 0, Errors: 0 -- in smarthome.DeviceFactoryTest
Tests run: 5, Failures: 0, Errors: 0 -- in smarthome.HomeConfigurationTest
Tests run: 5, Failures: 0, Errors: 0 -- in smarthome.SmartHomeFacadeTest
Tests run: 14, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Kết quả chạy chương trình demo (phần facade):

```text
=== Facade: che do Di lam ===
Living room light: OFF
Living room fan: OFF
Main door: LOCKED

=== Facade: che do Di ngu ===
Living room fan: OFF
Main door: LOCKED
Living room light: ON
Night light level: 30%
```

## Trả lời câu hỏi

**1. Factory đang che giấu quyết định thiết kế nào?**

Quyết định chọn lớp cụ thể nào để tạo ứng với mỗi loại thiết bị, cùng cách ánh xạ từ chuỗi `"light"`, `"fan"`, `"door"` sang `SmartLight`, `SmartFan`, `SmartDoor`. Client chỉ biết `IDevice`, nên sau này đổi lớp cài đặt (ví dụ thay `SmartLight` bằng một loại đèn khác) chỉ cần sửa trong factory.

**2. Singleton bảo đảm điều gì, nhưng tạo ra rủi ro nào?**

Bảo đảm cả ứng dụng chỉ có một đối tượng `HomeConfiguration` và mọi nơi đều truy cập được cùng đối tượng đó. Rủi ro: nó là trạng thái toàn cục, chỗ này sửa thì chỗ khác bị ảnh hưởng; các test dùng chung trạng thái nên có thể ảnh hưởng nhau; khó thay bằng đối tượng giả khi test; lớp nào gọi `getInstance()` bên trong thì có phụ thuộc ẩn.

**3. Facade khác Factory ở mục đích chính như thế nào?**

Factory là mẫu khởi tạo (creational), lo việc **tạo** đối tượng và che giấu lớp cụ thể. Facade là mẫu cấu trúc (structural), lo việc **sử dụng** một nhóm đối tượng đã có, gom nhiều thao tác thành một giao diện đơn giản. Factory trả lời câu hỏi "tạo cái gì", Facade trả lời câu hỏi "dùng thế nào cho gọn".

**4. Nếu bỏ `SmartHomeFacade`, client sẽ phải biết thêm những chi tiết nào?**

- Mỗi chế độ gồm những thiết bị nào và gọi thao tác gì theo thứ tự nào (đi làm: tắt đèn, tắt quạt, khóa cửa; đi ngủ: tắt quạt, khóa cửa, bật đèn).
- Cửa phải dùng qua `ILockable` mới khóa được, phải tự ép kiểu.
- Phải tự lấy `HomeConfiguration` để đọc mức sáng ban đêm và in thông báo.
- Mỗi nơi cần dùng chế độ sẽ lặp lại đoạn code này; khi đổi quy trình (ví dụ đi làm thì tắt thêm camera) phải sửa ở tất cả các nơi đó.
