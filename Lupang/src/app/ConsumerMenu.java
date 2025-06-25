package app;

import static app.InputUtil.safeReadInt;
import static app.InputUtil.safeReadString;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Cart.CartMenu;
import Cart.CartService;
import MyPageMenu.MyPageMenu;
import item.ItemDAO;
import item.ItemVO;
import member.Member;
import member.MemberService;
import payment.PaymentService;

/**
 * 구매자 기능 전용 컨트롤러 클래스
 * - 아이템 조회, 검색, 구매, 장바구니, 수령, 취소 등
 */
public class ConsumerMenu {
    
    private final Member loggedMember; // 로그인된 사용자
    private final ItemDAO itemDAO;
    private final CartService cartService;
    private final Scanner sc;
    private final MemberService ms;

    public ConsumerMenu(Member loggedMember, ItemDAO itemDAO, CartService cartService, Scanner sc, MemberService ms) {
        this.loggedMember = loggedMember;
        this.itemDAO = itemDAO;
        this.cartService = cartService;
        this.sc = sc;
        this.ms = ms;
    }

    // 메인 실행 루틴
    public void run() {
        String[] menu = {
            "로그아웃", "아이템 목록", "아이템 구매하기", "판매자 닉네임으로 검색", "장바구니", "아이템 검색",
            "판매하기", "수령 확인", "구매 취소", "내 정보 보기", "이전화면으로 돌아가기"
        };
        int sel;
        do {
            sel = selectMenu(menu); // 선택 입력
            switch (sel) {
                case 1 -> listItems();
                case 2 -> purchaseItem();
                case 3 -> searchBySellerNickname();
                case 4 -> new CartMenu(sc, cartService, itemDAO).run();
                case 5 -> searchItems();
                case 6 -> new SellerMenu(loggedMember, itemDAO, sc).run();
                case 7 -> confirmReceipt();
                case 8 -> cancelOrder();
                case 9 -> {
                    if (runMyPageMenu()) return;  // ✅ 탈퇴 시 run() 종료
                }
                case 10 -> { return; } // 이전 화면으로
            }
        } while (sel != 0);
    }

    // 메뉴 출력 및 사용자 선택
    private int selectMenu(String[] menu) {
        System.out.println("\n==== 구매자 메뉴 ====");
        for (int i = 0; i < menu.length; i++) {
            System.out.printf("%2d. %s%n", i, menu[i]);
        }
        return safeReadInt(">> 선택: ");
    }

    private void listItems() {
        String game = safeReadString("게임명 (예: 메이플스토리): ").trim();  // 입력값 공백 제거
        boolean found = false;

        System.out.printf("%-4s| %-20s| %-10s| %-10s| %-12s| %-10s| %-10s| %-8s%n",
                "ID", "아이템명", "게임", "서버", "가격", "판매자", "상태", "비고");

        for (ItemVO i : itemDAO.selectAll()) {
            // null 방지 및 공백 정리
            String itemGame = i.getGame() != null ? i.getGame().trim() : "";
            String itemStatus = i.getStatus() != null ? i.getStatus().trim() : "";

            // 정확한 게임명 + "판매 중" 또는 "예약 중" 상태만 필터링
            if (itemGame.equals(game) &&
                    (itemStatus.equals("판매 중") || itemStatus.equals("예약 중"))) {

                String sellerName = (i.getSeller() != null) ? i.getSeller().getNickname() : "미상";
                String status = itemStatus;
                String server = (i.getServer() != null) ? i.getServer() : "-";
                String remark = (loggedMember != null && i.getSeller() != null && i.getSeller().equals(loggedMember)) ? "내 아이템" : "";

                System.out.printf("%-4d| %-20s| %-10s| %-10s| %,10d원| %-10s| %-10s| %-8s%n",
                        i.getItemId(), i.getName(), itemGame, server, i.getPrice(), sellerName, status, remark);
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ 해당 게임의 '판매 중' 또는 '예약 중' 상태 아이템이 없습니다.");
        }
    }



    // 아이템 구매
    private void purchaseItem() {
        int id = safeReadInt("구매할 ID: ");
        ItemVO i = itemDAO.selectById(id);
        if (i == null || i.getSeller() != null && i.getSeller().equals(loggedMember)) {
            System.out.println(i == null ? "존재하지 않는 아이템입니다." : "내 아이템은 구매 불가");
            return;
        }
        if (!List.of("판매 중", "예약 중").contains(i.getStatus())) {
            System.out.printf("현재 상태 [%s]로 구매 불가%n", i.getStatus());
            return;
        }
        if (new PaymentService().processPayment(loggedMember, i)) {
            i.setStatus("예약 중");
            i.setBuyer(loggedMember);
            itemDAO.update(i);
            System.out.printf("[%s] 결제 완료. 판매자가 준비 중입니다.%n", i.getName());
        } else System.out.println("포인트 부족");
    }

    // 판매자 검색
    private void searchBySellerNickname() {
        String name = safeReadString("판매자 닉네임: ").toLowerCase();
        boolean found = false;
        for (ItemVO i : itemDAO.selectAll()) {
            if ("판매 중".equals(i.getStatus()) && i.getSeller() != null && i.getSeller().getNickname().toLowerCase().contains(name)) {
                System.out.printf("[%04d] %s (%s) | 게임: %s | %,d원 | 판매자: %s | 상태: %s%n",
                        i.getItemId(), i.getName(), i.getServer() != null ? i.getServer() : "-",
                        i.getGame(), i.getPrice(), i.getSeller().getNickname(), i.getStatus());
                found = true;
            }
        }
        if (!found) System.out.println("해당 판매자의 등록 아이템이 없습니다.");
    }

    // 키워드 검색
    private void searchItems() {
        String kw = safeReadString("게임 키워드: ").trim().toLowerCase();
        boolean found = false;
        for (ItemVO i : itemDAO.selectAll()) {
            if (i.getStatus() != null
                    && List.of("판매 중", "예약 중").contains(i.getStatus())
                    && i.getGame() != null
                    && i.getGame().toLowerCase().contains(kw)) {
                System.out.printf("[%d] %s (%s) | %s | %,d원 | 판매자: %s | 상태: %s%n",
                        i.getItemId(), i.getName(), i.getServer() != null ? i.getServer() : "-",
                        i.getGame(), i.getPrice(),
                        i.getSeller() != null ? i.getSeller().getNickname() : "미상", i.getStatus());
                found = true;
            }
        }
        if (!found) System.out.println("검색 결과 없음");
    }

    // 수령 확인 → 거래 완료
    private void confirmReceipt() {
        int id = safeReadInt("수령 확인할 ID: ");
        ItemVO i = itemDAO.selectById(id);
        if (i != null && "예약 중".equals(i.getStatus()) && loggedMember.equals(i.getBuyer())) {
            i.setStatus("거래 완료");
            itemDAO.update(i);
            System.out.println("거래 완료 처리됨");
        } else System.out.println("수령 확인 불가");
    }

    // 예약 취소 및 포인트 환불
    private void cancelOrder() {
        int id = safeReadInt("취소할 ID: ");
        ItemVO i = itemDAO.selectById(id);
        if (i == null || !loggedMember.equals(i.getBuyer()) || !"예약 중".equals(i.getStatus())) {
            System.out.println("취소 조건 불충분");
            return;
        }
        i.setStatus("판매 중");
        i.setBuyer(null);
        itemDAO.update(i);
        loggedMember.addPoint(i.getPrice());
        System.out.println("주문 취소됨, 포인트 환불됨");
    }

    // ✅ 내 정보 보기 메뉴 실행 + 탈퇴 여부 확인 처리
    private boolean runMyPageMenu() {
        MyPageMenu menu = new MyPageMenu(ms, sc, loggedMember);
        menu.run();
        if (menu.isLoggedOut()) {
            System.out.println("⛔️ 회원 탈퇴 후 자동 로그아웃되었습니다.");
            return true; // 탈퇴함 → run() 종료 신호
        }
        return false; // 계속 진행 가능
    }

}
