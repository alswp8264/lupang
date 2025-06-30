🕹️ 프로젝트 : 루팡샵
>원하는 아이템을 등록하고, 직접 판매하거나 구매해 수령까지 완료할 수 있는 아이템 거래 플랫폼입니다.
---
## 화면 시나리오
![스크린샷 2025-06-29 113131](https://github.com/user-attachments/assets/69c4b821-cd79-4131-bab2-98a974042cbf)


---

## 🛠 개발 언어 및 환경
☕ Language: Java 17+

🧠 IDE: Eclipse / IntelliJ

🖥️ 실행 방식: CLI (Console) 기반

---

## 🔐 패키지 구조

```plaintext
src/
├── app/                          # 콘솔 앱 실행 및 UI 흐름 제어
│   ├── lupangSHOPConsoleApp.java    # 메인 실행 클래스
│   ├── ConsumerMenu.java            # 구매자 전용 메뉴
│   ├── SellerMenu.java              # 판매자 전용 메뉴
│   ├── MyPageMenu.java              # 마이페이지 메뉴
│   └── InputUtil.java               # 예외 처리 포함 입력 유틸
│
├── cart/                         # 장바구니 기능 구현
│   ├── CartItemVO.java              # 장바구니 항목 정보 객체
│   ├── CartDAO.java                 # 장바구니 DAO 인터페이스
│   ├── CartService.java             # 장바구니 서비스 인터페이스
│   ├── CartServiceImpl.java         # 장바구니 서비스 구현체
│   └── HashMapCartDAO.java          # 장바구니 저장소 구현 (HashMap 기반)
│
├── item/                         # 아이템 관리 및 거래 처리
│   ├── ItemVO.java                  # 아이템 정보 객체
│   ├── ItemDAO.java                 # 아이템 DAO 인터페이스
│   ├── ItemService.java             # 아이템 서비스 인터페이스
│   ├── MJitemService.java           # 아이템 서비스 구현
│   ├── HashMapItemDAO.java          # HashMap 기반 DAO
│   ├── ObjFileHashMapItemDAO.java   # 파일 저장 DAO 구현
│   └── FileItemDB.java              # 아이템 파일 입출력 처리
│
├── member/                       # 회원 기능 및 정보 처리
│   ├── Member.java                  # 회원 정보 클래스
│   ├── MemberDAO.java               # 회원 DAO 인터페이스
│   ├── MemberService.java           # 회원 서비스 인터페이스
│   ├── MJMemberService.java         # 회원 서비스 구현
│   ├── HashMapMemberDAO.java        # HashMap 기반 DAO
│   ├── ObjFileHashMapMemberDAO.java # 파일 저장 DAO 구현
│   ├── FileMemberDB.java            # 회원 파일 입출력 처리
│   └── MemberPrinter.java           # 회원 정보 출력 유틸
│
├── transaction/                 # 거래(주문) 관리 기능
│   ├── Transaction.java             # 거래 정보 클래스
│   └── TransactionService.java      # 거래 서비스 구현
│
└── payment/                     # 포인트 결제 기능
    └── PaymentService.java          # 포인트 결제 처리 클래스
```
## 📋 주요기능 상세 설명




---

## 📋 요구사항정의서


![스크린샷 2025-06-25 083955](https://github.com/user-attachments/assets/1acd33dd-dabb-4776-9bd1-a79b05f1f7cd)



---

## 🎮기능요구 명세서 (아이템).
![스크린샷 2025-06-29 162129](https://github.com/user-attachments/assets/fc98dcff-b1dd-4c86-9060-f9b89ae1baba)

---
## 🎮기능요구 명세서 (사용자).
![스크린샷 2025-06-29 162138](https://github.com/user-attachments/assets/603ce17d-6b1d-4e81-be17-fd52e12679e6)
![스크린샷 2025-06-29 162147](https://github.com/user-attachments/assets/c3230f57-f846-4902-bf1e-988b11d74415)

---
## 🎮기능요구 명세서 (장바구니).
![스크린샷 2025-06-29 175440](https://github.com/user-attachments/assets/8d6caa47-bf33-47c8-8c31-d5063811e054)

---
## 🎮기능요구 명세서 (구매자).
![스크린샷 2025-06-29 162159](https://github.com/user-attachments/assets/a5e2779e-d7b4-44e6-a947-07ce1eb26e74)
![스크린샷 2025-06-29 162156](https://github.com/user-attachments/assets/77295c77-06a7-4ce7-815b-c1eeee11e467)

---
## 🎮기능요구 명세서 (관리자).
![스크린샷 2025-06-29 162209](https://github.com/user-attachments/assets/233f487d-a805-4b3b-8ca5-4836a99f25c2)
![스크린샷 2025-06-29 162219](https://github.com/user-attachments/assets/5c86fc32-5baa-4594-af6b-1db040bd5a1a)

---
## 📌  아이템 클래스 다이어그램

![스크린샷 2025-06-21 162912](https://github.com/user-attachments/assets/0466c950-e072-4615-b5a5-ae519a450527)



## 📌 맴버 클래스 다이어그램
![스크린샷 2025-06-29 180714](https://github.com/user-attachments/assets/bb9f1f71-74d4-4241-a04e-e817a6a0e7c6)
![스크린샷 2025-06-29 180720](https://github.com/user-attachments/assets/f1a68c2c-0592-4753-9b95-22e6abac4c7e)
## 📌 카트 클래스 다이어그램
![스크린샷 2025-06-28 153244](https://github.com/user-attachments/assets/26d9c737-0175-4219-be07-f55fb69f8b75)

