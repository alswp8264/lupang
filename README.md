🕹️ 프로젝트 : 루팡샵
>원하는 아이템을 등록하고, 직접 판매하거나 구매해 수령까지 완료할 수 있는 아이템 거래 플랫폼입니다.
---
## 프로젝트 선정 이유

🥇 대부분의 게임 거래 플랫폼은 재화 할인 판매나 미개봉 가챠 아이템 위주로 구성되어 있습니다.  
🥈 필요한 아이템을 직접 사고팔 수 있는 공간은 의외로 매우 부족합니다.  
🥉 그래서 유저가 원하는 아이템을 직접 등록하고, 거래하고, 수령까지 할 수 있는 공간을 만들었습니다.

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


## 📋 요구사항정의서


![스크린샷 2025-06-25 083955](https://github.com/user-attachments/assets/1acd33dd-dabb-4776-9bd1-a79b05f1f7cd)



---

## 🎮 루팡샵은 왜 만들어졌는가?

게임 아이템을 개인 간에 안전하게 거래할 수 있는 구조가 필요했습니다. 

루팡샵은 콘솔 환경에서도 기본적인 중고 거래 흐름을 구현하기 위해 만들어졌으며,  

회원 관리, 아이템 등록, 장바구니, 결제, 수령 확인 등  

거래의 전 과정을 포함하도록 설계되었습니다.



## 📌  아이템 클래스 다이어그램

![갠플클래스](https://github.com/user-attachments/assets/eb05d015-8e28-456b-93e7-a65ca87537ce) 



## 📌 맴버 클래스 다이어그램
![맴버클래스다이어그램](https://github.com/user-attachments/assets/0afe3e54-9387-4356-859d-03c6329f8667)



## 📌 카트 클래스 다이어그램
![카트](https://github.com/user-attachments/assets/8d409e2a-97cb-4c0b-9760-203c8f993385) 유스케이스 넣고싶은데 난 초반에 넣는게 더좋을거같거든 너가 한번 보고 어디다 넣을지 한번 추천해봐
